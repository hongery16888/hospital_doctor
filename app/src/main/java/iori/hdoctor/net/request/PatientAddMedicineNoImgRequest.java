package iori.hdoctor.net.request;

import android.os.Environment;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class PatientAddMedicineNoImgRequest extends BaseRequest {

	protected String name;
	protected String beizhu;
	protected String submit;

	public PatientAddMedicineNoImgRequest(String name, String beizhu, String submit) {
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
		return false;
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
		return null;
	}

}
