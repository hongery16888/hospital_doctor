package iori.hdoctor.activity;

import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientDoctorYYActivity extends BaseActivity{

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_doctor_yy_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.patient_doctor_yy_title_main));
    }

    @Override
    protected void initData() {
    }

}
