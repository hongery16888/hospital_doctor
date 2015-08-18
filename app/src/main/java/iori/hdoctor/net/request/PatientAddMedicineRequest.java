package iori.hdoctor.net.request;

import android.os.Environment;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class PatientAddMedicineRequest extends BaseRequest {

	protected String name;
	protected String beizhu;
	protected String submit;

	public PatientAddMedicineRequest(String name, String beizhu, String submit) {
		this.name = name;
		this.beizhu = beizhu;
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
		return PATH_PAT_ADD_MEDICINE;
	}

	@Override
	public String getRequestAction() {
		return PAT_ADD_MEDICINE;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		HashMap<String, String> list = new HashMap<>();
		list.put("img", Environment.getExternalStorageDirectory() + "/" + HDoctorCode.HEAD_PATH + ".jpg");
		return list;
	}

}
