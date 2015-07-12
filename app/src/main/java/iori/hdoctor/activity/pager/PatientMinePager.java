package iori.hdoctor.activity.pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import iori.hdoctor.R;
import iori.hdoctor.activity.base.BasePager;

/**
 * Created by Administrator on 2015/7/10.
 */
public class PatientMinePager extends BasePager{

    private View view;
    private Context context;

    public PatientMinePager(Context ct) {
        super(ct);
        context = ct;
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.doctor_pager_mine, null);
        return view;
    }

    @Override
    public void initData() {

    }

}
