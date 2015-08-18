package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientRegisterPhoneRequest extends BaseRequest {

	protected String name;
	protected String password;
	protected int type;
	protected String submit;

	public PatientRegisterPhoneRequest(String name, String password, int type, String submit) {
		this.name = name;
		this.password = password;
		this.type = type;
		this.submit = submit;
	}
	
	@Override
	public int postUserId() {
		return UNABLE_POST;
	}

	@Override
	public boolean postFile() {
		return false;
	}

	@Override
	public String getRequestUrl() {
		return PATH_PAT_REGISTER_PHONE;
	}

	@Override
	public String getRequestAction() {
		return PAT_REGISTER_PHONE;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
