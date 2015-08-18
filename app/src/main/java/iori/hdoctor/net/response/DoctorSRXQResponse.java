package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.DocBankInfo;

public class DoctorSRXQResponse {

	private String total;
    private ArrayList<DocBankInfo> banklist;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<DocBankInfo> getBanklist() {
        return banklist;
    }

    public void setBanklist(ArrayList<DocBankInfo> banklist) {
        this.banklist = banklist;
    }
}
