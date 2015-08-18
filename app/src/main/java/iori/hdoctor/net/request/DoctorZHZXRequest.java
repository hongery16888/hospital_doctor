package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorZHZXRequest extends BaseRequest {

	protected String password;
	protected String bindingphone;
	protected String bindingqq;
	protected String bindingwei;

	public DoctorZHZXRequest(String password, String bindingphone, String bindingqq, String bindingwei) {
		this.password = password;
		this.bindingphone = bindingphone;
		this.bindingqq = bindingqq;
		this.bindingwei = bindingwei;
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
		return PATH_DOC_ACCOUNT;
	}

	@Override
	public String getRequestAction() {
		return DOC_ACCOUNT;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
