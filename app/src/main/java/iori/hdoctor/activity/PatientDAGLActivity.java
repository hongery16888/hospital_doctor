package iori.hdoctor.activity;

import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.PatientConsultRecordAdapter;
import iori.hdoctor.adapter.PatientTestRecordAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.PatientConsultRecordResponse;
import iori.hdoctor.net.response.PatientTestRecordResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientDAGLActivity extends BaseActivity implements NetworkConnectListener{

    public int choiceTv = R.id.zjda ;

    @InjectView(R.id.list)
    ListView listView;

    @InjectView(R.id.zjda)
    TextView zjda;
    @InjectView(R.id.zxda)
    TextView zxda;

    @OnClick({R.id.zjda, R.id.zxda})
    public void load(TextView v){
        if (choiceTv != v.getId()){
            if (v.getId() == R.id.zjda){
                zjda.setTextColor(getResources().getColor(R.color.patient_top_bar_bg));
                zxda.setTextColor(getResources().getColor(R.color.global_title_color));
                NetworkAPI.getNetworkAPI().testrecord(showProgressDialog(), this);
            }else{
                zxda.setTextColor(getResources().getColor(R.color.patient_top_bar_bg));
                zjda.setTextColor(getResources().getColor(R.color.global_title_color));
                NetworkAPI.getNetworkAPI().consultrecord(showProgressDialog(), this);
            }
            choiceTv = v.getId();
        }
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_record_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction("健康档案");
    }

    @Override
    protected void initData() {
        NetworkAPI.getNetworkAPI().testrecord(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_TEST_RECORD.equals(requestAction)){
            zjda.setTextColor(getResources().getColor(R.color.patient_top_bar_bg));
            zxda.setTextColor(getResources().getColor(R.color.global_title_color));
            choiceTv = R.id.zjda;
            if (((PatientTestRecordResponse)data).getTestrecordlist().size() > 0)
                listView.setAdapter(new PatientTestRecordAdapter(this, ((PatientTestRecordResponse)data).getTestrecordlist()));
        }else if(HttpRequest.DOC_CONSULTING.equals(requestAction)){
            zxda.setTextColor(getResources().getColor(R.color.patient_top_bar_bg));
            zjda.setTextColor(getResources().getColor(R.color.global_title_color));
            choiceTv = R.id.zxda;
            if (((PatientConsultRecordResponse)data).getConsultrecordlist().size() > 0)
                listView.setAdapter(new PatientConsultRecordAdapter(this, ((PatientConsultRecordResponse)data).getConsultrecordlist()));
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
