package iori.hdoctor.activity;

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorRegisterCompleteActivity extends BaseActivity {

    @OnClick(R.id.next_step)
    public void nextStep() {
        startActivity(new Intent(DoctorRegisterCompleteActivity.this, DoctorMainActivity.class));
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_register_complete_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_register_title_main));
        setRightText(getResources().getString(R.string.login), loginClickListener);
    }

    @Override
    protected void initData() {

    }

    private View.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
