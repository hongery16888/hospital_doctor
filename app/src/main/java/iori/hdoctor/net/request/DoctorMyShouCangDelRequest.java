package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorMyShouCangDelRequest extends BaseRequest {

	protected String collectionid;
	protected String submit;

	public DoctorMyShouCangDelRequest(String collectionid, String submit) {
		this.collectionid = collectionid;
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
