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

import iori.hdoctor.net.request.CheckVersionRequest;
import iori.hdoctor.net.request.LoginRequest;
import iori.hdoctor.net.request.TestRequest;
import iori.hdoctor.net.response.CheckVersionResponse;
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
		return DataTransfer.getUserId();
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
		LoginRequest request = new LoginRequest(username, password);
		mConnection.sendRequestByPost(request, diag, CheckVersionResponse.class, listener);
	}

}
