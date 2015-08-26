package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.PatNearDoc;
import iori.hdoctor.net.entity.PatZiZhiDoc;

/**
 * Created by Administrator on 2015/8/23.
 */
public class PatientZiZhiDocResponse {

    private ArrayList<PatNearDoc> datalist;

    public ArrayList<PatNearDoc> getDatalist() {
        return datalist;
    }

    public void setDatalist(ArrayList<PatNearDoc> datalist) {
        this.datalist = datalist;
    }
}
