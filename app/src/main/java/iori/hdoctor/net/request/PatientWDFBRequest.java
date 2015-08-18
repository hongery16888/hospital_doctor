package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientWDFBRequest extends BaseRequest {

	public PatientWDFBRequest() {

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
		return PATH_PAT_PUBLISH;
	}

	@Override
	public String getRequestAction() {
		return PAT_PUBLISH;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
