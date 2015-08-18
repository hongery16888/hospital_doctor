package iori.hdoctor.net.request;

import java.util.HashMap;

public class DoctorChuSetRequest extends BaseRequest {

	protected String dset;
	protected String isopen;
	protected String dnum;
	protected String submit;

	public DoctorChuSetRequest(String dset, String isopen, String dnum, String submit) {
		this.dset = dset;
		this.isopen = isopen;
		this.dnum = dnum;
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
		return PATH_DOC_CHUSET;
	}

	@Override
	public String getRequestAction() {
		return DOC_CHUSET;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
