package iori.hdoctor.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorBLGLAdapter;
import iori.hdoctor.adapter.DoctorCircleAdapter;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorCircleActivity extends BaseActivity {

    @InjectView(R.id.circle_sq_listview)
    ListView listView;

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_circle_sq_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_circle_title_main));
    }

    @Override
    protected void initData() {
        listView.setAdapter(new DoctorCircleAdapter(this, null, new Handler()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(DoctorCircleActivity.this, DoctorCircleInfoActivity.class));
            }
        });
    }

}
