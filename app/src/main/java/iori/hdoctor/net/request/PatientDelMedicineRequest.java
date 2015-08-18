package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientDelMedicineRequest extends BaseRequest {

	protected String medicineid;
	protected String submit;

	public PatientDelMedicineRequest(String medicineid, String submit) {
		this.medicineid = medicineid;
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
		return PATH_PAT_MEDICINE;
	}

	@Override
	public String getRequestAction() {
		return PAT_MEDICINE;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
