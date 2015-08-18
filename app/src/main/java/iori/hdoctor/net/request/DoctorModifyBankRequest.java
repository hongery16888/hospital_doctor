package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorModifyBankRequest extends BaseRequest {

    protected String bankno;
    protected String banksn;
    protected String submit;
    protected String operate;
    protected String bankisselect;

    public DoctorModifyBankRequest(String bankno, String banksn, String submit, String operate, String bankisselect) {
        this.bankno = bankno;
        this.banksn = banksn;
        this.submit = submit;
        this.operate = operate;
        this.bankisselect = bankisselect;
    }

    @Override
    public int postUserId() {
        return FORCE_POST;
    }

    @Override
    public boolean postFile() {
        return false;
    }

    @Override
    public String getRequestUrl() {
        return PATH_DOC_INCOME;
    }

    @Override
    public String getRequestAction() {
        return DOC_INCOME;
    }

    @Override
    public HashMap<String, String> getFileEncode() {
        // TODO Auto-generated method stub
        return null;
    }

}
