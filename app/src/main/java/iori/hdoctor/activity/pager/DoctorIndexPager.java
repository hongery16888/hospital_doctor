package iori.hdoctor.activity.pager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import iori.hdoctor.R;
import iori.hdoctor.activity.DoctorBLGLActivity;
import iori.hdoctor.activity.DoctorCZSZActivity;
import iori.hdoctor.activity.DoctorFFGLActivity;
import iori.hdoctor.activity.DoctorMainActivity;
import iori.hdoctor.activity.DoctorMessageActivity;
import iori.hdoctor.activity.DoctorStatisticsActivity;
import iori.hdoctor.activity.DoctorYYZXActivity;
import iori.hdoctor.activity.base.BasePager;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.DoctorBenchResponse;
import iori.hdoctor.view.BadgeView;

/**
 * Created by Administrator on 2015/7/10.
 */
public class DoctorIndexPager extends BasePager implements NetworkConnectListener{

    private View view;
    private Context context;
    private TextView jrfk, jrhz;
    private BadgeView zxzxBV;

    private boolean isInit = false;

    public DoctorIndexPager(Context ct) {
        super(ct);
        context = ct;
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.doctor_pager_gzt, null);
        return view;
    }

    @Override
    public void initData() {
        view.findViewById(R.id.blgl).setOnClickListener(blglClickListener);
        view.findViewById(R.id.yyzx).setOnClickListener(yyzxglClickListener);
        view.findViewById(R.id.czsz).setOnClickListener(czszClickListener);
        view.findViewById(R.id.ffgl).setOnClickListener(ffglClickListener);
        view.findViewById(R.id.jrfk).setOnClickListener(tjClickListener);
        view.findViewById(R.id.jrhz).setOnClickListener(tjClickListener);
        view.findViewById(R.id.zxzx).setOnClickListener(zxListClickListener);
        jrfk = (TextView)view.findViewById(R.id.jrfk_info);
        jrhz = (TextView)view.findViewById(R.id.jrhz_info);
        NetworkAPI.getNetworkAPI().docBench(null, this);
        zxzxBV = new BadgeView(context, view.findViewById(R.id.zxzx));
    }

    @Override
    public void onResume() {
        if (!isInit) {
            initData();
            isInit = !isInit;
        }
    }

    private View.OnClickListener blglClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, DoctorBLGLActivity.class));
        }
    };

    private View.OnClickListener yyzxglClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, DoctorYYZXActivity.class));
        }
    };

    private View.OnClickListener czszClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, DoctorCZSZActivity.class));
        }
    };

    private View.OnClickListener ffglClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, DoctorFFGLActivity.class));
        }
    };

    private View.OnClickListener tjClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, DoctorStatisticsActivity.class));
        }
    };

    private View.OnClickListener zxListClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DoctorMessageActivity.class);
            intent.putExtra("title", context.getString(R.string.doctor_zx_list_title_main));
            context.startActivity(intent);
        }
    };

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_BENCH == requestAction) {
            jrfk.setText(((DoctorBenchResponse)data).getVnum());
            jrhz.setText(((DoctorBenchResponse) data).getHnum());
            if (Integer.parseInt(((DoctorBenchResponse)data).getNolooknum()) > 0) {
                zxzxBV.setText(((DoctorBenchResponse) data).getNolooknum());
                zxzxBV.show();
            }
        }
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
    }
}
