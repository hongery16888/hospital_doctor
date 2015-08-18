package iori.hdoctor.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientZWZD09Activity extends BaseActivity implements SeekBar.OnSeekBarChangeListener{

    private int score = 3;

    @OnClick(R.id.next_step)
    public void nextStep() {
        getApp().setActivities(this);
        getApp().getReport().setScore(getApp().getReport().getScore() + score);
        getApp().getScores().add(score);
        showToast(getApp().getReport().getScore() + "" );
        startActivity(new Intent(PatientZWZD09Activity.this, PatientZWZD10Activity.class));
    }

    @OnClick(R.id.last_step)
    public void lastStep(){
        returnScore();
        finish();
    }

    @InjectView(R.id.seekbar_self)
    SeekBar seekBar;

    @InjectView(R.id.thin)
    TextView thin;
    @InjectView(R.id.odds_info)
    TextView oddsInfo;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zwzd_09_main;
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
        thin.setText(progress + getResources().getString(R.string.patient_zwzd_piss_odds_unit));
        if (progress < 30)
            oddsInfo.setText(getResources().getString(R.string.patient_zwzd_piss_unit_little));
        else if (progress >= 30 && progress < 70)
            oddsInfo.setText(getResources().getString(R.string.patient_zwzd_piss_unit_little_more));
        else
            oddsInfo.setText(getResources().getString(R.string.patient_zwzd_piss_unit_much));

        if (progress == 100)
            score = 5;
        else if(progress > 50)
            score = 4;
        else if(progress == 50)
            score = 3;
        else if(progress > 20 && progress < 50)
            score = 2;
        else if(progress == 20)
            score = 1;
        else if (progress >= 0 && progress < 20)
            score = 0;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

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
