package iori.hdoctor.activity.pager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import iori.hdoctor.R;
import iori.hdoctor.activity.PatientFXBGActivity;
import iori.hdoctor.activity.PatientJKTXActivity;
import iori.hdoctor.activity.PatientWDYXActivity;
import iori.hdoctor.activity.PatientZWZD01Activity;
import iori.hdoctor.activity.PatientZWZD02Activity;
import iori.hdoctor.activity.base.BasePager;

/**
 * Created by Administrator on 2015/7/10.
 */
public class PatientIndexPager extends BasePager implements View.OnClickListener{

    private View view;
    private Context context;

    private boolean isInit = false;

    public PatientIndexPager(Context ct) {
        super(ct);
        context = ct;
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.patient_pager_jk, null);
        return view;
    }

    @Override
    public void initData() {
        view.findViewById(R.id.zwzd).setOnClickListener(this);
        view.findViewById(R.id.fxbg).setOnClickListener(this);
        view.findViewById(R.id.jktx).setOnClickListener(this);
        view.findViewById(R.id.wdyx).setOnClickListener(this);
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
            case R.id.zwzd:
                context.startActivity(new Intent(context, PatientZWZD01Activity.class));
                break;
            case R.id.fxbg:
                context.startActivity(new Intent(context, PatientFXBGActivity.class));
                break;
            case R.id.jktx:
                context.startActivity(new Intent(context, PatientJKTXActivity.class));
                break;
            case R.id.wdyx:
                context.startActivity(new Intent(context, PatientWDYXActivity.class));
                break;
            default:
                break;
        }
    }
}
