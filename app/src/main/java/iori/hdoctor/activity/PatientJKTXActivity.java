package iori.hdoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorCircleAdapter;
import iori.hdoctor.adapter.PatientJKTXAdapter;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientJKTXActivity extends BaseActivity {

    @InjectView(R.id.jktx_listview)
    ListView listView;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_jktx_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_jktx_title_main));
        setRightText(getResources().getString(R.string.patient_jktx_add), addListener);
    }

    @Override
    protected void initData() {
        listView.setAdapter(new PatientJKTXAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(PatientJKTXActivity.this, PatientCalendarActivity.class));
        }
    };

}
