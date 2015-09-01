package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientHospitalIntroRequest extends BaseRequest {

	protected String hospitalid;

	public PatientHospitalIntroRequest(String hospitalid) {
		this.hospitalid = hospitalid;
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
		return PATH_PAT_HOSPITAL;
	}

	@Override
	public String getRequestAction() {
		return PAT_HOSPITAL;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
