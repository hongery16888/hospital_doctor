package iori.hdoctor.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.HashMap;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.activity.base.HDoctorCode;
import iori.hdoctor.app.MyApp;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.DoctorImgResponse;
import iori.hdoctor.net.response.DoctorUserInfoResponse;
import iori.hdoctor.view.CircleBitmapDisplayer;
import iori.hdoctor.view.crop.CropHelper;
import iori.hdoctor.view.crop.CropParams;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorGRZXActivity extends BasePhotoCropActivity implements NetworkConnectListener{

    private CropParams mCropParams = new CropParams(HDoctorCode.HEAD_PATH);
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private PopupWindow mPhotoPopWindow;
    private boolean modify;
    private String path;
    private HashMap<String, String> titles = new HashMap<>();

    @InjectView(R.id.name) TextView name;
    @InjectView(R.id.introduce) TextView introduce;
    @InjectView(R.id.skill) TextView skill;
    @InjectView(R.id.hospital) TextView hospital;
    @InjectView(R.id.keshi) TextView keshi;
    @InjectView(R.id.post) TextView post;
    @InjectView(R.id.shenfen) TextView shenfen;
    @InjectView(R.id.email) TextView email;

    @OnClick(R.id.head_img)
    public void setHead(){
        ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        getPhotoPopWindowInstance();
        mPhotoPopWindow.setFocusable(true);
        mPhotoPopWindow.showAtLocation(this
                .findViewById(R.id.persion_main), Gravity.BOTTOM | Gravity
                .CENTER_HORIZONTAL, 0, 0);
    }

    @OnClick({R.id.name_lay, R.id.introduce_lay, R.id.skill_lay, R.id.hospital_lay, R.id.keshi_lay, R.id.post_lay, R.id.email_lay})
    public void modify(LinearLayout view){
        Intent intent = new Intent(this, DoctorModifyActivity.class);
        intent.putExtra("title", titles.get(view.getTag().toString()));
        intent.putExtra("key", view.getTag().toString());
        getApp().setPersonalTv((TextView) view.getChildAt(1));
        startActivity(intent);
    }

    @InjectView(R.id.head)
    ImageView head;

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_personal_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_personal_title_main));
    }

    @Override
    protected void initData() {
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_avater_register)
                .cacheInMemory(false) // default
                .cacheOnDisk(false) // default
                .displayer(new CircleBitmapDisplayer()) // default
                .build();

        titles.put(findViewById(R.id.name_lay).getTag().toString(), "名称修改");
        titles.put(findViewById(R.id.introduce_lay).getTag().toString(), "简介修改");
        titles.put(findViewById(R.id.skill_lay).getTag().toString(), "擅长修改");
        titles.put(findViewById(R.id.hospital_lay).getTag().toString(), "医院修改");
        titles.put(findViewById(R.id.keshi_lay).getTag().toString(), "科室修改");
        titles.put(findViewById(R.id.post_lay).getTag().toString(), "职称修改");
        titles.put(findViewById(R.id.email_lay).getTag().toString(), "邮箱修改");

        NetworkAPI.getNetworkAPI().docinfo(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_INFO.equals(requestAction)){
            if (!modify) {
                name.setText(((DoctorUserInfoResponse) data).getRealname());
                introduce.setText(((DoctorUserInfoResponse) data).getJianjie());
                skill.setText(((DoctorUserInfoResponse) data).getShanchang());
                hospital.setText(((DoctorUserInfoResponse) data).getHospital());
                keshi.setText(((DoctorUserInfoResponse) data).getKeshi());
                post.setText(((DoctorUserInfoResponse) data).getZhicheng());
                email.setText(((DoctorUserInfoResponse) data).getEmail());
                imageLoader.displayImage(HttpRequest.PHOTO_PATH + ((DoctorUserInfoResponse) data).getImg(), head, options);
            }else{
                imageLoader.displayImage(HttpRequest.PHOTO_PATH + ((DoctorImgResponse) data).getImg(), head, options);
                showToast(MyApp.PHOTO_BASIC_PATH + path );
                Message msg = new Message();
                msg.obj = HttpRequest.PHOTO_PATH + ((DoctorImgResponse) data).getImg();
                getApp().getAvatarHandler().sendMessage(msg);
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
//        Toast.makeText(this, "Photo Url : " + MyApp.PHOTO_BASIC_PATH + uri.getPath(), Toast.LENGTH_LONG).show();
        modify = true;
        path = uri.getPath();
        NetworkAPI.getNetworkAPI().docImg(showProgressDialog(), this);
//
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
