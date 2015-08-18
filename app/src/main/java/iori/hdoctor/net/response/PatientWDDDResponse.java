package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.PatMyOrder;

/**
 * Created by Administrator on 2015/8/14.
 */
public class PatientWDDDResponse {

    private ArrayList<PatMyOrder> orderlist;

    public ArrayList<PatMyOrder> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(ArrayList<PatMyOrder> orderlist) {
        this.orderlist = orderlist;
    }
}
