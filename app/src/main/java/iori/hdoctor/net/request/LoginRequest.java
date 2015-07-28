package iori.hdoctor.net.request;

import java.util.HashMap;

public class LoginRequest extends BaseRequest {

	protected String username;
	protected String password;

	public LoginRequest(String username, String password) {
		this.username = username;
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
		return PATH_LOGIN;
	}

	@Override
	public String getRequestAction() {
		return LOGIN;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
