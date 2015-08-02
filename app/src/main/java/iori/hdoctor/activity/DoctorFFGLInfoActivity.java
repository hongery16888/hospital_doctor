package iori.hdoctor.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorFFGLInfoActivity extends BaseActivity implements NetworkConnectListener {

    @InjectView(R.id.price)
    EditText price;

    @OnClick(R.id.next_step)
    public void nextStep() {
        if(TextUtils.isEmpty(price.getText().toString()))
            showToast(getString(R.string.doctor_ffgl_info_prompt));
        else
            NetworkAPI.getNetworkAPI().docServiceMagSet("1", price.getText().toString(), "1", showProgressDialog(), this);
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

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_SERVICE_MAG_SET.equals(requestAction)){

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
