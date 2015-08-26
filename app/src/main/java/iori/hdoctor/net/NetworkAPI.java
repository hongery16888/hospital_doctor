/*
 * Copyright © 2009-2012 Chengdu Tianfu Software Park Co., Ltd.</br>
 * Company: Chengdu Tianfu Software Park Co., Ltd.</br>
 * Project: CHLogistics
 * Package: com.tfsp.mobile.chlogistics.api
 * Version: 1.0</br>
 * Android SDK: min sdk level: 7</br>
 * Create Time: 2012-11-27</br>
 */
package iori.hdoctor.net;

import iori.hdoctor.activity.PatientPublishActivity;
import iori.hdoctor.activity.base.HDoctorCode;
import iori.hdoctor.net.request.CheckVersionRequest;
import iori.hdoctor.net.request.DoctorAddBankRequest;
import iori.hdoctor.net.request.DoctorBenchRequest;
import iori.hdoctor.net.request.DoctorBlglInfoRequest;
import iori.hdoctor.net.request.DoctorBlglPatientRequest;
import iori.hdoctor.net.request.DoctorBlglRequest;
import iori.hdoctor.net.request.DoctorChuSetRequest;
import iori.hdoctor.net.request.DoctorChuZhenRequest;
import iori.hdoctor.net.request.DoctorCircleInfoRequest;
import iori.hdoctor.net.request.DoctorCircleRequest;
import iori.hdoctor.net.request.DoctorCircleYSRequest;
import iori.hdoctor.net.request.DoctorCircleYSZanRequest;
import iori.hdoctor.net.request.DoctorCircleZanRequest;
import iori.hdoctor.net.request.DoctorDelBankRequest;
import iori.hdoctor.net.request.DoctorLoginRequest;
import iori.hdoctor.net.request.DoctorMessageRequest;
import iori.hdoctor.net.request.DoctorModifyBankRequest;
import iori.hdoctor.net.request.DoctorMyFaBiaoDelRequest;
import iori.hdoctor.net.request.DoctorMyFaBiaoRequest;
import iori.hdoctor.net.request.DoctorMyShouCangDelRequest;
import iori.hdoctor.net.request.DoctorMyShouCangRequest;
import iori.hdoctor.net.request.DoctorPersonalRequest;
import iori.hdoctor.net.request.DoctorPublicRequest;
import iori.hdoctor.net.request.DoctorPublicZanRequest;
import iori.hdoctor.net.request.DoctorPublishNoImgRequest;
import iori.hdoctor.net.request.DoctorPublishRequest;
import iori.hdoctor.net.request.DoctorRegisterInfo2Request;
import iori.hdoctor.net.request.DoctorRegisterInfoRequest;
import iori.hdoctor.net.request.DoctorRegisterRequest;
import iori.hdoctor.net.request.DoctorReplyRequest;
import iori.hdoctor.net.request.DoctorReserveRequest;
import iori.hdoctor.net.request.DoctorSRXQRequest;
import iori.hdoctor.net.request.DoctorServiceMagRequest;
import iori.hdoctor.net.request.DoctorServiceMagSetRequest;
import iori.hdoctor.net.request.DoctorShouCangRequest;
import iori.hdoctor.net.request.DoctorStaticRequest;
import iori.hdoctor.net.request.DoctorUserEmailRequest;
import iori.hdoctor.net.request.DoctorUserHospitalRequest;
import iori.hdoctor.net.request.DoctorUserImgRequest;
import iori.hdoctor.net.request.DoctorUserInfoRequest;
import iori.hdoctor.net.request.DoctorUserJianjieRequest;
import iori.hdoctor.net.request.DoctorUserKeShiRequest;
import iori.hdoctor.net.request.DoctorUserRealnameRequest;
import iori.hdoctor.net.request.DoctorUserShanChangRequest;
import iori.hdoctor.net.request.DoctorUserZhiChengRequest;
import iori.hdoctor.net.request.DoctorVerifyRequest;
import iori.hdoctor.net.request.DoctorZHZXInfoRequest;
import iori.hdoctor.net.request.DoctorZHZXRequest;
import iori.hdoctor.net.request.PatientAddMedicineNoImgRequest;
import iori.hdoctor.net.request.PatientAddMedicinePromptRequest;
import iori.hdoctor.net.request.PatientAddMedicineRequest;
import iori.hdoctor.net.request.PatientAlyReportRequest;
import iori.hdoctor.net.request.PatientCircleCollectionRequest;
import iori.hdoctor.net.request.PatientCircleInfoRequest;
import iori.hdoctor.net.request.PatientCircleRequest;
import iori.hdoctor.net.request.PatientCircleZanRequest;
import iori.hdoctor.net.request.PatientCommentRequest;
import iori.hdoctor.net.request.PatientCommunityRequest;
import iori.hdoctor.net.request.PatientCommunityZanRequest;
import iori.hdoctor.net.request.PatientConsultRecordRequest;
import iori.hdoctor.net.request.PatientDelMedicineRequest;
import iori.hdoctor.net.request.PatientGRZXRequest;
import iori.hdoctor.net.request.PatientHealthyRemindRequest;
import iori.hdoctor.net.request.PatientInfoImgRequest;
import iori.hdoctor.net.request.PatientInfoNoImgRequest;
import iori.hdoctor.net.request.PatientInfoRequest;
import iori.hdoctor.net.request.PatientLoginRequest;
import iori.hdoctor.net.request.PatientNearByDocRequest;
import iori.hdoctor.net.request.PatientNearByHospRequest;
import iori.hdoctor.net.request.PatientPublishNoImgRequest;
import iori.hdoctor.net.request.PatientPublishRequest;
import iori.hdoctor.net.request.PatientRegisterPhoneRequest;
import iori.hdoctor.net.request.PatientRegisterRequest;
import iori.hdoctor.net.request.PatientReplyRequest;
import iori.hdoctor.net.request.PatientSearchByDocRequest;
import iori.hdoctor.net.request.PatientSearchByHospRequest;
import iori.hdoctor.net.request.PatientTXARequest;
import iori.hdoctor.net.request.PatientTXBRequest;
import iori.hdoctor.net.request.PatientTestRecordRequest;
import iori.hdoctor.net.request.PatientUserReportRequest;
import iori.hdoctor.net.request.PatientWDDDInfoRequest;
import iori.hdoctor.net.request.PatientWDDDRequest;
import iori.hdoctor.net.request.PatientWDFBDelRequest;
import iori.hdoctor.net.request.PatientWDFBRequest;
import iori.hdoctor.net.request.PatientWDSCDelRequest;
import iori.hdoctor.net.request.PatientWDSCRequest;
import iori.hdoctor.net.request.PatientWDYXRequest;
import iori.hdoctor.net.request.PatientWDYYRequest;
import iori.hdoctor.net.request.PatientYSJSRequest;
import iori.hdoctor.net.request.PatientZHZXModifyRequest;
import iori.hdoctor.net.request.PatientZHZXRequest;
import iori.hdoctor.net.request.PatientZiZhiDocRequest;
import iori.hdoctor.net.request.PatientZiZhiHospRequest;
import iori.hdoctor.net.response.DoctorCircleInfoResponse;
import iori.hdoctor.net.response.DoctorCircleResponse;
import iori.hdoctor.net.response.DoctorCircleYSResponse;
import iori.hdoctor.net.response.DoctorMyFaBiaoResponse;
import iori.hdoctor.net.response.DoctorMyShouCangResponse;
import iori.hdoctor.net.response.DoctorPublicResponse;
import iori.hdoctor.net.response.PatientCircleInfoResponse;
import iori.hdoctor.net.response.PatientCircleResponse;
import iori.hdoctor.net.response.PatientCommunityResponse;
import iori.hdoctor.net.response.PatientGRZXResponse;
import iori.hdoctor.net.response.PatientInfoResponse;
import iori.hdoctor.net.response.PatientNearByDocResponse;
import iori.hdoctor.net.response.PatientNearByHospResponse;
import iori.hdoctor.net.response.PatientSearchByDocResponse;
import iori.hdoctor.net.response.PatientSearchByHospResponse;
import iori.hdoctor.net.response.PatientWDDDInfoResponse;
import iori.hdoctor.net.response.PatientWDFBResponse;
import iori.hdoctor.net.response.PatientWDSCResponse;
import iori.hdoctor.net.response.PatientWDYYResponse;
import iori.hdoctor.net.request.TestRequest;
import iori.hdoctor.net.response.CheckVersionResponse;
import iori.hdoctor.net.response.DoctorBenchResponse;
import iori.hdoctor.net.response.DoctorBlglInfoResponse;
import iori.hdoctor.net.response.DoctorBlglPatientResponse;
import iori.hdoctor.net.response.DoctorBlglResponse;
import iori.hdoctor.net.response.DoctorChuZhenResponse;
import iori.hdoctor.net.response.DoctorImgResponse;
import iori.hdoctor.net.response.DoctorLoginResponse;
import iori.hdoctor.net.response.DoctorMessageResponse;
import iori.hdoctor.net.response.DoctorPersonalResponse;
import iori.hdoctor.net.response.DoctorRegisterInfoResponse;
import iori.hdoctor.net.response.DoctorRegisterResponse;
import iori.hdoctor.net.response.DoctorReserveResponse;
import iori.hdoctor.net.response.DoctorSRXQResponse;
import iori.hdoctor.net.response.DoctorServiceMagResponse;
import iori.hdoctor.net.response.DoctorServiceMagSetResponse;
import iori.hdoctor.net.response.DoctorStaticResponse;
import iori.hdoctor.net.response.DoctorUserInfoResponse;
import iori.hdoctor.net.response.DoctorZHZXResponse;
import iori.hdoctor.net.response.EmptyResponse;
import iori.hdoctor.net.response.PatientConsultRecordResponse;
import iori.hdoctor.net.response.PatientHealthyRemindResponse;
import iori.hdoctor.net.response.PatientLoginResponse;
import iori.hdoctor.net.response.PatientRegisterPhoneResponse;
import iori.hdoctor.net.response.PatientRegisterResponse;
import iori.hdoctor.net.response.PatientTestRecordResponse;
import iori.hdoctor.net.response.PatientUserReportResponse;
import iori.hdoctor.net.response.PatientWDDDResponse;
import iori.hdoctor.net.response.PatientWDYXResponse;
import iori.hdoctor.net.response.PatientYSJSResponse;
import iori.hdoctor.net.response.PatientZHZXResponse;
import iori.hdoctor.net.response.PatientZiZhiDocResponse;
import iori.hdoctor.net.response.PatientZiZhiHospResponse;
import iori.hdoctor.net.response.TestResponse;
import iori.hdoctor.view.RequestProgressDialog;

