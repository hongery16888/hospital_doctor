package iori.hdoctor.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;

import iori.hdoctor.R;

public class MyApp extends Application {

    private float density;
    private ArrayList<Activity> activities = new ArrayList<Activity>();
    private DisplayImageOptions options;

    /*客户端在SD卡的存储根目录*/
    public final static String APP_ROOT = Environment.getExternalStorageDirectory().getPath() + File.separator;
    public final static String PHOTO_BASIC_PATH = "file://";

    @Override
    public void onCreate() {
        density = getResources().getDisplayMetrics().density;
        super.onCreate();
        initImageLoader(getApplicationContext());
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

        /*
        //我们在项目中不需要每一个都自己设置，一般使用createDefault()创建的ImageLoaderConfiguration就能使用，然后调用ImageLoader的init（）
        //方法将ImageLoaderConfiguration参数传递进去，ImageLoader使用单例模式
        //创建默认的ImageLoader配置参数
        */
//        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
//                .createDefault(context);
//        //Initialize ImageLoader with configuration.
//        ImageLoader.getInstance().init(configuration);


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
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(1000)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .considerExifParams(true) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .displayer(new SimpleBitmapDisplayer()) // default
                .build();

        setOptions(options);

        //全局初始化此配置
        ImageLoader.getInstance().init(config);
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
}
