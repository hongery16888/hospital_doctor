package iori.hdoctor.net.request;

import android.os.Environment;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class PatientDHZXImgRequest extends BaseRequest {

	protected String did;
	protected String describe;
	protected String submit;

	public PatientDHZXImgRequest(String did, String describe) {
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
		return true;
	}

	@Override
	public String getRequestUrl() {
		return PATH_PAT_ONLINE_TEL;
	}

	@Override
	public String getRequestAction() {
		return PAT_ONLINE_TEL;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		HashMap<String, String> list = new HashMap<>();
		list.put("photo", Environment.getExternalStorageDirectory() + "/" + HDoctorCode.HEAD_PATH + ".jpg");
		return list;
	}

}