/**
 * NetworkAPI summary: 数据请求接口类
 */
public class NetworkAPI implements HttpRequest {

	private static NetworkAPI customerNetApi;

	private final NetworkConnection mConnection;

	private NetworkAPI() {

		super();
		this.mConnection = NetworkConnection.getNetworkConnection();
	}

	public static NetworkAPI getNetworkAPI() {

		if (null == customerNetApi) {
			customerNetApi = new NetworkAPI();
		}
		return customerNetApi;
	}

	public static void recyleNetworkAPI() {
		customerNetApi = null;
		NetworkConnection.recyleConnection();
	}

	public String getUserId() {
		return DataTransfer.getUid();
	}

	// ///////////////////////////////////////////////////////////////////////
	// API
	// ///////////////////////////////////////////////////////////////////////
	public void testRequest(String testKey1, String testKey2, RequestProgressDialog diag,
			NetworkConnectListener listener) {
		TestRequest request = new TestRequest(testKey1, testKey2);
		mConnection.sendRequestByPost(request, diag, TestResponse.class, listener);
	}

//    public void checkVersion(String versionCode, RequestProgressDialog diag, NetworkConnectListener listener){
//        CheckVersionRequest request = new CheckVersionRequest(versionCode);
//        mConnection.sendRequestByPost(request, diag, CheckVersionResponse.class, listener);
//    }

