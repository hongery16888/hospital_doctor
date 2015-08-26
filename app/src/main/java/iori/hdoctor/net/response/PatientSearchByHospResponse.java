package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.PatNearHosp;

/**
 * Created by Administrator on 2015/8/23.
 */
public class PatientSearchByHospResponse {

    private ArrayList<PatNearHosp> datalist;

    public ArrayList<PatNearHosp> getDatalist() {
        return datalist;
    }

    public void setDatalist(ArrayList<PatNearHosp> datalist) {
        this.datalist = datalist;
    }
}
