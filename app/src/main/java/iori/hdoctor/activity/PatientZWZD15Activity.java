package iori.hdoctor.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientZWZD15Activity extends BaseActivity{

    @OnClick(R.id.zwzd_complete)
    public void complete() {
        for(int i = 0 ; i < getApp().getActivities().size() ; i++){
            getApp().getActivities().get(i).finish();
        }
        startActivity(new Intent(PatientZWZD15Activity.this, PatientFXBGActivity.class));
        getApp().getActivities().clear();
        finish();
    }

    private TextView tempTV;

    @OnClick(R.id.last_step)
    public void lastStep(){
        finish();
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zwzd_15_main;
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
