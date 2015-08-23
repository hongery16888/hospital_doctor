package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.DocCircle;

/**
 * Created by Administrator on 2015/8/7.
 */
public class DoctorMyFaBiaoResponse {

    private ArrayList<DocCircle> myfabiaolist;

    public ArrayList<DocCircle> getMyfabiaolist() {
        return myfabiaolist;
    }

    public void setMyfabiaolist(ArrayList<DocCircle> myfabiaolist) {
        this.myfabiaolist = myfabiaolist;
    }
}
