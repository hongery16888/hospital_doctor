package iori.hdoctor.net.request;

import java.util.HashMap;

public class PatientAlyReportRequest extends BaseRequest {

	protected int score;
	protected int height;
	protected int weight;
	protected int age;
	protected String health;
	protected String jieguo;

	public PatientAlyReportRequest(int score, int height, int weight, int age, String health, String jieguo) {
		this.score = score;
		this.height = height;
		this.weight = weight;
		this.age = age;
		this.health = health;
		this.jieguo = jieguo;
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
		return PATH_PAT_ALY_REPORT;
	}

	@Override
	public String getRequestAction() {
		return PAT_ALY_REPORT;
	}

	@Override
	public HashMap<String, String> getFileEncode() {
		// TODO Auto-generated method stub
		return null;
	}

}
