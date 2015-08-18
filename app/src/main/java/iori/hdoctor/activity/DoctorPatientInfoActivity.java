package iori.hdoctor.activity;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.DoctorBlglInfoResponse;
import iori.hdoctor.net.response.DoctorBlglPatientResponse;
import iori.hdoctor.view.CircleBitmapDisplayer;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorPatientInfoActivity extends BaseActivity implements NetworkConnectListener {

    private DisplayImageOptions optionsC;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    @InjectView(R.id.head) ImageView head;
    @InjectView(R.id.name) TextView name;
    @InjectView(R.id.age) TextView age;
    @InjectView(R.id.signature) TextView signature;
    @InjectView(R.id.adds) TextView address;

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction("");
    }

    @Override
    protected void initData() {
        optionsC = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false)  // default
                .showImageOnLoading(R.drawable.img_avater_register)
                .showImageForEmptyUri(R.drawable.img_avater_register)
                .showImageOnFail(R.drawable.img_avater_register)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .considerExifParams(true) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .displayer(new CircleBitmapDisplayer()) // default
                .build();
        NetworkAPI.getNetworkAPI().casespatient(getIntent().getStringExtra("bid"), showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_CASESPATIENT.equals(requestAction)) {
            imageLoader.displayImage(HttpRequest.PHOTO_PATH  + ((DoctorBlglPatientResponse)data).getImg(), head, optionsC);
            name.setText(((DoctorBlglPatientResponse) data).getNicheng());
            signature.setText(((DoctorBlglPatientResponse)data).getSignature());
            age.setText(((DoctorBlglPatientResponse)data).getAge());
            address.setText(((DoctorBlglPatientResponse)data).getAddress());
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
