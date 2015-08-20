package iori.hdoctor.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.HDoctorCode;
import iori.hdoctor.app.MyApp;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.PatientInfoResponse;
import iori.hdoctor.view.CircleBitmapDisplayer;
import iori.hdoctor.view.crop.CropHelper;
import iori.hdoctor.view.crop.CropParams;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientWDZLActivity extends BasePhotoCropActivity implements NetworkConnectListener {

    private CropParams mCropParams = new CropParams(HDoctorCode.HEAD_PATH);
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    private PopupWindow mPhotoPopWindow;
    private boolean flag;
    private boolean modify;

    @InjectView(R.id.head)
    ImageView head;
    @InjectView(R.id.name)
    EditText name;
    @InjectView(R.id.sex)
    EditText sex;
    @InjectView(R.id.age)
    EditText age;
    @InjectView(R.id.adds)
    EditText adds;

    @OnClick(R.id.confirm)
    public void confirm() {
        modify = true;
        if (TextUtils.isEmpty(name.getText().toString()) ||
                TextUtils.isEmpty(sex.getText().toString()) ||
                TextUtils.isEmpty(age.getText().toString()) ||
                TextUtils.isEmpty(adds.getText().toString()))
            showToast("修改信息不能为空");
        else if (flag) {
            NetworkAPI.getNetworkAPI().patinfoimg(name.getText().toString(),
                    sex.getText().toString(),
                    age.getText().toString(),
                    adds.getText().toString(),
                    showProgressDialog(),this);
        } else {
            NetworkAPI.getNetworkAPI().patinfonoimg(name.getText().toString(),
                    sex.getText().toString(),
                    age.getText().toString(),
                    adds.getText().toString(),
                    showProgressDialog(),this);
        }
    }

    @OnClick(R.id.head_img)
    public void setHead() {
        ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        getPhotoPopWindowInstance();
        mPhotoPopWindow.showAtLocation(this
                .findViewById(R.id.persion_main), Gravity.BOTTOM | Gravity
                .CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_personal_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_personal_info_title_main));
    }

    @Override
    protected void initData() {
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_avater_grzx)
                .showImageForEmptyUri(R.drawable.img_avater_grzx)
                .showImageOnFail(R.drawable.img_avater_grzx)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .displayer(new CircleBitmapDisplayer()) // default
                .build();
        NetworkAPI.getNetworkAPI().patinfo(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_INFO.equals(requestAction)) {
            if (!modify) {
                name.setText(((PatientInfoResponse)data).getNicheng());
                sex.setText(((PatientInfoResponse)data).getSex());
                age.setText(((PatientInfoResponse)data).getAge());
                adds.setText(((PatientInfoResponse)data).getAddress());
                imageLoader.displayImage(HttpRequest.PHOTO_PATH + ((PatientInfoResponse)data).getImg() , head, options);
            }else{
                getApp().getRefreshData().sendEmptyMessage(0);
                finish();
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
        flag = true;
        imageLoader.displayImage(MyApp.PHOTO_BASIC_PATH + uri.getPath(), head, options);
    }

    @Override
    public void onCropCancel() {
//        Toast.makeText(this, "Crop canceled!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCropFailed(String message) {
//        Toast.makeText(this, "Crop failed:" + message, Toast.LENGTH_LONG).show();
    }
}
