package iori.hdoctor.activity.pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import iori.hdoctor.R;
import iori.hdoctor.activity.base.BasePager;

/**
 * Created by Administrator on 2015/7/10.
 */
public class PatientIndexPager extends BasePager{

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

    }

    @Override
    public void onResume() {
        if (!isInit) {
            initData();
            isInit = !isInit;
        }
    }

}
