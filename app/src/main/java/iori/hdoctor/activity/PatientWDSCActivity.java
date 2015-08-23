package iori.hdoctor.activity;

import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.PatientWDFBAdapter;
import iori.hdoctor.adapter.PatientWDSCAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.PatientWDFB;
import iori.hdoctor.net.entity.PatientWDSC;
import iori.hdoctor.net.response.PatientWDFBResponse;
import iori.hdoctor.net.response.PatientWDSCResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientWDSCActivity extends BaseActivity implements NetworkConnectListener{

    @InjectView(R.id.wdsc_listview)
    ListView listView;

    private ArrayList<PatientWDSC> patientWDSCs = new ArrayList<>();
    private PatientWDSCAdapter adapter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkAPI.getNetworkAPI().delcollection(patientWDSCs.get(msg.what).getCollectionid(), showProgressDialog(), PatientWDSCActivity.this);
        }
    };

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
        adapter = new PatientWDSCAdapter(this, patientWDSCs, handler);
        listView.setAdapter(adapter);
        NetworkAPI.getNetworkAPI().collection(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_COLLECTION.equals(requestAction)){
            patientWDSCs.clear();
            patientWDSCs.addAll(((PatientWDSCResponse) data).getCollectionlist());
            adapter.addData(patientWDSCs);
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