	public void login(String username, String password, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorLoginRequest request = new DoctorLoginRequest(username, password);
		mConnection.sendRequestByPost(request, diag, DoctorLoginResponse.class, listener);
	}

	public void register(String username, String password, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorRegisterRequest request = new DoctorRegisterRequest(username, password, HDoctorCode.DOCTOR, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, DoctorRegisterResponse.class, listener);
	}

	public void registerInfo(String name, String hospital, String keshi, String tel, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorRegisterInfoRequest request = new DoctorRegisterInfoRequest(name, hospital, keshi, tel, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, DoctorRegisterInfoResponse.class, listener);
	}

	public void registerInfo2(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorRegisterInfo2Request request = new DoctorRegisterInfo2Request(HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, DoctorLoginResponse.class, listener);
	}

	public void docBench(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorBenchRequest request = new DoctorBenchRequest();
		mConnection.sendRequestByPost(request, diag, DoctorBenchResponse.class, listener);
	}

	public void docStatic(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorStaticRequest request = new DoctorStaticRequest();
		mConnection.sendRequestByPost(request, diag, DoctorStaticResponse.class, listener);
	}

	public void docServiceMag(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorServiceMagRequest request = new DoctorServiceMagRequest();
		mConnection.sendRequestByPost(request, diag, DoctorServiceMagResponse.class, listener);
	}

