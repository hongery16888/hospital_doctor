package iori.hdoctor.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.DataTransfer;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.DoctorLoginResponse;

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
        startActivity(new Intent(DoctorLoginActivity.this, DoctorRegisterPhoneActivity.class));
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
        getLoginHistoryInfo();
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
        if (HttpRequest.DOC_LOGIN.equals(requestAction)) {
            DataTransfer.setUid(((DoctorLoginResponse) data).getUid());
            getApp().setUser((DoctorLoginResponse) data);
            saveLoginHistoryInfo();
            startActivity(new Intent(DoctorLoginActivity.this, DoctorMainActivity.class));
            finish();
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        startActivity(new Intent(DoctorLoginActivity.this, DoctorMainActivity.class));
        showToast(errorMsg);
        dismissProgressDialog();
    }

    private void getLoginHistoryInfo()
    {
        SharedPreferences prefs = getSharedPreferences("HDoctor", MODE_WORLD_WRITEABLE);
        String username = prefs.getString("doc_username", "");
        this.username.setText(username);
        String password = prefs.getString("doc_password", "");
        this.password.setText(password);
    }

    private void saveLoginHistoryInfo()
    {
        SharedPreferences.Editor editor = getSharedPreferences("HDoctor", MODE_WORLD_WRITEABLE).edit();
        editor.putString("doc_username", this.username.getText().toString());
        editor.putString("doc_password", this.password.getText().toString());
        editor.commit();
    }
}
