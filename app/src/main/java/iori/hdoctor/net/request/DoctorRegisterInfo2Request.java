package iori.hdoctor.net.request;

import android.os.Environment;

import java.util.HashMap;

import iori.hdoctor.activity.base.HDoctorCode;

public class DoctorRegisterInfo2Request extends BaseRequest {

	protected String submit;

	public DoctorRegisterInfo2Request(String submit) {
		this.submit = submit;
	}
	
	@Override
	public int postUserId() {
		return ENABLE_POST;
	}

	@Override
	public boolean postFile() {
		return true;
	}

	@Override
	public String getRequestUrl() {
		return PATH_DOC_REGISTER_INFO_2;
	}

	@Override
	public String getRequestAction() {
		return DOC_REGISTER_INFO_2;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		HashMap<String, String> list = new HashMap<>();
		list.put("pic", Environment.getExternalStorageDirectory() + HDoctorCode.SHENFEN_PATH + ".jpg");
		list.put("zhengshu1", Environment.getExternalStorageDirectory() + HDoctorCode.ZIGE_PATH + ".jpg");
		list.put("zhengshu2", Environment.getExternalStorageDirectory() + HDoctorCode.GONGZUO_PATH + ".jpg");
		return list;
	}

}
