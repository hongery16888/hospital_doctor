package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.DocBankRecord;

/**
 * Created by Administrator on 2015/8/7.
 */
public class DoctorBankRecordResponse {

    private ArrayList<DocBankRecord> reflectlist;

    public ArrayList<DocBankRecord> getReflectlist() {
        return reflectlist;
    }

    public void setReflectlist(ArrayList<DocBankRecord> reflectlist) {
        this.reflectlist = reflectlist;
    }
}
