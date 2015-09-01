package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientDHZXResultRequest extends BaseRequest {

	protected String did;
	protected String orderid;

	public PatientDHZXResultRequest(String did, String orderid) {
		this.did = did;
		this.orderid = orderid;
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
		return PATH_PAT_DOC_TEL;
	}

	@Override
	public String getRequestAction() {
		return PAT_DOC_TEL;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
