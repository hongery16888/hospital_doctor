package iori.hdoctor.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorBankAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.DocBankInfo;
import iori.hdoctor.net.response.DoctorSRXQResponse;
import iori.hdoctor.view.NoScrollListView;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorSRXQActivity extends BaseActivity implements NetworkConnectListener{

    private int totalIncome;
    private ArrayList<DocBankInfo> docBankInfos = new ArrayList<>();
    @InjectView(R.id.bank_list)
    NoScrollListView bankList;
    @InjectView(R.id.income)
    TextView income;
    @OnClick(R.id.record)
    public void record(){
        startActivity(new Intent(this, DoctorBankRecordActivity.class));
    }

    @OnClick(R.id.next_step)
    public void next(){
        if (totalIncome <= 0){
            showToast("提现金额不足");
            return;
        }
        NetworkAPI.getNetworkAPI().tixian(showProgressDialog(), this);
//        Intent intent = new Intent(this, DoctorTiXianActivity.class);
//        for (int i = 0; i < docBankInfos.size(); i++){
//            if (docBankInfos.get(i).getBankisselect().equals("1")){
//                intent.putExtra("total",income.getText().toString());
//                intent.putExtra("bankname", docBankInfos.get(i).getBankname());
//                intent.putExtra("bankno", docBankInfos.get(i).getBankno());
//                intent.putExtra("banksn", docBankInfos.get(i).getBanksn());
//            }
//        }
//        startActivity(intent);

    }

    @OnClick(R.id.add_bank)
    public void addBank(){
        startActivity(new Intent(this, DoctorAddBankActivity.class));
    }

    private DoctorBankAdapter adapter;
    private Handler refreshBankHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkAPI.getNetworkAPI().docincome(showProgressDialog(), DoctorSRXQActivity.this);
        }
    };

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_income_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.doctor_income_title_main));
    }

    @Override
    protected void initData() {
        getApp().setRefreshBankHandler(refreshBankHandler);
        NetworkAPI.getNetworkAPI().docincome(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_INCOME.equals(requestAction)){
            income.setText(((DoctorSRXQResponse)data).getTotal());
            totalIncome = Integer.parseInt(((DoctorSRXQResponse)data).getTotal());
            bankList.setAdapter(new DoctorBankAdapter(this, ((DoctorSRXQResponse)data).getBanklist()));
            docBankInfos.addAll(((DoctorSRXQResponse)data).getBanklist());
        }else if (HttpRequest.DOC_TIXIAN.equals(requestAction)){
            showToast("提现成功，请等待处理");
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
