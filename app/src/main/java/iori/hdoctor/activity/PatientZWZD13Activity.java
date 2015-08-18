package iori.hdoctor.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.TextView;

import java.util.IdentityHashMap;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.PatientZWZD04Adapter;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientZWZD13Activity extends BaseActivity{

    @OnClick(R.id.next_step)
    public void nextStep() {
        getApp().setActivities(this);
        startActivity(new Intent(PatientZWZD13Activity.this, PatientZWZD14Activity.class));
    }

    @InjectView(R.id.yes)
    TextView yes;
    @InjectView(R.id.no)
    TextView no;

    @OnClick({R.id.yes, R.id.no})
    public void setChoice(TextView v){
        if (v.getId() == R.id.yes){
            yes.setBackgroundResource(R.drawable.icon_zwzd_ball_hl);
            yes.setTextColor(getResources().getColor(R.color.patient_top_bar_bg));
            no.setBackgroundResource(R.drawable.icon_zwzd_ball);
            no.setTextColor(getResources().getColor(R.color.global_title_color));
        }else{
            no.setBackgroundResource(R.drawable.icon_zwzd_ball_hl);
            no.setTextColor(getResources().getColor(R.color.patient_top_bar_bg));
            yes.setBackgroundResource(R.drawable.icon_zwzd_ball);
            yes.setTextColor(getResources().getColor(R.color.global_title_color));
        }
    }

    private TextView tempTV;

    @OnClick(R.id.last_step)
    public void lastStep(){
        finish();
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zwzd_13_main;
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
