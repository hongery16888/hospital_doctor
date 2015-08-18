package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientWDYXRequest extends BaseRequest {

	public PatientWDYXRequest() {

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
		return PATH_PAT_MEDICINE;
	}

	@Override
	public String getRequestAction() {
		return PAT_MEDICINE;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
