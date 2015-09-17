package iori.hdoctor.net.response;

/**
 * Created by Administrator on 2015/9/10.
 */
public class PatientScanSnResponse {

    private String img;
    private String beizhu;
    private String name;
    private String sn;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
