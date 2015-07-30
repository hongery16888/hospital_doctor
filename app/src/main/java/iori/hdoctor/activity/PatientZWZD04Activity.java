package iori.hdoctor.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.PatientZWZD04Adapter;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientZWZD04Activity extends BaseActivity implements AdapterView.OnItemSelectedListener{

    @InjectView(R.id.sit_list)
    Gallery sitList;
    @InjectView(R.id.sit_time)
    TextView sitTime;

    @OnClick(R.id.next_step)
    public void nextStep() {
        getApp().setActivities(this);
        startActivity(new Intent(PatientZWZD04Activity.this, PatientZWZD05Activity.class));
    }

    private TextView tempTV;

    @OnClick(R.id.last_step)
    public void lastStep(){
        finish();
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zwzd_04_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_zwzd_title_main));
    }

    @Override
    protected void initData() {
        sitList.setAdapter(new PatientZWZD04Adapter(this));
        sitList.setSelection(2);
        sitList.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(tempTV != null) {
            tempTV.setTextColor(Color.GRAY);
        }
        ((TextView)view.findViewById(R.id.sit_time)).setTextColor(getResources().getColor(R.color.patient_top_bar_bg));
        tempTV = (TextView)view.findViewById(R.id.sit_time);

        sitTime.setText(position * 2 + "");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
