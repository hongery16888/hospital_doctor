package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.PatWDYY;

/**
 * Created by Administrator on 2015/8/16.
 */
public class PatientWDYYResponse {

    private ArrayList<PatWDYY> appointmentlist;

    public ArrayList<PatWDYY> getAppointmentlist() {
        return appointmentlist;
    }

    public void setAppointmentlist(ArrayList<PatWDYY> appointmentlist) {
        this.appointmentlist = appointmentlist;
    }
}
