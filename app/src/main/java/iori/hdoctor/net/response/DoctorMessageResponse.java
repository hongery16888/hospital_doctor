package iori.hdoctor.net.response;


import java.util.ArrayList;

import iori.hdoctor.net.entity.DocMessage;

public class DoctorMessageResponse {

    private ArrayList<DocMessage> consultinglist;

    public ArrayList<DocMessage> getConsultinglist() {
        return consultinglist;
    }

    public void setConsultinglist(ArrayList<DocMessage> consultinglist) {
        this.consultinglist = consultinglist;
    }
}
