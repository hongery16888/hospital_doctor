package iori.hdoctor.activity;

import android.content.Intent;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorBLGLAdapter;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorBLGLInfoActivity extends BaseActivity {

    @OnClick(R.id.doctor_info)
    public void info(){
        startActivity(new Intent(this, DoctorInfoActivity.class));
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_blgl_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_blgl_title_main));
    }

    @Override
    protected void initData() {

    }

}
