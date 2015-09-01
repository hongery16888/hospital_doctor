package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientChuZhenByHospRequest extends BaseRequest {

	protected String jingdu;
	protected String weidu;

	public PatientChuZhenByHospRequest(String jingdu, String weidu) {
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
		return PATH_PAT_CHUZHEN;
	}

	@Override
	public String getRequestAction() {
		return PAT_CHUZHEN;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
