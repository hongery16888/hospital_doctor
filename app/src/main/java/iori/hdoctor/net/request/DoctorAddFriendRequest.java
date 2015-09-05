package iori.hdoctor.net.request;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class DoctorAddFriendRequest extends BaseRequest {

	protected String name;
	protected String submit;

	public DoctorAddFriendRequest(String name) {
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
		return PATH_DOC_ADD_FRIENDS;
	}

	@Override
	public String getRequestAction() {
		return DOC_ADD_FRIEND;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
