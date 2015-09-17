package iori.hdoctor.net.request;

import java.util.HashMap;

public class LoginWchatRequest extends BaseRequest {

	protected String bangdingwei;

	public LoginWchatRequest(String bangdingwei) {
		this.bangdingwei = bangdingwei;
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
		return PATH_LOGIN_WCHAT;
	}

	@Override
	public String getRequestAction() {
		return LOGIN_WCHAT;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
