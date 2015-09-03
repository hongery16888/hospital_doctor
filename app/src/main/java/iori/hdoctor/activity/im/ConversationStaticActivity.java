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

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.widget.provider.CameraInputProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imkit.widget.provider.VoIPInputProvider;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ImageMessage;
import io.rong.message.VoiceMessage;
import iori.hdoctor.R;

/**
 * Created by Administrator on 2015/8/30.
 */
public class ConversationStaticActivity extends FragmentActivity implements RongIM.ConversationBehaviorListener {

    @InjectView(R.id.mid_title)
    TextView midTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.conversation);
        ButterKnife.inject(this);

        //扩展功能自定义
        InputProvider.ExtendProvider[] provider = {
                new ImageInputProvider(RongContext.getInstance()),//图片
                new CameraInputProvider(RongContext.getInstance()),//相机
//                new VoIPInputProvider(RongContext.getInstance()),// 语音通话
        };

        midTitle.setText(getIntent().getData().getQueryParameter("title"));

        RongIM.getInstance().resetInputExtensionProvider(Conversation.ConversationType.PRIVATE, provider);

//        ConversationFragment fragment =
//                (ConversationFragment)getSupportFragmentManager().findFragmentById(R.id.conversation);
//        /* 传入私聊会话 PRIVATE 的参数*/
//        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon().appendPath("conversation")
//                .appendPath(io.rong.imlib.model.Conversation.ConversationType.PRIVATE.getName().toLowerCase())
//                .appendQueryParameter("targetId", "44").appendQueryParameter("title", "盗版哥").build();
//
//        fragment.setUri(uri);

//        RongIM.setConversationBehaviorListener(this);

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
            VoiceMessage voiceMessage = (VoiceMessage) message.getContent();

        }
        return true;
    }

    @Override
    public boolean onMessageLongClick(Context context, View view, Message message) {
        return false;
    }
}