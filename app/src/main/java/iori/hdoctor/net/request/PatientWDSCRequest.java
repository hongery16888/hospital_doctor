package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientWDSCRequest extends BaseRequest {

	public PatientWDSCRequest() {

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
		return PATH_PAT_COLLECTION;
	}

	@Override
	public String getRequestAction() {
		return PAT_COLLECTION;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
