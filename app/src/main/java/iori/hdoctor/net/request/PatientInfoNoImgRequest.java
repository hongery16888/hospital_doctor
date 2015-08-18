package iori.hdoctor.net.request;

import android.os.Environment;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class PatientInfoNoImgRequest extends BaseRequest {

	protected String nicheng;
	protected String sex;
	protected String age;
	protected String address;
	protected String submit;


	public PatientInfoNoImgRequest(String nicheng, String sex, String age, String address, String submit) {
		this.nicheng = nicheng;
		this.sex = sex;
		this.age = age;
		this.address = address;
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
		return PATH_PAT_INFO;
	}

	@Override
	public String getRequestAction() {
		return PAT_INFO;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		return null;
	}

}
