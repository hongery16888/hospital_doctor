package iori.hdoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorBLGLAdapter;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorBLGLActivity extends BaseActivity {

    @InjectView(R.id.blgl_listview)
    PullToRefreshListView listView;

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_blgl_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_blgl_title_main));
    }

    @Override
    protected void initData() {
        listView.setAdapter(new DoctorBLGLAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(DoctorBLGLActivity.this, DoctorBLGLInfoActivity.class));
            }
        });
    }

}
