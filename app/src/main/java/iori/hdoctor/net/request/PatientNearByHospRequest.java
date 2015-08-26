package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientNearByHospRequest extends BaseRequest {

	protected String jingdu;
	protected String weidu;

	public PatientNearByHospRequest(String jingdu, String weidu) {
		this.jingdu = jingdu;
		this.weidu = weidu;
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
		return PATH_PAT_NEARBYHOSP;
	}

	@Override
	public String getRequestAction() {
		return PAT_NEARBYHOSP;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
