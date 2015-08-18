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

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientZWZD12Activity extends BaseActivity{

    @OnClick(R.id.next_step)
    public void nextStep() {
        getApp().setActivities(this);
        startActivity(new Intent(PatientZWZD12Activity.this, PatientZWZD13Activity.class));
    }

    private TextView tempTV;

    @OnClick(R.id.last_step)
    public void lastStep(){
        returnScore();
        finish();
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zwzd_12_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_zwzd_title_main));
    }

    @Override
    protected void initData() {
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
