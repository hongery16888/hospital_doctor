package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorBlglInfoRequest extends BaseRequest {

	protected String orderid;

	public DoctorBlglInfoRequest(String orderid) {
		this.orderid = orderid;
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
		return PATH_DOC_CASESMAG;
	}

	@Override
	public String getRequestAction() {
		return DOC_CASESMAG;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
