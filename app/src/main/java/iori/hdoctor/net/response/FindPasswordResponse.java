package iori.hdoctor.net.response;

/**
 * Created by Administrator on 2015/9/7.
 */
public class FindPasswordResponse {

    private String userId;

    private String pwdcode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPwdcode() {
        return pwdcode;
    }

    public void setPwdcode(String pwdcode) {
        this.pwdcode = pwdcode;
    }
}
