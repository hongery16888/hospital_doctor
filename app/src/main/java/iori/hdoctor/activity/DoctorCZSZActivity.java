package iori.hdoctor.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.DoctorChuZhenResponse;
import iori.hdoctor.view.crop.CropHelper;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorCZSZActivity extends BaseActivity implements NetworkConnectListener{

    private PopupWindow mWeekPopWindow;
    private CheckBox one, two, three, four, five, six, seven;
    private String weeks;

    @InjectView(R.id.dset)
    TextView dset;
    @InjectView(R.id.dnum)
    TextView dnum;
    @InjectView(R.id.status)
    TextView status;
    @InjectView(R.id.isopen)
    CheckBox isOpen;

    @OnClick(R.id.week)
    public void week(){
        getPhotoPopWindowInstance();
        mWeekPopWindow.setFocusable(true);
        mWeekPopWindow.showAtLocation(this
                .findViewById(R.id.persion_main), Gravity.BOTTOM | Gravity
                .CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_czgl_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.doctor_czgl_title_main));
        setRightText(getString(R.string.doctor_ffgl_info_save), saveListener);
    }

    @Override
    protected void initData() {
        isOpen.setOnCheckedChangeListener(openListener);
        NetworkAPI.getNetworkAPI().docchuzhen(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_CHUZHEN.equals(requestAction)){
            dset.setText(((DoctorChuZhenResponse)data).getDset());
            dnum.setText(((DoctorChuZhenResponse)data).getDnum());
            isOpen.setChecked(((DoctorChuZhenResponse)data).getIsopen().equals("1")?true:false);
            status.setText(((DoctorChuZhenResponse)data).getIsopen().equals("1")? R.string.doctor_cagl_start_diagnose : R.string.doctor_cagl_stop_diagnose);
        }else if (HttpRequest.DOC_CHUSET.equals(requestAction)){
            showToast(getString(R.string.doctor_cz_success));
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }

    private CompoundButton.OnCheckedChangeListener openListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            status.setText(isChecked? R.string.doctor_cagl_start_diagnose : R.string.doctor_cagl_stop_diagnose);
        }
    };

    private View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!TextUtils.isEmpty(dnum.getText().toString()))
                NetworkAPI.getNetworkAPI().docChuSet(weeks, isOpen.isChecked()?"1":"", dnum.getText().toString(), showProgressDialog(), DoctorCZSZActivity.this);
            else
                showToast(getString(R.string.doctor_dnum_empty));
        }
    };

    private void getPhotoPopWindowInstance() {
        if (null != mWeekPopWindow) {
            mWeekPopWindow.dismiss();
            return;
        } else {
            initPhotoPopWindow();
        }
    }

    private void initPhotoPopWindow() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View popupWindow = layoutInflater.inflate(R.layout.week_pop_main, null);
        mWeekPopWindow = new PopupWindow(popupWindow, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mWeekPopWindow.getContentView().measure(0, 0);
        mWeekPopWindow.setAnimationStyle(R.style.mypopwindow_anim_style);

        one = (CheckBox)popupWindow.findViewById(R.id.one);
        two = (CheckBox)popupWindow.findViewById(R.id.two);
        three = (CheckBox)popupWindow.findViewById(R.id.three);
        four = (CheckBox)popupWindow.findViewById(R.id.four);
        five = (CheckBox)popupWindow.findViewById(R.id.five);
        six = (CheckBox)popupWindow.findViewById(R.id.six);
        seven = (CheckBox)popupWindow.findViewById(R.id.seven);

        popupWindow.findViewById(R.id.week_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWeekPopWindow.dismiss();
            }
        });

        popupWindow.findViewById(R.id.week_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weeks = (one.isChecked()?getString(R.string.one) + "、":"") +
                        (two.isChecked()?getString(R.string.two) + "、":"") +
                        (three.isChecked()?getString(R.string.three) + "、":"") +
                        (four.isChecked()?getString(R.string.four) + "、":"") +
                        (five.isChecked()?getString(R.string.five) + "、":"") +
                        (six.isChecked()?getString(R.string.six) + "、":"") +
                        (seven.isChecked()?getString(R.string.seven) + "、":"");

                weeks = weeks.substring(0, weeks.length() - 1);

                if (one.isChecked() && two.isChecked() &&
                        three.isChecked() && four.isChecked() &&
                        five.isChecked() && six.isChecked() &&
                        seven.isChecked() ) {
                    weeks = getString(R.string.everyday);
                }

                dset.setText(weeks);

                mWeekPopWindow.dismiss();
            }
        });

    }
}
