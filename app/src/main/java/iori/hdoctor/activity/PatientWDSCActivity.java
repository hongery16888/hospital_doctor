package iori.hdoctor.activity;

import android.widget.ListView;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.PatientWDFBAdapter;
import iori.hdoctor.adapter.PatientWDSCAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.PatientWDFBResponse;
import iori.hdoctor.net.response.PatientWDSCResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientWDSCActivity extends BaseActivity implements NetworkConnectListener{

    @InjectView(R.id.wdsc_listview)
    ListView listView;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_wdsc_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_wdsc_title_main));
    }

    @Override
    protected void initData() {
        NetworkAPI.getNetworkAPI().collection(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_COLLECTION.equals(requestAction)){
            listView.setAdapter(new PatientWDSCAdapter(this, ((PatientWDSCResponse)data).getCollectionlist()));
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
