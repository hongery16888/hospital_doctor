package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorUserShanChangRequest extends BaseRequest {

	protected String shanchang;
	protected String submit;

	public DoctorUserShanChangRequest(String shanchang, String submit) {
		this.shanchang = shanchang;
		this.submit = submit;
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
		return PATH_DOC_INFO;
	}

	@Override
	public String getRequestAction() {
		return DOC_INFO;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
