package iori.hdoctor.activity;

import android.graphics.Color;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.DoctorStaticResponse;
import iori.hdoctor.view.PinChart;

/**
 * Created by Administrator on 2015/7/11.
 */
public class DoctorStatisticsActivity extends BaseActivity implements NetworkConnectListener {

    @InjectView(R.id.chart)
    PinChart mChart;
    private DecimalFormat df = new DecimalFormat("###.00");
    private float[] humidity = {360, 0};
    private String str[] = {"100%", "0%"};

    @InjectView(R.id.doctor_visitor_total) TextView visitorTotal;
    @InjectView(R.id.doctor_patient_total) TextView patientTotal;
    @InjectView(R.id.doctor_today_visitor) TextView todayVisitor;
    @InjectView(R.id.doctor_today_patient) TextView todayPatient;
    @InjectView(R.id.today_zxzx) TextView todayZxzx;
    @InjectView(R.id.today_dhzx) TextView todayDhxz;
    @InjectView(R.id.today_yyzx) TextView todayYyzx;
    @InjectView(R.id.doctor_yesterday_visitor) TextView yesVisitor;
    @InjectView(R.id.doctor_yesterday_patient) TextView yesPatient;
    @InjectView(R.id.yesterday_zxzx) TextView yesZxzx;
    @InjectView(R.id.yesterday_dhzx) TextView yesDhzx;
    @InjectView(R.id.yesterday_yyzx) TextView yesYyzx;

    @Override
    protected int setContentViewResId() {
        return R.layout.doctor_statistics_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.doctor_statistics_title_main));
    }

    @Override
    protected void initData() {
        mChart.setHumidity(humidity);
        mChart.setStr(str);
        mChart.start(2);
        NetworkAPI.getNetworkAPI().docStatic(showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.DOC_STATIC.equals(requestAction)){

            int vistorTotals = Integer.parseInt(((DoctorStaticResponse)data).getTotalstatic().getVnum());
            int patientTotals = Integer.parseInt(((DoctorStaticResponse) data).getTotalstatic().getHnum());

            visitorTotal.setText(((DoctorStaticResponse)data).getTotalstatic().getVnum());
            patientTotal.setText(((DoctorStaticResponse)data).getTotalstatic().getHnum());
            todayVisitor.setText(((DoctorStaticResponse)data).getTodaystatic().getVnum());
            todayPatient.setText(((DoctorStaticResponse)data).getTodaystatic().getHnum());
            todayZxzx.setText(((DoctorStaticResponse)data).getTodaystatic().getOnlinenum());
            todayDhxz.setText(((DoctorStaticResponse)data).getTodaystatic().getTelnum());
            todayYyzx.setText(((DoctorStaticResponse)data).getTodaystatic().getYuyuenum());
            yesVisitor.setText(((DoctorStaticResponse)data).getYesterdaystatic().getVnum());
            yesPatient.setText(((DoctorStaticResponse)data).getYesterdaystatic().getHnum());
            yesZxzx.setText(((DoctorStaticResponse)data).getYesterdaystatic().getOnlinenum());
            yesDhzx.setText(((DoctorStaticResponse)data).getTodaystatic().getTelnum());
            yesYyzx.setText(((DoctorStaticResponse)data).getTodaystatic().getYuyuenum());

            float[] humidity = {360 - (patientTotals*100)/vistorTotals, (patientTotals*100)/vistorTotals};

            String str[] = { (100 - (patientTotals*100)/vistorTotals) + "%", (patientTotals*100)/vistorTotals  + "%"};

            mChart.setHumidity(humidity);
            mChart.setStr(str);
            mChart.start(2);

        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {

        showToast(errorMsg);
        dismissProgressDialog();
    }
}
