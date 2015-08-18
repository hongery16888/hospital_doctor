package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorBlglPatientRequest extends BaseRequest {

	protected String bid;

	public DoctorBlglPatientRequest(String bid) {
		this.bid = bid;
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
		return PATH_DOC_CASESPATIENT;
	}

	@Override
	public String getRequestAction() {
		return DOC_CASESPATIENT;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
