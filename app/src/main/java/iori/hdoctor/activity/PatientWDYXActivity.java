package iori.hdoctor.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.activity.base.HDoctorCode;
import iori.hdoctor.adapter.PatientWDYXAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.Medicine;
import iori.hdoctor.net.response.PatientScanSnResponse;
import iori.hdoctor.net.response.PatientWDYXResponse;
import iori.hdoctor.view.ProgressWheel;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientWDYXActivity extends BaseActivity implements NetworkConnectListener{

    private ArrayList<Medicine> medicines = new ArrayList<>();
    private ArrayList<Medicine> medicinesAll = new ArrayList<>();
    private PatientWDYXAdapter adapter;
    public static int isSave = -1;

    @InjectView(R.id.wdyx_listview)
    ListView listView;

    private Handler getMedicineHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkAPI.getNetworkAPI().scansn("222", showProgressDialog(), PatientWDYXActivity.this);
        }
    };

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

    @OnClick(R.id.smtj)
    public void smtj(){
        getApp().setGetMedicineHandler(getMedicineHandler);
        startActivity(new Intent(this, CaptureActivity.class));
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
            adapter = new PatientWDYXAdapter(this, ((PatientWDYXResponse)data).getMedicinelist());
            listView.setAdapter(adapter);
            dismissProgressDialog();
        }else if (HttpRequest.SCAN_SN.equals(requestAction)){
            showToast("添加成功");
            NetworkAPI.getNetworkAPI().medicine(showProgressDialog(), PatientWDYXActivity.this);
        }
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }

    public static void readAsFile(InputStream inSream, File file) throws Exception{
        FileOutputStream outStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int len = -1;
        while( (len = inSream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inSream.close();
    }
}
