package iori.hdoctor.activity.im;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.widget.provider.CameraInputProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imkit.widget.provider.VoIPInputProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ImageMessage;
import io.rong.message.VoiceMessage;
import iori.hdoctor.R;
import iori.hdoctor.app.MyApp;

/**
 * Created by Administrator on 2015/8/30.
 */
public class ConversationStaticActivity extends FragmentActivity implements RongIM.ConversationBehaviorListener {

    @InjectView(R.id.mid_title)
    TextView midTitle;
    @InjectView(R.id.title_bar)
    View titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.conversation);
        ButterKnife.inject(this);

        titleBar.setBackgroundColor(((MyApp)getApplication()).isDoc()?getResources().getColor(R.color.top_bar_bg):getResources().getColor(R.color.patient_top_bar_bg));
//
        midTitle.setText(getIntent().getData().getQueryParameter("title"));
//
//        ConversationFragment fragment =
//                (ConversationFragment)getSupportFragmentManager().findFragmentById(R.id.conversation);
//        /* 传入私聊会话 PRIVATE 的参数*/
//        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon().appendPath("conversation")
//                .appendPath(io.rong.imlib.model.Conversation.ConversationType.PRIVATE.getName().toLowerCase())
//                .appendQueryParameter("targetId", getIntent().getData().getQueryParameter("targetId")).appendQueryParameter("title", getIntent().getData().getQueryParameter("title")).build();
//
//        fragment.setUri(uri);
//
        RongIM.setConversationBehaviorListener(this);
//
        findViewById(R.id.left_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean onMessageClick(Context context, View view, Message message) {

        if (message.getContent() instanceof ImageMessage){
            ImageMessage imageMessage = (ImageMessage) message.getContent();
            Intent intent = new Intent(this, PhotoActivity.class);
            intent.putExtra("photo", imageMessage.getLocalUri() == null ? imageMessage.getRemoteUri().toString() : imageMessage.getLocalUri().toString());
            if (imageMessage.getThumUri() != null){
                intent.putExtra("thumbnail", imageMessage.getThumUri().getPath().toString());
            }

            startActivity(intent);
        }else if (message.getContent() instanceof VoiceMessage) {//语音消息
            return false;
        }
        return true;
    }

    @Override
    public boolean onMessageLongClick(Context context, View view, Message message) {
        return false;
    }
}