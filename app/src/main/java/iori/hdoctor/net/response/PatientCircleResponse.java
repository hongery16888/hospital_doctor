package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.PatCircle;

/**
 * Created by Administrator on 2015/8/7.
 */
public class PatientCircleResponse {

    private ArrayList<PatCircle> patquanzilist;

    public ArrayList<PatCircle> getPatquanzilist() {
        return patquanzilist;
    }

    public void setPatquanzilist(ArrayList<PatCircle> patquanzilist) {
        this.patquanzilist = patquanzilist;
    }
}
