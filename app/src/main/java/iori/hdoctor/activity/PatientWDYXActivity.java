package iori.hdoctor.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.PatientWDYXAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.Medicine;
import iori.hdoctor.net.response.PatientWDYXResponse;
import iori.hdoctor.view.ProgressWheel;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientWDYXActivity extends BaseActivity implements NetworkConnectListener{

    private ArrayList<Medicine> medicines = new ArrayList<>();
    private ArrayList<Medicine> medicinesAll = new ArrayList<>();
    private PatientWDYXAdapter adapter;

    @InjectView(R.id.wdyx_listview)
    ListView listView;

    private Handler refreshMedicineHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkAPI.getNetworkAPI().medicine(showProgressDialog(), PatientWDYXActivity.this);
        }
    };

    @OnClick(R.id.sdtj)
    public void sdtj(){
        startActivity(new Intent(this, PatientAddMedicinectivity.class));
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_wdyx_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_wdyx_title_main));
    }

    @Override
    protected void initData() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        getApp().setRefreshListHandler(refreshMedicineHandler);
        NetworkAPI.getNetworkAPI().medicine(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_MEDICINE.equals(requestAction)){
            adapter = new PatientWDYXAdapter(this, ((PatientWDYXResponse)data).getMedicinelist(), getApp().getOptions());
            listView.setAdapter(adapter);
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
