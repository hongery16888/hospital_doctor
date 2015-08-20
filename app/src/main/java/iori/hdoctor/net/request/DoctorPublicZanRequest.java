package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorPublicZanRequest extends BaseRequest {

	protected String frumid;
	protected String submit;

	public DoctorPublicZanRequest(String frumid, String submit) {
		this.frumid = frumid;
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
