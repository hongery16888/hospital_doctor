package iori.hdoctor.net.request;

import java.util.HashMap;

public class LoginQQRequest extends BaseRequest {

	protected String bangdingqq;

	public LoginQQRequest(String bangdingqq) {
		this.bangdingqq = bangdingqq;
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
		return PATH_LOGIN_QQ;
	}

	@Override
	public String getRequestAction() {
		return LOGIN_QQ;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
