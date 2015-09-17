package iori.hdoctor.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.PatientZHZXResponse;
import iori.hdoctor.wxapi.WXEntryActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientZHZXActivity extends BaseActivity implements NetworkConnectListener {

    private boolean modify;
    private Tencent mTencent;
    public static final String App_ID = "wxf5d877b0b2c2bd4b";

    public static IWXAPI WXapi;

    @InjectView(R.id.password)
    EditText passwrod;
    @InjectView(R.id.total)
    TextView total;
    @InjectView(R.id.tencent)
    TextView tencent;
    @InjectView(R.id.wchat)
    TextView wchat;

    @OnClick(R.id.confirm)
    public void confirm() {
        modify = true;
        if (TextUtils.isEmpty(passwrod.getText().toString()))
            showToast("密码不能为空");
        else
            NetworkAPI.getNetworkAPI().pataccountpassword(passwrod.getText().toString(), showProgressDialog(), this);
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zhzx_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.doctor_zhzx_title_main));
    }

    @Override
    protected void initData() {
//        mTencent = Tencent.createInstance("1104740141", this.getApplicationContext());
        mTencent = Tencent.createInstance("222222", this.getApplicationContext());
        NetworkAPI.getNetworkAPI().pataccountinfo(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_ACCOUNT.equals(requestAction)) {
            if (modify) {

            }else {
                passwrod.setText(((PatientZHZXResponse) data).getPassword());
                total.setText(((PatientZHZXResponse) data).getTotal() + "元");
                if (!TextUtils.isEmpty(((PatientZHZXResponse) data).getBangdingwei()))
                    wchat.setText("已绑定微信");
                if (!TextUtils.isEmpty(((PatientZHZXResponse) data).getBangdingqq()))
                    tencent.setText("已绑定QQ");
            }
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }

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
        NetworkAPI.getNetworkAPI().pataccountQQ(openId, null, this);
        modify = true;
        SharedPreferences.Editor editor = getSharedPreferences("HDoctor", MODE_WORLD_WRITEABLE).edit();
        editor.putString("pat_openId", openId);
        editor.commit();
        showToast("绑定QQ成功");
    }

}
