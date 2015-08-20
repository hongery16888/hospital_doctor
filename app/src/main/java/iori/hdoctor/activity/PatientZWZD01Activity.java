package iori.hdoctor.activity;

import android.content.Intent;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.entity.TestingReport;
import iori.hdoctor.util.DateUtil;
import iori.hdoctor.view.DatePicker;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientZWZD01Activity extends BaseActivity {

    private int age;

    @InjectView(R.id.date_picker)
    DatePicker datePicker;

    @OnClick(R.id.next_step)
    public void nextStep() {
        if (age > 0) {
            getApp().getActivities().clear();
            getApp().setActivities(this);
            startActivity(new Intent(PatientZWZD01Activity.this, PatientZWZD02Activity.class));
        } else
            showToast("选择的生日不正确");
    }

    @OnClick(R.id.last_step)
    public void lastStep() {
        finish();
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zwzd_01_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_zwzd_title_main));
    }

    @Override
    protected void initData() {
        getApp().setReport(new TestingReport());
        getApp().setScores(new ArrayList<Integer>());
        datePicker.setOnSelectingListener(new DatePicker.OnSelectingListener() {
            @Override
            public void selected(boolean selected) {
                age = DateUtil.getAgeByBirthday(DateUtil.StrToDate(datePicker.getFormaTime(), "yyyy-MM-dd"));
                getApp().getReport().setAge(age);
            }
        });
    }

}
