package iori.hdoctor.net.request;

import java.util.HashMap;
import java.util.Queue;

public class PatientTXARequest extends BaseRequest {

	protected String txaid;
	protected String submit;

	public PatientTXARequest(String txaid, String submit) {
		this.txaid = txaid;
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
		return PATH_PAT_HEALTHY_REMIND;
	}

	@Override
	public String getRequestAction() {
		return PAT_HEALTHY_REMIND;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
