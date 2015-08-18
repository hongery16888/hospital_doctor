package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorCircleRequest extends BaseRequest {

	public DoctorCircleRequest() {

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
		return PATH_DOC_CIRCLE;
	}

	@Override
	public String getRequestAction() {
		return DOC_CIRCLE;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