	public void docServiceMagSet(String sid, String price, String isopen, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorServiceMagSetRequest request = new DoctorServiceMagSetRequest(sid, price, isopen, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, DoctorServiceMagSetResponse.class, listener);
	}

	public void docchuzhen(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorChuZhenRequest request = new DoctorChuZhenRequest();
		mConnection.sendRequestByPost(request, diag, DoctorChuZhenResponse.class, listener);
	}

	public void docChuSet(String dset, String isopen, String dnum, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorChuSetRequest request = new DoctorChuSetRequest(dset, isopen, dnum, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, null, listener);
	}

	public void casesdesc(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorBlglRequest request = new DoctorBlglRequest();
		mConnection.sendRequestByPost(request, diag, DoctorBlglResponse.class, listener);
	}

	public void casesmange(String orderid, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorBlglInfoRequest request = new DoctorBlglInfoRequest(orderid);
		mConnection.sendRequestByPost(request, diag, DoctorBlglInfoResponse.class, listener);
	}

	public void casespatient(String bid, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorBlglPatientRequest request = new DoctorBlglPatientRequest(bid);
		mConnection.sendRequestByPost(request, diag, DoctorBlglPatientResponse.class, listener);
	}

	public void docreserve(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorReserveRequest request = new DoctorReserveRequest();
		mConnection.sendRequestByPost(request, diag, DoctorReserveResponse.class, listener);
	}

	public void docverify(String orderid, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorVerifyRequest request = new DoctorVerifyRequest(orderid);
		mConnection.sendRequestByPost(request, diag, null, listener);
	}

	public void docconsulting(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorMessageRequest request = new DoctorMessageRequest();
		mConnection.sendRequestByPost(request, diag, DoctorMessageResponse.class, listener);
	}

	public void docincome(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorSRXQRequest request = new DoctorSRXQRequest();
		mConnection.sendRequestByPost(request, diag, DoctorSRXQResponse.class, listener);
	}

	public void docaccountinfo(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorZHZXInfoRequest request = new DoctorZHZXInfoRequest();
		mConnection.sendRequestByPost(request, diag, DoctorZHZXResponse.class, listener);
	}

	public void docaccount(String password, String bindingphone, String bindingqq, String bindingwei, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorZHZXRequest request = new DoctorZHZXRequest(password, bindingphone, bindingqq, bindingwei );
		mConnection.sendRequestByPost(request, diag, DoctorZHZXResponse.class, listener);
	}

	public void docinfo(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorUserInfoRequest request = new DoctorUserInfoRequest();
		mConnection.sendRequestByPost(request, diag, DoctorUserInfoResponse.class, listener);
	}

