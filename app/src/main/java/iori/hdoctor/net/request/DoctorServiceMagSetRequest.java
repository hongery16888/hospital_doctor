package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorServiceMagSetRequest extends BaseRequest {

	protected String sid;
	protected String submit;
	protected String price;
	protected String isopen;

	public DoctorServiceMagSetRequest(String sid, String price, String isopen, String submit) {
		this.sid = sid;
		this.price = price;
		this.submit = submit;
		this.isopen = isopen;
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
		return PATH_DOC_SERVICE_MAG_SET;
	}

	@Override
	public String getRequestAction() {
		return DOC_SERVICE_MAG_SET;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
