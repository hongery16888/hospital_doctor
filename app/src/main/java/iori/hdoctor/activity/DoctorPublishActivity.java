package iori.hdoctor.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
import iori.hdoctor.view.crop.CropHelper;
import iori.hdoctor.view.crop.CropParams;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorPublishActivity extends BasePhotoCropActivity implements NetworkConnectListener {

    private CropParams mCropParams = new CropParams(HDoctorCode.HEAD_PATH);
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    private PopupWindow mPhotoPopWindow;
    private boolean flag;

    @InjectView(R.id.content)
    TextView content;
    @InjectView(R.id.checkbox)
    CheckBox checkBox;
    @InjectView(R.id.img)
    ImageView img;

    @OnClick(R.id.img)
    public void setimg() {
        getPhotoPopWindowInstance();
        mPhotoPopWindow.setFocusable(true);
        mPhotoPopWindow.showAtLocation(this
                .findViewById(R.id.persion_main), Gravity.BOTTOM | Gravity
                .CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_publish_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.publish_title));
        setRightText(getString(R.string.publish), publishListener);
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
    }

    public View.OnClickListener publishListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(content.getText().toString()))
                showToast(getString(R.string.publish_no_empty));
            else {
                if (flag)
                    NetworkAPI.getNetworkAPI().docpublish(content.getText().toString(), checkBox.isChecked() ? "1" : "0", showProgressDialog(), DoctorPublishActivity.this);
                else
                    NetworkAPI.getNetworkAPI().docpublishnoimg(content.getText().toString(), checkBox.isChecked() ? "1" : "0", showProgressDialog(), DoctorPublishActivity.this);
            }
        }
    };

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_PUBLISH.equals(requestAction)) {
            showToast(getString(R.string.publish_success));
            finish();
            if (getApp().getCircleRefreshHandler() != null)
                getApp().getCircleRefreshHandler().sendEmptyMessage(0);
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
