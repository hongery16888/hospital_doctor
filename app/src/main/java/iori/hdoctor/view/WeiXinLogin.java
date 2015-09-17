package iori.hdoctor.view;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import iori.hdoctor.app.MyApp;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;

public class WeiXinLogin implements NetworkConnectListener {

    private Context context;
    protected RequestProgressDialog mProgressDialog;
    private Application application;

    public WeiXinLogin(Context context, Application application) {
        this.context = context;
        this.application = application;
    }

    public void getWeiXinInfo(String code) {
        Log.d("gaolei", "getWeiXinOpenId------------------------");
        String getWeiXinOpenIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?"
                + "appid="
                + "wxf5d877b0b2c2bd4b"
                + "&secret="
                + "dc26373d344592f669345b8b1c2a51e6"
                + "&code="
                + code
                + "&grant_type=authorization_code";
        new AsyncHttpClient().get(getWeiXinOpenIdUrl,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          byte[] responseBody) {
                        // TODO Auto-generated method stub
                        String result = new String(responseBody);
                        try {
                            JSONObject object = new JSONObject(result);
                            String openId = object.getString("openid");
                            String accessToken = object.getString("access_token");
                            saveLoginHistoryInfo(openId);
                            if (((MyApp) application).getLoginWXHandler() == null) {
                                NetworkAPI.getNetworkAPI().pataccountWchat(openId, null, WeiXinLogin.this);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                          Throwable arg3) {
                        // TODO Auto-generated method stub
                    }
                });
    }

    private void saveLoginHistoryInfo(String wchatID) {
        SharedPreferences.Editor editor = context.getSharedPreferences("HDoctor", context.MODE_WORLD_WRITEABLE).edit();
        editor.putString("wchatID", wchatID);
        editor.commit();
        if (((MyApp) application).getLoginWXHandler() != null) {
            ((MyApp) application).getLoginWXHandler().sendEmptyMessage(0);
        }
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_ACCOUNT.equals(requestAction)) {
            Toast.makeText(context, "微信绑定成功", Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
        dismissProgressDialog();
    }

    public RequestProgressDialog showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new RequestProgressDialog(context);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
            return mProgressDialog;
        }
        return mProgressDialog;
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
