package iori.hdoctor.activity;

import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.DoctorServiceMagResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorFFGLActivity extends BaseActivity implements NetworkConnectListener{

    @InjectView(R.id.zx_status) TextView zxStatus;
    @InjectView(R.id.dh_status) TextView dhStatus;
    @InjectView(R.id.yy_status) TextView yyStatus;

    @OnClick(R.id.zxzx)
    public void zxzx(){
        Intent intent = new Intent(DoctorFFGLActivity.this, DoctorFFGLInfoActivity.class);
        intent.putExtra("id", "1");
        intent.putExtra("isSwitch", getApp().getServiceMag().getA1().getIsopen().endsWith("1")?true:false);
        startActivity(intent);
        getApp().setIsSwith(zxStatus);
    }

    @OnClick(R.id.dhzx)
    public void dhzx(){
        Intent intent = new Intent(DoctorFFGLActivity.this, DoctorFFGLInfoActivity.class);
        intent.putExtra("id", "2");
        intent.putExtra("isSwitch", getApp().getServiceMag().getA2().getIsopen().endsWith("1")?true:false);
        startActivity(intent);
        getApp().setIsSwith(dhStatus);
    }

    @OnClick(R.id.yyzx)
    public void yyzx(){
        Intent intent = new Intent(DoctorFFGLActivity.this, DoctorFFGLInfoActivity.class);
        intent.putExtra("id", "3");
        intent.putExtra("isSwitch", getApp().getServiceMag().getA3().getIsopen().endsWith("1")?true:false);
        startActivity(intent);
        getApp().setIsSwith(yyStatus);
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
            getApp().setServiceMag((DoctorServiceMagResponse) data);
            if(((DoctorServiceMagResponse) data).getA1().getIsopen().equals("1")){
                zxStatus.setText(getString(R.string.doctor_ffgl_add));
            }else if(((DoctorServiceMagResponse) data).getA2().getIsopen().equals("1")){
                dhStatus.setText(getString(R.string.doctor_ffgl_add));
            }else if(((DoctorServiceMagResponse) data).getA3().getIsopen().equals("1")){
                yyStatus.setText(getString(R.string.doctor_ffgl_add));
            }
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }


}
