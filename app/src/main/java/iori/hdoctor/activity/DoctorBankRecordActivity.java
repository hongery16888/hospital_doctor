package iori.hdoctor.activity;

import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.NetworkConnectListener;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorBankRecordActivity extends BaseActivity implements NetworkConnectListener{

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_income_record_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.doctor_income_record_title_main));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
