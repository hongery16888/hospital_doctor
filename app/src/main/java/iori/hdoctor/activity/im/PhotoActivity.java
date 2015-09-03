package iori.hdoctor.activity.im;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import iori.hdoctor.R;

/**
 * Created by Administrator on 2015/8/30.
 */
public class PhotoActivity extends Activity {

    private ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.photo_main);

        ImageView img = (ImageView) findViewById(R.id.img);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        imageLoader.displayImage(getIntent().getStringExtra("photo"), img, options);

    }
}
