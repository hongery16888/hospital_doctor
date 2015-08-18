package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorChuZhenRequest extends BaseRequest {

	public DoctorChuZhenRequest() {

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
		return PATH_DOC_CHUZHEN;
	}

	@Override
	public String getRequestAction() {
		return DOC_CHUZHEN;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
