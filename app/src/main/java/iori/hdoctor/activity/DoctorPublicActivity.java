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
import iori.hdoctor.adapter.DoctorCircleAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.DocCircle;
import iori.hdoctor.net.response.DoctorPublicResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorPublicActivity extends BaseActivity implements NetworkConnectListener{

    private DoctorCircleAdapter adapter;
    private ArrayList<DocCircle> docCircles = new ArrayList<>();

    @InjectView(R.id.circle_sq_listview)
    PullToRefreshListView listView;

    private Handler refreshHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkAPI.getNetworkAPI().allpublic(showProgressDialog(), DoctorPublicActivity.this);
        }
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkAPI.getNetworkAPI().docpubliczan(docCircles.get(msg.what).getFrumid(), showProgressDialog(), DoctorPublicActivity.this);
        }
    };

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_circle_sq_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_circle_title_main));
        showPublish(publishListener);
    }

    @Override
    protected void initData() {
        getApp().setCircleRefreshHandler(refreshHandler);
        adapter = new DoctorCircleAdapter(this, docCircles, handler);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DoctorPublicActivity.this, DoctorCircleInfoActivity.class);
                intent.putExtra("frumid", docCircles.get(position - 1).getFrumid());
                intent.putExtra("type", "allpublic");
                startActivity(intent);
            }
        });
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                NetworkAPI.getNetworkAPI().allpublic(null, DoctorPublicActivity.this);
            }
        });

        NetworkAPI.getNetworkAPI().allpublic(showProgressDialog(), DoctorPublicActivity.this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_ALL_PUBLISH.equals(requestAction)){
            docCircles.clear();
            docCircles.addAll(((DoctorPublicResponse) data).getAllpubliclist());
            adapter.setData(docCircles);
        }
        listView.onRefreshComplete();
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        listView.onRefreshComplete();
        showToast(errorMsg);
        dismissProgressDialog();
    }

    private View.OnClickListener publishListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(DoctorPublicActivity.this, DoctorPublishActivity.class));
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getApp().setCircleRefreshHandler(null);
    }
}
