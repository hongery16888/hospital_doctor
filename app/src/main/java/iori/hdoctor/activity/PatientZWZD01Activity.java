package iori.hdoctor.activity;

import android.content.Intent;

import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientZWZD01Activity extends BaseActivity{

    @OnClick(R.id.next_step)
    public void nextStep() {
        getApp().getActivities().clear();
        getApp().setActivities(this);
        startActivity(new Intent(PatientZWZD01Activity.this, PatientZWZD02Activity.class));
    }

    @OnClick(R.id.last_step)
    public void lastStep(){
        finish();
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zwzd_01_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_zwzd_title_main));
    }

    @Override
    protected void initData() {

    }

}
