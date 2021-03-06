package iori.hdoctor.activity.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.app.MyApp;
import iori.hdoctor.modules.common.BaseManager;
import iori.hdoctor.view.RequestProgressDialog;

public abstract class BaseActivity extends SysKeyInvalidActivity {

    @InjectView(R.id.left_icon)
    ImageView leftIcon;
    @InjectView(R.id.right_icon)
    ImageView rightIcon;
    @InjectView(R.id.mid_title)
    TextView title;
    @InjectView(R.id.right_text)
    TextView rightText;
    @InjectView(R.id.title_bar)
    View titleBar;
    @InjectView(R.id.mid_bar)
    View midBar;
    @InjectView(R.id.setting)
    View setting;
	
	protected Toast mToast;
    protected RequestProgressDialog mProgressDialog;

    public MyApp getApp() {
        return ((MyApp) getApplication());
    }

    @SuppressWarnings("unused")
    private void startAnmiActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentViewResId());
        ButterKnife.inject(this);
        initView();
        initData();
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T getViewById(int id) {
        return (T) findViewById(id);
    }

    protected abstract int setContentViewResId();

    protected abstract void initView();

    protected abstract void initData();

    public void setBackAction(){
        leftIcon.setImageResource(R.drawable.btn_back);
        leftIcon.setOnClickListener(backListener);
    }

    public void setHideBackAction(){
        leftIcon.setVisibility(View.GONE);
    }

    public void setLeftIconAction(int resId, View.OnClickListener listener){
        leftIcon.setImageResource(resId);
        leftIcon.setOnClickListener(listener);
    }

    public void showMidIcon(){
        midBar.setVisibility(View.VISIBLE);
    }

    public void setTitleAction(String titleStr){
        title.setText(titleStr);
        title.setVisibility(View.VISIBLE);
    }

    public void setRightIconAction(int resId, View.OnClickListener listener){
        rightIcon.setImageResource(resId);
        rightIcon.setOnClickListener(listener);
        rightIcon.setVisibility(View.VISIBLE);
    }

    public void setRightText(String textStr, View.OnClickListener listener){
        rightIcon.setVisibility(View.GONE);
        rightText.setVisibility(View.VISIBLE);
        rightText.setText(textStr);
        rightText.setOnClickListener(listener);
    }

    public void setHideTitleBar(boolean flag){
        titleBar.setVisibility(flag ? View.GONE : View.VISIBLE);
    }

    public void showSetting(View.OnClickListener listener){
        setting.setVisibility(View.VISIBLE);
        setting.setOnClickListener(listener);
    }

    public void setHideSetting(){
        setting.setVisibility(View.GONE);
    }

    public void showPublish(View.OnClickListener listener){
        rightText.setText("发表");
        rightText.setVisibility(View.VISIBLE);
        rightText.setOnClickListener(listener);
    }

    public void setHidePublish(){
        rightText.setVisibility(View.INVISIBLE);
    }

    public void setTitleBarBackground(int color){
        titleBar.setBackgroundColor(color);
    }

    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    /**
     * 网络检测并跳转
     * @param intent
     */
    public void startActivityByCheckNet(Intent intent) {
        boolean flag = getApp().isNetworkConnect();
        if(!flag)
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show();
    }
    
    
    /**
     * 网络检测,调用startActivityForResult启动activity
     * @param intent
     */
    public void startActivityByCheckNet(Intent intent,int requestCode) {
        boolean flag = getApp().isNetworkConnect();
        if (flag) {
        	startActivityForResult(intent,requestCode);
        } else {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show();
        }
    }
    

    /**
     * 网络检测
     */
    public boolean checkNet() {
        boolean flag = getApp().isNetworkConnect();
        if (flag) {
           return flag;
        } else {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show();
            return flag;
        }
    }

    public void hideInput(Context context,View view){
        InputMethodManager inputMethodManager =
                (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    
    /*显示提示信息*/
    protected void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    public RequestProgressDialog showProgressDialog() {
        if(mProgressDialog == null) {
            mProgressDialog = new RequestProgressDialog(this);
        }
        if(!mProgressDialog.isShowing()) {
            mProgressDialog.show();
            return mProgressDialog;
        }
        return mProgressDialog;
    }

    public void dismissProgressDialog() {
        if(mProgressDialog!=null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
        }
    }

    public static void DEBUG(String tag,String log){
        BaseManager.DEBUG(tag, log);
    }

    public static void INFO(String tag,String log){
    	BaseManager.INFO(tag,log);
    }

    public static void WARN(String tag,String log){
    	BaseManager.WARN(tag,log);
    }
    public static void ERROR(String tag,String log){
        BaseManager.ERROR(tag,log);
    }
}
