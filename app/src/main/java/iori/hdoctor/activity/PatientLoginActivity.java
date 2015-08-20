package iori.hdoctor.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
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
import iori.hdoctor.net.response.PatientLoginResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientLoginActivity extends BaseActivity implements NetworkConnectListener {

    @InjectView(R.id.username)
    EditText username;
    @InjectView(R.id.password)
    EditText password;

    @OnClick(R.id.register)
    public void register() {
        startActivity(new Intent(PatientLoginActivity.this, PatientRegisterPhoneActivity.class));
    }

    @OnClick(R.id.login)
    public void login() {
        if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
            showToast("用户名或密码不能为空");
        }else
            NetworkAPI.getNetworkAPI().patlogin(username.getText().toString(), password.getText().toString(),
                showProgressDialog(), this);
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_login_main;
    }

    @Override
    protected void initView() {
        setHideBackAction();
        setTitleAction(getResources().getString(R.string.patient_login_title_main));
        setRightText(getResources().getString(R.string.doctor_login_type), typeListener);
    }

    @Override
    protected void initData() {
        getLoginHistoryInfo();
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_LOGIN.equals(requestAction)) {
            DataTransfer.setUid(((PatientLoginResponse) data).getUid());
            saveLoginHistoryInfo();
            startActivity(new Intent(PatientLoginActivity.this, PatientMainActivity.class));
            finish();
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }

    private View.OnClickListener typeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(PatientLoginActivity.this, DoctorLoginActivity.class));
            finish();
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME){
            Intent intent=new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getLoginHistoryInfo()
    {
        SharedPreferences prefs = getSharedPreferences("HDoctor", MODE_WORLD_WRITEABLE);
        String username = prefs.getString("pat_username", "");
        this.username.setText(username);
        String password = prefs.getString("pat_password", "");
        this.password.setText(password);
    }

    private void saveLoginHistoryInfo()
    {
        SharedPreferences.Editor editor = getSharedPreferences("HDoctor", MODE_WORLD_WRITEABLE).edit();
        editor.putString("pat_username", this.username.getText().toString());
        editor.putString("pat_password", this.password.getText().toString());
        editor.commit();
    }
}
