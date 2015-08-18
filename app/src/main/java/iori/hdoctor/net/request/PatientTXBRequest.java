package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientTXBRequest extends BaseRequest {

	protected String txbid;
	protected String submit;

	public PatientTXBRequest(String txbid, String submit) {
		this.txbid = txbid;
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
