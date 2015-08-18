package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorBankRecordRequest extends BaseRequest {


	public DoctorBankRecordRequest() {
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
		return PATH_DOC_INCOME_RECORD;
	}

	@Override
	public String getRequestAction() {
		return DOC_INCOME_RECORD;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
