package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.Friend;

/**
 * Created by Administrator on 2015/8/29.
 */
public class PatientFriendResponse {

    private ArrayList<Friend> friendslist;

    public ArrayList<Friend> getFriendslist() {
        return friendslist;
    }

    public void setFriendslist(ArrayList<Friend> friendslist) {
        this.friendslist = friendslist;
    }
}
