package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientReplyRequest extends BaseRequest {

	protected String frumid;
	protected String content;
	protected String submit;

	public PatientReplyRequest(String frumid, String content, String submit) {
		this.frumid = frumid;
		this.content = content;
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
		return PATH_PAT_CONTENT_DESC;
	}

	@Override
	public String getRequestAction() {
		return PAT_CONTENT_DESC;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
