package iori.hdoctor.activity;

import android.widget.ListView;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorBankAdapter;
import iori.hdoctor.adapter.DoctorBankRecordAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.DoctorBankRecordResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorBankRecordActivity extends BaseActivity implements NetworkConnectListener{

    @InjectView(R.id.record_list)
    ListView recordList;

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
        NetworkAPI.getNetworkAPI().docincomerecord(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_INCOME_RECORD.equals(requestAction)){
            if (data != null && ((DoctorBankRecordResponse)data).getReflectlist() != null){
                recordList.setAdapter(new DoctorBankRecordAdapter(this, ((DoctorBankRecordResponse)data).getReflectlist()));
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
