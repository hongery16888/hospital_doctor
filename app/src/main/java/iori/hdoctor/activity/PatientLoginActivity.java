package iori.hdoctor.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.DataTransfer;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.PatientLoginResponse;
import iori.hdoctor.util.DateUtil;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientLoginActivity extends BaseActivity implements NetworkConnectListener {

    @InjectView(R.id.username)
    EditText username;
    @InjectView(R.id.password)
    EditText password;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getLoginWchatInfo(null);
        }
    };

    @OnClick(R.id.forget_password)
    public void forgetPwd(){
        Intent intent = new Intent(this, PatientForgetPwdActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.register)
    public void register() {
        if (DateUtil.LoginFlag()) {
            startActivity(new Intent(PatientLoginActivity.this, PatientRegisterPhoneActivity.class));
        } else
            showToast("不好意思，已过试用期了");
    }

    @OnClick(R.id.login)
    public void login() {
        if (DateUtil.LoginFlag()) {
            if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                showToast("用户名或密码不能为空");
            } else
                NetworkAPI.getNetworkAPI().patlogin(username.getText().toString(), password.getText().toString(),
                        showProgressDialog(), this);
        } else
            showToast("不好意思，已过试用期了");
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_login_main;
    }

    @Override
    protected void initView() {
        setHideBackAction();
        setTitleAction(getResources().getString(R.string.patient_login_title_main));
        setRightText(getResources().getString(R.string.doctor_login_type), typeListener);
    }

    @Override
    protected void initData() {
        getApp().setLoginWXHandler(handler);
        getLoginHistoryInfo();
        mTencent = Tencent.createInstance("222222", this.getApplicationContext());
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_LOGIN.equals(requestAction) ||
                HttpRequest.LOGIN_WCHAT.equals(requestAction) ||
                HttpRequest.LOGIN_QQ.equals(requestAction)) {
            DataTransfer.setUid(((PatientLoginResponse) data).getUid());
            getApp().setRongToken(((PatientLoginResponse) data).getLiaotiantoken());
            saveLoginHistoryInfo();
            startActivity(new Intent(PatientLoginActivity.this, PatientMainActivity.class));
            finish();
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }

    private View.OnClickListener typeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(PatientLoginActivity.this, DoctorLoginActivity.class));
            finish();
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getApp().setLoginWXHandler(null);
    }

    private void getLoginHistoryInfo() {
        SharedPreferences prefs = getSharedPreferences("HDoctor", MODE_WORLD_WRITEABLE);
        String username = prefs.getString("pat_username", "");
        this.username.setText(username);
        String password = prefs.getString("pat_password", "");
        this.password.setText(password);
    }

    private void saveLoginHistoryInfo() {
        SharedPreferences.Editor editor = getSharedPreferences("HDoctor", MODE_WORLD_WRITEABLE).edit();
        editor.putString("pat_username", this.username.getText().toString());
        editor.putString("pat_password", this.password.getText().toString());
        editor.commit();
    }

    public void getLoginWchatInfo(View view) {
        SharedPreferences prefs = getSharedPreferences("HDoctor", MODE_WORLD_WRITEABLE);
        String wchatID = prefs.getString("wchatID", "");
        if (TextUtils.isEmpty(wchatID)){
//            showToast("没有绑定微信号");
            wchat(null);
        }else{
            NetworkAPI.getNetworkAPI().patloginwchat(wchatID, showProgressDialog(), this);
        }
    }

    public void getLoginQQInfo(View view) {
        SharedPreferences prefs = getSharedPreferences("HDoctor", MODE_WORLD_WRITEABLE);
        String qq = prefs.getString("pat_openId", "");
        if (TextUtils.isEmpty(qq)){
            tencent(null);
//            showToast("没有绑定QQ");
        }else{
            NetworkAPI.getNetworkAPI().patloginqq(qq, showProgressDialog(), this);
        }
    }

    private Tencent mTencent;
    public static final String App_ID = "wxf5d877b0b2c2bd4b";
    public static IWXAPI WXapi;

    public void tencent(View view) {
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", loginListener);
        }
    }

    private IUiListener loginListener = new IUiListener() {
        @Override
        public void onComplete(Object response) {
            try {
                //获得的数据是JSON格式的，获得你想获得的内容
                //如果你不知道你能获得什么，看一下下面的LOG
                saveLoginHistoryInfo(((JSONObject) response).getString("openid"));
                //access_token= ((JSONObject) response).getString("access_token");              //expires_in = ((JSONObject) response).getString("expires_in");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            showToast(uiError.errorMessage);
        }

        @Override
        public void onCancel() {
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Constants.REQUEST_LOGIN) {
            Tencent.handleResultData(data, loginListener);
        }

        try {
            if (data != null && !TextUtils.isEmpty(data.getStringExtra("key_response")))
                saveLoginHistoryInfo(new JSONObject(data.getStringExtra("key_response")).getString("openid"));
        } catch (Exception e) {

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void wchat(View vieww) {
        WXapi = WXAPIFactory.createWXAPI(this, App_ID, true);
        WXapi.registerApp(App_ID);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo";
        WXapi.sendReq(req);
    }

    private void saveLoginHistoryInfo(String openId) {
        if (getApp().getLoginWXHandler() == null)
            NetworkAPI.getNetworkAPI().pataccountQQ(openId, null, this);
        SharedPreferences.Editor editor = getSharedPreferences("HDoctor", MODE_WORLD_WRITEABLE).edit();
        editor.putString("pat_openId", openId);
        editor.commit();
        NetworkAPI.getNetworkAPI().patloginqq(openId, showProgressDialog(), this);
    }
}
