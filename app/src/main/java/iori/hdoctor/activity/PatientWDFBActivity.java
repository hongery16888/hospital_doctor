package iori.hdoctor.activity;

import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.PatientWDFBAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.PatCircle;
import iori.hdoctor.net.entity.PatientWDFB;
import iori.hdoctor.net.response.PatientWDFBResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientWDFBActivity extends BaseActivity implements NetworkConnectListener{

    @InjectView(R.id.wdfb_listview)
    ListView listView;

    private ArrayList<PatientWDFB> patientWDFBs = new ArrayList<>();
    private PatientWDFBAdapter adapter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkAPI.getNetworkAPI().delmypublish(patientWDFBs.get(msg.what).getFrumid(), showProgressDialog(), PatientWDFBActivity.this);
        }
    };

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
        adapter = new PatientWDFBAdapter(this, patientWDFBs, handler);
        listView.setAdapter(adapter);
        NetworkAPI.getNetworkAPI().mypublish(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_PUBLISH.equals(requestAction)){
            if (((PatientWDFBResponse)data).getPublishlist() != null && ((PatientWDFBResponse)data).getPublishlist().size() > 0){
                patientWDFBs.clear();
                patientWDFBs.addAll(((PatientWDFBResponse)data).getPublishlist());
                adapter.addData(patientWDFBs);
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
