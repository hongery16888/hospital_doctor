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
public class PatientZWZD02Activity extends BaseActivity implements SeekBar.OnSeekBarChangeListener{

    @OnClick(R.id.next_step)
    public void nextStep() {
        startActivity(new Intent(PatientZWZD02Activity.this, PatientZWZD03Activity.class));
    }

    @OnClick(R.id.last_step)
    public void lastStep(){
        finish();
    }

    @InjectView(R.id.seekbar_self)
    SeekBar seekBar;

    @InjectView(R.id.weight_info)
    TextView weight;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zwzd_02_main;
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
            weight.setText((progress + 18) + getResources().getString(R.string.patient_zwzd_weight_unit));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
