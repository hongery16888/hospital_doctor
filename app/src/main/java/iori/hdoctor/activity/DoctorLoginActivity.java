package iori.hdoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorLoginActivity extends BaseActivity implements NetworkConnectListener {

    @InjectView(R.id.username)
    EditText username;

    @InjectView(R.id.password)
    EditText password;

    @OnClick(R.id.register)
    public void register() {
        startActivity(new Intent(DoctorLoginActivity.this, DoctorRegisterActivity.class));
    }

    @OnClick(R.id.login)
    public void login() {
        NetworkAPI.getNetworkAPI().login(username.getText().toString(), password.getText().toString(), showProgressDialog(), DoctorLoginActivity.this);
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

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.LOGIN.equals(requestAction)) {
            finish();
        }
        startActivity(new Intent(DoctorLoginActivity.this, DoctorMainActivity.class));
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        startActivity(new Intent(DoctorLoginActivity.this, DoctorMainActivity.class));
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
