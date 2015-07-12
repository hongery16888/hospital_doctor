package iori.hdoctor.activity;

import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorCZSZActivity extends BaseActivity {

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_czgl_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_czgl_title_main));
    }

    @Override
    protected void initData() {

    }

}
