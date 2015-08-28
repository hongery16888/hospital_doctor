package iori.hdoctor.net.request;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class PatientZXZXNoImgRequest extends BaseRequest {

	protected String did;
	protected String describe;
	protected String submit;

	public PatientZXZXNoImgRequest(String did, String describe) {
		this.did = did;
		this.describe = describe;
		this.submit = HDoctorCode.YES;
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
		return PATH_PAT_ONLINE_CONS;
	}

	@Override
	public String getRequestAction() {
		return PAT_ONLINE_CONS;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		return  null;
	}

}
