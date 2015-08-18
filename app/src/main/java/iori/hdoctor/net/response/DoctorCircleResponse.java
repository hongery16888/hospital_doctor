package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.DocCircle;
import iori.hdoctor.net.entity.PatCircle;

/**
 * Created by Administrator on 2015/8/7.
 */
public class DoctorCircleResponse {

    private ArrayList<DocCircle> quanzilist;

    public ArrayList<DocCircle> getQuanzilist() {
        return quanzilist;
    }

    public void setQuanzilist(ArrayList<DocCircle> quanzilist) {
        this.quanzilist = quanzilist;
    }
}
