package iori.hdoctor.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.activity.base.HDoctorCode;
import iori.hdoctor.app.MyApp;
import iori.hdoctor.net.DataTransfer;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.DoctorLoginResponse;
import iori.hdoctor.net.response.PatientRegisterPhoneResponse;
import iori.hdoctor.view.CircleBitmapDisplayer;
import iori.hdoctor.view.crop.CropHelper;
import iori.hdoctor.view.crop.CropParams;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorRegisterCompleteActivity extends BasePhotoCropActivity implements NetworkConnectListener {

    private CropParams mCropParams;
    private String isShenfenFlag = "shenfen";
    private String isZigeFlag = "zige";
    private String isGongzuoFlag = "gongzuo";
    private String isChoice ;
    private boolean shenfenFlag, zigeFlag, gongzuoFlag;

    private PopupWindow mPhotoPopWindow;

    @InjectView(R.id.shenfen_img)
    ImageView shenfenImg;
    @InjectView(R.id.zige_img)
    ImageView zigeImg;
    @InjectView(R.id.gongzuo_img)
    ImageView gongzuoImg;

    @OnClick(R.id.shenfen)
    public void shenfen(){
        mCropParams = new CropParams(HDoctorCode.SHENFEN_PATH);
        mCropParams.crop = "false";
        isChoice = isShenfenFlag;
        showPopWindow();
    }

    @OnClick(R.id.zige)
    public void zige(){
        mCropParams = new CropParams(HDoctorCode.ZIGE_PATH);
        isChoice = isZigeFlag;
        mCropParams.scale = false;
        showPopWindow();
    }

    @OnClick(R.id.gongzuo)
    public void gongzuo(){
        mCropParams = new CropParams(HDoctorCode.GONGZUO_PATH);
        isChoice = isGongzuoFlag;
        showPopWindow();
    }

    private void showPopWindow(){
        getPhotoPopWindowInstance();
        mPhotoPopWindow.setFocusable(true);
        mPhotoPopWindow.showAtLocation(this
                .findViewById(R.id.persion_main), Gravity.BOTTOM | Gravity
                .CENTER_HORIZONTAL, 0, 0);
    }

    @OnClick(R.id.next_step)
    public void nextStep() {
        if (!shenfenFlag){
            showToast(getString(R.string.shenfen_prompt));
        }else if(!zigeFlag){
            showToast(getString(R.string.zige_prompt));
        }else if(!gongzuoFlag){
            showToast(getString(R.string.gongzuo_prompt));
        }else{
            NetworkAPI.getNetworkAPI().registerInfo2(showProgressDialog(), this);
        }

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

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_REGISTER_INFO_2.equals(requestAction)) {
            DataTransfer.setUid(((DoctorLoginResponse) data).getUid());
            getApp().setRongToken(((DoctorLoginResponse) data).getLiaotiantoken());
            getApp().setUser((DoctorLoginResponse)data);
            startActivity(new Intent(DoctorRegisterCompleteActivity.this, DoctorMainActivity.class));
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }

    private View.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private void getPhotoPopWindowInstance() {
        if (null != mPhotoPopWindow) {
            mPhotoPopWindow.dismiss();
            return;
        } else {
            initPhotoPopWindow();
        }
    }

    private void initPhotoPopWindow() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View popupWindow = layoutInflater.inflate(R.layout.photo_pop_main, null);
        mPhotoPopWindow = new PopupWindow(popupWindow, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPhotoPopWindow.getContentView().measure(0, 0);
        mPhotoPopWindow.setAnimationStyle(R.style.mypopwindow_anim_style);

        popupWindow.findViewById(R.id.photo_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoPopWindow.dismiss();
            }
        });

        popupWindow.findViewById(R.id.open_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropHelper.clearCachedCropFile(mCropParams.uri);
                startActivityForResult(CropHelper.buildCropFromGalleryIntent(mCropParams), CropHelper.REQUEST_CROP);
                mPhotoPopWindow.dismiss();
            }
        });

        popupWindow.findViewById(R.id.open_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CropHelper.buildCaptureIntent(mCropParams.uri);
                startActivityForResult(intent, CropHelper.REQUEST_CAMERA);
                mPhotoPopWindow.dismiss();
            }
        });

        popupWindow.findViewById(R.id.outside).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoPopWindow.dismiss();
            }
        });
    }

    @Override
    public CropParams getCropParams() {
        return mCropParams;
    }

    @Override
    public void onPhotoCropped(Uri uri) {
//        Toast.makeText(this, "Photo Url : " + MyApp.PHOTO_BASIC_PATH + uri.getPath(), Toast.LENGTH_LONG).show();
        if (isChoice.equals(isShenfenFlag)){
            shenfenFlag = true;
            shenfenImg.setVisibility(View.VISIBLE);
        }else if(isChoice.equals(isZigeFlag)){
            zigeFlag = true;
            zigeImg.setVisibility(View.VISIBLE);
        }else if(isChoice.equals(isGongzuoFlag)){
            gongzuoFlag = true;
            gongzuoImg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCropCancel() {
        Toast.makeText(this, "Crop canceled!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCropFailed(String message) {
        Toast.makeText(this, "Crop failed:" + message, Toast.LENGTH_LONG).show();
    }
}
