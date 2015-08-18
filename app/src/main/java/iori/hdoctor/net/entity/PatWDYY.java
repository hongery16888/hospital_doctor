package iori.hdoctor.net.entity;

import java.security.PrivateKey;

/**
 * Created by Administrator on 2015/8/16.
 */
public class PatWDYY {

    private String orderid;
    private String tixingtime;
    private String yuyuetime;
    private String isremind;
    private String img;
    private String hospital;
    private String realname;
    private String address;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getTixingtime() {
        return tixingtime;
    }

    public void setTixingtime(String tixingtime) {
        this.tixingtime = tixingtime;
    }

    public String getYuyuetime() {
        return yuyuetime;
    }

    public void setYuyuetime(String yuyuetime) {
        this.yuyuetime = yuyuetime;
    }

    public String getIsremind() {
        return isremind;
    }

    public void setIsremind(String isremind) {
        this.isremind = isremind;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
