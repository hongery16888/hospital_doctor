package iori.hdoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorBLGLAdapter;
import iori.hdoctor.adapter.DoctorFriendAdapter;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorFriendActivity extends BaseActivity {

    @InjectView(R.id.friend_listview)
    PullToRefreshListView listView;
    @InjectView(R.id.mid_left_icon)
    TextView midLeftText;
    @InjectView(R.id.mid_right_icon)
    TextView midRightText;
    @OnClick({R.id.mid_left_icon, R.id.mid_right_icon})
    public void midIcon(){
        if (flag) {
            midLeftText.setTextColor(getResources().getColor(R.color.white));
            midRightText.setTextColor(getResources().getColor(R.color.global_title_color));
            midLeftText.setBackgroundResource(R.drawable.bg_tab2_blue_lef);
            midRightText.setBackgroundResource(R.drawable.bg_tab2_right_hl);
        }else{
            midLeftText.setTextColor(getResources().getColor(R.color.global_title_color));
            midRightText.setTextColor(getResources().getColor(R.color.white));
            midLeftText.setBackgroundResource(R.drawable.bg_tab2_left_hl);
            midRightText.setBackgroundResource(R.drawable.bg_tab2_blue_right);
        }
        flag = !flag;
    }

    private boolean flag = true;

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_circle_friend_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setRightText(getResources().getString(R.string.doctor_circle_info_friend_add), addListener);
        showMidIcon();
    }

    @Override
    protected void initData() {
        listView.setAdapter(new DoctorFriendAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

}