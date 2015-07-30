package iori.hdoctor.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.view.crop.CropHandler;
import iori.hdoctor.view.crop.CropHelper;
import iori.hdoctor.view.crop.CropParams;

/**
 * Created with Android Studio.
 * User: ryan@xisue.com
 * Date: 10/1/14
 * Time: 10:58 AM
 * Desc: BasePhotoCropActivity
 */
public class BasePhotoCropActivity extends BaseActivity implements CropHandler {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropHelper.handleResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onPhotoCropped(Uri uri) {
    }

    @Override
    public void onCropCancel() {
    }

    @Override
    public void onCropFailed(String message) {
    }

    @Override
    public CropParams getCropParams() {
        return null;
    }

    @Override
    public Activity getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        if (getCropParams() != null)
            CropHelper.clearCachedCropFile(getCropParams().uri);
        super.onDestroy();
    }

    @Override
    protected int setContentViewResId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
