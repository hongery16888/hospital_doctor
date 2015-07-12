package iori.hdoctor.activity;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorBLGLAdapter;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorBLGLInfoActivity extends BaseActivity {

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
