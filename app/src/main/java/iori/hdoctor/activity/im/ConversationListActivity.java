package iori.hdoctor.activity.im;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import iori.hdoctor.R;
import iori.hdoctor.activity.PatientMainActivity;

/**
 * Created by Administrator on 2015/7/16.
 */
public class ConversationListActivity extends FragmentActivity {

    private String Token = "I9NjvTRCmx9+Brqh+D2ojgoiHXyvhjY180IPwm5VcEO9tluflJrL2C8CEEWkikc6ErEj/4uWOC1oe/vTttnYMA==";

    @InjectView(R.id.mid_title)
    TextView midTitle;
    @OnClick(R.id.left_icon)
    public void back(View v) {
        finish();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //唯一有用的代码，加载一个 layout
        setContentView(R.layout.conversation_list);
        ButterKnife.inject(this);

        midTitle.setText("消息");

        /* 创建 conversationlist 的Fragment */
        ConversationListFragment fragment =
                (ConversationListFragment)getSupportFragmentManager().findFragmentById(R.id.conversationlist);

        /* 给 IMKit 传递默认的参数，用于显示*/
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话采用聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false") //设置群组会话采用聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false") // 设置讨论组不采用聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true") //设置系统会话采用聚合显示
                .build();

        fragment.setUri(uri);

        if (RongIM.getInstance() == null)
            connectRong();

    }

    public void connectRong(){
        RongIM.connect(Token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                //Connect Token 失效的状态处理，需要重新获取 Token
                Toast.makeText(ConversationListActivity.this, "onTokenIncorrect", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(String userId) {
                Toast.makeText(ConversationListActivity.this, "onSuccess userId : " + userId, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Toast.makeText(ConversationListActivity.this, "onError errorCode : " + errorCode, Toast.LENGTH_LONG).show();
            }
        });
    }

}
