package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientYSJSRequest extends BaseRequest {

	protected String did;

	public PatientYSJSRequest(String did) {
		this.did = did;
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
		return PATH_PAT_DOC_SERVITEM;
	}

	@Override
	public String getRequestAction() {
		return PAT_DOC_SERVITEM;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
