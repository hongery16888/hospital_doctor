package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientFriendRequest extends BaseRequest {

	protected int isdoc;

	public PatientFriendRequest(int isdoc) {
		this.isdoc = isdoc;
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
		return PATH_PAT_FRIENDS;
	}

	@Override
	public String getRequestAction() {
		return PAT_FRIEND;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
