package iori.hdoctor.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.adapter.DoctorCircleAdapter;
import iori.hdoctor.adapter.DoctorCircleInfoAdapter;
import iori.hdoctor.adapter.PatientCircleInfoAdapter;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.DoctorCircleInfoResponse;
import iori.hdoctor.net.response.PatientCircleInfoResponse;
import iori.hdoctor.view.CircleBitmapDisplayer;
import iori.hdoctor.view.NoScrollListView;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorCircleInfoActivity extends BaseActivity implements NetworkConnectListener{

    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    @InjectView(R.id.head)
    ImageView head;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.time)
    TextView time;
    @InjectView(R.id.img)
    ImageView img;
    @InjectView(R.id.content)
    TextView content;
    @InjectView(R.id.best)
    TextView best;
    @InjectView(R.id.commentnum)
    TextView commentNum;
    @InjectView(R.id.reply_content)
    EditText replyContent;

    @OnClick(R.id.best)
    public void zan(){
        if (getIntent().getStringExtra("type") != null && getIntent().getStringExtra("type").equals("allpublic"))
            NetworkAPI.getNetworkAPI().docpubliczan(getIntent().getStringExtra("frumid"), showProgressDialog(), DoctorCircleInfoActivity.this);
        else if (getIntent().getStringExtra("type") != null && getIntent().getStringExtra("type").equals("circle_ys"))
            NetworkAPI.getNetworkAPI().docquanzan(getIntent().getStringExtra("frumid"), showProgressDialog(), DoctorCircleInfoActivity.this);
        else
            NetworkAPI.getNetworkAPI().doccirclezan(getIntent().getStringExtra("frumid"), showProgressDialog(), DoctorCircleInfoActivity.this);
    }

    @OnClick(R.id.reply)
    public void reply(){
        if (!TextUtils.isEmpty(replyContent.getText().toString()))
            NetworkAPI.getNetworkAPI().docreply(getIntent().getStringExtra("frumid"), replyContent.getText().toString(), showProgressDialog(), this);
        else
            showToast("评论不能为空");
    }

    @InjectView(R.id.circle_info_list)
    NoScrollListView listView;

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_circle_info_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_circle_title_main));
        setRightIconAction(R.drawable.btn_sc, scListener);
    }

    @Override
    protected void initData() {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_avater_blgl_head)
                .showImageForEmptyUri(R.drawable.img_avater_blgl_head)
                .showImageOnFail(R.drawable.img_avater_blgl_head)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new CircleBitmapDisplayer())
                .build();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        NetworkAPI.getNetworkAPI().doccomments(getIntent().getStringExtra("frumid"), showProgressDialog(), this);
    }

    private View.OnClickListener scListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NetworkAPI.getNetworkAPI().docshoucang(getIntent().getStringExtra("frumid"), showProgressDialog(), DoctorCircleInfoActivity.this);
        }
    };

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_COMMENTS.equals(requestAction)){
            replyContent.setText(getString(R.string.text_null));
            listView.setAdapter(new DoctorCircleInfoAdapter(this, ((DoctorCircleInfoResponse) data).getPinglunlist()));
            imageLoader.displayImage(HttpRequest.PHOTO_PATH + ((DoctorCircleInfoResponse) data).getUimg(), head, options);
            imageLoader.displayImage(HttpRequest.PHOTO_PATH + ((DoctorCircleInfoResponse) data).getImg(), img, getApp().getOptions());
            name.setText(((DoctorCircleInfoResponse) data).getNicheng());
            time.setText(((DoctorCircleInfoResponse) data).getAddtime());
            content.setText(((DoctorCircleInfoResponse) data).getContent());
            best.setText(((DoctorCircleInfoResponse) data).getBest());
            commentNum.setText(((DoctorCircleInfoResponse) data).getCommentnum());
        }else if(HttpRequest.DOC_SHOUCANG.equals(requestAction)){
            showToast("收藏成功");
        }else if(HttpRequest.DOC_ALL_PUBLISH.equals(requestAction)){
            best.setText((Integer.parseInt(best.getText().toString())+1) + "");
        }else if (HttpRequest.DOC_CIRCLE.equals(requestAction)){
            best.setText((Integer.parseInt(best.getText().toString())+1) + "");
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }
}
