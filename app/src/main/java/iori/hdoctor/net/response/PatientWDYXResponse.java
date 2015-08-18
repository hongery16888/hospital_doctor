package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.Medicine;

/**
 * Created by Administrator on 2015/8/7.
 */
public class PatientWDYXResponse {

    private ArrayList<Medicine> medicinelist;

    public ArrayList<Medicine> getMedicinelist() {
        return medicinelist;
    }

    public void setMedicinelist(ArrayList<Medicine> medicinelist) {
        this.medicinelist = medicinelist;
    }
}
