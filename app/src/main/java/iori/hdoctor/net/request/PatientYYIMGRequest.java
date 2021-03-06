package iori.hdoctor.net.request;

import android.os.Environment;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class PatientYYIMGRequest extends BaseRequest {

	protected String did;
	protected String describe;
	protected String yuyuetime;
	protected String isremind;
	protected String tixingtime;
	protected String submit;

	public PatientYYIMGRequest(String did,String describe, String yuyuetime, String isremind, String tixingtime) {
		this.did = did;
		this.describe = describe;
		this.yuyuetime = yuyuetime;
		this.isremind = isremind;
		this.tixingtime = tixingtime;
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
		return PATH_PAT_YUYUE;
	}

	@Override
	public String getRequestAction() {
		return PAT_YUYUE;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		HashMap<String, String> list = new HashMap<>();
		list.put("photo", Environment.getExternalStorageDirectory() + "/" + HDoctorCode.HEAD_PATH + ".jpg");
		return list;
	}

}
