package iori.hdoctor.net.request;

import java.util.HashMap;

public class ConfirmFriendRequest extends BaseRequest {

	protected String personalid;
	protected String type;

	public ConfirmFriendRequest(String personalid, String type) {
		this.personalid = personalid;
		this.type = type;
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
		return PATH_CONFIRM_FRIEND;
	}

	@Override
	public String getRequestAction() {
		return CONFIRM_FRIEND;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
