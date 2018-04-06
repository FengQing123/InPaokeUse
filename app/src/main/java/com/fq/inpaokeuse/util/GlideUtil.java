package com.fq.inpaokeuse.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;

/**
 * @author fengqing
 * @date 2018/4/6
 */
public class GlideUtil {
    /**
     * 基本使用（不管是静态的，还是动态的，Glide会自动判断）
     *
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void loadImage(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext).load(url).into(imageView);
    }


    /**
     * 指定图片的加载格式（gif,png.jpg）
     * <p>
     * Glide有个亮眼的功能就是可以加载gif图片，可以自动识别是否是动态图
     * .asBitmap().load():只允许加载静态图片，Glide会展示该GIF图的第一帧，而不会去播放它（注意顺序不能调换）
     * .asGif(): 转成gif图片
     * .asFile()：强制指定文件格式的加载
     * .asDrawable():强制指定Drawable格式的加载
     *
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void loadImageAsBitmap(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext).asBitmap().load(url).into(imageView);
    }


    /**
     * 把RequestOptions作为参数传入到方法中
     *
     * @param context
     * @param url
     * @param imageView
     * @param options
     */
    public static void loadImageWithOptions(Context context, String url, ImageView imageView, RequestOptions options) {
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    /**
     * 设置占位图功能
     *
     * @param mContext
     * @param url
     * @param drawable
     * @param imageView
     */
    public static void loadImageWithPlaceholder(Context mContext, String url, int drawable, ImageView imageView) {
        RequestOptions options = new RequestOptions().placeholder(drawable);
        loadImageWithOptions(mContext, url, imageView, options);
    }

    /**
     * 设置异常占位图功能
     *
     * @param mContext
     * @param url
     * @param drawable
     * @param imageView
     */
    public static void loadImageWithErrorPlace(Context mContext, String url, int drawable, ImageView imageView) {
        RequestOptions options = new RequestOptions().error(drawable);
        loadImageWithOptions(mContext, url, imageView, options);
    }

    /**
     * 指定图片大小(大小好像不变，只是使图片变模糊了，改变的是像素)
     * Target.SIZE_ORIGINAL：加载图片的原始尺寸，Glide不做压缩，会面临更高的OOM风险
     *
     * @param mContext
     * @param url
     * @param width
     * @param height
     * @param imageView
     */
    public static void loadImageInSpecailSize(Context mContext, String url, int width, int height, ImageView imageView) {
        RequestOptions options = new RequestOptions().override(width, height);
        loadImageWithOptions(mContext, url, imageView, options);
    }

    /**
     * 禁用Glide的内存缓存功能
     * <p>
     * <p>
     * Glide拥有强大的缓存功能（内存缓存和硬盘缓存），下次加载时直接从缓存中获取，不会再去网络下载
     * 内存缓存：防止应用重复将图片数据读取到内存当中
     * 硬盘缓存：防止应用重复从网络或其他地方重复下载和读取数据
     * <p>
     * DiskCacheStrategy.NONE： 表示不缓存任何内容。
     * DiskCacheStrategy.DATA： 表示只缓存原始图片。
     * DiskCacheStrategy.RESOURCE： 表示只缓存转换过后的图片。（压缩和转换后的图片）
     * DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
     * DiskCacheStrategy.AUTOMATIC： 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。
     *
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void loadImageSkipMemoryCache(Context mContext, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions().skipMemoryCache(true);
        loadImageWithOptions(mContext, url, imageView, options);
    }

    /**
     * Glide硬盘缓存功能
     * <p>
     * DiskCacheStrategy.NONE： 表示不缓存任何内容。
     * DiskCacheStrategy.DATA： 表示只缓存原始图片。
     * DiskCacheStrategy.RESOURCE： 表示只缓存转换过后的图片。（压缩和转换后的图片）
     * DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
     * DiskCacheStrategy.AUTOMATIC： 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。
     *
     * @param mContext
     * @param url
     * @param diskCacheStrategy
     * @param imageView
     */
    public static void loadImageDiskCacheStrategy(Context mContext, String url, DiskCacheStrategy diskCacheStrategy, ImageView imageView) {
        RequestOptions options = new RequestOptions().diskCacheStrategy(diskCacheStrategy);
        loadImageWithOptions(mContext, url, imageView, options);
    }


    /**
     * into()方法中的使用 ，主要Glide的Target的使用
     * 这里创建了一个SimpleTarget的实例，并且指定它的泛型是Drawable，也就是方法参数中传过来的Drawable对象。
     * 有了这个对象之后你可以使用它进行任意的逻辑操作,这里只是简单的显示在ImageView上
     * SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
     * public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
     * imageView.setImageDrawable(resource);
     * }};
     *
     * @param mContext
     * @param url
     * @param simpleTarget
     */
    public static void loadImageToSimpleTarget(Context mContext, String url, SimpleTarget<Drawable> simpleTarget) {
        Glide.with(mContext).load(url).into(simpleTarget);
    }

    /**
     * Glide的预加载功能
     * 预加载：提前对图片进行一个预加载，等真正需要加载图片的时候就直接从缓存中读取，不用再等待慢长的网络加载时间
     * preload()方法有两个方法重载，一个不带参数，表示将会加载图片的原始尺寸，另一个可以通过参数指定加载图片的宽和高。
     *
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void loadImageProload(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext).load(url).preload();
//        Glide.with(mContext).load(url).into(imageView);
    }


    /**
     * submit()方法 只会下载图片，而不会对图片进行加载 可以查看缓存文件的存储路径
     * <p>
     * <p>
     * 两个方法重载
     * submit()：用于下载原始尺寸的图片
     * submit(int width, int height)：可以指定下载图片的尺寸
     * <p>
     * 当调用submit()方法时会返回一个FutureTarget对象，Glide会在后台开始下载图片，调用FutureTarget的get()方法就可以获取下载好的图片文件
     * 因为get()方法会阻塞线程，所以要new个线程
     *
     * @param mContext 这里必须是Application context
     */
    public void loadImage(final Context mContext) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://www.guolin.tech/book.png";
                    FutureTarget<File> target = Glide.with(mContext)
                            .asFile()
                            .load(url)
                            .submit();
                    File imageFile = target.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 回调和监听 listener()方法 监听Glide加载图片的状态
     * GlideException 可以查看错误信息
     * 当图片加载完成的时候就会回调onResourceReady()方法，而当图片加载失败的时候就会回调onLoadFailed()方法
     * onResourceReady()方法和onLoadFailed()方法都有一个布尔值的返回值，返回false就表示这个事件没有被处理，还会继续向下传递，
     * 返回true就表示这个事件已经被处理掉了，从而不会再继续向下传递。
     * 例如：如果我们在RequestListener的onResourceReady()方法中返回了true，那么就不会再回调Target的onResourceReady()方法了。
     */
    public void loadImageInListener(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
    }


    /**
     * 圆形显示
     *
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void loadImageInCircle(Context mContext, String url, ImageView imageView) {
        //截图中间部分
        RequestOptions options1 = new RequestOptions().centerCrop();
        //自适应
        RequestOptions options2 = new RequestOptions().fitCenter();
        //圆形显示
        RequestOptions options3 = new RequestOptions().circleCrop();
        loadImageWithOptions(mContext, url, imageView, options3);
    }

    /**
     * 圆角化显示
     *
     * @param mContext
     * @param url
     * @param imageView
     * @param roundingRadius
     */
    public static void loadImageInRoundCircle(Context mContext, String url, ImageView imageView, int roundingRadius) {
        RequestOptions options = new RequestOptions().transform(new RoundedCorners(roundingRadius));
        loadImageWithOptions(mContext, url, imageView, options);
    }
}
