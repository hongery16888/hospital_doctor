package iori.hdoctor.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.PatientWDYYAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.PatientWDYYResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientWDYYActivity extends BaseActivity implements NetworkConnectListener{

    @InjectView(R.id.wdyy_listview)
    ListView listView;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_wdyy_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.patient_wdyy_title_main));
    }

    @Override
    protected void initData() {
        NetworkAPI.getNetworkAPI().myappointment(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_MY_POINTMENT.equals(requestAction)){
            System.out.println("size : " + ((PatientWDYYResponse)data).getAppointmentlist().size());
            listView.setAdapter(new PatientWDYYAdapter(this, ((PatientWDYYResponse)data).getAppointmentlist()));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
