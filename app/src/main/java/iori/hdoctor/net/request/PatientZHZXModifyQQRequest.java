package iori.hdoctor.net.request;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class PatientZHZXModifyQQRequest extends BaseRequest {

	protected String bangdingqq;
	protected String submit;

	public PatientZHZXModifyQQRequest(String bangdingqq) {
		this.bangdingqq = bangdingqq;
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
		return PATH_PAT_ACCOUNT;
	}

	@Override
	public String getRequestAction() {
		return PAT_ACCOUNT;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
