package iori.hdoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.InjectView;
import butterknife.OnItemClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.PatientWDDDAdapter;
import iori.hdoctor.adapter.PatientWDYYAdapter;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientWDDDActivity extends BaseActivity {

    @InjectView(R.id.wddd_listview)
    ListView listView;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_wddd_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.patient_wddd_title_main));
    }

    @Override
    protected void initData() {
        listView.setAdapter(new PatientWDDDAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(PatientWDDDActivity.this, PatientPublishCommentActivity.class));
            }
        });
    }

}