	public void docRealname(String realname, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorUserRealnameRequest request = new DoctorUserRealnameRequest(realname, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void docImg(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorUserImgRequest request = new DoctorUserImgRequest(HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, DoctorImgResponse.class, listener);
	}

	public void docJianJie(String jianjie, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorUserJianjieRequest request = new DoctorUserJianjieRequest(jianjie, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void docShanChang(String shanchang, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorUserShanChangRequest request = new DoctorUserShanChangRequest(shanchang, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void docHospital(String hospital, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorUserHospitalRequest request = new DoctorUserHospitalRequest(hospital, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void docKeshi(String keshi, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorUserKeShiRequest request = new DoctorUserKeShiRequest(keshi, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void docZhiCheng(String zhicheng, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorUserZhiChengRequest request = new DoctorUserZhiChengRequest(zhicheng, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void docEmail(String email, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorUserEmailRequest request = new DoctorUserEmailRequest(email, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void docpersonal(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorPersonalRequest request = new DoctorPersonalRequest();
		mConnection.sendRequestByPost(request, diag, DoctorPersonalResponse.class, listener);
	}

	public void docAddBank(String bankname, String bankno, String banksn, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorAddBankRequest request = new DoctorAddBankRequest(bankname, bankno, banksn, HDoctorCode.YES, HDoctorCode.ADD);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void docDelBank(String bankno, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorDelBankRequest request = new DoctorDelBankRequest(bankno, HDoctorCode.YES, HDoctorCode.DEL);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void docModifyBank(String bankno, String banksn, String bankisselect, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorModifyBankRequest request = new DoctorModifyBankRequest(bankno, banksn, HDoctorCode.YES, HDoctorCode.MODIFY, bankisselect);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void patlogin(String username, String password, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientLoginRequest request = new PatientLoginRequest(username, password);
		mConnection.sendRequestByPost(request, diag, PatientLoginResponse.class, listener);
	}

	public void patregisterphone(String username, String password, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientRegisterPhoneRequest request = new PatientRegisterPhoneRequest(username, password, HDoctorCode.PATIENT, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, PatientRegisterPhoneResponse.class, listener);
	}

	public void patregister(String nicheng, String sex, String age, String address, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientRegisterRequest request = new PatientRegisterRequest(nicheng, sex, age, address, HDoctorCode.PATIENT, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, PatientRegisterResponse.class, listener);
	}

	public void medicine(RequestProgressDialog diag, NetworkConnectListener listener){
		PatientWDYXRequest request = new PatientWDYXRequest();
		mConnection.sendRequestByPost(request, diag, PatientWDYXResponse.class, listener);
	}

	public void addmedicine(String name, String beizhu, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientAddMedicineRequest request = new PatientAddMedicineRequest(name, beizhu, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void addmedicinenoimg(String name, String beizhu, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientAddMedicineNoImgRequest request = new PatientAddMedicineNoImgRequest(name, beizhu, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void delmedicine(String medicineid, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientDelMedicineRequest request = new PatientDelMedicineRequest(medicineid, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void alyreport(int score, int height, int weight, int age, String health, String jieguo, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientAlyReportRequest request = new PatientAlyReportRequest(score, height, weight, age, health, jieguo);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void usereport(RequestProgressDialog diag, NetworkConnectListener listener){
		PatientUserReportRequest request = new PatientUserReportRequest();
		mConnection.sendRequestByPost(request, diag, PatientUserReportResponse.class, listener);
	}

	public void testrecord(RequestProgressDialog diag, NetworkConnectListener listener){
		PatientTestRecordRequest request = new PatientTestRecordRequest();
		mConnection.sendRequestByPost(request, diag, PatientTestRecordResponse.class, listener);
	}

	public void consultrecord(RequestProgressDialog diag, NetworkConnectListener listener){
		PatientConsultRecordRequest request = new PatientConsultRecordRequest();
		mConnection.sendRequestByPost(request, diag, PatientConsultRecordResponse.class, listener);
	}

	public void healthyremind(RequestProgressDialog diag, NetworkConnectListener listener){
		PatientHealthyRemindRequest request = new PatientHealthyRemindRequest();
		mConnection.sendRequestByPost(request, diag, PatientHealthyRemindResponse.class, listener);
	}

	public void txa(String txaid, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientTXARequest request = new PatientTXARequest(txaid,HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void txb(String txbid, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientTXBRequest request = new PatientTXBRequest(txbid, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void addremind(String name, String takeingtime, String takingnum, String takingperiod, String beizhu, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientAddMedicinePromptRequest request = new PatientAddMedicinePromptRequest(name, takeingtime, takingnum, takingperiod, beizhu, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void myorder(RequestProgressDialog diag, NetworkConnectListener listener){
		PatientWDDDRequest request = new PatientWDDDRequest();
		mConnection.sendRequestByPost(request, diag, PatientWDDDResponse.class, listener);
	}

	public void patcomment(String orderid, String content, int manyi, int zhuanye, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientCommentRequest request = new PatientCommentRequest(orderid, content, manyi, zhuanye, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void myappointment(RequestProgressDialog diag, NetworkConnectListener listener){
		PatientWDYYRequest request = new PatientWDYYRequest();
		mConnection.sendRequestByPost(request, diag, PatientWDYYResponse.class, listener);
	}

	public void patpersonal(RequestProgressDialog diag, NetworkConnectListener listener){
		PatientGRZXRequest request = new PatientGRZXRequest();
		mConnection.sendRequestByPost(request, diag, PatientGRZXResponse.class, listener);
	}

	public void orderdesc(String orderId, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientWDDDInfoRequest request = new PatientWDDDInfoRequest(orderId);
		mConnection.sendRequestByPost(request, diag, PatientWDDDInfoResponse.class, listener);
	}

	public void patinfo( RequestProgressDialog diag, NetworkConnectListener listener){
		PatientInfoRequest request = new PatientInfoRequest();
		mConnection.sendRequestByPost(request, diag, PatientInfoResponse.class, listener);
	}

	public void patinfoimg(String nicheng, String sex, String age, String address, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientInfoImgRequest request = new PatientInfoImgRequest(nicheng, sex, age, address, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, PatientInfoResponse.class, listener);
	}

	public void patinfonoimg(String nicheng, String sex, String age, String address, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientInfoNoImgRequest request = new PatientInfoNoImgRequest(nicheng, sex, age, address, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, PatientInfoResponse.class, listener);
	}

	public void pataccount(String password, String bindingphone, String bindingqq, String bindingwei, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientZHZXModifyRequest request = new PatientZHZXModifyRequest(password, bindingphone, bindingqq, bindingwei, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void pataccountinfo(RequestProgressDialog diag, NetworkConnectListener listener){
		PatientZHZXRequest request = new PatientZHZXRequest();
		mConnection.sendRequestByPost(request, diag, PatientZHZXResponse.class, listener);
	}

	public void mypublish(RequestProgressDialog diag, NetworkConnectListener listener){
		PatientWDFBRequest request = new PatientWDFBRequest();
		mConnection.sendRequestByPost(request, diag, PatientWDFBResponse.class, listener);
	}

	public void delmypublish(String frumid, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientWDFBDelRequest request = new PatientWDFBDelRequest(frumid, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, PatientWDFBResponse.class, listener);
	}

	public void collection(RequestProgressDialog diag, NetworkConnectListener listener){
		PatientWDSCRequest request = new PatientWDSCRequest();
		mConnection.sendRequestByPost(request, diag, PatientWDSCResponse.class, listener);
	}

	public void delcollection(String collectionid, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientWDSCDelRequest request = new PatientWDSCDelRequest(collectionid, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, PatientWDSCResponse.class, listener);
	}

	public void patcircle(RequestProgressDialog diag, NetworkConnectListener listener){
		PatientCircleRequest request = new PatientCircleRequest();
		mConnection.sendRequestByPost(request, diag, PatientCircleResponse.class, listener);
	}

	public void patcirclezan(String frumid, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientCircleZanRequest request = new PatientCircleZanRequest(frumid, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, PatientCircleResponse.class, listener);
	}

	public void contentdesc(String frumid, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientCircleInfoRequest request = new PatientCircleInfoRequest(frumid);
		mConnection.sendRequestByPost(request, diag, PatientCircleInfoResponse.class, listener);
	}

	public void patreply(String frumid, String content, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientReplyRequest request = new PatientReplyRequest(frumid, content, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, PatientCircleInfoResponse.class, listener);
	}

	public void circlecollection(String frumid, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientCircleCollectionRequest request = new PatientCircleCollectionRequest(frumid);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void circlepublish(String content, String ispublic, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientPublishRequest request = new PatientPublishRequest(content, ispublic, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void circlepublishnoimg(String content, String ispublic, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientPublishNoImgRequest request = new PatientPublishNoImgRequest(content, ispublic, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void community(RequestProgressDialog diag, NetworkConnectListener listener){
		PatientCommunityRequest request = new PatientCommunityRequest();
		mConnection.sendRequestByPost(request, diag, PatientCommunityResponse.class, listener);
	}

	public void patcommunityzan(String frumid, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientCommunityZanRequest request = new PatientCommunityZanRequest(frumid, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, PatientCommunityResponse.class, listener);
	}

	public void doccircle(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorCircleRequest request = new DoctorCircleRequest();
		mConnection.sendRequestByPost(request, diag, DoctorCircleResponse.class, listener);
	}

	public void doccirclezan(String frumid, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorCircleZanRequest request = new DoctorCircleZanRequest(frumid, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, DoctorCircleResponse.class, listener);
	}

	public void allpublic(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorPublicRequest request = new DoctorPublicRequest();
		mConnection.sendRequestByPost(request, diag, DoctorPublicResponse.class, listener);
	}

	public void docquan(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorCircleYSRequest request = new DoctorCircleYSRequest();
		mConnection.sendRequestByPost(request, diag, DoctorCircleYSResponse.class, listener);
	}

	public void docquanzan(String frumid, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorCircleYSZanRequest request = new DoctorCircleYSZanRequest(frumid, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, DoctorCircleYSResponse.class, listener);
	}

	public void docpubliczan(String frumid, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorPublicZanRequest request = new DoctorPublicZanRequest(frumid, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, DoctorPublicResponse.class, listener);
	}

	public void doccomments(String frumid, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorCircleInfoRequest request = new DoctorCircleInfoRequest(frumid);
		mConnection.sendRequestByPost(request, diag, DoctorCircleInfoResponse.class, listener);
	}

	public void docshoucang(String frumid, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorShouCangRequest request = new DoctorShouCangRequest(frumid);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void docreply(String frumid, String content, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorReplyRequest request = new DoctorReplyRequest(frumid, content,DataTransfer.getUid(), HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, DoctorCircleInfoResponse.class, listener);
	}

	public void docpublish(String content, String ispublic, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorPublishRequest request = new DoctorPublishRequest(content, ispublic, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void docpublishnoimg(String content, String ispublic, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorPublishNoImgRequest request = new DoctorPublishNoImgRequest(content, ispublic, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, EmptyResponse.class, listener);
	}

	public void docmyfabiao(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorMyFaBiaoRequest request = new DoctorMyFaBiaoRequest();
		mConnection.sendRequestByPost(request, diag, DoctorMyFaBiaoResponse.class, listener);
	}

	public void deldocmyfabiao(String frumid, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorMyFaBiaoDelRequest request = new DoctorMyFaBiaoDelRequest(frumid, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, DoctorMyFaBiaoResponse.class, listener);
	}

	public void docmyshoucang(RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorMyShouCangRequest request = new DoctorMyShouCangRequest();
		mConnection.sendRequestByPost(request, diag, DoctorMyShouCangResponse.class, listener);
	}

	public void deldocmyshoucang(String collectionid, RequestProgressDialog diag, NetworkConnectListener listener){
		DoctorMyShouCangDelRequest request = new DoctorMyShouCangDelRequest(collectionid, HDoctorCode.YES);
		mConnection.sendRequestByPost(request, diag, DoctorMyShouCangResponse.class, listener);
	}

	public void nearbydoc(String jingdu, String weidu, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientNearByDocRequest request = new PatientNearByDocRequest(jingdu, weidu);
		mConnection.sendRequestByPost(request, diag, PatientNearByDocResponse.class, listener);
	}

	public void nearbyhosp(String jingdu, String weidu, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientNearByHospRequest request = new PatientNearByHospRequest(jingdu, weidu);
		mConnection.sendRequestByPost(request, diag, PatientNearByHospResponse.class, listener);
	}

	public void zizhidoc(String jingdu, String weidu, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientZiZhiDocRequest request = new PatientZiZhiDocRequest(jingdu, weidu);
		mConnection.sendRequestByPost(request, diag, PatientZiZhiDocResponse.class, listener);
	}

	public void zizhihosp(String jingdu, String weidu, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientZiZhiHospRequest request = new PatientZiZhiHospRequest(jingdu, weidu);
		mConnection.sendRequestByPost(request, diag, PatientZiZhiHospResponse.class, listener);
	}

	public void docservitem(String did, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientYSJSRequest request = new PatientYSJSRequest(did);
		mConnection.sendRequestByPost(request, diag, PatientYSJSResponse.class, listener);
	}

	public void searchbydoc(String jingdu, String weidu, String keyword, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientSearchByDocRequest request = new PatientSearchByDocRequest(jingdu, weidu, keyword);
		mConnection.sendRequestByPost(request, diag, PatientSearchByDocResponse.class, listener);
	}

	public void searchbyhosp(String jingdu, String weidu, String keyword, RequestProgressDialog diag, NetworkConnectListener listener){
		PatientSearchByHospRequest request = new PatientSearchByHospRequest(jingdu, weidu, keyword);
		mConnection.sendRequestByPost(request, diag, PatientSearchByHospResponse.class, listener);
	}
}
