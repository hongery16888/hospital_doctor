package iori.hdoctor.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorFriendAdapter;
import iori.hdoctor.adapter.PatientFriendAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.Friend;
import iori.hdoctor.net.response.PatientFriendResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientFriendActivity extends BaseActivity implements NetworkConnectListener{

    private ArrayList<Friend> friends = new ArrayList<>();
    private PatientFriendAdapter adapter;

    private int tempId = R.id.mid_left_icon;

    @InjectView(R.id.listview)
    ListView listView;
    @InjectView(R.id.mid_left_icon)
    TextView midLeftText;
    @InjectView(R.id.mid_right_icon)
    TextView midRightText;
    @OnClick({R.id.mid_left_icon, R.id.mid_right_icon})
    public void midIcon(TextView textView){
        if (tempId != textView.getId())
            if (textView.getId() == R.id.mid_left_icon) {
                midLeftText.setTextColor(getResources().getColor(R.color.white));
                midRightText.setTextColor(getResources().getColor(R.color.global_title_color));
                midLeftText.setBackgroundResource(R.drawable.bg_tab2_lef);
                midRightText.setBackgroundResource(R.drawable.bg_tab2_right_hl);
                NetworkAPI.getNetworkAPI().patfriend(0, showProgressDialog(), this);
            }else{
                midLeftText.setTextColor(getResources().getColor(R.color.global_title_color));
                midRightText.setTextColor(getResources().getColor(R.color.white));
                midLeftText.setBackgroundResource(R.drawable.bg_tab2_left_hl);
                midRightText.setBackgroundResource(R.drawable.bg_tab2_right);
                NetworkAPI.getNetworkAPI().patfriend(1, showProgressDialog(), this);
            }
        tempId = textView.getId();
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_circle_friend_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setRightText(getResources().getString(R.string.doctor_circle_info_friend_add), addListener);
        showMidIcon();

        midLeftText.setTextColor(getResources().getColor(R.color.white));
        midRightText.setTextColor(getResources().getColor(R.color.global_title_color));
        midLeftText.setBackgroundResource(R.drawable.bg_tab2_lef);
        midRightText.setBackgroundResource(R.drawable.bg_tab2_right_hl);
    }

    @Override
    protected void initData() {
        adapter = new PatientFriendAdapter(this, friends);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (RongIM.getInstance() != null) {
                    getApp().setIsDoc(false);
                    RongIM.getInstance().startPrivateChat(
                            PatientFriendActivity.this,
                            friends.get(position).getPersonalid(),
                            friends.get(position).getNicheng());
                }
            }
        });

        NetworkAPI.getNetworkAPI().patfriend(0, showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_FRIEND.equals(requestAction)){
            friends.clear();
            if (data != null && ((PatientFriendResponse)data).getFriendslist() != null) {
                friends.addAll(((PatientFriendResponse) data).getFriendslist());
                adapter.addData(friends);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (RongIM.getInstance() != null) {
                            for (int i = 0; i < friends.size(); i++) {
                                RongIM.getInstance().refreshUserInfoCache(
                                        new UserInfo(friends.get(i).getPersonalid(),
                                                friends.get(i).getNicheng(),
                                                Uri.parse(HttpRequest.PHOTO_PATH + friends.get(i).getImg())));
                            }
                        }
                    }
                }).start();
            }else{
                adapter.addData(new ArrayList<Friend>());
            }
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        if (HttpRequest.PAT_FRIEND.equals(requestAction)){
            adapter.addData(new ArrayList<Friend>());
        }
        showToast(errorMsg);
        dismissProgressDialog();
    }

    private View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(PatientFriendActivity.this, PatientAddFriendActivity.class));
        }
    };

}
