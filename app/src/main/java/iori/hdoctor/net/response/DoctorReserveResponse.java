package iori.hdoctor.net.response;


import java.util.ArrayList;

import iori.hdoctor.net.entity.DocReserve;

public class DoctorReserveResponse {

    private ArrayList<DocReserve> reservelist;

    public ArrayList<DocReserve> getReservelist() {
        return reservelist;
    }

    public void setReservelist(ArrayList<DocReserve> reservelist) {
        this.reservelist = reservelist;
    }

}
