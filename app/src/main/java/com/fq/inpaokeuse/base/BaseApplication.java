package com.fq.inpaokeuse.base;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * @author fengqing
 * @date 2018/1/22
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initUniversalImageLoader();
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    /**
     * 初始化UIL配置参数
     */
    private void initUniversalImageLoader() {
        //创建默认的ImageLoader配置参数
//        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .writeDebugLogs() //打印log信息
                .build();
        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);
    }

    /**
     * UIL获取自己设置的配置信息
     *
     * @param context
     * @return
     */
    private ImageLoaderConfiguration getUniversalImagegLoaderConfig(Context context) {
        File cacheDir = StorageUtils.getCacheDirectory(context);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 1) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(context)) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()
                .build();

        return configuration;
    }
}
