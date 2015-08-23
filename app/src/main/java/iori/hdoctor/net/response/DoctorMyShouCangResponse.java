package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.DocCircle;

/**
 * Created by Administrator on 2015/8/7.
 */
public class DoctorMyShouCangResponse {

    private ArrayList<DocCircle> myshoucanglist;

    public ArrayList<DocCircle> getMyshoucanglist() {
        return myshoucanglist;
    }

    public void setMyshoucanglist(ArrayList<DocCircle> myshoucanglist) {
        this.myshoucanglist = myshoucanglist;
    }
}
