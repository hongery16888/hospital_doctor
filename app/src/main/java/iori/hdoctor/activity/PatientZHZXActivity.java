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
import iori.hdoctor.net.response.PatientZHZXResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientZHZXActivity extends BaseActivity implements NetworkConnectListener {

    private boolean modify;

    @InjectView(R.id.password)
    EditText passwrod;
    @InjectView(R.id.phone)
    EditText phone;
    @InjectView(R.id.qq)
    EditText qq;
    @InjectView(R.id.wchat)
    EditText wchat;
    @InjectView(R.id.total)
    TextView total;

    @OnClick(R.id.confirm)
    public void confirm() {
        modify = true;
        if (TextUtils.isEmpty(passwrod.getText().toString()))
            showToast("密码不能为空");
        else
            NetworkAPI.getNetworkAPI().pataccount(passwrod.getText().toString(),
                    phone.getText().toString(),
                    qq.getText().toString(),
                    wchat.getText().toString(),
                    showProgressDialog(),
                    this);
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zhzx_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.doctor_zhzx_title_main));
    }

    @Override
    protected void initData() {
        NetworkAPI.getNetworkAPI().pataccountinfo(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_ACCOUNT.equals(requestAction)){
            if (modify)
                finish();
            else{
                passwrod.setText(((PatientZHZXResponse)data).getPassword());
                qq.setText(((PatientZHZXResponse)data).getBangdingqq());
                phone.setText(((PatientZHZXResponse)data).getBangdingphone());
                wchat.setText(((PatientZHZXResponse)data).getBangdingwei());
                total.setText(((PatientZHZXResponse)data).getTotal() + "元");
            }
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
