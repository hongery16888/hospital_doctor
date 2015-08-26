package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientZiZhiHospRequest extends BaseRequest {

	protected String jingdu;
	protected String weidu;

	public PatientZiZhiHospRequest(String jingdu, String weidu) {
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
		return PATH_PAT_ZIZHI_HOSP;
	}

	@Override
	public String getRequestAction() {
		return PAT_ZIZHI_HOSP;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
