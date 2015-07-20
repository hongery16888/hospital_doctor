package iori.hdoctor.activity;

import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorMessageAdapter;
import iori.hdoctor.adapter.PatientMessageAdapter;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientMessageActivity extends BaseActivity {

    @InjectView(R.id.message_listview)
    PullToRefreshListView listView;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_circle_message_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_circle_xx));
    }

    @Override
    protected void initData() {
        listView.setAdapter(new PatientMessageAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

}
