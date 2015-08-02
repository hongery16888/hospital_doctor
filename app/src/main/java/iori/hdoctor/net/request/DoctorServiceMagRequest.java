package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorServiceMagRequest extends BaseRequest {

	public DoctorServiceMagRequest() {

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
		return PATH_DOC_SERVICE_MAG;
	}

	@Override
	public String getRequestAction() {
		return DOC_SERVICE_MAG;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
