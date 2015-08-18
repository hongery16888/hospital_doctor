package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.PatientWDFB;

/**
 * Created by Administrator on 2015/8/7.
 */
public class PatientWDFBResponse {

    private ArrayList<PatientWDFB> publishlist;

    public ArrayList<PatientWDFB> getPublishlist() {
        return publishlist;
    }

    public void setPublishlist(ArrayList<PatientWDFB> publishlist) {
        this.publishlist = publishlist;
    }
}
