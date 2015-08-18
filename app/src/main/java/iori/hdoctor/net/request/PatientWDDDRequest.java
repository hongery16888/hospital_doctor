package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientWDDDRequest extends BaseRequest {

	public PatientWDDDRequest() {

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
		return PATH_PAT_MY_ORDER;
	}

	@Override
	public String getRequestAction() {
		return PAT_MY_ORDER;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
