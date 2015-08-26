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

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientFJActivity extends BaseActivity implements NetworkConnectListener {

    private ArrayList<PatNearDoc> patNearDocs = new ArrayList<>();
    private ArrayList<PatNearHosp> patNearHosps = new ArrayList<>();
    private boolean isDoc = true;

    @InjectView(R.id.fj_listview)
    ListView listView;
    @InjectView(R.id.mid_left_ys)
    TextView midLeftText;
    @InjectView(R.id.mid_right_yy)
    TextView midRightText;

    @OnClick({R.id.mid_left_ys, R.id.mid_right_yy})
    public void midIcon(TextView textView) {
        if (tempTv != textView)
            if (textView.getId() == R.id.mid_left_ys) {
                midLeftText.setTextColor(getResources().getColor(R.color.white));
                midLeftText.setBackgroundResource(R.drawable.bg_tab_left_hl);
                midRightText.setTextColor(getResources().getColor(R.color.global_title_color));
                midRightText.setBackgroundResource(R.drawable.bg_tab_right);
                tempTv = midLeftText;
                NetworkAPI.getNetworkAPI().nearbydoc(getApp().getLongitude() + "", getApp().getLatitude() + "", showProgressDialog(), this);
            } else {
                midLeftText.setTextColor(getResources().getColor(R.color.global_title_color));
                midLeftText.setBackgroundResource(R.drawable.bg_tab_left);
                midRightText.setTextColor(getResources().getColor(R.color.white));
                midRightText.setBackgroundResource(R.drawable.bg_tab_right_hl);
                tempTv = midRightText;
                NetworkAPI.getNetworkAPI().nearbyhosp(getApp().getLongitude() + "", getApp().getLatitude() + "", showProgressDialog(), this);
            }
    }

    private TextView tempTv;

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
        tempTv = midLeftText;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isDoc) {
                    Intent intent = new Intent(PatientFJActivity.this, PatientDoctorIntroduceActivity.class);
                    intent.putExtra("did", patNearDocs.get(position).getDid());
                    startActivity(intent);
                }
            }
        });
        NetworkAPI.getNetworkAPI().nearbydoc("121.422467" + "", "31.242112" + "", showProgressDialog(), this);
//        NetworkAPI.getNetworkAPI().nearbydoc(getApp().getLongitude() + "", getApp().getLatitude() + "", showProgressDialog(), this);
    }

    private View.OnClickListener mapListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(PatientFJActivity.this, PatientMapActivity.class));
        }
    };

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_NEARBYDOC.equals(requestAction)) {
            patNearDocs.clear();
            patNearDocs.addAll(((PatientNearByDocResponse) data).getDatalist());
            listView.setAdapter(new PatientFJYSAdapter(this, ((PatientNearByDocResponse) data).getDatalist()));
            isDoc = true;
        } else if (HttpRequest.PAT_NEARBYHOSP.equals(requestAction)) {
            patNearHosps.clear();
            patNearHosps.addAll(((PatientNearByHospResponse) data).getDatalist());
            listView.setAdapter(new PatientFJYYAdapter(this, ((PatientNearByHospResponse) data).getDatalist()));
            isDoc = false;
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        if (HttpRequest.PAT_NEARBYDOC.equals(requestAction)) {
            listView.setAdapter(new PatientFJYSAdapter(this, new ArrayList<PatNearDoc>()));
        } else if (HttpRequest.PAT_NEARBYHOSP.equals(requestAction)) {
            listView.setAdapter(new PatientFJYYAdapter(this, new ArrayList<PatNearHosp>()));
        }
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
