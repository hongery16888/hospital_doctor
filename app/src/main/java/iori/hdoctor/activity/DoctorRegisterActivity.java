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

import org.w3c.dom.Text;

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
import iori.hdoctor.net.response.DoctorRegisterResponse;
import iori.hdoctor.view.CircleBitmapDisplayer;
import iori.hdoctor.view.crop.CropHelper;
import iori.hdoctor.view.crop.CropParams;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorRegisterActivity extends BasePhotoCropActivity implements NetworkConnectListener {

    private CropParams mCropParams = new CropParams(HDoctorCode.HEAD_PATH);
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private boolean imgFlag = false;

    @OnClick(R.id.next_step)
    public void nextStep() {
        if (TextUtils.isEmpty(name.getText().toString()) ||
                TextUtils.isEmpty(hospital.getText().toString()) ||
                TextUtils.isEmpty(keshi.getText().toString()) ||
                TextUtils.isEmpty(tel.getText().toString())) {
            showToast(getString(R.string.register_info_empty));
        } else
            NetworkAPI.getNetworkAPI().registerInfo(name.getText().toString(),
                    hospital.getText().toString(),
                    keshi.getText().toString(),
                    tel.getText().toString(),
                    showProgressDialog(),
                    this);
    }

    @OnClick(R.id.head)
    public void setHead() {
        Intent intent = CropHelper.buildCaptureIntent(mCropParams.uri);
        startActivityForResult(intent, CropHelper.REQUEST_CAMERA);
    }

    @InjectView(R.id.name)
    EditText name;
    @InjectView(R.id.hospital)
    EditText hospital;
    @InjectView(R.id.keshi)
    EditText keshi;
    @InjectView(R.id.tel)
    EditText tel;
    @InjectView(R.id.head)
    ImageView head;

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_register_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_register_title_main));
        setRightText(getResources().getString(R.string.login), loginClickListener);
    }

    @Override
    protected void initData() {
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(1000)
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
        if (HttpRequest.DOC_REGISTER_INFO.equals(requestAction)) {
            startActivity(new Intent(DoctorRegisterActivity.this, DoctorRegisterCompleteActivity.class));
            dismissProgressDialog();
        }
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
