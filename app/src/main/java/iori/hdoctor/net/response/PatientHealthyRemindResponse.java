package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.HealthyRemind;

/**
 * Created by Administrator on 2015/8/7.
 */
public class PatientHealthyRemindResponse {

    private ArrayList<HealthyRemind> orderlist;

    private ArrayList<HealthyRemind> remindlist;

    public ArrayList<HealthyRemind> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(ArrayList<HealthyRemind> orderlist) {
        this.orderlist = orderlist;
    }

    public ArrayList<HealthyRemind> getRemindlist() {
        return remindlist;
    }

    public void setRemindlist(ArrayList<HealthyRemind> remindlist) {
        this.remindlist = remindlist;
    }
}
