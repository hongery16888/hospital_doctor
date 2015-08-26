package iori.hdoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorFriendAdapter;
import iori.hdoctor.adapter.PatientFJYSAdapter;
import iori.hdoctor.adapter.PatientFJYYAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.PatNearDoc;
import iori.hdoctor.net.entity.PatNearHosp;
import iori.hdoctor.net.response.PatientNearByDocResponse;
import iori.hdoctor.net.response.PatientNearByHospResponse;
import iori.hdoctor.net.response.PatientZiZhiDocResponse;
import iori.hdoctor.net.response.PatientZiZhiHospResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientZiZhiDocActivity extends BaseActivity implements NetworkConnectListener {

    private ArrayList<PatNearDoc> patNearDocs = new ArrayList<>();
    private ArrayList<PatNearHosp> patNearHosps = new ArrayList<>();
    private boolean isDoc = true;

    @InjectView(R.id.fj_listview)
    ListView listView;
    @InjectView(R.id.mid_left_icon)
    TextView midLeftText;
    @InjectView(R.id.mid_right_icon)
    TextView midRightText;
    @OnClick({R.id.mid_left_icon, R.id.mid_right_icon})
    public void midIcon(TextView textView){
        if (tempTv != textView)
            if (R.id.mid_left_icon == textView.getId()) {
                midLeftText.setTextColor(getResources().getColor(R.color.white));
                midRightText.setTextColor(getResources().getColor(R.color.global_title_color));
                midLeftText.setBackgroundResource(R.drawable.bg_tab2_lef);
                midRightText.setBackgroundResource(R.drawable.bg_tab2_right_hl);
                tempTv = textView;
                NetworkAPI.getNetworkAPI().zizhidoc(getApp().getLongitude() + "", getApp().getLatitude() + "", showProgressDialog(), this);
            }else{
                midLeftText.setTextColor(getResources().getColor(R.color.global_title_color));
                midRightText.setTextColor(getResources().getColor(R.color.white));
                midLeftText.setBackgroundResource(R.drawable.bg_tab2_left_hl);
                midRightText.setBackgroundResource(R.drawable.bg_tab2_right);
                tempTv = textView;
//                NetworkAPI.getNetworkAPI().zizhihosp(getApp().getLongitude() + "", getApp().getLatitude() + "", showProgressDialog(), this);
                NetworkAPI.getNetworkAPI().zizhihosp("121.422467", "31.242112", showProgressDialog(), this);
            }
    }

    private TextView tempTv;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zizhi_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_fj_title_main));
        setRightIconAction(R.drawable.icon_map, mapListener);
        showMidIcon();
    }

    @Override
    protected void initData() {

        midLeftText.setText("医生");
        midLeftText.setTextColor(getResources().getColor(R.color.white));
        midRightText.setTextColor(getResources().getColor(R.color.global_title_color));
        midRightText.setText("医院");
        midLeftText.setBackgroundResource(R.drawable.bg_tab2_lef);
        midRightText.setBackgroundResource(R.drawable.bg_tab2_right_hl);

        tempTv = midLeftText;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isDoc) {
                    Intent intent = new Intent(PatientZiZhiDocActivity.this, PatientDoctorIntroduceActivity.class);
                    intent.putExtra("did", patNearDocs.get(position).getDid());
                    startActivity(intent);
                }
            }
        });

//        NetworkAPI.getNetworkAPI().zizhidoc(getApp().getLongitude() + "", getApp().getLatitude() + "", showProgressDialog(), this);
        NetworkAPI.getNetworkAPI().zizhidoc("121.422467" + "", "31.242112" + "", showProgressDialog(), this);
    }

    private View.OnClickListener mapListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_ZIZHI_DOC.equals(requestAction)) {
            patNearDocs.clear();
            patNearDocs.addAll(((PatientZiZhiDocResponse) data).getDatalist());
            listView.setAdapter(new PatientFJYSAdapter(this, ((PatientZiZhiDocResponse) data).getDatalist()));
            isDoc = true;
        } else if (HttpRequest.PAT_ZIZHI_HOSP.equals(requestAction)) {
            patNearHosps.clear();
            patNearHosps.addAll(((PatientZiZhiHospResponse) data).getDatalist());
            listView.setAdapter(new PatientFJYYAdapter(this, ((PatientZiZhiHospResponse) data).getDatalist()));
            isDoc = false;
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        if (HttpRequest.PAT_ZIZHI_DOC.equals(requestAction)) {
            listView.setAdapter(new PatientFJYSAdapter(this, new ArrayList<PatNearDoc>()));
        } else if (HttpRequest.PAT_ZIZHI_HOSP.equals(requestAction)) {
            listView.setAdapter(new PatientFJYYAdapter(this, new ArrayList<PatNearHosp>()));
        }
        showToast(errorMsg);
        dismissProgressDialog();
    }

}
