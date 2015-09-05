package iori.hdoctor.net.request;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class PatientAddFriendRequest extends BaseRequest {

	protected String name;
	protected String submit;

	public PatientAddFriendRequest(String name) {
		this.name = name;
		this.submit = HDoctorCode.YES;
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
		return PATH_PAT_ADD_FRIENDS;
	}

	@Override
	public String getRequestAction() {
		return PAT_ADD_FRIEND;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
