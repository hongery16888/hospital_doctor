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

import android.transition.SidePropagation;

import iori.hdoctor.activity.base.HDoctorCode;
import iori.hdoctor.net.request.CheckVersionRequest;
import iori.hdoctor.net.request.DoctorBenchRequest;
import iori.hdoctor.net.request.DoctorLoginRequest;
import iori.hdoctor.net.request.DoctorRegisterInfo2Request;
import iori.hdoctor.net.request.DoctorRegisterInfoRequest;
import iori.hdoctor.net.request.DoctorRegisterRequest;
import iori.hdoctor.net.request.DoctorServiceMagRequest;
import iori.hdoctor.net.request.DoctorServiceMagSetRequest;
import iori.hdoctor.net.request.DoctorStaticRequest;
import iori.hdoctor.net.request.TestRequest;
import iori.hdoctor.net.response.CheckVersionResponse;
import iori.hdoctor.net.response.DoctorBenchResponse;
import iori.hdoctor.net.response.DoctorLoginResponse;
import iori.hdoctor.net.response.DoctorRegisterInfo2Response;
import iori.hdoctor.net.response.DoctorRegisterInfoResponse;
import iori.hdoctor.net.response.DoctorRegisterResponse;
import iori.hdoctor.net.response.DoctorServiceMagResponse;
import iori.hdoctor.net.response.DoctorServiceMagSetResponse;
import iori.hdoctor.net.response.DoctorStaticResponse;
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

    public void checkVersion(String versionCode, RequestProgressDialog diag, NetworkConnectListener listener){
        CheckVersionRequest request = new CheckVersionRequest(versionCode);
        mConnection.sendRequestByPost(request, diag, CheckVersionResponse.class, listener);
    }

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

}
