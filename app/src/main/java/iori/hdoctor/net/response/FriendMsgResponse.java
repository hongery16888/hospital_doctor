package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.FriendMsg;

/**
 * Created by Administrator on 2015/9/12.
 */
public class FriendMsgResponse {

    private ArrayList<FriendMsg> addfriend;

    public ArrayList<FriendMsg> getAddfriend() {
        return addfriend;
    }

    public void setAddfriend(ArrayList<FriendMsg> addfriend) {
        this.addfriend = addfriend;
    }
}
