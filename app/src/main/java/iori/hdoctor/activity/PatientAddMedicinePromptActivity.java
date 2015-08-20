package iori.hdoctor.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
public class PatientAddMedicinePromptActivity extends BasePhotoCropActivity implements NetworkConnectListener {

    private CropParams mCropParams = new CropParams(HDoctorCode.HEAD_PATH);
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    private PopupWindow mPhotoPopWindow;
    private boolean flag;

    private String[] times;
    private String[] counts;
    private String[] cycles;

    @InjectView(R.id.time)
    TextView time;
    @InjectView(R.id.count)
    TextView count;
    @InjectView(R.id.cycle)
    TextView cycle;
    @InjectView(R.id.img)
    ImageView img;
    @InjectView(R.id.name)
    EditText name;
    @InjectView(R.id.beizhu)
    EditText beizhu;

    @OnClick(R.id.img)
    public void setHead() {
        ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        getPhotoPopWindowInstance();
        mPhotoPopWindow.setFocusable(true);
        mPhotoPopWindow.showAtLocation(this
                .findViewById(R.id.persion_main), Gravity.BOTTOM | Gravity
                .CENTER_HORIZONTAL, 0, 0);
    }

    @OnClick(R.id.time_lay)
    public void setTime() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(times,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        time.setText(times[which]);
                    }
                });
        builder.show();
    }

    @OnClick(R.id.count_lay)
    public void setCount() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(counts,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        count.setText(counts[which]);
                    }
                });
        builder.show();
    }

    @OnClick(R.id.cycle_lay)
    public void setCycle() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(cycles,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cycle.setText(cycles[which]);
                    }
                });
        builder.show();
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_add_medicine_prompt_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.patient_xztx_title_main));
        setRightText(getString(R.string.doctor_ffgl_info_save), saveListener);
    }

    @Override
    protected void initData() {
        times = getResources().getStringArray(R.array.time);
        counts = getResources().getStringArray(R.array.count);
        cycles = getResources().getStringArray(R.array.cycle);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_fj_hospital)
                .showImageOnFail(R.drawable.img_fj_hospital)
                .showImageForEmptyUri(R.drawable.img_fj_hospital)
                .cacheInMemory(false) // default
                .cacheOnDisk(false) // default
                .build();
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_ADD_REMIND.equals(requestAction)) {
            getApp().getRefreshListHandler().sendEmptyMessage(0);
            finish();
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }

    private View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (flag)
                if (!TextUtils.isEmpty(name.getText().toString())) {
                    NetworkAPI.getNetworkAPI().addremind(name.getText().toString(),
                            time.getText().toString(),
                            count.getText().toString(),
                            cycle.getText().toString(),
                            beizhu.getText().toString(),
                            showProgressDialog(), PatientAddMedicinePromptActivity.this);
                } else
                    showToast(getString(R.string.medicine_input));
            else
                showToast(getString(R.string.medicine_photo));
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
