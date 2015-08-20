package iori.hdoctor.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.activity.pager.PatientCirclePager;
import iori.hdoctor.adapter.DoctorCircleAdapter;
import iori.hdoctor.adapter.PatientCircleAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.PatCircle;
import iori.hdoctor.net.response.PatientCircleResponse;
import iori.hdoctor.net.response.PatientCommunityResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientCircleActivity extends BaseActivity implements NetworkConnectListener{

    private PatientCircleAdapter adapter;
    private ArrayList<PatCircle> patCircles = new ArrayList<>();

    @InjectView(R.id.circle_sq_listview)
    PullToRefreshListView listView;

    private Handler refreshHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkAPI.getNetworkAPI().community(showProgressDialog(), PatientCircleActivity.this);
        }
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkAPI.getNetworkAPI().patcommunityzan(patCircles.get(msg.what).getFrumid(), showProgressDialog(), PatientCircleActivity.this);
        }
    };

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_circle_sq_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_circle_title_main));
        showPublish(publishListener);
    }

    @Override
    protected void initData() {
        adapter = new PatientCircleAdapter(this, patCircles, handler);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PatientCircleActivity.this, PatientCircleInfoActivity.class);
                intent.putExtra("frumid", patCircles.get(position - 1).getFrumid());
                intent.putExtra("type", "community");
                startActivity(intent);
            }
        });
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                NetworkAPI.getNetworkAPI().community(null, PatientCircleActivity.this);
            }
        });

        NetworkAPI.getNetworkAPI().community(showProgressDialog(), PatientCircleActivity.this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_COMMUNITY.equals(requestAction)){
            patCircles.clear();
            patCircles.addAll(((PatientCommunityResponse) data).getPubliclist());
            adapter.setData(patCircles);
        }
        listView.onRefreshComplete();
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }

    private View.OnClickListener publishListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(PatientCircleActivity.this, PatientPublishActivity.class));
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getApp().setCircleRefreshHandler(null);
    }
}
