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

import iori.hdoctor.R;
import iori.hdoctor.activity.DoctorPublicActivity;
import iori.hdoctor.activity.DoctorCircleInfoActivity;
import iori.hdoctor.activity.DoctorFriendActivity;
import iori.hdoctor.activity.DoctorMainActivity;
import iori.hdoctor.activity.DoctorMessageActivity;
import iori.hdoctor.activity.PatientMainActivity;
import iori.hdoctor.activity.base.BasePager;
import iori.hdoctor.adapter.DoctorCircleAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.DocCircle;
import iori.hdoctor.net.response.DoctorCircleResponse;

/**
 * Created by Administrator on 2015/7/10.
 */
public class DoctorCirclePager extends BasePager implements NetworkConnectListener{

    private View view;
    private Context context;
    private PullToRefreshListView listView;
    private DoctorCircleAdapter adapter;
    private ArrayList<DocCircle> patCircles = new ArrayList<>();

    private boolean isInit = false;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkAPI.getNetworkAPI().doccirclezan(patCircles.get(msg.what).getFrumid(), ((DoctorMainActivity) context).showProgressDialog(), DoctorCirclePager.this);
        }
    };

    public DoctorCirclePager(Context ct) {
        super(ct);
        context = ct;
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.doctor_pager_circle, null);
        return view;
    }

    @Override
    public void initData() {
        listView = (PullToRefreshListView)view.findViewById(R.id.circle_listview);
        adapter = new DoctorCircleAdapter(context, patCircles, handler);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, DoctorCircleInfoActivity.class);
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
                NetworkAPI.getNetworkAPI().doccircle(null, DoctorCirclePager.this);
            }
        });

        NetworkAPI.getNetworkAPI().doccircle(((DoctorMainActivity) context).showProgressDialog(), this);
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
        if (HttpRequest.DOC_CIRCLE.equals(requestAction)){
            patCircles.clear();
            patCircles.addAll(((DoctorCircleResponse) data).getQuanzilist());
            adapter.setData(patCircles);
        }
        listView.onRefreshComplete();
        ((DoctorMainActivity)context).dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
        ((DoctorMainActivity)context).dismissProgressDialog();
    }

    private View.OnClickListener sqListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, DoctorPublicActivity.class));
        }
    };

    private View.OnClickListener hyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, DoctorFriendActivity.class));
        }
    };

    private View.OnClickListener xxListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DoctorMessageActivity.class);
            intent.putExtra("title", context.getString(R.string.doctor_circle_xx));
            context.startActivity(intent);
        }
    };

}
