package iori.hdoctor.net.request;

import android.os.Environment;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class PatientRegisterRequest extends BaseRequest {

	protected String nicheng;
	protected String sex;
	protected String age;
	protected String address;
	protected int type;
	protected String submit;

	public PatientRegisterRequest(String nicheng, String sex, String age, String address, int type, String submit) {
		this.nicheng = nicheng;
		this.sex = sex;
		this.age = age;
		this.address = address;
		this.type = type;
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
		return PATH_PAT_REGISTER_INFO;
	}

	@Override
	public String getRequestAction() {
		return PAT_REGISTER_INFO;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		HashMap<String, String> list = new HashMap<>();
		list.put("img", Environment.getExternalStorageDirectory() + "/" + HDoctorCode.HEAD_PATH + ".jpg");
		return list;
	}

}
