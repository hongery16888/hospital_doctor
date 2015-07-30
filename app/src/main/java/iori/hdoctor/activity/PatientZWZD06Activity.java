package iori.hdoctor.activity;

import android.content.Intent;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientZWZD06Activity extends BaseActivity implements SeekBar.OnSeekBarChangeListener{

    @OnClick(R.id.next_step)
    public void nextStep() {
        getApp().setActivities(this);
        startActivity(new Intent(PatientZWZD06Activity.this, PatientZWZD07Activity.class));
    }

    @OnClick(R.id.last_step)
    public void lastStep(){
        finish();
    }

    @InjectView(R.id.seekbar_self)
    SeekBar seekBar;

    @InjectView(R.id.odds_time)
    TextView oddsTime;
    @InjectView(R.id.odds_info)
    TextView oddsInfo;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zwzd_06_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_zwzd_title_main));
    }

    @Override
    protected void initData() {
        seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        oddsTime.setText(progress + getResources().getString(R.string.patient_zwzd_piss_odds_unit));
        if (progress < 30)
            oddsInfo.setText(getResources().getString(R.string.patient_zwzd_piss_unit_little));
        else if (progress >= 30 && progress < 70)
            oddsInfo.setText(getResources().getString(R.string.patient_zwzd_piss_unit_little_more));
        else
            oddsInfo.setText(getResources().getString(R.string.patient_zwzd_piss_unit_much));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
