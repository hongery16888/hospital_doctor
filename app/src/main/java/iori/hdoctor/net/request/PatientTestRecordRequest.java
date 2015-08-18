package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientTestRecordRequest extends BaseRequest {

	public PatientTestRecordRequest() {

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
		return PATH_PAT_TEST_RECORD;
	}

	@Override
	public String getRequestAction() {
		return PAT_TEST_RECORD;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
