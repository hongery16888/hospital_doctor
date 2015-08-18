package iori.hdoctor.net.response;

import java.security.PrivateKey;

/**
 * Created by Administrator on 2015/8/7.
 */
public class DoctorZHZXResponse {

    private String password;
    private String bindingphone;
    private String bindingqq;
    private String bindingwei;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBindingphone() {
        return bindingphone;
    }

    public void setBindingphone(String bindingphone) {
        this.bindingphone = bindingphone;
    }

    public String getBindingqq() {
        return bindingqq;
    }

    public void setBindingqq(String bindingqq) {
        this.bindingqq = bindingqq;
    }

    public String getBindingwei() {
        return bindingwei;
    }

    public void setBindingwei(String bindingwei) {
        this.bindingwei = bindingwei;
    }
}


