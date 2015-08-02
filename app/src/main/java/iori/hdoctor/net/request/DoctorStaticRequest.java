package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorStaticRequest extends BaseRequest {

	public DoctorStaticRequest() {

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
		return PATH_DOC_STATIC;
	}

	@Override
	public String getRequestAction() {
		return DOC_STATIC;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
