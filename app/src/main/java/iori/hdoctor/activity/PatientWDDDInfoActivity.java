package iori.hdoctor.activity;

import android.content.Intent;
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
import iori.hdoctor.net.response.PatientWDDDInfoResponse;
import iori.hdoctor.view.CircleBitmapDisplayer;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientWDDDInfoActivity extends BaseActivity implements NetworkConnectListener {

    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    @InjectView(R.id.head)
    ImageView head;
    @InjectView(R.id.hospital)
    TextView hospital;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.price)
    TextView price;
    @InjectView(R.id.price_2)
    TextView price2;
    @InjectView(R.id.status)
    TextView status;
    @InjectView(R.id.order_id)
    TextView orderId;
    @InjectView(R.id.time)
    TextView time;
    @InjectView(R.id.type)
    TextView type;
    @InjectView(R.id.type_2)
    TextView type2;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_wddd_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_wddd_info_title_main));
    }

    @Override
    protected void initData() {
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.img_fj_doctor)
                .showImageOnLoading(R.drawable.img_fj_doctor)
                .showImageOnFail(R.drawable.img_fj_doctor)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .displayer(new CircleBitmapDisplayer())
                .build();
        NetworkAPI.getNetworkAPI().orderdesc(getIntent().getStringExtra("orderId"), showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_ORDER_DESC.equals(requestAction)) {
            imageLoader.displayImage(HttpRequest.PHOTO_PATH + ((PatientWDDDInfoResponse) data).getImg(), head, options);
            hospital.setText(((PatientWDDDInfoResponse) data).getHospital());
            name.setText(((PatientWDDDInfoResponse) data).getRealname());
            price.setText(((PatientWDDDInfoResponse) data).getPrice() + "元");
            price2.setText(((PatientWDDDInfoResponse) data).getPrice() + "元");
            status.setText("已消费");
            orderId.setText(((PatientWDDDInfoResponse) data).getOrderid());
            time.setText(((PatientWDDDInfoResponse) data).getYuyuetime());
            type.setText(((PatientWDDDInfoResponse) data).getZixunname());
            type2.setText(((PatientWDDDInfoResponse) data).getZixunname());
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
