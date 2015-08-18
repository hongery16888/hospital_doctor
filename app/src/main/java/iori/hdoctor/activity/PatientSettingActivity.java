package iori.hdoctor.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientSettingActivity extends BaseActivity {

    @InjectView(R.id.version)
    TextView version;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_setting_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.doctor_setting_title_main));
    }

    @Override
    protected void initData() {
        version.setText(getVersion());
    }

    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return "版本 QLXGJ " + version;
        } catch (Exception e) {
            e.printStackTrace();
            return "版本 QLXGJ " + "0";
        }
    }

}
