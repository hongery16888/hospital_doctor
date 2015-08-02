package iori.hdoctor.net.request;

import java.util.HashMap;

public class TestRequest extends BaseRequest {

	protected String testKey1;
	protected String testKey2;
	
	public TestRequest(String testKey1, String testKey2) {
		this.testKey1 = testKey1;
		this.testKey2 = testKey2;
	}
	
	@Override
	public int postUserId() {
		return UNABLE_POST;
	}

	@Override
	public boolean postFile() {
		return false;
	}

	@Override
	public String getRequestUrl() {
		return PATH_DOC_BENCH;
	}

	@Override
	public String getRequestAction() {
		return TEST_PATH;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
