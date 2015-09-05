package iori.hdoctor.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientAddFriendActivity extends BaseActivity implements NetworkConnectListener{

    @InjectView(R.id.username)
    EditText username;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_add_friend_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction("添加好友");
    }

    @Override
    protected void initData() {

    }

    public void addFriend(View view){
        if (!TextUtils.isEmpty(username.getText().toString())){
            NetworkAPI.getNetworkAPI().pataddfriend(username.getText().toString(), showProgressDialog(), this);
        }else
            showToast("好友帐号不能为空");
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_ADD_FRIEND.equals(requestAction)){
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
