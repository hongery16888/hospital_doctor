package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientScanSnRequest extends BaseRequest {

	protected String sn;

	public PatientScanSnRequest(String sn) {
		this.sn = sn;
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
		return PATH_SCAN_SN;
	}

	@Override
	public String getRequestAction() {
		return SCAN_SN;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
