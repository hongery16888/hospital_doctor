package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorPublicRequest extends BaseRequest {

	public DoctorPublicRequest() {

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
		return PATH_DOC_ALL_PUBLISH;
	}

	@Override
	public String getRequestAction() {
		return DOC_ALL_PUBLISH;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
