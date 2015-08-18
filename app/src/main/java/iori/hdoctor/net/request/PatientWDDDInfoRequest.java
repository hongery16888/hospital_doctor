package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientWDDDInfoRequest extends BaseRequest {

	protected String orderid;

	public PatientWDDDInfoRequest(String orderid) {
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
		return PATH_PAT_ORDER_DESC;
	}

	@Override
	public String getRequestAction() {
		return PAT_ORDER_DESC;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
