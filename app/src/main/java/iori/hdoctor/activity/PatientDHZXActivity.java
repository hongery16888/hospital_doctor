package iori.hdoctor.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.HDoctorCode;
import iori.hdoctor.app.MyApp;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.PatientDHZXResponse;
import iori.hdoctor.net.response.PatientZXZXResponse;
import iori.hdoctor.view.crop.CropHelper;
import iori.hdoctor.view.crop.CropParams;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientDHZXActivity extends BasePhotoCropActivity implements NetworkConnectListener {

    private CropParams mCropParams = new CropParams(HDoctorCode.HEAD_PATH);
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    private PopupWindow mPhotoPopWindow;
    private ColorDrawable dw = new ColorDrawable(0xb0000000);

    private boolean isConfirm;
    private boolean hasImg;

    @InjectView(R.id.content)
    TextView content;
    @InjectView(R.id.img)
    ImageView img;
    @InjectView(R.id.hospital)
    TextView hospital;
    @InjectView(R.id.doctor)
    TextView doctor;

    @OnClick(R.id.img)
    public void setimg() {
        getPhotoPopWindowInstance();
        mPhotoPopWindow.setFocusable(true);
        mPhotoPopWindow.showAtLocation(this
                .findViewById(R.id.persion_main), Gravity.BOTTOM | Gravity
                .CENTER_HORIZONTAL, 0, 0);
    }

    @OnClick(R.id.confirm)
    public void confirm() {

        if (!TextUtils.isEmpty(content.getText().toString())) {
            isConfirm = true;
            if (hasImg)
                NetworkAPI.getNetworkAPI().patdhzximg(getIntent().getStringExtra("did"),
                        content.getText().toString(),
                        showProgressDialog(), this);
            else
                NetworkAPI.getNetworkAPI().patdhzxnoimg(getIntent().getStringExtra("did"),
                        content.getText().toString(),
                        showProgressDialog(), this);
        } else {
            showToast("病情简述不能为空");
        }
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zxzx_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction("电话咨询");
    }

    @Override
    protected void initData() {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.icon_camera)
                .showImageForEmptyUri(R.drawable.icon_camera)
                .showImageOnFail(R.drawable.icon_camera)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        NetworkAPI.getNetworkAPI().patdhzx(getIntent().getStringExtra("did"), showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_ONLINE_TEL.equals(requestAction)){
            if (isConfirm) {
                getApp().getPatDocInfoActivity().finish();
                finish();
            } else {
                hospital.setText(((PatientDHZXResponse) data).getHospital());
                doctor.setText(((PatientDHZXResponse) data).getRealname());
            }
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }

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
        hasImg = true;
        imageLoader.displayImage(MyApp.PHOTO_BASIC_PATH + uri.getPath(), img, options);
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
