package iori.hdoctor.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.view.WiperSwitch;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorFFGLInfoActivity extends BaseActivity implements NetworkConnectListener {

    @InjectView(R.id.price) EditText price;
    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.is_switch) WiperSwitch isSwitch;

    @OnClick(R.id.next_step)
    public void nextStep() {
        if(TextUtils.isEmpty(price.getText().toString()))
            showToast(getString(R.string.doctor_ffgl_info_prompt));
        else
            NetworkAPI.getNetworkAPI().docServiceMagSet(getIntent().getStringExtra("id"), price.getText().toString(), isSwitch.isChecked()?"1":"0", showProgressDialog(), this);
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
        if(getIntent().getStringExtra("id").equals("1")){
            title.setText(getString(R.string.doctor_ffgl_zxzx));
        }else if(getIntent().getStringExtra("id").equals("2")){
            title.setText(getString(R.string.doctor_ffgl_dhzx));
        }else if(getIntent().getStringExtra("id").equals("3")){
            title.setText(getString(R.string.doctor_ffgl_yyzx));
        }
        isSwitch.setChecked(getIntent().getBooleanExtra("isSwitch", false));
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_SERVICE_MAG_SET.equals(requestAction)){
            getApp().getIsSwith().setText(isSwitch.isChecked() ? R.string.doctor_ffgl_add : R.string.doctor_ffgl_no_add);
            if(getIntent().getStringExtra("id").equals("1")){
                getApp().getServiceMag().getA1().setIsopen(isSwitch.isChecked() ? "1" : "0");
            }else if(getIntent().getStringExtra("id").equals("2")){
                getApp().getServiceMag().getA2().setIsopen(isSwitch.isChecked() ? "1" : "0");
            }else if(getIntent().getStringExtra("id").equals("3")){
                getApp().getServiceMag().getA3().setIsopen(isSwitch.isChecked() ? "1" : "0");
            }
            finish();
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
