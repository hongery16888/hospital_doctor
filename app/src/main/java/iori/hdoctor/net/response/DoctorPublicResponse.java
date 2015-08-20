package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.DocCircle;

/**
 * Created by Administrator on 2015/8/7.
 */
public class DoctorPublicResponse {

    private ArrayList<DocCircle> allpubliclist;

    public ArrayList<DocCircle> getAllpubliclist() {
        return allpubliclist;
    }

    public void setAllpubliclist(ArrayList<DocCircle> allpubliclist) {
        this.allpubliclist = allpubliclist;
    }
}
