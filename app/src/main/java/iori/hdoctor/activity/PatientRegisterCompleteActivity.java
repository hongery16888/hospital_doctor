package iori.hdoctor.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
            } else
                NetworkAPI.getNetworkAPI().patregister(nicheng.getText().toString(), sex.getText().toString(),
                        age.getText().toString(), adds.getText().toString(), showProgressDialog(), this);
        else
            showToast("请上传头像");
    }

    @OnClick(R.id.head_img)
    public void setHead() {
        Intent intent = CropHelper.buildCaptureIntent(mCropParams.uri);
        startActivityForResult(intent, CropHelper.REQUEST_CAMERA);
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

    @Override
    public CropParams getCropParams() {
        return mCropParams;
    }

    @Override
    public void onPhotoCropped(Uri uri) {
        imgFlag = true;
        Toast.makeText(this, "Photo Url : " + MyApp.PHOTO_BASIC_PATH + uri.getPath(), Toast.LENGTH_LONG).show();
        imageLoader.displayImage(MyApp.PHOTO_BASIC_PATH + uri.getPath(), head, options);
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
