package iori.hdoctor.activity;

import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorYYZXInfoActivity extends BaseActivity {

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_yygl_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_yygl_title_main));
    }

    @Override
    protected void initData() {

    }

}
