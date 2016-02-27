package caisheng.com.search;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by caochuang on 2015/5/7.
 */
public class MyImageLoader {
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private static MyImageLoader mInstance;

    public MyImageLoader(Context context) {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.i)
                .showImageOnFail(R.drawable.i)
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .delayBeforeLoading(500)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();//开始构建
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
    }

    public static MyImageLoader getInstance(Context context) {
        if (null == mInstance) {
            mInstance = new MyImageLoader(context);
        }
        return mInstance;
    }

    public void displayImage(String url, ImageView imageView) {
        imageLoader.displayImage(url, imageView, options);
    }
}
