package iori.hdoctor.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.HistoryAdapter;
import iori.hdoctor.adapter.PatientFJYSAdapter;
import iori.hdoctor.adapter.PatientFJYYAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.PatNearDoc;
import iori.hdoctor.net.entity.PatNearHosp;
import iori.hdoctor.net.response.PatientNearByDocResponse;
import iori.hdoctor.net.response.PatientNearByHospResponse;
import iori.hdoctor.net.response.PatientSearchByDocResponse;
import iori.hdoctor.net.response.PatientSearchByHospResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientSearchActivity extends BaseActivity implements NetworkConnectListener{

    private String[] newHistories = new String[50];
    private ArrayList<String> strings = new ArrayList<>();
    private HistoryAdapter adapter;
    private Dialog dialog;
    private final static int DOCTOR = 1;
    private final static int HOSPITAL = 2;
    private int typeId = 1;

    private ArrayList<PatNearDoc> patSearchDocs = new ArrayList<>();
    private ArrayList<PatNearHosp> patSearchHosps = new ArrayList<>();

    @InjectView(R.id.history_lay)
    View historyLay;
    @InjectView(R.id.search_edit)
    AutoCompleteTextView search;
    @InjectView(R.id.history)
    ListView historyList;
    @InjectView(R.id.type)
    TextView type;
    @InjectView(R.id.search_listview)
    ListView searchListView;

    @OnClick(R.id.type)
    public void showDialog(){
        if (dialog == null)
            initDialog();
        dialog.show();
    }

    @OnClick(R.id.back_icon)
    public void back() {
        finish();
    }

    @OnClick(R.id.clear)
    public void clear(){
        SharedPreferences sp = getSharedPreferences("HDoctor", 0);
        sp.edit().putString("history", "").commit();
        initAutoComplete();
    }

    @OnClick(R.id.search)
    public void search(TextView view) {

        if (TextUtils.isEmpty(search.getText().toString()))
            showToast("搜索内容不能为空");
        else {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            if (typeId == DOCTOR)
//                NetworkAPI.getNetworkAPI().searchbydoc(getApp().getLongitude() + "", getApp().getLatitude() + "", search.getText().toString(), showProgressDialog(), this);
                NetworkAPI.getNetworkAPI().searchbydoc("121.422467" + "", "31.242112" + "", search.getText().toString(), showProgressDialog(), this);
            else
//                NetworkAPI.getNetworkAPI().searchbyhosp(getApp().getLongitude() + "", getApp().getLatitude() + "", search.getText().toString(), showProgressDialog(), this);
                NetworkAPI.getNetworkAPI().searchbyhosp("121.422467" + "", "31.242112" + "", search.getText().toString(), showProgressDialog(), this);

            saveHistory();
            search.setText("");
            initAutoComplete();
            historyLay.setVisibility(View.GONE);
        }
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_search_main;
    }

    @Override
    protected void initView() {
        setHideTitleBar(true);
    }

    @Override
    protected void initData() {
        adapter = new HistoryAdapter(this, strings);
        historyList.setAdapter(adapter);
        initAutoComplete();

        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PatientSearchActivity.this, PatientDoctorIntroduceActivity.class);
                intent.putExtra("did", patSearchDocs.get(position).getDid());
                startActivity(intent);
            }
        });

        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                search.setText(strings.get(position));
                historyLay.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_SEARCH_BY_DOC.equals(requestAction)) {
            patSearchDocs.clear();
            patSearchDocs.addAll(((PatientSearchByDocResponse) data).getDatalist());
            searchListView.setAdapter(new PatientFJYSAdapter(this, ((PatientSearchByDocResponse) data).getDatalist()));
        } else if (HttpRequest.PAT_SEARCH_BY_HOSP.equals(requestAction)) {
            patSearchHosps.clear();
            patSearchHosps.addAll(((PatientSearchByHospResponse) data).getDatalist());
            searchListView.setAdapter(new PatientFJYYAdapter(this, ((PatientSearchByHospResponse) data).getDatalist()));
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        if (HttpRequest.PAT_SEARCH_BY_DOC.equals(requestAction)) {
            searchListView.setAdapter(new PatientFJYSAdapter(this, new ArrayList<PatNearDoc>()));
        } else if (HttpRequest.PAT_SEARCH_BY_HOSP.equals(requestAction)) {
            searchListView.setAdapter(new PatientFJYYAdapter(this, new ArrayList<PatNearHosp>()));
        }
        showToast(errorMsg);
        dismissProgressDialog();
    }

    private void saveHistory() {
        SharedPreferences sp = getSharedPreferences("HDoctor", 0);
        String longhistory = sp.getString("history", "");
        if (!TextUtils.isEmpty(search.getText().toString()) && !longhistory.contains(search.getText().toString() + ",")) {
            StringBuilder sb = new StringBuilder(longhistory);
            sb.insert(0, search.getText().toString() + ",");
            sp.edit().putString("history", sb.toString()).commit();
        }
    }

    private void initAutoComplete() {
        SharedPreferences sp = getSharedPreferences("HDoctor", 0);
        String longhistory = sp.getString("history", "");
        String[] histories = longhistory.split(",");

        strings.clear();

        for (int i = 0; i < histories.length; i++) {
            if (!TextUtils.isEmpty(histories[i]))
                strings.add(histories[i]);
        }

        adapter.addData(strings);

        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    historyLay.setVisibility(View.VISIBLE);
                else
                    historyLay.setVisibility(View.GONE);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyLay.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initDialog(){
        dialog = new Dialog(this, R.style.dialog);
        View view = View.inflate(this, R.layout.search_type_dialog, null);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        dialog.findViewById(R.id.doctor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type.setText("医生");
                typeId = DOCTOR;
                dialog.hide();
            }
        });

        dialog.findViewById(R.id.hospital).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type.setText("医院");
                typeId = HOSPITAL;
                dialog.hide();
            }
        });
    }

}
