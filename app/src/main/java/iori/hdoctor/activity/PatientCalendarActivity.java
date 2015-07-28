package iori.hdoctor.activity;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.view.calendar.CalendarCard;
import iori.hdoctor.view.calendar.CardGridItem;
import iori.hdoctor.view.calendar.OnCellItemClick;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientCalendarActivity extends BaseActivity {

    private CalendarCard mCalendarCard;
    private int[] position = new int[2];
    private PopupWindow mPopupWindow;

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_calendar_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getResources().getString(R.string.patient_czsj_title_main));
    }

    @Override
    protected void initData() {
        mCalendarCard = (CalendarCard) findViewById(R.id.calendarCard1);
        mCalendarCard.setOnCellItemClick(new OnCellItemClick() {
            @Override
            public void onCellClick(View v, CardGridItem item) {
                v.getLocationInWindow(position);
                getPopupWindowInstance();
                mPopupWindow.showAsDropDown(v, 0 - (mPopupWindow.getContentView().getMeasuredWidth() - v.getMeasuredWidth())/2, 0 - (v.getMeasuredHeight() + mPopupWindow.getContentView().getMeasuredHeight()/2));
                Toast.makeText(PatientCalendarActivity.this, "X : " + mPopupWindow.getContentView().getMeasuredWidth(), Toast.LENGTH_LONG).show();
//                Toast.makeText(PatientCalendarActivity.this, ((new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(item.getDate().getTime()))) + "", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getPopupWindowInstance() {
        if (null != mPopupWindow) {
            mPopupWindow.dismiss();
            return;
        } else {
            initPopuptWindow();
        }
    }

    private void initPopuptWindow() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View popupWindow = layoutInflater.inflate(R.layout.pop_main, null);
        mPopupWindow = new PopupWindow(popupWindow, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.getContentView().measure(0,0);
    }
}