package iori.hdoctor.net.request;

import android.os.Environment;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class PatientCommentRequest extends BaseRequest {

	protected String orderid;
	protected String content;
	protected int manyi;
	protected int zhuanye;
	protected String submit;

	public PatientCommentRequest(String orderid, String content, int manyi, int zhuanye, String submit) {
		this.orderid = orderid;
		this.content = content;
		this.manyi = manyi;
		this.zhuanye = zhuanye;
		this.submit = submit;
	}
	
	@Override
	public int postUserId() {
		return FORCE_POST;
	}

	@Override
	public boolean postFile() {
		return true;
	}

	@Override
	public String getRequestUrl() {
		return PATH_PAT_COMMENT;
	}

	@Override
	public String getRequestAction() {
		return PAT_COMMENT;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		HashMap<String, String> list = new HashMap<>();
		list.put("pic", Environment.getExternalStorageDirectory() + "/" + HDoctorCode.HEAD_PATH + "_comment" + ".jpg");
		return list;
	}

}
