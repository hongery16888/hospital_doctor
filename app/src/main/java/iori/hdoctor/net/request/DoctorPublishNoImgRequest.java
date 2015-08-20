package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorPublishNoImgRequest extends BaseRequest {

	protected String content;
	protected String ispublic;
	protected String submit;

	public DoctorPublishNoImgRequest(String content, String ispublic, String submit) {
		this.content = content;
		this.ispublic = ispublic;
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
		return PATH_DOC_PUBLISH;
	}

	@Override
	public String getRequestAction() {
		return DOC_PUBLISH;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		return null;
	}

}
