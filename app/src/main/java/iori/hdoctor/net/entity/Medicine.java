package iori.hdoctor.net.entity;

/**
 * Created by Administrator on 2015/8/9.
 */
public class Medicine {

    private String medicineidid;
    private String img;
    private String name;
    private String beizhu;

    public String getMedicineidid() {
        return medicineidid;
    }

    public void setMedicineidid(String medicineidid) {
        this.medicineidid = medicineidid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}
