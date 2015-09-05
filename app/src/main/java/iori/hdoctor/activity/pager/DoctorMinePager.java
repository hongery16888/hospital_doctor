package iori.hdoctor.activity.pager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import iori.hdoctor.R;
import iori.hdoctor.activity.DoctorGRZXActivity;
import iori.hdoctor.activity.DoctorMainActivity;
import iori.hdoctor.activity.DoctorMyFaBiaoActivity;
import iori.hdoctor.activity.DoctorMyShouCangActivity;
import iori.hdoctor.activity.DoctorSRXQActivity;
import iori.hdoctor.activity.DoctorSettingActivity;
import iori.hdoctor.activity.DoctorZHZXctivity;
import iori.hdoctor.activity.base.BasePager;
import iori.hdoctor.app.MyApp;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.DoctorPersonalResponse;
import iori.hdoctor.view.CircleBitmapDisplayer;

/**
 * Created by Administrator on 2015/7/10.
 */
public class DoctorMinePager extends BasePager implements View.OnClickListener, NetworkConnectListener{

    private View view;
    private Context context;
    private boolean isInit = false;
    private ImageView head;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private Handler avatarHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            imageLoader.displayImage(msg.obj.toString(), head, options);
        }
    };

    public DoctorMinePager(Context ct) {
        super(ct);
        context = ct;
    }

    @Override
    public void onResume() {
        if (!isInit) {
            initData();
            isInit = !isInit;
        }
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.doctor_pager_mine, null);
        return view;
    }

    @Override
    public void initData() {
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .considerExifParams(true) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .displayer(new CircleBitmapDisplayer()) // default
                .build();
        head = (ImageView)view.findViewById(R.id.head);
            view.findViewById(R.id.srxq).setOnClickListener(this);
        view.findViewById(R.id.zhzx).setOnClickListener(this);
        view.findViewById(R.id.szzx).setOnClickListener(this);
        view.findViewById(R.id.wdzl).setOnClickListener(this);
        view.findViewById(R.id.wdfb).setOnClickListener(this);
        view.findViewById(R.id.wdsc).setOnClickListener(this);

        ((DoctorMainActivity) context).getApp().setAvatarHandler(avatarHandler);

        NetworkAPI.getNetworkAPI().docpersonal(null, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.srxq:
                context.startActivity(new Intent(context, DoctorSRXQActivity.class));
                break;
            case R.id.zhzx:
                context.startActivity(new Intent(context, DoctorZHZXctivity.class));
                break;
            case R.id.szzx:
                context.startActivity(new Intent(context, DoctorSettingActivity.class));
                break;
            case R.id.wdzl:
                context.startActivity(new Intent(context, DoctorGRZXActivity.class));
                break;
            case R.id.wdfb:
                context.startActivity(new Intent(context, DoctorMyFaBiaoActivity.class));
                break;
            case R.id.wdsc:
                context.startActivity(new Intent(context, DoctorMyShouCangActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_PERSONAL.equals(requestAction)){
            ((TextView) view.findViewById(R.id.name)).setText(((DoctorMainActivity) context).getApp().getUser().getName());
            ((TextView) view.findViewById(R.id.income)).setText(((DoctorPersonalResponse)data).getTotal() + context.getString(R.string.rmb_unit));
            imageLoader.displayImage(HttpRequest.PHOTO_PATH + ((DoctorPersonalResponse)data).getImg(), head, options);
        }
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
    }
}
