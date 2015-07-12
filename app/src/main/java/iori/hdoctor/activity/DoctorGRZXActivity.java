package iori.hdoctor.activity;

import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorGRZXActivity extends BaseActivity {

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_personal_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_personal_title_main));
    }

    @Override
    protected void initData() {

    }

}
