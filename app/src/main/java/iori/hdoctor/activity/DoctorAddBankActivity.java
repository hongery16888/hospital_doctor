package iori.hdoctor.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
public class DoctorAddBankActivity extends BaseActivity implements NetworkConnectListener{

    @InjectView(R.id.bank_name)
    EditText bankName;
    @InjectView(R.id.bank_no)
    EditText bankNo;

    @OnClick(R.id.confirm)
    public void confirm(){
        NetworkAPI.getNetworkAPI().docAddBank(bankName.getText().toString(), bankNo.getText().toString(),
                "100", showProgressDialog(), this);
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_add_bank_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.doctor_add_bank));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_INCOME.equals(requestAction)){
            finish();
            getApp().getRefreshBankHandler().sendEmptyMessage(0);
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
