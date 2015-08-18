package iori.hdoctor.activity;

import android.text.TextUtils;
import android.widget.EditText;

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
public class DoctorModifyActivity extends BaseActivity implements NetworkConnectListener{

    @OnClick(R.id.confirm)
    public void modify(){
        if (!TextUtils.isEmpty(content.getText().toString())) {
            if (getIntent().getStringExtra("key").equals("realname")) {
                NetworkAPI.getNetworkAPI().docRealname(content.getText().toString(), showProgressDialog(), this);
            } else if (getIntent().getStringExtra("key").equals("jianjie")) {
                NetworkAPI.getNetworkAPI().docJianJie(content.getText().toString(), showProgressDialog(), this);
            } else if (getIntent().getStringExtra("key").equals("shanchang")) {
                NetworkAPI.getNetworkAPI().docShanChang(content.getText().toString(), showProgressDialog(), this);
            } else if (getIntent().getStringExtra("key").equals("hospital")) {
                NetworkAPI.getNetworkAPI().docHospital(content.getText().toString(), showProgressDialog(), this);
            } else if (getIntent().getStringExtra("key").equals("keshi")) {
                NetworkAPI.getNetworkAPI().docKeshi(content.getText().toString(), showProgressDialog(), this);
            } else if (getIntent().getStringExtra("key").equals("zhicheng")) {
                NetworkAPI.getNetworkAPI().docZhiCheng(content.getText().toString(), showProgressDialog(), this);
            } else if (getIntent().getStringExtra("key").equals("email")) {
                NetworkAPI.getNetworkAPI().docEmail(content.getText().toString(), showProgressDialog(), this);
            }
        }else
            showToast("修改内容不能为空");
    }

    @InjectView(R.id.content)
    EditText content;

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_personal_modify_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getIntent().getStringExtra("title"));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_INFO.equals(requestAction)){
            getApp().getPersonalTv().setText(content.getText().toString());
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
