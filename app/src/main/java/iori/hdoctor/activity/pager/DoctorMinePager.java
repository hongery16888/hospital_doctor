package iori.hdoctor.activity.pager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import iori.hdoctor.R;
import iori.hdoctor.activity.DoctorBLGLActivity;
import iori.hdoctor.activity.DoctorGRZXActivity;
import iori.hdoctor.activity.base.BasePager;

/**
 * Created by Administrator on 2015/7/10.
 */
public class DoctorMinePager extends BasePager{

    private View view;
    private Context context;
    private boolean isInit = false;

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
        view.findViewById(R.id.wdzl).setOnClickListener(wdzlClickListener);
    }

    private View.OnClickListener wdzlClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, DoctorGRZXActivity.class));
        }
    };

}
