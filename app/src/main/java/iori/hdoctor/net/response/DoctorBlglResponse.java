package iori.hdoctor.net.response;


import java.util.ArrayList;

import iori.hdoctor.net.entity.DocBlgl;

public class DoctorBlglResponse {

    ArrayList<DocBlgl> caselist;

    public ArrayList<DocBlgl> getCaselist() {
        return caselist;
    }

    public void setCaselist(ArrayList<DocBlgl> caselist) {
        this.caselist = caselist;
    }
}
