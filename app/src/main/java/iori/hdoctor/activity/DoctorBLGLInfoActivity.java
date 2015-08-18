package iori.hdoctor.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.DoctorBlglInfoResponse;
import iori.hdoctor.view.CircleBitmapDisplayer;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorBLGLInfoActivity extends BaseActivity implements NetworkConnectListener{

    private DisplayImageOptions optionsC;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private String bid;
    
    @InjectView(R.id.head) ImageView head;
    @InjectView(R.id.photo) ImageView photo;
    @InjectView(R.id.name) TextView name;
    @InjectView(R.id.age) TextView age;
    @InjectView(R.id.sex) TextView sex;
    @InjectView(R.id.time) TextView time;
    @InjectView(R.id.bingli) TextView bingli;
    @InjectView(R.id.describe) TextView describe;
    @InjectView(R.id.price) TextView price;
    @InjectView(R.id.zixunname) TextView zixunname;

    @OnClick(R.id.doctor_info)
    public void info(){
        Intent intent = new Intent(this, DoctorPatientInfoActivity.class);
        intent.putExtra("bid", bid);
        startActivity(intent);
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_blgl_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_blgl_title_main));
    }

    @Override
    protected void initData() {
        optionsC = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false)  // default
                .showImageOnLoading(R.drawable.img_avater_blgl_head)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .considerExifParams(true) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .displayer(new CircleBitmapDisplayer()) // default
                .build();
        NetworkAPI.getNetworkAPI().casesmange(getIntent().getStringExtra("orderId"), showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if(HttpRequest.DOC_CASESMAG.equals(requestAction)){
            imageLoader.displayImage(HttpRequest.PHOTO_PATH + ((DoctorBlglInfoResponse)data).getImg(), head, optionsC);
            imageLoader.displayImage(HttpRequest.PHOTO_PATH + ((DoctorBlglInfoResponse)data).getPhoto(), photo, getApp().getOptions());
            age.setText(((DoctorBlglInfoResponse) data).getAge());
            sex.setText(((DoctorBlglInfoResponse)data).getSex());
            name.setText(((DoctorBlglInfoResponse)data).getNicheng());
            time.setText(((DoctorBlglInfoResponse)data).getCha());
            bingli.setText(((DoctorBlglInfoResponse)data).getBingli());
            describe.setText(((DoctorBlglInfoResponse)data).getDescribe());
            price.setText(((DoctorBlglInfoResponse)data).getPrice() + getString(R.string.blgl_item_reserve_price_unit));
            zixunname.setText(((DoctorBlglInfoResponse)data).getZixunname());
            bid = ((DoctorBlglInfoResponse)data).getBid();
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
