package iori.hdoctor.net.request;

import java.util.HashMap;

public class FindPasswordRequest extends BaseRequest {

	protected String username;

	public FindPasswordRequest(String username) {
		this.username = username;
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
		return PATH_FIND_PWD;
	}

	@Override
	public String getRequestAction() {
		return FIND_PWD;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
