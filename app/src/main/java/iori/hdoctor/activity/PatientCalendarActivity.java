package iori.hdoctor.activity;

import android.view.View;
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

        mCalendarCard = (CalendarCard)findViewById(R.id.calendarCard1);
        mCalendarCard.setOnCellItemClick(new OnCellItemClick() {
            @Override
            public void onCellClick(View v, CardGridItem item) {
                Toast.makeText(PatientCalendarActivity.this, ((new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(item.getDate().getTime()))) + "", Toast.LENGTH_LONG).show();
            }
        });

    }

}
