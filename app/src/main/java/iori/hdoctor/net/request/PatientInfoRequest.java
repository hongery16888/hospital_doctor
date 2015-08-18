package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientInfoRequest extends BaseRequest {

	public PatientInfoRequest() {

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
		return PATH_PAT_INFO;
	}

	@Override
	public String getRequestAction() {
		return PAT_INFO;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		return null;
	}

}
