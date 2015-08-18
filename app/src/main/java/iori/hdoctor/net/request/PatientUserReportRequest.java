package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientUserReportRequest extends BaseRequest {

	public PatientUserReportRequest() {

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
		return PATH_PAT_USER_REPORT;
	}

	@Override
	public String getRequestAction() {
		return PAT_USER_REPORT;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
