package iori.hdoctor.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorTiXianActivity extends BaseActivity {

    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    @InjectView(R.id.total)
    TextView total;
    @InjectView(R.id.tixian)
    TextView tixian;
    @InjectView(R.id.bank_ico)
    ImageView bankIcon;
    @InjectView(R.id.bank_name)
    TextView bankName;
    @InjectView(R.id.bank_no)
    TextView bankNo;

    @OnClick(R.id.confirm)
    public void confirm(){
        if (Integer.parseInt(getIntent().getStringExtra("total")) < 500){
            showToast("总金额小于500不能提现");
            return;
        }

        if (TextUtils.isEmpty(tixian.getText().toString())){
            showToast("提现金额不能为空");
            return;
        }else if (Integer.parseInt(tixian.getText().toString()) < 500){
            showToast("提现金额不能小于500");
            return;
        }else{

        }

    }

    @Override
    protected int setContentViewResId() {
        return R.layout.tixian_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction("提现");
    }

    @Override
    protected void initData() {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.icon_tx_switch_ball)
                .showImageOnFail(R.drawable.icon_tx_switch_ball)
                .showImageForEmptyUri(R.drawable.icon_tx_switch_ball)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        imageLoader.displayImage(HttpRequest.BASIC_PATH + "yinhang/" + getIntent().getStringExtra("banksn") + ".jpg", bankIcon, options);
        total.setText(getIntent().getStringExtra("total"));
        bankName.setText(getIntent().getStringExtra("bankname"));
        bankNo.setText("**** **** **** " + getIntent().getStringExtra("bankno").substring(getIntent().getStringExtra("bankno").length()-4, getIntent().getStringExtra("bankno").length()));

    }

}
