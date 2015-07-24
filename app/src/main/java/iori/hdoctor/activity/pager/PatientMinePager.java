package iori.hdoctor.activity.pager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import iori.hdoctor.R;
import iori.hdoctor.activity.PatientWDDDActivity;
import iori.hdoctor.activity.PatientWDYYActivity;
import iori.hdoctor.activity.PatientWDZLActivity;
import iori.hdoctor.activity.base.BasePager;

/**
 * Created by Administrator on 2015/7/10.
 */
public class PatientMinePager extends BasePager implements View.OnClickListener{

    private View view;
    private Context context;
    private boolean isInit = false;

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
            default:
                break;
        }
    }
}
