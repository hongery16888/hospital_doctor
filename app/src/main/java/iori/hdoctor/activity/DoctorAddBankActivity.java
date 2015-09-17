package iori.hdoctor.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.view.ScrollerNumberPicker;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorAddBankActivity extends BaseActivity implements NetworkConnectListener{

    private PopupWindow mBankPopWindow;
    private ColorDrawable dw = new ColorDrawable(0xb0000000);
    private ScrollerNumberPicker bankPick;
    private ArrayList<String> bankSns = new ArrayList<>();
    private ArrayList<String> bankNames = new ArrayList<>();
    private int bankId;
    @InjectView(R.id.bank_name)
    TextView bankName;
    @InjectView(R.id.bank_no)
    EditText bankNo;

    @OnClick(R.id.bank_lay)
    public void setBankName(){
        getBankPopWindowInstance();
        mBankPopWindow.setFocusable(true);
        mBankPopWindow.showAtLocation(this
                .findViewById(R.id.persion_main), Gravity.BOTTOM | Gravity
                .CENTER_HORIZONTAL, 0, 0);
    }

    @OnClick(R.id.confirm)
    public void confirm(){
        if (TextUtils.isEmpty(bankName.getText().toString()) ||
                TextUtils.isEmpty(bankNo.getText().toString())){
            showToast("请选择银行或输入卡号");
            return;
        }
        NetworkAPI.getNetworkAPI().docAddBank(bankName.getText().toString(), bankNo.getText().toString(),
                bankSns.get(bankId), showProgressDialog(), this);
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_add_bank_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.doctor_add_bank));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_INCOME.equals(requestAction)){
            finish();
            getApp().getRefreshBankHandler().sendEmptyMessage(0);
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }

    private void getBankPopWindowInstance() {
        if (null != mBankPopWindow) {
            mBankPopWindow.dismiss();
            return;
        } else {
            initBankPopWindow();
        }
    }

    private void initBankPopWindow() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View popupWindow = layoutInflater.inflate(R.layout.bank_pop_main, null);
        mBankPopWindow = new PopupWindow(popupWindow, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mBankPopWindow.getContentView().measure(0, 0);
        mBankPopWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        //设置SelectPicPopupWindow弹出窗体的背景
        mBankPopWindow.setBackgroundDrawable(dw);
        mBankPopWindow.setOutsideTouchable(true);

        bankPick = (ScrollerNumberPicker) popupWindow.findViewById(R.id.bank_picker);

        String[] bankSnsstr = getResources().getStringArray(R.array.bank_sn);
        for (int i = 0; i < bankSnsstr.length; i++){
            bankSns.add(bankSnsstr[i]);
        }

        String[] bankNamesstr = getResources().getStringArray(R.array.bank_name);
        for (int i = 0; i < bankNamesstr.length; i++){
            bankNames.add(bankNamesstr[i]);
        }

        bankPick.setData(this.bankNames);
        bankPick.setDefault(0);

        bankPick.setOnSelectListener(new ScrollerNumberPicker.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                bankId = id;
            }

            @Override
            public void selecting(int id, String text) {
                bankId = id;
            }
        });

        popupWindow.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBankPopWindow.dismiss();
            }
        });
        popupWindow.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bankName.setText(bankNames.get(bankId));
                mBankPopWindow.dismiss();
            }
        });
        popupWindow.findViewById(R.id.outside).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBankPopWindow.dismiss();
            }
        });
    }
}
