package iori.hdoctor.activity.pager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import iori.hdoctor.R;
import iori.hdoctor.activity.DoctorCircleActivity;
import iori.hdoctor.activity.DoctorCircleInfoActivity;
import iori.hdoctor.activity.DoctorFriendActivity;
import iori.hdoctor.activity.DoctorMessageActivity;
import iori.hdoctor.activity.base.BasePager;
import iori.hdoctor.adapter.DoctorCircleAdapter;

/**
 * Created by Administrator on 2015/7/10.
 */
public class DoctorCirclePager extends BasePager{

    private View view;
    private Context context;
    private PullToRefreshListView listView;

    private boolean isInit = false;

    public DoctorCirclePager(Context ct) {
        super(ct);
        context = ct;
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.doctor_pager_circle, null);
        return view;
    }

    @Override
    public void initData() {
        listView = (PullToRefreshListView)view.findViewById(R.id.circle_listview);
        listView.setAdapter(new DoctorCircleAdapter(context));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                context.startActivity(new Intent(context, DoctorCircleInfoActivity.class));
            }
        });
        view.findViewById(R.id.sq).setOnClickListener(sqListener);
        view.findViewById(R.id.hy).setOnClickListener(hyListener);
        view.findViewById(R.id.xx).setOnClickListener(xxListener);
    }

    @Override
    public void onResume() {
        if (!isInit) {
            initData();
            isInit = !isInit;
        }
    }

    private View.OnClickListener sqListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, DoctorCircleActivity.class));
        }
    };

    private View.OnClickListener hyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, DoctorFriendActivity.class));
        }
    };

    private View.OnClickListener xxListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, DoctorMessageActivity.class));
        }
    };

}
