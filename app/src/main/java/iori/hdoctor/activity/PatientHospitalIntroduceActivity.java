package iori.hdoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.PatientHospitalIntroResponse;
import iori.hdoctor.view.CircleBitmapDisplayer;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientHospitalIntroduceActivity extends BaseActivity implements NetworkConnectListener {

    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    @InjectView(R.id.head)
    ImageView head;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.type)
    TextView type;
    @InjectView(R.id.adds)
    TextView adds;
    @InjectView(R.id.introduce)
    TextView introduce;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_hospital_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction("医院介绍");
    }

    @Override
    protected void initData() {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.patient_doctor_introduce_avater)
                .showImageOnFail(R.drawable.patient_doctor_introduce_avater)
                .showImageForEmptyUri(R.drawable.patient_doctor_introduce_avater)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new CircleBitmapDisplayer())
                .build();
//        doctorIntroduce.setText(getString(R.string.patient_doctor_info_introduce_ex));
//        doctorSkill.setText(getString(R.string.patient_doctor_info_sc_ex));
        getApp().setPatDocInfoActivity(this);
        NetworkAPI.getNetworkAPI().pathospital(getIntent().getStringExtra("hospitalid"), showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_HOSPITAL.equals(requestAction)) {
            imageLoader.displayImage(HttpRequest.PHOTO_PATH + ((PatientHospitalIntroResponse) data).getImg(), head, options);
            name.setText(((PatientHospitalIntroResponse) data).getName());
            adds.setText(((PatientHospitalIntroResponse) data).getAddress());
            type.setText(((PatientHospitalIntroResponse) data).getLevel());
            introduce.setText(((PatientHospitalIntroResponse) data).getContent());
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
