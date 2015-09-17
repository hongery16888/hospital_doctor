package iori.hdoctor.net.request;

import java.util.HashMap;

public class ResetPasswordRequest extends BaseRequest {

	protected String username;
	protected String pwdcode;

	public ResetPasswordRequest(String username, String pwdcode) {
		this.username = username;
		this.pwdcode = pwdcode;
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
		return PATH_RESET_PWD;
	}

	@Override
	public String getRequestAction() {
		return RESET_PWD;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
