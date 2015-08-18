package iori.hdoctor.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.PatientZWZD04Adapter;
import iori.hdoctor.adapter.PatientZWZD11Adapter;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientZWZD11Activity extends BaseActivity implements AdapterView.OnItemSelectedListener{

    @InjectView(R.id.sit_list)
    Gallery sitList;
    @InjectView(R.id.sit_time)
    TextView sitTime;

    private int score = 2;

    @OnClick(R.id.next_step)
    public void nextStep() {
        getApp().setActivities(this);
        getApp().getReport().setScore(getApp().getReport().getScore() + score);
        getApp().getScores().add(score);
        showToast(getApp().getReport().getScore() + "" );
        startActivity(new Intent(PatientZWZD11Activity.this, PatientZWZD12Activity.class));
    }

    private TextView tempTV;

    @OnClick(R.id.last_step)
    public void lastStep(){
        returnScore();
        finish();
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zwzd_11_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_zwzd_title_main));
    }

    @Override
    protected void initData() {
        sitList.setAdapter(new PatientZWZD11Adapter(this));
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

        sitTime.setText(position + "");
        score = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean onKeyDown(int kCode, KeyEvent kEvent) {
        if (kCode == KeyEvent.KEYCODE_BACK) {
            returnScore();
        }
        return super.onKeyDown(kCode, kEvent);
    }

    public void returnScore(){
        getApp().getReport().setScore(getApp().getReport().getScore() - getApp().getScores().get(getApp().getScores().size() - 1));
        getApp().getScores().remove(getApp().getScores().size() - 1);
    }
}
