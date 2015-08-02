package iori.hdoctor.activity.pager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import iori.hdoctor.R;
import iori.hdoctor.activity.DoctorBLGLActivity;
import iori.hdoctor.activity.DoctorGRZXActivity;
import iori.hdoctor.activity.DoctorMainActivity;
import iori.hdoctor.activity.DoctorSRXQActivity;
import iori.hdoctor.activity.base.BasePager;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.view.CircleBitmapDisplayer;

/**
 * Created by Administrator on 2015/7/10.
 */
public class DoctorMinePager extends BasePager{

    private View view;
    private Context context;
    private boolean isInit = false;
    private ImageView head;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

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
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(1000)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .considerExifParams(true) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .displayer(new CircleBitmapDisplayer()) // default
                .build();
        view.findViewById(R.id.wdzl).setOnClickListener(wdzlClickListener);
        head = (ImageView)view.findViewById(R.id.head);
        ((TextView)view.findViewById(R.id.name)).setText(((DoctorMainActivity)context).getApp().getUser().getName());
        imageLoader.displayImage(HttpRequest.PHOTO_PATH + ((DoctorMainActivity) context).getApp().getUser().getImg(), head, options);

        view.findViewById(R.id.zhzx).setOnClickListener(zhzxClickListener);
        view.findViewById(R.id.szzx).setOnClickListener(zhzxClickListener);
    }

    private View.OnClickListener wdzlClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, DoctorGRZXActivity.class));
        }
    };

    private View.OnClickListener zhzxClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, DoctorSRXQActivity.class));
        }
    };

}
