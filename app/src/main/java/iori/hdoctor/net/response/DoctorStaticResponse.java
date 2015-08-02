package iori.hdoctor.net.response;

import iori.hdoctor.net.entity.DocTodaystatic;
import iori.hdoctor.net.entity.DocTotalstatic;
import iori.hdoctor.net.entity.DocYesterdaystatic;

public class DoctorStaticResponse {

	private String uid;
	private DocTotalstatic totalstatic;
	private DocTodaystatic todaystatic;
	private DocYesterdaystatic yesterdaystatic;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public DocTotalstatic getTotalstatic() {
		return totalstatic;
	}

	public void setTotalstatic(DocTotalstatic totalstatic) {
		this.totalstatic = totalstatic;
	}

	public DocTodaystatic getTodaystatic() {
		return todaystatic;
	}

	public void setTodaystatic(DocTodaystatic todaystatic) {
		this.todaystatic = todaystatic;
	}

	public DocYesterdaystatic getYesterdaystatic() {
		return yesterdaystatic;
	}

	public void setYesterdaystatic(DocYesterdaystatic yesterdaystatic) {
		this.yesterdaystatic = yesterdaystatic;
	}
}
