package iori.hdoctor.activity;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
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
public class PatientChuZhenActivity extends BaseActivity implements NetworkConnectListener {

    private ArrayList<PatNearDoc> patNearDocs = new ArrayList<>();
    private ArrayList<PatNearHosp> patNearHosps = new ArrayList<>();
    private boolean isDoc = true;
    private Dialog dialog;

    @InjectView(R.id.type)
    TextView type;

    @OnClick(R.id.type)
    public void type(){
        if (dialog == null)
            initDialog();
        dialog.show();
    }

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
                NetworkAPI.getNetworkAPI().chuzhenbydoc(getApp().getLongitude() + "", getApp().getLatitude() + "", showProgressDialog(), this);
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
        type.setText("出诊");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isDoc) {
                    Intent intent = new Intent(PatientChuZhenActivity.this, PatientDoctorIntroduceActivity.class);
                    intent.putExtra("did", patNearDocs.get(position).getDid());
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(PatientChuZhenActivity.this, PatientHospitalIntroduceActivity.class);
                    intent.putExtra("hospitalid", patNearHosps.get(position).getHospitalid());
                    startActivity(intent);
                }
            }
        });
        NetworkAPI.getNetworkAPI().chuzhenbydoc("121.422467" + "", "31.242112" + "", showProgressDialog(), this);
//        NetworkAPI.getNetworkAPI().nearbydoc(getApp().getLongitude() + "", getApp().getLatitude() + "", showProgressDialog(), this);
    }

    private View.OnClickListener mapListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(PatientChuZhenActivity.this, PatientMapActivity.class));
        }
    };

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_CHUZHEN.equals(requestAction)) {
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

    private void initDialog(){
        dialog = new Dialog(this, R.style.dialog);
        View view = View.inflate(this, R.layout.doctor_type_dialog, null);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);


        dialog.findViewById(R.id.juli).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientChuZhenActivity.this, PatientFJActivity.class));
                dialog.hide();
                finish();
            }
        });

        dialog.findViewById(R.id.zizhi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientChuZhenActivity.this, PatientZiZhiDocActivity.class));
                dialog.hide();
                finish();
            }
        });

        dialog.findViewById(R.id.chuzhen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
            }
        });
    }
}
