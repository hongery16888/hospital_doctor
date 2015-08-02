package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorLoginRequest extends BaseRequest {

	protected String name;
	protected String password;

	public DoctorLoginRequest(String name, String password) {
		this.name = name;
		this.password = password;
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
		return PATH_DOC_LOGIN;
	}

	@Override
	public String getRequestAction() {
		return DOC_LOGIN;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
