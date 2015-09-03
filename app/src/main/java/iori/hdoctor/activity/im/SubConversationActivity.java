package iori.hdoctor.activity.im;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;

/**
 * Created by Administrator on 2015/7/16.
 */
public class SubConversationActivity extends FragmentActivity {

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
        setContentView(R.layout.sub_conversation);
        ButterKnife.inject(this);

        midTitle.setText("我的消息");
    }

}
