package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.DocContent;
import iori.hdoctor.net.entity.PatContent;

/**
 * Created by Administrator on 2015/8/17.
 */
public class DoctorCircleInfoResponse {

    private String img;
    private String pubuid;
    private String frumid;
    private String uimg;
    private String nicheng;
    private String addtime;
    private String content;
    private String commentnum;
    private String best;
    private ArrayList<DocContent> pinglunlist;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPubuid() {
        return pubuid;
    }

    public void setPubuid(String pubuid) {
        this.pubuid = pubuid;
    }

    public String getFrumid() {
        return frumid;
    }

    public void setFrumid(String frumid) {
        this.frumid = frumid;
    }

    public String getUimg() {
        return uimg;
    }

    public void setUimg(String uimg) {
        this.uimg = uimg;
    }

    public String getNicheng() {
        return nicheng;
    }

    public void setNicheng(String nicheng) {
        this.nicheng = nicheng;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(String commentnum) {
        this.commentnum = commentnum;
    }

    public String getBest() {
        return best;
    }

    public void setBest(String best) {
        this.best = best;
    }

    public ArrayList<DocContent> getPinglunlist() {
        return pinglunlist;
    }

    public void setPinglunlist(ArrayList<DocContent> pinglunlist) {
        this.pinglunlist = pinglunlist;
    }
}
