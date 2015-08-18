package iori.hdoctor.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorCircleAdapter;
import iori.hdoctor.adapter.PatientJKTXAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.HealthyRemind;
import iori.hdoctor.net.response.PatientHealthyRemindResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientJKTXActivity extends BaseActivity implements NetworkConnectListener{

    private ArrayList<HealthyRemind> healthyReminds = new ArrayList<>();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkAPI.getNetworkAPI().healthyremind(showProgressDialog(), PatientJKTXActivity.this);
        }
    };

    @InjectView(R.id.jktx_listview)
    ListView listView;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_jktx_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_jktx_title_main));
        setRightText(getResources().getString(R.string.patient_jktx_add), addListener);
    }

    @Override
    protected void initData() {
        getApp().setRefreshListHandler(handler);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        NetworkAPI.getNetworkAPI().healthyremind(showProgressDialog(), this);
    }

    private View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(PatientJKTXActivity.this, PatientAddMedicinePromptActivity.class));
        }
    };

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_HEALTHY_REMIND.equals(requestAction)){
            if (((PatientHealthyRemindResponse)data).getOrderlist() != null && ((PatientHealthyRemindResponse)data).getRemindlist() != null) {
                ((PatientHealthyRemindResponse) data).getOrderlist().addAll(((PatientHealthyRemindResponse) data).getRemindlist());
                listView.setAdapter(new PatientJKTXAdapter(this, ((PatientHealthyRemindResponse) data).getOrderlist(), getApp().getOptions()));
            }
            else if(((PatientHealthyRemindResponse)data).getOrderlist() != null && ((PatientHealthyRemindResponse)data).getRemindlist() == null)
                listView.setAdapter(new PatientJKTXAdapter(this, ((PatientHealthyRemindResponse)data).getOrderlist(), getApp().getOptions()));
            else if(((PatientHealthyRemindResponse)data).getOrderlist() == null && ((PatientHealthyRemindResponse)data).getRemindlist() != null)
                listView.setAdapter(new PatientJKTXAdapter(this, ((PatientHealthyRemindResponse)data).getRemindlist(), getApp().getOptions()));
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
