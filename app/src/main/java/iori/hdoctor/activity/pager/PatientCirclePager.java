package iori.hdoctor.activity.pager;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import io.rong.imkit.MainActivity;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import iori.hdoctor.R;
import iori.hdoctor.activity.DoctorCircleInfoActivity;
import iori.hdoctor.activity.PatientCircleActivity;
import iori.hdoctor.activity.PatientCircleInfoActivity;
import iori.hdoctor.activity.PatientFriendActivity;
import iori.hdoctor.activity.PatientMainActivity;
import iori.hdoctor.activity.PatientMessageActivity;
import iori.hdoctor.activity.base.BasePager;
import iori.hdoctor.activity.im.ConversationListActivity;
import iori.hdoctor.activity.im.SubConversationActivity;
import iori.hdoctor.adapter.DoctorCircleAdapter;
import iori.hdoctor.adapter.PatientCircleAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.PatCircle;
import iori.hdoctor.net.response.PatientCircleResponse;

/**
 * Created by Administrator on 2015/7/10.
 */
public class PatientCirclePager extends BasePager implements NetworkConnectListener{

    private View view;
    private Context context;
    private PullToRefreshListView listView;
    private PatientCircleAdapter adapter;
    private ArrayList<PatCircle> patCircles = new ArrayList<>();

    private boolean isInit = false;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkAPI.getNetworkAPI().patcirclezan(patCircles.get(msg.what).getFrumid(), ((PatientMainActivity) context).showProgressDialog(), PatientCirclePager.this);
        }
    };

    public PatientCirclePager(Context ct) {
        super(ct);
        context = ct;
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.patient_pager_circle, null);
        return view;
    }

    @Override
    public void initData() {
        listView = (PullToRefreshListView)view.findViewById(R.id.circle_listview);
        adapter = new PatientCircleAdapter(context, patCircles, handler);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, PatientCircleInfoActivity.class);
                intent.putExtra("frumid", patCircles.get(position - 1).getFrumid());
                context.startActivity(intent);
            }
        });
        view.findViewById(R.id.sq).setOnClickListener(sqListener);
        view.findViewById(R.id.hy).setOnClickListener(hyListener);
        view.findViewById(R.id.xx).setOnClickListener(xxListener);

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                NetworkAPI.getNetworkAPI().patcircle(null, PatientCirclePager.this);
            }
        });

        NetworkAPI.getNetworkAPI().patcircle(((PatientMainActivity)context).showProgressDialog(), this);
    }

    @Override
    public void onResume() {
        if (!isInit) {
            initData();
            isInit = !isInit;
        }
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_ROUND.equals(requestAction)){
            patCircles.clear();
            patCircles.addAll(((PatientCircleResponse) data).getPatquanzilist());
            adapter.setData(patCircles);
        }
        listView.onRefreshComplete();
        ((PatientMainActivity)context).dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
        ((PatientMainActivity)context).dismissProgressDialog();
        listView.onRefreshComplete();
    }

    private View.OnClickListener sqListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, PatientCircleActivity.class));
        }
    };

    private View.OnClickListener hyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, PatientFriendActivity.class));
        }
    };

    private View.OnClickListener xxListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            context.startActivity(new Intent(context, PatientMessageActivity.class));
            context.startActivity(new Intent(context, ConversationListActivity.class));
//            if(RongIM.getInstance() != null)
//                RongIM.getInstance().startSubConversationList(context, Conversation.ConversationType.PRIVATE);
        }
    };

}
