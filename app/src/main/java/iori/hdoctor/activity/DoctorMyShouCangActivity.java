package iori.hdoctor.activity;

import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorMyAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.DocCircle;
import iori.hdoctor.net.response.DoctorCircleResponse;
import iori.hdoctor.net.response.DoctorMyShouCangResponse;
import iori.hdoctor.net.response.DoctorPublicResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorMyShouCangActivity extends BaseActivity implements NetworkConnectListener{

    private DoctorMyAdapter adapter;
    private ArrayList<DocCircle> docCircles = new ArrayList<>();

    @InjectView(R.id.circle_sq_listview)
    ListView listView;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkAPI.getNetworkAPI().deldocmyshoucang(docCircles.get(msg.what).getCollectionid(), showProgressDialog(), DoctorMyShouCangActivity.this);
        }
    };

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_my_shoucang_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction("我的收藏");
    }

    @Override
    protected void initData() {
        adapter = new DoctorMyAdapter(this, docCircles, handler);
        listView.setAdapter(adapter);

        NetworkAPI.getNetworkAPI().docmyshoucang(showProgressDialog(), DoctorMyShouCangActivity.this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_MY_SHOUCANG.equals(requestAction)){
            docCircles.clear();
            if (((DoctorMyShouCangResponse) data).getMyshoucanglist() != null) {
                docCircles.addAll(((DoctorMyShouCangResponse) data).getMyshoucanglist());
                adapter.setData(docCircles);
            }
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        adapter.setData(new ArrayList<DocCircle>());
        showToast(errorMsg);
        dismissProgressDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
