package iori.hdoctor.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.PatientWDDDAdapter;
import iori.hdoctor.adapter.PatientWDYYAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.PatMyOrder;
import iori.hdoctor.net.response.PatientWDDDResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientWDDDActivity extends BaseActivity implements NetworkConnectListener{

    private ArrayList<PatMyOrder> patMyOrdersAll = new ArrayList<>();
    private ArrayList<PatMyOrder> patMyOrders = new ArrayList<>();
    private PatientWDDDAdapter adapter;
    private int pid = R.id.all;

    @InjectView(R.id.all_order)
    TextView allOrder;
    @InjectView(R.id.wait_order)
    TextView waitOrder;
    @InjectView(R.id.wddd_listview)
    ListView listView;

    @OnClick({R.id.all, R.id.wait})
    public void click(View view){
        if (pid != view.getId())
            if(view.getId() == R.id.all){
                pid = R.id.all;
                allOrder.setTextColor(getResources().getColor(R.color.patient_top_bar_bg));
                waitOrder.setTextColor(getResources().getColor(R.color.global_title_color));
                adapter.setData(patMyOrdersAll);
            }else {
                pid = R.id.wait;
                allOrder.setTextColor(getResources().getColor(R.color.global_title_color));
                waitOrder.setTextColor(getResources().getColor(R.color.patient_top_bar_bg));
                adapter.setData(patMyOrders);
            }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkAPI.getNetworkAPI().myorder(showProgressDialog(), PatientWDDDActivity.this);
        }
    };

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_wddd_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.patient_wddd_title_main));
    }

    @Override
    protected void initData() {
        adapter = new PatientWDDDAdapter(this, patMyOrders);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if(pid == R.id.all){
                    Intent intent = new Intent(PatientWDDDActivity.this, PatientWDDDInfoActivity.class);
                    intent.putExtra("orderId", patMyOrdersAll.get(position).getOrderid());
                    startActivity(intent);
                }else if(pid == R.id.wait){
                    Intent intent = new Intent(PatientWDDDActivity.this, PatientWDDDInfoActivity.class);
                    intent.putExtra("orderId", patMyOrders.get(position).getOrderid());
                    startActivity(intent);
                }
            }
        });
        NetworkAPI.getNetworkAPI().myorder(showProgressDialog(), this);
        getApp().setRefreshListHandler(handler);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_MY_ORDER.equals(requestAction)){
            patMyOrdersAll.clear();
            patMyOrders.clear();
            patMyOrdersAll.addAll(((PatientWDDDResponse) data).getOrderlist());
            adapter.setData(patMyOrdersAll);
            for (int i = 0 ; i < patMyOrdersAll.size() ; i++){
                if (patMyOrdersAll.get(i).getIscomment().equals("0")){
                    patMyOrders.add(patMyOrdersAll.get(i));
                }
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
