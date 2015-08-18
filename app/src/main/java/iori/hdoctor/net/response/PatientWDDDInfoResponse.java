package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.PatWDYY;

/**
 * Created by Administrator on 2015/8/16.
 */
public class PatientWDDDInfoResponse {

    private String hospital;
    private String realname;
    private String img;
    private String orderid;
    private String zixunname;
    private String yuyuetime;
    private String price;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getZixunname() {
        return zixunname;
    }

    public void setZixunname(String zixunname) {
        this.zixunname = zixunname;
    }

    public String getYuyuetime() {
        return yuyuetime;
    }

    public void setYuyuetime(String yuyuetime) {
        this.yuyuetime = yuyuetime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
