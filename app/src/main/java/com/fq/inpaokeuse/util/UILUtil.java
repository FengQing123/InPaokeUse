package com.fq.inpaokeuse.util;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.fq.inpaokeuse.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * UniversalImageLoader库的工具类
 * <p>
 * 如何控制OOM:
 * 1.减少线程池中线程的个数，在ImageLoaderConfiguration中的（.threadPoolSize）中配置，推荐配置1-5
 * 2.在DisplayImageOptions选项中配置bitmapConfig为Bitmap.Config.RGB_565，因为默认是ARGB_8888， 使用RGB_565会比使用ARGB_8888少消耗2倍的内存
 * 3.在ImageLoaderConfiguration中配置图片的内存缓存为memoryCache(new WeakMemoryCache()) 或者不使用内存缓存
 * 4.在DisplayImageOptions选项中设置.imageScaleType(ImageScaleType.IN_SAMPLE_INT)或者imageScaleType(ImageScaleType.EXACTLY)
 * 5.尽量的使用displayImage()方法去加载图片,displayImage()方法中，对ImageView对象使用的是Weak references，方便垃圾回收器回收ImageView对象
 *
 * @author fengqing
 * @date 2018/2/12
 */

public class UILUtil {

    private static final String TAG = "UILUtil";
    private static String imageUrl = "http://www.guolin.tech/book.png";


    public static void useLoadImageOne(final ImageView mImageView) {

        String imageUrl = "https://l";

        ImageLoader.getInstance().loadImage(imageUrl, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {
                Log.e(TAG, "onLoadingStarted: ");
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Log.e(TAG, "onLoadingFailed: ");
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Log.e(TAG, "onLoadingComplete: ");
                mImageView.setImageBitmap(loadedImage);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                Log.e(TAG, "onLoadingCancelled: ");
            }
        });
    }

    public static void useLoadImageTwo(final ImageView mImageView) {
        String imageUrl = "https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg";
        ImageLoader.getInstance().loadImage(imageUrl, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                mImageView.setImageBitmap(loadedImage);
            }
        });
    }


    /**
     * 指定大小
     *
     * @param mImageView
     */
    public static void useLoadImageThree(final ImageView mImageView) {
        String imageUrl = "http://www.guolin.tech/book.png";
        ImageSize imageSize = new ImageSize(500, 500);
        ImageLoader.getInstance().loadImage(imageUrl, imageSize, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                mImageView.setImageBitmap(loadedImage);
            }
        });
    }


    /**
     * 使用DisplayImageOptions来配置图片显示的选项
     * DisplayImageOptions选项中有些选项对于loadImage()方法是无效的，比如showImageOnLoading, showImageForEmptyUri等，
     * <p>
     * <p>
     * DisplayImageOptions options = new DisplayImageOptions.Builder()
     * .showImageOnLoading(R.drawable.ic_stub) // resource or drawable
     * .showImageForEmptyUri(R.drawable.ic_empty) // resource or drawable
     * .showImageOnFail(R.drawable.ic_error) // resource or drawable
     * .resetViewBeforeLoading(false)  // default
     * .delayBeforeLoading(1000)
     * .cacheInMemory(false) // default
     * .cacheOnDisk(false) // default
     * .preProcessor(...)
     * .postProcessor(...)
     * .extraForDownloader(...)
     * .considerExifParams(false) // default
     * .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
     * .bitmapConfig(Bitmap.Config.ARGB_8888) // default
     * .decodingOptions(...)
     * .displayer(new SimpleBitmapDisplayer()) // default
     * .handler(new Handler()) // default
     * .build();
     */
    public static void useLoadImageFour(final ImageView mImageView) {
        ImageSize mImageSize = new ImageSize(100, 100);

        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance().loadImage(imageUrl, mImageSize, options, new SimpleImageLoadingListener() {

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                mImageView.setImageBitmap(loadedImage);
            }
        });
    }


    /**
     * 使用displayImage(),会根据控件的大小和imageScaleType来自动裁剪图片
     *
     * @param mImageView
     */
    public static void useDisplayImageOne(final ImageView mImageView) {
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher_round)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance().displayImage(imageUrl, mImageView, options);
    }


    /**
     * 图片下载进度的需求
     *
     * @param mImageView
     */
    public static void useDisplayImageTwo(final ImageView mImageView) {
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher_round)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance().displayImage(imageUrl, mImageView, options, new SimpleImageLoadingListener(), new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String s, View view, int i, int i1) {

            }
        });
    }

    /**
     * 加载其他来源的图片
     * <p>
     * "http://site.com/image.png" // from Web
     * "file:///mnt/sdcard/image.png" // from SD card
     * "file:///mnt/sdcard/video.mp4" // from SD card (video thumbnail)
     * "content://media/external/images/media/13" // from content provider
     * "content://media/external/video/media/13" // from content provider (video thumbnail)
     * "assets://image.png" // from assets
     * "drawable://" + R.drawable.img // from drawables (non-9patch images)------Use drawable:// only if you really need it! Always consider the native way to load drawables - ImageView.setImageResource(...) instead of using of ImageLoader.
     *
     * @param mImageView
     */
    public static void useDisplayImageThree(final ImageView mImageView) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher_round)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        //图片来源于文件
        String imageUrl = ImageDownloader.Scheme.FILE.wrap("/mnt/sdcard/image.png");

        //图片来源于Content provider
        String contentprividerUrl = "content://media/external/audio/albumart/13";

        //图片来源于assets
        String assetsUrl = ImageDownloader.Scheme.ASSETS.wrap("image.png");

        //图片来源于
        String drawableUrl = ImageDownloader.Scheme.DRAWABLE.wrap("R.drawable.image");

//      String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";

        ImageLoader.getInstance().displayImage(imageUrl, mImageView, options);
    }

    /**
     * 提供了PauseOnScrollListener这个类来控制ListView,GridView滑动过程中停止去加载图片，该类使用的是代理模式
     *
     * @param listView
     * @param pauseOnScroll 控制是否在滑动过程中暂停加载图片
     * @param pauseOnFling  猛的滑动界面的时候图片是否加载
     */
    public static void listOrGridLoadImage(ListView listView, boolean pauseOnScroll, boolean pauseOnFling) {
        listView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), pauseOnScroll, pauseOnFling));
//        gridView.setOnScrollListener(new PauseOnScrollListener(imageLoader, pauseOnScroll, pauseOnFling));
    }
}
