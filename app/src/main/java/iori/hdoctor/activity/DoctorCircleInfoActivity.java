package iori.hdoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorCircleAdapter;
import iori.hdoctor.adapter.DoctorCircleInfoAdapter;
import iori.hdoctor.view.NoScrollListView;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorCircleInfoActivity extends BaseActivity {

    @InjectView(R.id.circle_info_list)
    NoScrollListView listView;

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_circle_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_circle_title_main));
        setRightIconAction(R.drawable.btn_sc, scListener);
    }

    @Override
    protected void initData() {
        listView.setAdapter(new DoctorCircleInfoAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(DoctorCircleInfoActivity.this, DoctorBLGLInfoActivity.class));
            }
        });
    }

    private View.OnClickListener scListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
