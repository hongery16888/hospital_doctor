package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.PatientWDFB;
import iori.hdoctor.net.entity.PatientWDSC;

/**
 * Created by Administrator on 2015/8/7.
 */
public class PatientWDSCResponse {

    private ArrayList<PatientWDSC> collectionlist;

    public ArrayList<PatientWDSC> getCollectionlist() {
        return collectionlist;
    }

    public void setCollectionlist(ArrayList<PatientWDSC> collectionlist) {
        this.collectionlist = collectionlist;
    }
}
