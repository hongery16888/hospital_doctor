package iori.hdoctor.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.activity.base.HDoctorCode;
import iori.hdoctor.app.MyApp;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.PatientYYResponse;
import iori.hdoctor.util.DateUtil;
import iori.hdoctor.view.DatePickerNew;
import iori.hdoctor.view.ScrollerNumberPicker;
import iori.hdoctor.view.crop.CropHelper;
import iori.hdoctor.view.crop.CropParams;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientYYActivity extends BasePhotoCropActivity implements NetworkConnectListener {

    private boolean isConfirm;
    private boolean hasImg;
    private PopupWindow mDatePopWindow;
    private DatePickerNew datePicker;
    private ScrollerNumberPicker timePicker;
    private String timeTxt = "0:00";

    private PopupWindow mTimePopWindow;
    private ScrollerNumberPicker txTimePicker;
    private String txTimeTxt = "8:00";

    private CropParams mCropParams = new CropParams(HDoctorCode.HEAD_PATH);
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    private PopupWindow mPhotoPopWindow;

    private ColorDrawable dw = new ColorDrawable(0xb0000000);

    @InjectView(R.id.content)
    TextView content;
    @InjectView(R.id.checkbox)
    CheckBox checkBox;
    @InjectView(R.id.img)
    ImageView img;
    @InjectView(R.id.hospital)
    TextView hospital;
    @InjectView(R.id.doctor)
    TextView doctor;
    @InjectView(R.id.time)
    TextView time;
    @InjectView(R.id.tixingtime)
    TextView tixingtime;
    @InjectView(R.id.tx_time)
    View txTimeLay;

    @OnClick(R.id.time_lay)
    public void datePop(View v) {
        getDatePopWindowInstance();
        mDatePopWindow.setFocusable(true);
        mDatePopWindow.showAtLocation(PatientYYActivity.this
                .findViewById(R.id.persion_main), Gravity.BOTTOM | Gravity
                .CENTER_HORIZONTAL, 0, 0);
    }

    @OnClick(R.id.tx_time)
    public void txTime(View v) {
        getTimePopWindowInstance();
        mTimePopWindow.setFocusable(true);
        mTimePopWindow.showAtLocation(PatientYYActivity.this
                .findViewById(R.id.persion_main), Gravity.BOTTOM | Gravity
                .CENTER_HORIZONTAL, 0, 0);
    }

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

        if (!TextUtils.isEmpty(content.getText().toString()) && !TextUtils.isEmpty(time.getText().toString())) {
            isConfirm = true;
            if (hasImg)
                NetworkAPI.getNetworkAPI().patyuyueimg(getIntent().getStringExtra("did"),
                        content.getText().toString(),
                        time.getText().toString(),
                        checkBox.isChecked() ? "1" : "0",
                        tixingtime.getText().toString(),
                        showProgressDialog(), this);
            else
                NetworkAPI.getNetworkAPI().patyuyuenoimg(getIntent().getStringExtra("did"),
                        content.getText().toString(),
                        time.getText().toString(),
                        checkBox.isChecked() ? "1" : "0",
                        tixingtime.getText().toString(),
                        showProgressDialog(), this);
        } else {
            showToast("病情简述或时间不能为空");
        }
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_yy_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction("预约");
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

        NetworkAPI.getNetworkAPI().patyuyue(getIntent().getStringExtra("did"), showProgressDialog(), this);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    txTimeLay.setVisibility(View.VISIBLE);
                else
                    txTimeLay.setVisibility(View.GONE);
            }
        });

        tixingtime.setText(txTimeTxt);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_YUYUE.equals(requestAction)) {
            if (isConfirm) {
                getApp().getPatDocInfoActivity().finish();
                finish();
            } else {
                hospital.setText(((PatientYYResponse) data).getHospital());
                doctor.setText(((PatientYYResponse) data).getRealname());
            }
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
        finish();
    }

    private void getDatePopWindowInstance() {
        if (null != mDatePopWindow) {
            mDatePopWindow.dismiss();
            return;
        } else {
            initDatePopWindow();
        }
    }

    private void initDatePopWindow() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View popupWindow = layoutInflater.inflate(R.layout.date_pop_main, null);
        mDatePopWindow = new PopupWindow(popupWindow, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mDatePopWindow.getContentView().measure(0, 0);
        mDatePopWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        //设置SelectPicPopupWindow弹出窗体的背景
        mDatePopWindow.setBackgroundDrawable(dw);

        datePicker = (DatePickerNew) popupWindow.findViewById(R.id.datePicker);
        timePicker = (ScrollerNumberPicker) popupWindow.findViewById(R.id.timePicker);

        timePicker.setData(DateUtil.getAllDayTime());
        timePicker.setDefault(0);

        timePicker.setOnSelectListener(new ScrollerNumberPicker.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                timeTxt = text;
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

        popupWindow.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePopWindow.dismiss();
            }
        });

        popupWindow.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setText(datePicker.getYear() + "-" + datePicker.getMonth() + "-" +
                        datePicker.getDay() + " " + timeTxt);
                mDatePopWindow.dismiss();
            }
        });

        popupWindow.findViewById(R.id.outside).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePopWindow.dismiss();
            }
        });
    }

    private void getTimePopWindowInstance() {
        if (null != mTimePopWindow) {
            mTimePopWindow.dismiss();
            return;
        } else {
            initTimePopWindow();
        }
    }

    private void initTimePopWindow() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View popupWindow = layoutInflater.inflate(R.layout.date_pop_main, null);
        mTimePopWindow = new PopupWindow(popupWindow, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTimePopWindow.getContentView().measure(0, 0);
        mTimePopWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        //设置SelectPicPopupWindow弹出窗体的背景
        mTimePopWindow.setBackgroundDrawable(dw);

        datePicker = (DatePickerNew) popupWindow.findViewById(R.id.datePicker);
        timePicker = (ScrollerNumberPicker) popupWindow.findViewById(R.id.timePicker);

        timePicker.setData(DateUtil.getAllDayTime());
        timePicker.setDefault(0);

        txTimePicker.setOnSelectListener(new ScrollerNumberPicker.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                txTimeTxt = text;
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

        popupWindow.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePopWindow.dismiss();
            }
        });

        popupWindow.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tixingtime.setText(datePicker.getYear() + "-" + datePicker.getMonth() + "-" +
                        datePicker.getDay() + " " + txTimeTxt);
                mTimePopWindow.dismiss();
            }
        });

        popupWindow.findViewById(R.id.outside).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePopWindow.dismiss();
            }
        });
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
