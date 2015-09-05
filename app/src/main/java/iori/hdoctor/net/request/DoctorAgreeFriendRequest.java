package iori.hdoctor.net.request;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class DoctorAgreeFriendRequest extends BaseRequest {

	protected String haoyouid;
	protected String submit;

	public DoctorAgreeFriendRequest(String haoyouid) {
		this.haoyouid = haoyouid;
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
		return PATH_DOC_AGREE_FRIENDS;
	}

	@Override
	public String getRequestAction() {
		return DOC_AGREE_FRIEND;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
