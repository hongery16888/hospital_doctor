package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorVerifyRequest extends BaseRequest {

	protected String orderid;

	public DoctorVerifyRequest(String orderid) {
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
		return PATH_DOC_VERIFY;
	}

	@Override
	public String getRequestAction() {
		return DOC_VERIFY;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
