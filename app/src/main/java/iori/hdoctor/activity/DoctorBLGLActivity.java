package iori.hdoctor.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.activity.base.HDoctorCode;
import iori.hdoctor.adapter.DoctorBLGLAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.DocBlgl;
import iori.hdoctor.net.response.DoctorBlglResponse;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorBLGLActivity extends BaseActivity implements NetworkConnectListener{

    private DoctorBLGLAdapter adapter;
    private ArrayList<DocBlgl> docBlgls = new ArrayList<>();
    private ArrayList<DocBlgl> docBlglsAll = new ArrayList<>();
    private int clickId = R.id.all;

    @InjectView(R.id.blgl_listview)
    ListView listView;
    @InjectView(R.id.all)
    RadioButton all;
    @OnClick(R.id.all)
    public void all(RadioButton all){
        if (all.getId() != clickId && docBlgls.size() > 0){
            adapter.setCaseArray(getDocBlgls(HDoctorCode.ALL));
            clickId = all.getId();
        }
    }
    @OnClick(R.id.wait)
    public void waits(RadioButton wait){
        if (wait.getId() != clickId){
            adapter.setCaseArray(getDocBlgls(HDoctorCode.WAIT));
            clickId = wait.getId();
        }
    }
    @OnClick(R.id.done)
    public void done(RadioButton done){
        if (done.getId() != clickId){
            adapter.setCaseArray(getDocBlgls(HDoctorCode.DONE));
            clickId = done.getId();
        }
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_blgl_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_blgl_title_main));
    }

    @Override
    protected void initData() {
        all.setChecked(true);
        adapter = new DoctorBLGLAdapter(this , docBlglsAll, new DisplayImageOptions.Builder().cloneFrom(getApp().getOptions()).showImageOnLoading(R.drawable.img_bl)
                .showImageForEmptyUri(R.drawable.img_bl).showImageOnFail(R.drawable.img_bl).build());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DoctorBLGLActivity.this, DoctorBLGLInfoActivity.class);
                intent.putExtra("orderId", docBlgls.get(position).getOrderid());
                startActivity(intent);
            }
        });
        NetworkAPI.getNetworkAPI().casesdesc(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_CASES.equals(requestAction) && ((DoctorBlglResponse)data).getCaselist().size() > 0){
            docBlglsAll.addAll(((DoctorBlglResponse) data).getCaselist());
            adapter.setCaseArray(getDocBlgls(HDoctorCode.ALL));
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }

    private ArrayList<DocBlgl> getDocBlgls(int type){
        docBlgls.clear();
        for (DocBlgl docBlgl : docBlglsAll){
            if (HDoctorCode.ALL == type ||docBlgl.getIsok() == type)
                docBlgls.add(docBlgl);
        }
        return docBlgls;
    }
}
