package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.PatNearDoc;

/**
 * Created by Administrator on 2015/8/23.
 */
public class PatientSearchByDocResponse {

    private ArrayList<PatNearDoc> datalist;

    public ArrayList<PatNearDoc> getDatalist() {
        return datalist;
    }

    public void setDatalist(ArrayList<PatNearDoc> datalist) {
        this.datalist = datalist;
    }
}
