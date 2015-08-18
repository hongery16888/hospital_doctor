package iori.hdoctor.activity.pager;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import iori.hdoctor.R;
import iori.hdoctor.activity.PatientMainActivity;
import iori.hdoctor.activity.PatientWDDDActivity;
import iori.hdoctor.activity.PatientWDFBActivity;
import iori.hdoctor.activity.PatientWDSCActivity;
import iori.hdoctor.activity.PatientWDYYActivity;
import iori.hdoctor.activity.PatientWDZLActivity;
import iori.hdoctor.activity.PatientZHZXActivity;
import iori.hdoctor.activity.base.BasePager;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.PatientGRZXResponse;
import iori.hdoctor.view.CircleBitmapDisplayer;

/**
 * Created by Administrator on 2015/7/10.
 */
public class PatientMinePager extends BasePager implements View.OnClickListener, NetworkConnectListener{

    private View view;
    private Context context;
    private boolean isInit = false;
    private ImageView head;
    private TextView name;
    private TextView orderNum;
    private TextView yuyueNum;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkAPI.getNetworkAPI().patpersonal(null, PatientMinePager.this);
        }
    };

    public PatientMinePager(Context ct) {
        super(ct);
        context = ct;
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.patient_pager_mine, null);
        return view;
    }

    @Override
    public void initData() {
        view.findViewById(R.id.wddd).setOnClickListener(this);
        view.findViewById(R.id.wdyy).setOnClickListener(this);
        view.findViewById(R.id.wdzl).setOnClickListener(this);
        view.findViewById(R.id.zhzx).setOnClickListener(this);
        view.findViewById(R.id.wdsc).setOnClickListener(this);
        view.findViewById(R.id.wdfb).setOnClickListener(this);
        head = (ImageView)view.findViewById(R.id.head);
        name = (TextView)view.findViewById(R.id.name);
        orderNum = (TextView)view.findViewById(R.id.order_num);
        yuyueNum = (TextView)view.findViewById(R.id.yuyue_num);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_avater_register)
                .showImageForEmptyUri(R.drawable.img_avater_register)
                .showImageOnFail(R.drawable.img_avater_register)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new CircleBitmapDisplayer())
                .build();
        ((PatientMainActivity)context).getApp().setRefreshData(handler);
        NetworkAPI.getNetworkAPI().patpersonal(null, PatientMinePager.this);
    }

    @Override
    public void onResume() {
        if (!isInit) {
            initData();
            isInit = !isInit;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wddd:
                context.startActivity(new Intent(context, PatientWDDDActivity.class));
                break;
            case R.id.wdyy:
                context.startActivity(new Intent(context, PatientWDYYActivity.class));
                break;
            case R.id.wdzl:
                context.startActivity(new Intent(context, PatientWDZLActivity.class));
                break;
            case R.id.wdsc:
                context.startActivity(new Intent(context, PatientWDSCActivity.class));
                break;
            case R.id.wdfb:
                context.startActivity(new Intent(context, PatientWDFBActivity.class));
                break;
            case R.id.zhzx:
                context.startActivity(new Intent(context, PatientZHZXActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_PERSONAL.equals(requestAction)){
            imageLoader.displayImage(HttpRequest.PHOTO_PATH + ((PatientGRZXResponse)data).getImg(), head, options);
            name.setText(((PatientGRZXResponse) data).getNicheng());
            orderNum.setText(((PatientGRZXResponse) data).getOrdernum());
            yuyueNum.setText(((PatientGRZXResponse)data).getYuyuenum());
        }
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {

    }
}
