package iori.hdoctor.net;

public interface HttpRequest {

	public static final String CHARSET_UTF8 = "UTF-8";
	public static final String CHARSET = CHARSET_UTF8;
	public static final int TIMEOUT = 30000;
	// ///////////////////////////////////////////////////////////
	// Request error
	// ///////////////////////////////////////////////////////////
	/** 网络连接错误 **/
	public static final int ERROR_OTHER = -1;
	public static final String ERRORMSG_OTHER = "其他错误";
	/** 网络连接错误 **/
	public static final int ERROR_CONNECT = 101;
	public static final String ERRORMSG_CONNECT = "网络连接错误";
	/** 网络连接超时 **/
	public static final int ERROR_CONNECT_TIMEOUT = 102;
	public static final String ERRORMSG_CONNECT_TIMEOUT = "网络连接超时";
	/** 数据解析错误 **/
	public static final int ERROR_DATA_TRANSFORM = 103;
	public static final String ERRORMSG_DATA_TRANSFORM = "数据解析错误";
	/** 未登陆 **/
	public static final int ERROR_LOGOUT = 105;
	public static final String ERRORMSG_LOGOUT = "您当前尚未登录";
	/** 上传附件错误 **/
	public static final int ERROR_UPLOAD = 106;
	public static final String ERRORMSG_UPLOAD = "上传附件失败";
	
	/** 请求类型GET **/
	public static final int GET = 0;
	/** 请求类型POST **/
	public static final int POST = 1;

	/** 成功 **/
	public static final int ERROR_NONE = 0;

	// ///////////////////////////////////////////////////////////
	// Request action & URI
	// ///////////////////////////////////////////////////////////

	// 开发环境

    public static final String BASIC_PATH = "http://61.155.202.142:51080/";
//    public static final String PATH_NORMAL =  "etc_web";
    public static final String PATH_CHECK_VERSION = BASIC_PATH + "check_version";
    public static final String CHECK_VERSION =  "check_version";
	// ////////////////
	public static final String TEST_PATH = "webserviceTest";
	public static final String PHOTO_PATH = BASIC_PATH +  "app/";


