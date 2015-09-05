package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.Friend;

/**
 * Created by Administrator on 2015/8/29.
 */
public class DoctorFriendResponse {

    private ArrayList<Friend> dochaoyoulist;

    public ArrayList<Friend> getDochaoyoulist() {
        return dochaoyoulist;
    }

    public void setDochaoyoulist(ArrayList<Friend> dochaoyoulist) {
        this.dochaoyoulist = dochaoyoulist;
    }
}
