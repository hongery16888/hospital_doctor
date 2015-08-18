package iori.hdoctor.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorZHZXctivity extends BaseActivity implements NetworkConnectListener {

    @InjectView(R.id.password)
    EditText passwrod;
    @InjectView(R.id.phone)
    EditText phone;
    @InjectView(R.id.qq)
    EditText qq;
    @InjectView(R.id.wchat)
    EditText wchat;

    @OnClick(R.id.confirm)
    public void confirm() {
        if (TextUtils.isEmpty(passwrod.getText().toString()) ||
                TextUtils.isEmpty(passwrod.getText().toString()) ||
                TextUtils.isEmpty(passwrod.getText().toString()) ||
                TextUtils.isEmpty(passwrod.getText().toString()))
            showToast("修改信息不能为空");
        else
            NetworkAPI.getNetworkAPI().docaccount(passwrod.getText().toString(),
                    phone.getText().toString(),
                    qq.getText().toString(),
                    wchat.getText().toString(),
                    showProgressDialog(),
                    this);
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_zhzx_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.doctor_zhzx_title_main));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_ACCOUNT.equals(requestAction)){
            finish();
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
