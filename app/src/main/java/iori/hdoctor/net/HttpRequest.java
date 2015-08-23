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
	public static final String DOC_INCOME = "docincome";
	public static final String PATH_DOC_INCOME = BASIC_PATH + "user/docincome";
	public static final String DOC_INCOME_RECORD = "";
	public static final String PATH_DOC_INCOME_RECORD = BASIC_PATH + "user/";
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
