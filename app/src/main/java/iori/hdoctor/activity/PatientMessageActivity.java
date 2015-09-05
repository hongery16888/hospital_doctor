package iori.hdoctor.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorMessageAdapter;
import iori.hdoctor.adapter.PatientMessageAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.DoctorMessageResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientMessageActivity extends BaseActivity implements NetworkConnectListener{

    @InjectView(R.id.listview)
    ListView listView;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_circle_friend_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction("消息");
    }

    @Override
    protected void initData() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        NetworkAPI.getNetworkAPI().docconsulting(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_CONSULTING.equals(requestAction)){
            if (data != null && ((DoctorMessageResponse)data).getConsultinglist() != null)
                listView.setAdapter(new DoctorMessageAdapter(this, ((DoctorMessageResponse)data).getConsultinglist()));
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
