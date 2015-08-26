package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.Docintro;
import iori.hdoctor.net.entity.Servitem;

/**
 * Created by Administrator on 2015/8/25.
 */
public class PatientYSJSResponse {

    private ArrayList<Servitem> servitem;

    private Docintro docintro;

    public ArrayList<Servitem> getServitem() {
        return servitem;
    }

    public void setServitem(ArrayList<Servitem> servitem) {
        this.servitem = servitem;
    }

    public Docintro getDocintro() {
        return docintro;
    }

    public void setDocintro(Docintro docintro) {
        this.docintro = docintro;
    }
}
