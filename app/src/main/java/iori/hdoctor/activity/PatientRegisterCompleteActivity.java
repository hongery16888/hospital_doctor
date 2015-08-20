package iori.hdoctor.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.view.CircleBitmapDisplayer;
import iori.hdoctor.view.crop.CropHelper;
import iori.hdoctor.view.crop.CropParams;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientRegisterCompleteActivity extends BasePhotoCropActivity implements NetworkConnectListener {

    private CropParams mCropParams = new CropParams(HDoctorCode.HEAD_PATH);
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    private boolean imgFlag = false;
    private PopupWindow mPhotoPopWindow;

    @InjectView(R.id.nicheng)
    EditText nicheng;
    @InjectView(R.id.sex)
    EditText sex;
    @InjectView(R.id.age)
    EditText age;
    @InjectView(R.id.adds)
    EditText adds;
    @InjectView(R.id.head)
    ImageView head;

    @OnClick(R.id.next_step)
    public void nextStep() {
        if (imgFlag)
            if (TextUtils.isEmpty(nicheng.getText().toString()) ||
                    TextUtils.isEmpty(sex.getText().toString()) ||
                    TextUtils.isEmpty(age.getText().toString()) ||
                    TextUtils.isEmpty(adds.getText().toString())) {
                showToast("填写信息不能为空");
            } else if(!sex.getText().toString().equals("男") || !sex.getText().toString().equals("女")){
                showToast("性别只能是男或女");
            } else
                NetworkAPI.getNetworkAPI().patregister(nicheng.getText().toString(), sex.getText().toString(),
                        age.getText().toString(), adds.getText().toString(), showProgressDialog(), this);
        else
            showToast("请上传头像");
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
        return R.layout.patient_register_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_register_title_main));
        setRightText(getResources().getString(R.string.login), loginClickListener);
    }

    @Override
    protected void initData() {
        options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false)  // default
                .cacheInMemory(false) // default
                .cacheOnDisk(false) // default
                .considerExifParams(false) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .displayer(new CircleBitmapDisplayer()) // default
                .build();
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_REGISTER_INFO.equals(requestAction)) {
            startActivity(new Intent(this, PatientMainActivity.class));
            finish();
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
        imgFlag = true;
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
