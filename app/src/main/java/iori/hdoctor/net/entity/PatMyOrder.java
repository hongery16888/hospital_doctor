package iori.hdoctor.net.entity;

/**
 * Created by Administrator on 2015/8/14.
 */
public class PatMyOrder {
    private String did;
    private String orderid;
    private String type;
    private String price;
    private String zixunname;
    private String iscomment;
    private String hospital;
    private String realname;
    private String img;

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getZixunname() {
        return zixunname;
    }

    public void setZixunname(String zixunname) {
        this.zixunname = zixunname;
    }

    public String getIscomment() {
        return iscomment;
    }

    public void setIscomment(String iscomment) {
        this.iscomment = iscomment;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
