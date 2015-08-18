package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientGRZXRequest extends BaseRequest {

	public PatientGRZXRequest() {

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
		return PATH_PAT_PERSONAL;
	}

	@Override
	public String getRequestAction() {
		return PAT_PERSONAL;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
