package iori.hdoctor.activity;

import android.content.Intent;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientDoctorIntroduceActivity extends BaseActivity{

    @InjectView(R.id.doctor_introduce)
    ExpandableTextView doctorIntroduce;
    @InjectView(R.id.doctor_skill)
    ExpandableTextView doctorSkill;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_doctor_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_doctor_introduce_main));
    }

    @Override
    protected void initData() {
        doctorIntroduce.setText(getString(R.string.patient_doctor_info_introduce_ex));
        doctorSkill.setText(getString(R.string.patient_doctor_info_sc_ex));
    }

}
