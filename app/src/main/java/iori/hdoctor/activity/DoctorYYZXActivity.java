package iori.hdoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.activity.base.HDoctorCode;
import iori.hdoctor.adapter.DoctorYYGLAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.DocBlgl;
import iori.hdoctor.net.entity.DocReserve;
import iori.hdoctor.net.response.DoctorBlglResponse;
import iori.hdoctor.net.response.DoctorReserveResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorYYZXActivity extends BaseActivity implements NetworkConnectListener{

    private ArrayList<DocReserve> reserves = new ArrayList<>();
    private ArrayList<DocReserve> reservesAll = new ArrayList<>();
    private DoctorYYGLAdapter adapter;
    @InjectView(R.id.yygl_listview)
    ListView listView;
    @InjectView(R.id.all) RadioButton all;
    @OnClick({R.id.all, R.id.wait, R.id.done})
    public void statusBar(View view){
        if (view.getId() == R.id.all){
            adapter.setCaseArray(getDocReserve(HDoctorCode.ALL));
        }else if (view.getId() == R.id.wait){
            adapter.setCaseArray(getDocReserve(HDoctorCode.WAIT));
        }else if (view.getId() == R.id.done){
            adapter.setCaseArray(getDocReserve(HDoctorCode.DONE));
        }
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_yygl_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_yygl_title_main));
    }

    @Override
    protected void initData() {
        all.setChecked(true);
        adapter = new DoctorYYGLAdapter(this, reserves, new DisplayImageOptions.Builder().cloneFrom(getApp().getOptions()).showImageOnLoading(R.drawable.img_bl)
                .showImageForEmptyUri(R.drawable.img_bl).showImageOnFail(R.drawable.img_bl).build());
        listView.setAdapter(adapter);
        NetworkAPI.getNetworkAPI().docreserve(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_RESERVE.equals(requestAction)){
            reservesAll.addAll(((DoctorReserveResponse) data).getReservelist());
            adapter.setCaseArray(getDocReserve(HDoctorCode.ALL));
        }else if(HttpRequest.DOC_VERIFY.equals(requestAction)){
            all.setChecked(true);
            reservesAll.clear();
            reservesAll.addAll(((DoctorReserveResponse) data).getReservelist());
            adapter.setCaseArray(getDocReserve(HDoctorCode.ALL));
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }

    private ArrayList<DocReserve> getDocReserve(int type){
        reserves.clear();
        for (DocReserve reserve : reservesAll){
            if (HDoctorCode.ALL == type ||reserve.getIsok() == type)
                reserves.add(reserve);
        }
        return reserves;
    }
}
