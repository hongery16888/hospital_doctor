package iori.hdoctor.activity;

import android.content.Intent;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.TestingReport;
import iori.hdoctor.net.response.PatientUserReportResponse;
import iori.hdoctor.view.ProgressWheel;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientFXBGActivity extends BaseActivity implements NetworkConnectListener{

    @InjectView(R.id.progress)
    ProgressWheel progress;
    @InjectView(R.id.height)
    TextView height;
    @InjectView(R.id.weight)
    TextView weight;
    @InjectView(R.id.age)
    TextView age;
    @InjectView(R.id.score)
    TextView score;
    @InjectView(R.id.status)
    TextView status;
    @InjectView(R.id.jieguo)
    TextView jieguo;

    @OnClick(R.id.zwzd_again)
    public void again() {
        startActivity(new Intent(PatientFXBGActivity.this, PatientZWZD01Activity.class));
        finish();
    }

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
        NetworkAPI.getNetworkAPI().usereport(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_USER_REPORT.equals(requestAction)){
            TestingReport testingReport = new TestingReport();
            testingReport.setScore(((PatientUserReportResponse) data).getScore());
            testingReport.setHeight(((PatientUserReportResponse) data).getHeight());
            testingReport.setWeight(((PatientUserReportResponse) data).getWeight());
            testingReport.setAge(((PatientUserReportResponse) data).getAge());
            getApp().setReport(testingReport);
            if (getApp().getReport() != null){
                height.setText(getApp().getReport().getHeight() + "");
                weight.setText(getApp().getReport().getWeight() + "");
                age.setText(getApp().getReport().getAge() + "");
                jieguo.setText(((PatientUserReportResponse) data).getJieguo());
                if (getApp().getReport().getScore() * 100 / 35 <= 100) {
                    score.setText((100 - getApp().getReport().getScore() * 100 / 35) + "");
                    progress.setProgress((100 - getApp().getReport().getScore() * 100 / 35) * 360 / 100);
                }else {
                    progress.setProgress(0);
                    score.setText(0 + "");
                }

                if (getApp().getReport().getScore() >= 0 && getApp().getReport().getScore() <= 7){
                    status.setText("轻度");
                }else if (getApp().getReport().getScore() >= 8 && getApp().getReport().getScore() <= 19)
                    status.setText("中度");
                else
                    status.setText("重度");

            }else
                progress.setProgress(0);
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
