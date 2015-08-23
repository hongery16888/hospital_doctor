package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorMyShouCangRequest extends BaseRequest {


	public DoctorMyShouCangRequest() {
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
		return PATH_DOC_MY_SHOUCANG;
	}

	@Override
	public String getRequestAction() {
		return DOC_MY_SHOUCANG;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
