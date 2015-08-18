package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientCircleCollectionRequest extends BaseRequest {

	protected String frumid;

	public PatientCircleCollectionRequest(String frumid) {
		this.frumid = frumid;
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
		return PATH_PAT_CIRCLE_COLLECTION;
	}

	@Override
	public String getRequestAction() {
		return PAT_CIRCLE_COLLECTION;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
