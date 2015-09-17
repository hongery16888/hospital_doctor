package iori.hdoctor.activity;

import android.content.Intent;
import android.os.CountDownTimer;
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
import iori.hdoctor.net.response.FindPasswordResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientForgetPwdActivity extends BaseActivity implements NetworkConnectListener{

    private String verifyCode;
    private boolean flag = true;
    private String userId;

    @InjectView(R.id.username)
    EditText username;
    @InjectView(R.id.verify)
    EditText verify;
    @InjectView(R.id.send_verify_code)
    TextView sendVerify;
    @InjectView(R.id.next_step)
    TextView nextStep;

    @OnClick(R.id.next_step)
    public void nextStep(){
        if (!TextUtils.isEmpty(verify.getText().toString()) && verify.getText().toString().equals(verifyCode)){
            Intent intent = new Intent(this, PatientResetPasswordActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
            finish();
        }else
            showToast("验证码错误");
    }

    @OnClick(R.id.send_verify_code)
    public void sendSms(){
        if (!TextUtils.isEmpty(username.getText().toString())){
            if (flag){
                flag = false;
                NetworkAPI.getNetworkAPI().findpassword(username.getText().toString(), null, this);
                new CountDownTimer(60000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        sendVerify.setText(millisUntilFinished / 1000 + getString(R.string.register_end));
                    }
                    public void onFinish() {
                        flag = true;
                        sendVerify.setText(getString(R.string.doctor_register_get_verify));
                    }
                }.start();
            }
        }else
            showToast("用户名不能为空");
    }



    @Override
    protected int setContentViewResId() {
        return R.layout.patient_forget_password_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction("找回密码");
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.FIND_PWD.equals(requestAction)){
            verifyCode = ((FindPasswordResponse)data).getPwdcode();
            userId = ((FindPasswordResponse)data).getUserId();
        }
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
    }
}
