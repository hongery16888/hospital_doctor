package iori.hdoctor.activity;

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorLoginActivity extends BaseActivity {

    @OnClick(R.id.register)
    public void register(){
        startActivity(new Intent(DoctorLoginActivity.this, DoctorRegisterActivity.class));
    }

    @OnClick(R.id.login)
    public void login() {
        startActivity(new Intent(DoctorLoginActivity.this, DoctorMainActivity.class));
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_login_main;
    }

    @Override
    protected void initView() {
        setHideBackAction();
        setTitleAction(getResources().getString(R.string.doctor_login_title_main));
        setRightText(getResources().getString(R.string.patient_login_type), typeListener);
    }

    @Override
    protected void initData() {

    }

    private View.OnClickListener typeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(DoctorLoginActivity.this, PatientLoginActivity.class));
            finish();
        }
    };
}
