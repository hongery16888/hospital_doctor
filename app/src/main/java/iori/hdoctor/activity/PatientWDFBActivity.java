package iori.hdoctor.activity;

import android.widget.ListView;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.PatientWDFBAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.PatientWDFBResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientWDFBActivity extends BaseActivity implements NetworkConnectListener{

    @InjectView(R.id.wdfb_listview)
    ListView listView;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_wdfb_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_wdfb_title_main));
    }

    @Override
    protected void initData() {
        NetworkAPI.getNetworkAPI().mypublish(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_PUBLISH.equals(requestAction)){
            listView.setAdapter(new PatientWDFBAdapter(this, ((PatientWDFBResponse)data).getPublishlist()));
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
