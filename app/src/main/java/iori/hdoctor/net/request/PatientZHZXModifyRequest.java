package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientZHZXModifyRequest extends BaseRequest {

	protected String password;
	protected String bindingphone;
	protected String bindingqq;
	protected String bindingwei;
	protected String submit;

	public PatientZHZXModifyRequest(String password, String bindingphone, String bindingqq, String bindingwei, String submit) {
		this.password = password;
		this.bindingphone = bindingphone;
		this.bindingqq = bindingqq;
		this.bindingwei = bindingwei;
		this.submit = submit;
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
		return PATH_PAT_ACCOUNT;
	}

	@Override
	public String getRequestAction() {
		return PAT_ACCOUNT;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
