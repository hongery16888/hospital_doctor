package iori.hdoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.w3c.dom.Text;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorFriendAdapter;
import iori.hdoctor.adapter.PatientFJYSAdapter;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientFJActivity extends BaseActivity {

    @InjectView(R.id.fj_listview)
    ListView listView;
    @InjectView(R.id.mid_left_ys)
    TextView midLeftText;
    @InjectView(R.id.mid_right_yy)
    TextView midRightText;
    @OnClick({R.id.mid_left_ys, R.id.mid_right_yy})
    public void midIcon(TextView textView){
        if (clickTV == textView.getId()) {
            midLeftText.setTextColor(getResources().getColor(R.color.white));
            midLeftText.setBackgroundResource(R.drawable.bg_tab_left_hl);
            midRightText.setTextColor(getResources().getColor(R.color.global_title_color));
            midRightText.setBackgroundResource(R.drawable.bg_tab_right);
        }else{
            midLeftText.setTextColor(getResources().getColor(R.color.global_title_color));
            midLeftText.setBackgroundResource(R.drawable.bg_tab_left);
            midRightText.setTextColor(getResources().getColor(R.color.white));
            midRightText.setBackgroundResource(R.drawable.bg_tab_right_hl);
        }
    }

    private int clickTV = R.id.mid_left_ys;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_nearby_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_fj_title_main));
        setRightIconAction(R.drawable.icon_map, mapListener);
    }

    @Override
    protected void initData() {
        listView.setAdapter(new PatientFJYSAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(PatientFJActivity.this, PatientDoctorIntroduceActivity.class));
            }
        });
    }

    private View.OnClickListener mapListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

}
