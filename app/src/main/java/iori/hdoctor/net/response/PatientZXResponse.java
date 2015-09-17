package iori.hdoctor.net.response;

/**
 * Created by Administrator on 2015/8/29.
 */
public class PatientZXResponse {

    private String orderid;
    private String tel;
    private String parterID;
    private String sellerID;
    private String rsa_private;
    private String return_url;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getParterID() {
        return parterID;
    }

    public void setParterID(String parterID) {
        this.parterID = parterID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getRsa_private() {
        return rsa_private;
    }

    public void setRsa_private(String rsa_private) {
        this.rsa_private = rsa_private;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }
}
