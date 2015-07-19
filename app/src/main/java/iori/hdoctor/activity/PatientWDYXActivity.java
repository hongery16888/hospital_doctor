package iori.hdoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.PatientJKTXAdapter;
import iori.hdoctor.adapter.PatientWDYXAdapter;
import iori.hdoctor.view.ProgressWheel;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientWDYXActivity extends BaseActivity{

    @InjectView(R.id.wdyx_listview)
    ListView listView;

    private TextView tempTV;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_wdyx_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_wdyx_title_main));
    }

    @Override
    protected void initData() {
        listView.setAdapter(new PatientWDYXAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

}
