package iori.hdoctor.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorMessageAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.DoctorMessageResponse;
import iori.hdoctor.net.response.FriendMsgResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorMessageActivity extends BaseActivity implements NetworkConnectListener{

    @InjectView(R.id.listview)
    ListView listView;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            finish();
        }
    };

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_circle_friend_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getIntent().getStringExtra("title"));
    }

    @Override
    protected void initData() {
        NetworkAPI.getNetworkAPI().docconsulting(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.FRIEND_MSG_LIST.equals(requestAction)){
            if (data != null && ((FriendMsgResponse)data).getAddfriend() != null)
                listView.setAdapter(new DoctorMessageAdapter(this, ((FriendMsgResponse)data).getAddfriend(), handler));
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
