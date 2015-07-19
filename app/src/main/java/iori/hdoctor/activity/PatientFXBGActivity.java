package iori.hdoctor.activity;

import android.content.Intent;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.view.ProgressWheel;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientFXBGActivity extends BaseActivity{

    @InjectView(R.id.progress)
    ProgressWheel progress;

    @OnClick(R.id.zwzd_again)
    public void again() {
        startActivity(new Intent(PatientFXBGActivity.this, PatientZWZD01Activity.class));
    }

    private TextView tempTV;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_fxbg_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_fxbg_title_main));
    }

    @Override
    protected void initData() {
        progress.setProgress((int)(360*0.75));
    }

}
