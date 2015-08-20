package iori.hdoctor.activity;

import android.content.Intent;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientZWZDCompleteActivity extends BaseActivity{

    @InjectView(R.id.status)
    TextView status;
    @InjectView(R.id.status_info)
    TextView statusInfo;

    @OnClick(R.id.zwzd_again)
    public void again() {
        startActivity(new Intent(PatientZWZDCompleteActivity.this, PatientZWZD01Activity.class));
    }

    @OnClick(R.id.zwzd_result)
    public void result(){
        for(int i = 0 ; i < getApp().getActivities().size() ; i++){
            getApp().getActivities().get(i).finish();
        }
        getApp().getActivities().clear();
        finish();
        startActivity(new Intent(this, PatientFXBGActivity.class));
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zwzd_complete_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_zwzd_title_main));
    }

    @Override
    protected void initData() {
        status.setText(getIntent().getStringExtra("healthy"));
        statusInfo.setText(getIntent().getStringExtra("jieguo"));
    }

}
