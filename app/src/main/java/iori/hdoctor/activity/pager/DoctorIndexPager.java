package iori.hdoctor.activity.pager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import iori.hdoctor.R;
import iori.hdoctor.activity.DoctorBLGLActivity;
import iori.hdoctor.activity.DoctorCZSZActivity;
import iori.hdoctor.activity.DoctorFFGLActivity;
import iori.hdoctor.activity.DoctorYYZXActivity;
import iori.hdoctor.activity.base.BasePager;

/**
 * Created by Administrator on 2015/7/10.
 */
public class DoctorIndexPager extends BasePager{

    private View view;
    private Context context;

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

}
