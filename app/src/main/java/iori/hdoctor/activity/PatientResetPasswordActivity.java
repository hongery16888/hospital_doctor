package iori.hdoctor.activity;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

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
public class PatientResetPasswordActivity extends BaseActivity implements NetworkConnectListener{

    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.password_confirm)
    EditText passwordConfirm;
    @InjectView(R.id.next_step)
    TextView nextStep;

    @OnClick(R.id.next_step)
    public void nextStep(){
        if (password.getText().toString().length() < 6) {
            showToast("新密码不能小于6位");
            return;
        }if (TextUtils.isEmpty(password.getText().toString()) ||
                TextUtils.isEmpty(passwordConfirm.getText().toString())){
            showToast("密码不能为空");
            return;
        }else if(!password.getText().toString().equals(passwordConfirm.getText().toString())){
            showToast("两次密码不同");
            return;
        }

        NetworkAPI.getNetworkAPI().resetpassword(getIntent().getStringExtra("userId"),
                password.getText().toString(), showProgressDialog(), this);
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_reset_pwd_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction("重置密码");
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.RESET_PWD.equals(requestAction)){
            finish();
        }
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
    }
}
