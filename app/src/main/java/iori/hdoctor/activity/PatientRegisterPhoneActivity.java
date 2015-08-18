package iori.hdoctor.activity;

import android.content.Intent;
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
import iori.hdoctor.net.response.DoctorRegisterResponse;
import iori.hdoctor.net.response.PatientRegisterPhoneResponse;
import iori.hdoctor.net.response.PatientRegisterResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientRegisterPhoneActivity extends BaseActivity implements NetworkConnectListener{

    @OnClick(R.id.next_step)
    public void nextStep() {
        if (password.getText().toString().equals(passwordConfirm.getText().toString()))
            NetworkAPI.getNetworkAPI().patregisterphone(username.getText().toString(), password.getText().toString(), showProgressDialog(), this);
        else
            showToast(getString(R.string.register_password_diff));
    }

    @InjectView(R.id.username)
    EditText username;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.password_confirm)
    EditText passwordConfirm;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_register_phone_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_register_title_main));
        setRightText(getResources().getString(R.string.login), loginClickListener);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_REGISTER_PHONE.equals(requestAction)) {
            DataTransfer.setUid(((PatientRegisterPhoneResponse)data).getUid());
            startActivity(new Intent(this, PatientRegisterCompleteActivity.class));
            dismissProgressDialog();
        }
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }

    private View.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
