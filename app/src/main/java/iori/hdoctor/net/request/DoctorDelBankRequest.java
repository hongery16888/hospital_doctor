package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorDelBankRequest extends BaseRequest {

    protected String bankno;
    protected String submit;
    protected String operate;


    public DoctorDelBankRequest(String bankno, String submit, String operate) {
        this.bankno = bankno;
        this.submit = submit;
        this.operate = operate;
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
