package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorReplyRequest extends BaseRequest {

	protected String frumid;
	protected String content;
	protected String pinglunuid;
	protected String submit;

	public DoctorReplyRequest(String frumid, String content, String pinglunuid, String submit) {
		this.frumid = frumid;
		this.content = content;
		this.pinglunuid = pinglunuid;
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
		return PATH_DOC_COMMENTS;
	}

	@Override
	public String getRequestAction() {
		return DOC_COMMENTS;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
