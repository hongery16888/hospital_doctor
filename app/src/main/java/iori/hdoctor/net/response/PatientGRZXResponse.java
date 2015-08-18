package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.PatWDYY;

/**
 * Created by Administrator on 2015/8/16.
 */
public class PatientGRZXResponse {

    private String img;
    private String nicheng;
    private String ordernum;
    private String yuyuenum;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNicheng() {
        return nicheng;
    }

    public void setNicheng(String nicheng) {
        this.nicheng = nicheng;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getYuyuenum() {
        return yuyuenum;
    }

    public void setYuyuenum(String yuyuenum) {
        this.yuyuenum = yuyuenum;
    }
}
