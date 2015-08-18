package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorBlglRequest extends BaseRequest {

	public DoctorBlglRequest() {

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
		return PATH_DOC_CASES;
	}

	@Override
	public String getRequestAction() {
		return DOC_CASES;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
