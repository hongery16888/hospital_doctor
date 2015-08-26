package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientSearchByHospRequest extends BaseRequest {

	protected String jingdu;
	protected String weidu;
	protected String keyword;

	public PatientSearchByHospRequest(String jingdu, String weidu, String keyword) {
		this.jingdu = jingdu;
		this.weidu = weidu;
		this.keyword = keyword;
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
		return PATH_PAT_SEARCH_BY_HOSP;
	}

	@Override
	public String getRequestAction() {
		return PAT_SEARCH_BY_HOSP;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
