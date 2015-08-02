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
