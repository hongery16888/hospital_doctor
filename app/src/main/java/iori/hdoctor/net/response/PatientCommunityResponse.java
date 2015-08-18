package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.PatCircle;
import iori.hdoctor.net.entity.PatWDYY;

/**
 * Created by Administrator on 2015/8/16.
 */
public class PatientCommunityResponse {

    private ArrayList<PatCircle> publiclist;

    public ArrayList<PatCircle> getPubliclist() {
        return publiclist;
    }

    public void setPubliclist(ArrayList<PatCircle> publiclist) {
        this.publiclist = publiclist;
    }
}
