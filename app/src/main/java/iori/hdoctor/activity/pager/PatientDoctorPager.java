package iori.hdoctor.activity.pager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import iori.hdoctor.R;
import iori.hdoctor.activity.DoctorBLGLActivity;
import iori.hdoctor.activity.DoctorCZSZActivity;
import iori.hdoctor.activity.DoctorFFGLActivity;
import iori.hdoctor.activity.DoctorYYZXActivity;
import iori.hdoctor.activity.PatientChuZhenActivity;
import iori.hdoctor.activity.PatientFJActivity;
import iori.hdoctor.activity.PatientSearchActivity;
import iori.hdoctor.activity.PatientZiZhiDocActivity;
import iori.hdoctor.activity.base.BasePager;

/**
 * Created by Administrator on 2015/7/10.
 */
public class PatientDoctorPager extends BasePager{

    private View view;
    private Context context;

    private boolean isInit = false;

    public PatientDoctorPager(Context ct) {
        super(ct);
        context = ct;
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.patient_pager_ys, null);
        return view;
    }

    @Override
    public void initData() {
        view.findViewById(R.id.fj).setOnClickListener(fjClickListener);
        view.findViewById(R.id.zzys).setOnClickListener(zzysClickListener);
        view.findViewById(R.id.czys).setOnClickListener(czysClickListener);
        view.findViewById(R.id.search).setOnClickListener(searchClickListener);
    }

    @Override
    public void onResume() {
        if (!isInit) {
            initData();
            isInit = !isInit;
        }
    }

    private View.OnClickListener fjClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, PatientFJActivity.class));
        }
    };

    private View.OnClickListener zzysClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, PatientZiZhiDocActivity.class));
        }
    };

    private View.OnClickListener czysClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, PatientChuZhenActivity.class));
        }
    };

    private View.OnClickListener searchClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, PatientSearchActivity.class));
        }
    };

}
