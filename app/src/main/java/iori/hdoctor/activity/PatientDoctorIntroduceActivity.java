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
import iori.hdoctor.net.response.PatientYSJSResponse;
import iori.hdoctor.view.CircleBitmapDisplayer;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientDoctorIntroduceActivity extends BaseActivity implements NetworkConnectListener {

    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    @InjectView(R.id.doctor_introduce)
    ExpandableTextView doctorIntroduce;
    @InjectView(R.id.doctor_skill)
    ExpandableTextView doctorSkill;

    @InjectView(R.id.head)
    ImageView head;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.adds)
    TextView adds;
    @InjectView(R.id.zili)
    TextView zili;
    @InjectView(R.id.status)
    TextView status;
    @InjectView(R.id.yyzx_price)
    TextView yyzxPrice;
    @InjectView(R.id.zxzx_price)
    TextView zxzxPrice;
    @InjectView(R.id.dhzx_price)
    TextView dhzxPrice;
    @InjectView(R.id.yyzx)
    View yyzx;
    @InjectView(R.id.zxzx)
    View zxzx;
    @InjectView(R.id.dhzx)
    View dhzx;

    @OnClick(R.id.patient_yy)
    public void patientYY() {
        startActivity(new Intent(PatientDoctorIntroduceActivity.this, PatientDoctorYYActivity.class));
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_doctor_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_doctor_introduce_main));
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

        NetworkAPI.getNetworkAPI().docservitem(getIntent().getStringExtra("did"), showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_DOC_SERVITEM.equals(requestAction)) {
            imageLoader.displayImage(HttpRequest.PHOTO_PATH + ((PatientYSJSResponse) data).getDocintro().getImg(), head, options);
            name.setText(((PatientYSJSResponse) data).getDocintro().getRealname());
            adds.setText(((PatientYSJSResponse) data).getDocintro().getHospital());
            zili.setText(((PatientYSJSResponse) data).getDocintro().getZhicheng());
            status.setText(((PatientYSJSResponse) data).getDocintro().getStatus());

            if (((PatientYSJSResponse) data).getServitem() != null) {
                for (int i = 0; i < ((PatientYSJSResponse) data).getServitem().size(); i++) {
                    if (((PatientYSJSResponse) data).getServitem().get(i).getItemname().equals("在线咨询")) {
                        zxzxPrice.setText(((PatientYSJSResponse) data).getServitem().get(i).getPrice());
                        zxzx.setVisibility(View.VISIBLE);
                    } else if (((PatientYSJSResponse) data).getServitem().get(i).getItemname().equals("预约咨询")) {
                        yyzxPrice.setText(((PatientYSJSResponse) data).getServitem().get(i).getPrice());
                        yyzx.setVisibility(View.VISIBLE);
                    } else if (((PatientYSJSResponse) data).getServitem().get(i).getItemname().equals("电话咨询")) {
                        dhzxPrice.setText(((PatientYSJSResponse) data).getServitem().get(i).getPrice());
                        dhzx.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
