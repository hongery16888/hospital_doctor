package iori.hdoctor.net.request;

import android.os.Environment;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class PatientAddMedicinePromptRequest extends BaseRequest {

	protected String name;
	protected String takeingtime;
	protected String takingnum;
	protected String takingperiod;
	protected String beizhu;
	protected String submit;

	public PatientAddMedicinePromptRequest(String name, String takeingtime, String takingnum, String takingperiod, String beizhu, String submit) {
		this.name = name;
		this.takeingtime = takeingtime;
		this.takingnum = takingnum;
		this.takingperiod = takingperiod;
		this.beizhu = beizhu;
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
		return PATH_PAT_ADD_REMIND;
	}

	@Override
	public String getRequestAction() {
		return PAT_ADD_REMIND;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		HashMap<String, String> list = new HashMap<>();
		list.put("thumb", Environment.getExternalStorageDirectory() + "/" + HDoctorCode.HEAD_PATH + ".jpg");
		return list;
	}

}
