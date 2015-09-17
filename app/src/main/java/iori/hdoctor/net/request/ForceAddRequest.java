package iori.hdoctor.net.request;

import java.util.HashMap;

public class ForceAddRequest extends BaseRequest {

	protected String haoyouid;

	public ForceAddRequest(String haoyouid) {
		this.haoyouid = haoyouid;
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
		return PATH_FORCE_ADD;
	}

	@Override
	public String getRequestAction() {
		return FORCE_ADD;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
