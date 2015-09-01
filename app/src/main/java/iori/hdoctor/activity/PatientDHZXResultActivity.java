package iori.hdoctor.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.PatientDHZXResultResponse;
import iori.hdoctor.net.response.PatientYSJSResponse;
import iori.hdoctor.view.CircleBitmapDisplayer;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientDHZXResultActivity extends BaseActivity implements NetworkConnectListener{

    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

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
    @InjectView(R.id.phone)
    TextView phone;

    @OnClick(R.id.phone)
    public void callPhone(){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone.getText().toString()));
        startActivity(intent);
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_dhzx_result_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction("医生介绍");
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
        NetworkAPI.getNetworkAPI().doctel(getIntent().getStringExtra("did"), getIntent().getStringExtra("orderid"), showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_DOC_TEL.equals(requestAction)) {
            imageLoader.displayImage(HttpRequest.PHOTO_PATH + ((PatientDHZXResultResponse) data).getImg(), head, options);
            name.setText(((PatientDHZXResultResponse) data).getRealname());
            adds.setText(((PatientDHZXResultResponse) data).getHospital());
            zili.setText(((PatientDHZXResultResponse) data).getZhicheng());
            status.setText(((PatientDHZXResultResponse) data).getStatus());
            phone.setText(((PatientDHZXResultResponse) data).getTel());
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
