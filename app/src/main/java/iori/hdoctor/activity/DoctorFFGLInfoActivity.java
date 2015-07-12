package iori.hdoctor.activity;

import android.content.Intent;

import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorFFGLInfoActivity extends BaseActivity {

    @OnClick(R.id.next_step)
    public void nextStep() {
        finish();;
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_ffgl_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_ffgl_title_main));
    }

    @Override
    protected void initData() {

    }

}
