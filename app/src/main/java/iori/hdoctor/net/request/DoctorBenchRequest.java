package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorBenchRequest extends BaseRequest {

	public DoctorBenchRequest() {

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
		return PATH_DOC_BENCH;
	}

	@Override
	public String getRequestAction() {
		return DOC_BENCH;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
