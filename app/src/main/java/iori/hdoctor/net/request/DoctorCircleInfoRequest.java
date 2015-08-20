package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorCircleInfoRequest extends BaseRequest {

	protected String frumid;

	public DoctorCircleInfoRequest(String frumid) {
		this.frumid = frumid;
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
		return PATH_DOC_COMMENTS;
	}

	@Override
	public String getRequestAction() {
		return DOC_COMMENTS;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
