package iori.hdoctor.net.request;

import java.util.HashMap;

public class FriendMsgRequest extends BaseRequest {

	public FriendMsgRequest() {

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
		return PATH_FRIEND_MSG_LIST;
	}

	@Override
	public String getRequestAction() {
		return FRIEND_MSG_LIST;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
