package iori.hdoctor.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;

import io.rong.imkit.RongIM;
import io.rong.imlib.ipc.RongExceptionHandler;
import iori.hdoctor.net.entity.TestingReport;
import iori.hdoctor.net.response.DoctorLoginResponse;
import iori.hdoctor.net.response.DoctorServiceMagResponse;

public class MyApp extends Application {

    private float density;
    private ArrayList<Activity> activities = new ArrayList<Activity>();
    private DisplayImageOptions options;
    private DoctorLoginResponse user;
    private DoctorServiceMagResponse serviceMag;
    private TextView isSwith;
    private TextView personalTv;
    private Handler avatarHandler;
    private Handler refreshBankHandler;
    private Handler refreshMedicineHandler;
    private TestingReport report;
    private ArrayList<Integer> scores;
    private Handler refreshListHandler;
    private Handler refreshData;
    private Handler circleRefreshHandler;
    private Activity mainActivity;
    private Activity patDocInfoActivity;
    public LocationClient mLocationClient;
    public Vibrator mVibrator;
    public MyLocationListener mMyLocationListener;
    private double latitude;
    private double longitude;
    private String rongToken;

    /*客户端在SD卡的存储根目录*/
    public final static String APP_ROOT = Environment.getExternalStorageDirectory().getPath() + File.separator;
    public final static String PHOTO_BASIC_PATH = "file://";

    @Override
    public void onCreate() {
        density = getResources().getDisplayMetrics().density;
        super.onCreate();
        initImageLoader(getApplicationContext());

//        SDKInitializer.initialize(getApplicationContext());

        if ("iori.hdoctor".equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            RongIM.init(this);

        }

        mLocationClient = new LocationClient(this.getApplicationContext());
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);

    }

    /*
     * 获取手机屏幕大小
     */
    public float getDensity() {
        return density;
    }

    /**
     * 检测当前是否有网络连接，true表示有网络连接，false表示无网络连接
     */
    public boolean isNetworkConnect() {
        boolean flag = false;
        // 获得网络连接服务
        ConnectivityManager conm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取代表联网状态的check对象
        NetworkInfo check = conm.getActiveNetworkInfo();
        if (check != null) {
            flag = check.isAvailable();
        }
        return flag;
    }

    public void initImageLoader(Context context) {

        //缓存文件的目录
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "universalimageloader/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3) //线程池内线程的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //将保存的时候的URI名称用MD5 加密
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024) // 内存缓存的最大值
                .diskCacheSize(50 * 1024 * 1024)  // SD卡缓存的最大值
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                        // 由原先的discCache -> diskCache
                .diskCache(new UnlimitedDiskCache(cacheDir))//自定义缓存路径
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .build();

        setOptions(options);

        //全局初始化此配置
        ImageLoader.getInstance().init(config);
    }

    /**
     * 实现实时位置回调监听
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            setLatitude(location.getLatitude());
            setLongitude(location.getLongitude());
        }
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Activity activity) {
        activities.add(activity);
    }

    public DisplayImageOptions getOptions() {
        return options;
    }

    public void setOptions(DisplayImageOptions options) {
        this.options = options;
    }

    public DoctorLoginResponse getUser() {
        return user;
    }

    public void setUser(DoctorLoginResponse user) {
        this.user = user;
    }

    public DoctorServiceMagResponse getServiceMag() {
        return serviceMag;
    }

    public void setServiceMag(DoctorServiceMagResponse serviceMag) {
        this.serviceMag = serviceMag;
    }

    public TextView getIsSwith() {
        return isSwith;
    }

    public void setIsSwith(TextView isSwith) {
        this.isSwith = isSwith;
    }

    public TextView getPersonalTv() {
        return personalTv;
    }

    public void setPersonalTv(TextView personalTv) {
        this.personalTv = personalTv;
    }

    public Handler getAvatarHandler() {
        return avatarHandler;
    }

    public void setAvatarHandler(Handler avatarHandler) {
        this.avatarHandler = avatarHandler;
    }

    public Handler getRefreshBankHandler() {
        return refreshBankHandler;
    }

    public void setRefreshBankHandler(Handler refreshBankHandler) {
        this.refreshBankHandler = refreshBankHandler;
    }

    public Handler getRefreshMedicineHandler() {
        return refreshMedicineHandler;
    }

    public void setRefreshMedicineHandler(Handler refreshMedicineHandler) {
        this.refreshMedicineHandler = refreshMedicineHandler;
    }

    public TestingReport getReport() {
        return report;
    }

    public void setReport(TestingReport report) {
        this.report = report;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }

    public Handler getRefreshListHandler() {
        return refreshListHandler;
    }

    public void setRefreshListHandler(Handler refreshListHandler) {
        this.refreshListHandler = refreshListHandler;
    }

    public Handler getRefreshData() {
        return refreshData;
    }

    public void setRefreshData(Handler refreshData) {
        this.refreshData = refreshData;
    }

    public Handler getCircleRefreshHandler() {
        return circleRefreshHandler;
    }

    public void setCircleRefreshHandler(Handler circleRefreshHandler) {
        this.circleRefreshHandler = circleRefreshHandler;
    }

    public Activity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Activity getPatDocInfoActivity() {
        return patDocInfoActivity;
    }

    public void setPatDocInfoActivity(Activity patDocInfoActivity) {
        this.patDocInfoActivity = patDocInfoActivity;
    }

    public String getRongToken() {
        return rongToken;
    }

    public void setRongToken(String rongToken) {
        this.rongToken = rongToken;
    }
}
