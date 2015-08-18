package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientCircleZanRequest extends BaseRequest {

	protected String frumid;
	protected String submit;

	public PatientCircleZanRequest(String frumid, String submit) {
		this.frumid = frumid;
		this.submit = submit;
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
		return PATH_PAT_ROUND;
	}

	@Override
	public String getRequestAction() {
		return PAT_ROUND;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
