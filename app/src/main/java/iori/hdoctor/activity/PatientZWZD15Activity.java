package iori.hdoctor.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientZWZD15Activity extends BaseActivity implements NetworkConnectListener{

    private String health;
    private HashMap<String, String> jieguo;

    @OnClick(R.id.zwzd_complete)
    public void complete() {
        if (getApp().getReport().getScore() >= 0 && getApp().getReport().getScore() <= 7){
            health = "轻度";
        }else if (getApp().getReport().getScore() >= 8 && getApp().getReport().getScore() <= 19)
            health = "中度";
        else
        health = "重度";

        NetworkAPI.getNetworkAPI().alyreport(getApp().getReport().getScore(),
                                            getApp().getReport().getHeight(),
                                            getApp().getReport().getWeight(),
                                            getApp().getReport().getAge(),
                                            health,
                                            jieguo.get(health),
                                            showProgressDialog(), this);
    }

    private TextView tempTV;

    @OnClick(R.id.last_step)
    public void lastStep(){
        finish();
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zwzd_15_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_zwzd_title_main));
    }

    @Override
    protected void initData() {
        getApp().getActivities().add(this);
        jieguo = new HashMap<>();
        jieguo.put("轻度", "建议您平时多注意饮食规律，多运动");
        jieguo.put("中度", "您有中度前列腺症状，建议您在线咨询医生，或到医院检查");
        jieguo.put("重度", "您有重度前列腺症状，建议您到医院检查");
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_ALY_REPORT.equals(requestAction)){
            Intent intent = new Intent(PatientZWZD15Activity.this, PatientZWZDCompleteActivity.class);
            intent.putExtra("jieguo", jieguo.get(health));
            intent.putExtra("healthy", health);
            startActivity(intent);

        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
