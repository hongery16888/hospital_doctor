package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorTixianRequest extends BaseRequest {

	public DoctorTixianRequest() {

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
		return PATH_DOC_TIXIAN;
	}

	@Override
	public String getRequestAction() {
		return DOC_TIXIAN;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
