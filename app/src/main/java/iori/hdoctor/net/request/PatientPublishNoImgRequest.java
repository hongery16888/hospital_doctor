package iori.hdoctor.net.request;

import android.os.Environment;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class PatientPublishNoImgRequest extends BaseRequest {

	protected String content;
	protected String ispublic;
	protected String submit;

	public PatientPublishNoImgRequest(String content, String ispublic, String submit) {
		this.content = content;
		this.ispublic = ispublic;
		this.submit = submit;
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
		return PATH_PAT_CIRCLE_PUBLISH;
	}

	@Override
	public String getRequestAction() {
		return PAT_CIRCLE_PUBLISH;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		return null;
	}

}
