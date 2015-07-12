package iori.hdoctor.activity;

import android.content.Intent;
import android.widget.LinearLayout;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorFFGLActivity extends BaseActivity {

    @OnClick(R.id.czsz)
    public void czsz(){
        startActivity(new Intent(DoctorFFGLActivity.this, DoctorFFGLInfoActivity.class));
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_ffgl_main;
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
