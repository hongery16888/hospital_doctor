package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.ConsultRecord;

/**
 * Created by Administrator on 2015/8/7.
 */
public class PatientConsultRecordResponse {

    private ArrayList<ConsultRecord> consultrecordlist;

    public ArrayList<ConsultRecord> getConsultrecordlist() {
        return consultrecordlist;
    }

    public void setConsultrecordlist(ArrayList<ConsultRecord> consultrecordlist) {
        this.consultrecordlist = consultrecordlist;
    }
}
