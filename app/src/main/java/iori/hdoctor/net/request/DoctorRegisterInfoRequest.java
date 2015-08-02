package iori.hdoctor.net.request;

import android.os.Environment;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class DoctorRegisterInfoRequest extends BaseRequest {

	protected String realname;
	protected String hospital;
	protected String keshi;
	protected String tel;
	protected String submit;

	public DoctorRegisterInfoRequest(String realname, String hospital, String keshi, String tel, String submit) {
		this.realname = realname;
		this.hospital = hospital;
		this.keshi = keshi;
		this.tel = tel;
		this.submit = submit;
	}
	
	@Override
	public int postUserId() {
		return ENABLE_POST;
	}

	@Override
	public boolean postFile() {
		return true;
	}

	@Override
	public String getRequestUrl() {
		return PATH_DOC_REGISTER_INFO;
	}

	@Override
	public String getRequestAction() {
		return DOC_REGISTER_INFO;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		HashMap<String, String> list = new HashMap<>();
		list.put("img", Environment.getExternalStorageDirectory() + HDoctorCode.HEAD_PATH + ".jpg");
		return list;
	}

}
