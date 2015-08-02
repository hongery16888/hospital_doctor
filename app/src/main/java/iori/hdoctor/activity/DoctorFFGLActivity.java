package iori.hdoctor.activity;

import android.content.Intent;
import android.widget.LinearLayout;

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
public class DoctorFFGLActivity extends BaseActivity implements NetworkConnectListener{

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
        NetworkAPI.getNetworkAPI().docServiceMag(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_SERVICE_MAG.equals(requestAction)){

        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
