package iori.hdoctor.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorMessageAdapter;
import iori.hdoctor.adapter.PatientWDYYAdapter;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientWDYYActivity extends BaseActivity {

    @InjectView(R.id.wdyy_listview)
    ListView listView;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_wdyy_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.patient_wdyy_title_main));
    }

    @Override
    protected void initData() {
        listView.setAdapter(new PatientWDYYAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

}