	public static final String DOC_LOGIN = "doc_login";
	public static final String PATH_DOC_LOGIN = BASIC_PATH + "user/login";
	public static final String DOC_REGISTER = "doc_register";
	public static final String PATH_DOC_REGISTER = BASIC_PATH + "user/register";
	public static final String DOC_REGISTER_INFO = "doc_register_info";
	public static final String PATH_DOC_REGISTER_INFO = BASIC_PATH + "user/docregister";
	public static final String DOC_REGISTER_INFO_2 = "doc_register_info_2";
	public static final String PATH_DOC_REGISTER_INFO_2 = BASIC_PATH + "user/docregister2";
	public static final String DOC_BENCH = "doc_bench";
	public static final String PATH_DOC_BENCH = BASIC_PATH + "user/bench";
	public static final String DOC_STATIC = "doc_static";
	public static final String PATH_DOC_STATIC = BASIC_PATH + "user/docstatic";
	public static final String DOC_SERVICE_MAG = "doc_service_mag";
	public static final String PATH_DOC_SERVICE_MAG = BASIC_PATH + "user/docservmag";
	public static final String DOC_SERVICE_MAG_SET = "doc_service_mag_set";
	public static final String PATH_DOC_SERVICE_MAG_SET = BASIC_PATH + "user/docservmagset";
	public static final String DOC_CHUZHEN = "doc_chuzhen";
	public static final String PATH_DOC_CHUZHEN = BASIC_PATH + "user/docchuzhen";
	public static final String DOC_CHUSET = "doc_chuset";
	public static final String PATH_DOC_CHUSET = BASIC_PATH + "user/docchuset";
	public static final String DOC_CASES = "doc_cases";
	public static final String PATH_DOC_CASES = BASIC_PATH + "user/casesdesc";
	public static final String DOC_CASESMAG = "casesmange";
	public static final String PATH_DOC_CASESMAG = BASIC_PATH + "user/casesmange";
	public static final String DOC_CASESPATIENT = "casespatient";
	public static final String PATH_DOC_CASESPATIENT = BASIC_PATH + "user/casespatient";
	public static final String DOC_RESERVE = "docreserve";
	public static final String PATH_DOC_RESERVE = BASIC_PATH + "user/docreserve";
	public static final String DOC_VERIFY = "docverify";
	public static final String PATH_DOC_VERIFY = BASIC_PATH + "user/docverify";
	public static final String DOC_CONSULTING = "docconsulting";
	public static final String PATH_DOC_CONSULTING = BASIC_PATH + "user/docconsulting";
	public static final String DOC_INCOME_RECORD = "docwithdrawal";
	public static final String PATH_DOC_INCOME_RECORD = BASIC_PATH + "user/docwithdrawal";
	public static final String DOC_INCOME = "docincome";
	public static final String PATH_DOC_INCOME = BASIC_PATH + "user/docincome";
	public static final String DOC_ACCOUNT = "docaccount";
	public static final String PATH_DOC_ACCOUNT = BASIC_PATH + "user/docaccount";
	public static final String DOC_INFO = "docinfo";
	public static final String PATH_DOC_INFO = BASIC_PATH + "user/docinfo";
	public static final String DOC_PERSONAL = "docpersonal";
	public static final String PATH_DOC_PERSONAL = BASIC_PATH + "user/docpersonal";
	public static final String PAT_REGISTER_PHONE = "patregister";
	public static final String PATH_PAT_REGISTER_PHONE = BASIC_PATH + "user/patregister";
	public static final String PAT_LOGIN = "patlogin";
	public static final String PATH_PAT_LOGIN = BASIC_PATH + "user/userlogin";
	public static final String PAT_REGISTER_INFO = "ureginfo";
	public static final String PATH_PAT_REGISTER_INFO = BASIC_PATH + "user/ureginfo";
	public static final String PAT_MEDICINE = "medicine";
	public static final String PATH_PAT_MEDICINE = BASIC_PATH + "user/medicine";
	public static final String PAT_ADD_MEDICINE = "addmedicine";
	public static final String PATH_PAT_ADD_MEDICINE = BASIC_PATH + "user/addmedicine";
	public static final String PAT_ALY_REPORT = "alyreport";
	public static final String PATH_PAT_ALY_REPORT = BASIC_PATH + "user/alyreport";
	public static final String PAT_USER_REPORT = "usereport";
	public static final String PATH_PAT_USER_REPORT = BASIC_PATH + "user/usereport";
	public static final String PAT_TEST_RECORD = "testrecord";
	public static final String PATH_PAT_TEST_RECORD = BASIC_PATH + "user/testrecord";
	public static final String PAT_CONSULT_RECORD = "consultrecord";
	public static final String PATH_PAT_CONSULT_RECORD = BASIC_PATH + "user/consultrecord";
	public static final String PAT_HEALTHY_REMIND = "healthyremind";
	public static final String PATH_PAT_HEALTHY_REMIND = BASIC_PATH + "user/healthyremind";
	public static final String PAT_ADD_REMIND = "addremind";
	public static final String PATH_PAT_ADD_REMIND = BASIC_PATH + "user/addremind";
	public static final String PAT_MY_ORDER = "myorder";
	public static final String PATH_PAT_MY_ORDER = BASIC_PATH + "user/myorder";
	public static final String PAT_COMMENT = "comment";
	public static final String PATH_PAT_COMMENT = BASIC_PATH + "user/comment";
	public static final String PAT_MY_POINTMENT = "myappointment";
	public static final String PATH_PAT_MY_POINTMENT = BASIC_PATH + "user/myappointment";
	public static final String PAT_PERSONAL = "patpersonal";
	public static final String PATH_PAT_PERSONAL = BASIC_PATH + "user/patpersonal";
	public static final String PAT_ORDER_DESC = "orderdesc";
	public static final String PATH_PAT_ORDER_DESC = BASIC_PATH + "user/orderdesc";
	public static final String PAT_INFO = "patinfo";
	public static final String PATH_PAT_INFO = BASIC_PATH + "user/patinfo";
	public static final String PAT_ACCOUNT = "pataccount";
	public static final String PATH_PAT_ACCOUNT = BASIC_PATH + "user/pataccount";
	public static final String PAT_PUBLISH= "mypublish";
	public static final String PATH_PAT_PUBLISH = BASIC_PATH + "user/mypublish";
	public static final String PAT_COLLECTION= "collection";
	public static final String PATH_PAT_COLLECTION = BASIC_PATH + "user/collection";
	public static final String PAT_ROUND= "round";
	public static final String PATH_PAT_ROUND = BASIC_PATH + "user/round";
	public static final String PAT_CONTENT_DESC= "contentdesc";
	public static final String PATH_PAT_CONTENT_DESC = BASIC_PATH + "user/contentdesc";
	public static final String PAT_CIRCLE_COLLECTION = "patcollection";
	public static final String PATH_PAT_CIRCLE_COLLECTION = BASIC_PATH + "user/patcollection";
	public static final String PAT_CIRCLE_PUBLISH = "patpublish";
	public static final String PATH_PAT_CIRCLE_PUBLISH = BASIC_PATH + "user/patpublish";
	public static final String PAT_COMMUNITY = "community";
	public static final String PATH_PAT_COMMUNITY = BASIC_PATH + "user/community";
	public static final String DOC_CIRCLE = "doccircle";
	public static final String PATH_DOC_CIRCLE = BASIC_PATH + "user/doccircle";
	public static final String DOC_ALL_PUBLISH = "allpublic";
	public static final String PATH_DOC_ALL_PUBLISH = BASIC_PATH + "user/allpublic";
	public static final String DOC_COMMENTS = "doccomments";
	public static final String PATH_DOC_COMMENTS = BASIC_PATH + "user/doccomments";
	public static final String DOC_PUBLISH = "docrelease";
	public static final String PATH_DOC_PUBLISH = BASIC_PATH + "user/docrelease";
	public static final String DOC_SHOUCANG = "docshoucang";
	public static final String PATH_DOC_SHOUCANG = BASIC_PATH + "user/docshoucang";
	public static final String DOC_MY_FABIAO = "docmyfabiao";
	public static final String PATH_DOC_MY_FABIAO = BASIC_PATH + "user/docmyfabiao";
	public static final String DOC_MY_SHOUCANG = "docmyshoucang";
	public static final String PATH_DOC_MY_SHOUCANG = BASIC_PATH + "user/docmyshoucang";
	public static final String DOC_CIRCLE_YS = "docquan";
	public static final String PATH_DOC_CIRCLE_YS = BASIC_PATH + "user/docquan";
	public static final String PAT_ZIZHI_DOC = "zizhidoc";
	public static final String PATH_PAT_ZIZHI_DOC = BASIC_PATH + "user/zizhidoc";
	public static final String PAT_ZIZHI_HOSP = "zizhihosp";
	public static final String PATH_PAT_ZIZHI_HOSP = BASIC_PATH + "user/zizhihosp";
	public static final String PAT_NEARBYDOC = "nearbydoc";
	public static final String PATH_PAT_NEARBYDOC = BASIC_PATH + "user/nearbydoc";
	public static final String PAT_NEARBYHOSP = "nearbyhosp";
	public static final String PATH_PAT_NEARBYHOSP = BASIC_PATH + "user/nearbyhosp";
	public static final String PAT_DOC_SERVITEM = "docservitem";
	public static final String PATH_PAT_DOC_SERVITEM = BASIC_PATH + "user/docservitem";
	public static final String PAT_SEARCH_BY_DOC = "yssearch";
	public static final String PATH_PAT_SEARCH_BY_DOC = BASIC_PATH + "user/yssearch";
	public static final String PAT_SEARCH_BY_HOSP = "yysearch";
	public static final String PATH_PAT_SEARCH_BY_HOSP = BASIC_PATH + "user/yysearch";
	public static final String PAT_YUYUE = "patyuyue";
	public static final String PATH_PAT_YUYUE = BASIC_PATH + "user/patyuyue";
	public static final String PAT_ONLINE_CONS = "onlinecons";
	public static final String PATH_PAT_ONLINE_CONS = BASIC_PATH + "user/onlinecons";
	public static final String PAT_ONLINE_TEL = "onlinetel";
	public static final String PATH_PAT_ONLINE_TEL = BASIC_PATH + "user/onlinetel";
	public static final String PAT_HOSPITAL = "hospital";
	public static final String PATH_PAT_HOSPITAL = BASIC_PATH + "user/hospital";
	public static final String PAT_DOC_TEL = "doctel";
	public static final String PATH_PAT_DOC_TEL = BASIC_PATH + "user/doctel";
	public static final String PAT_CHUZHEN = "dchuzhen";
	public static final String PATH_PAT_CHUZHEN = BASIC_PATH + "user/dchuzhen";
	public static final String PAT_PINGJIA = "pingjia";
	public static final String PATH_PAT_PINGJIA = BASIC_PATH + "user/pingjia";
	public static final String PAT_FRIEND = "friends";
	public static final String PATH_PAT_FRIENDS = BASIC_PATH + "user/friends";
	public static final String PAT_ADD_FRIEND = "patadd";
	public static final String PATH_PAT_ADD_FRIENDS = BASIC_PATH + "user/patadd";
	public static final String PAT_AGREE_FRIEND = "agree";
	public static final String PATH_PAT_AGREE_FRIENDS = BASIC_PATH + "user/agree";
	public static final String DOC_FRIEND = "docfriend";
	public static final String PATH_DOC_FRIENDS = BASIC_PATH + "user/docfriend";
	public static final String DOC_ADD_FRIEND = "docjia";
	public static final String PATH_DOC_ADD_FRIENDS = BASIC_PATH + "user/docjia";
	public static final String DOC_AGREE_FRIEND = "docjiasucess";
	public static final String PATH_DOC_AGREE_FRIENDS = BASIC_PATH + "user/docjiasucess";
	public static final String FIND_PWD = "findpwd";
	public static final String PATH_FIND_PWD = BASIC_PATH + "user/findpwd";
	public static final String RESET_PWD = "resetpwd";
	public static final String PATH_RESET_PWD = BASIC_PATH + "user/resetpwd";
	public static final String FORCE_ADD = "forceadd";
	public static final String PATH_FORCE_ADD = BASIC_PATH + "user/forceadd";
	public static final String DOC_TIXIAN = "docapply";
	public static final String PATH_DOC_TIXIAN = BASIC_PATH + "user/docapply";
	public static final String SCAN_SN = "scansn";
	public static final String PATH_SCAN_SN = BASIC_PATH + "user/scansn";
	public static final String FRIEND_MSG_LIST = "friendmsglist";
	public static final String PATH_FRIEND_MSG_LIST = BASIC_PATH + "user/friendmsglist";
	public static final String CONFIRM_FRIEND = "confirmfriend";
	public static final String PATH_CONFIRM_FRIEND = BASIC_PATH + "user/confirmfriend";
	public static final String LOGIN_WCHAT = "threeloginofwx";
	public static final String PATH_LOGIN_WCHAT = BASIC_PATH + "user/threeloginofwx";
	public static final String LOGIN_QQ = "threeloginofqq";
	public static final String PATH_LOGIN_QQ = BASIC_PATH + "user/threeloginofqq";


	/** 06、检查更新 **/
//	public static final String CHECK_VERSION = "checkVersion";


	// ///////////////////////////////////////////////////////////
	// 取数据需要的key
	// ///////////////////////////////////////////////////////////
	/** result **/
	public static final String KEY_RESULT = "Result";
	/** action **/
	public static final String KEY_ACTION = "Action";
	/** error id **/
	public static final String KEY_ERROR_ID = "ErrorId";
	/** error message **/
	public static final String KEY_ERROR_MSG = "ErrorMsg";
	/** data **/
	public static final String KEY_DATA = "Data";

	// upload
	/** upload result **/
	public static final String KEY_SUCCEED = "Succeed";
	/** upload message id **/
	public static final String KEY_MESSAGE = "Message";
	/** upload return value **/
	public static final String KEY_RETURN_VALUE = "ReturnValue";

}
