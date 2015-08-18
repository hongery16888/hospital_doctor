package iori.hdoctor.net.request;

import android.os.Environment;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class PatientPublishRequest extends BaseRequest {

	protected String content;
	protected String ispublic;
	protected String submit;

	public PatientPublishRequest(String content, String ispublic, String submit) {
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
		return true;
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
		HashMap<String, String> list = new HashMap<>();
		list.put("img", Environment.getExternalStorageDirectory() + "/" + HDoctorCode.HEAD_PATH + ".jpg");
		return list;
	}

}
