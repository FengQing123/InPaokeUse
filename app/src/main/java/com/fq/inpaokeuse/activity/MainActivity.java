package com.fq.inpaokeuse.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.fq.inpaokeuse.R;
import com.fq.inpaokeuse.util.Logger;
import com.fq.inpaokeuse.util.PhoneUtil;
import com.fq.inpaokeuse.util.UIHelper;
import com.fq.inpaokeuse.viewpager.ViewpagerActivity;
import com.fq.inpaokeuse.viewpager.WeiboViewPagerActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author fengqing
 * @date 2018/1/22
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.error(TAG, "onCreate");
//        //设置没有标题
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //设置全屏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Logger.error(TAG, "onSaveInstanceState1");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Logger.error(TAG, "onSaveInstanceState2");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Logger.error(TAG, "onRestoreInstanceState");

    }

    @Override
    protected void onStart() {
        super.onStart();
        showNotification();
//        testThreadLocal();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.error(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.error(TAG, "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.error(TAG, "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.error(TAG, "onDestroy");
        System.gc();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e(TAG, "onWindowFocusChanged:屏幕高度(不包含状态栏)=" + PhoneUtil.getScreenHeight());//1920
        Log.e(TAG, "onWindowFocusChanged:屏幕宽度=" + PhoneUtil.getScreenWidth());
        Log.e(TAG, "onWindowFocusChanged:dpi=" + PhoneUtil.getPhoneDpi());


        View decorView = getWindow().getDecorView();

        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int statusBarHeight = rect.top;
        Log.e(TAG, "onWindowFocusChanged:状态栏高度statusBarHeight=" + statusBarHeight);//60

        int decorViewHeight = decorView.getHeight();
        Log.e(TAG, "onWindowFocusChanged: DecorView高度=" + decorViewHeight);//1920

        int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        Log.e(TAG, "onWindowFocusChanged: 内容顶部contentTop=" + contentTop);//168

        int titleBarHeight = contentTop - statusBarHeight;
        Log.e(TAG, "onWindowFocusChanged: 标题栏titleBarHeight=" + titleBarHeight);//108
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_to_define_view:
                UIHelper.showActivity(this, DispatchTouchEventActivity.class);
                break;
            case R.id.btn_to_zhiHu:
                UIHelper.showActivity(this, ZhiHuHeadActivity.class);
                break;
            case R.id.btn_to_animation:
                UIHelper.showActivity(this, AnimationActivity.class);
                break;
            case R.id.btn_to_3d_animation:
                UIHelper.showActivity(this, Animation3DActivity.class);
                break;
            case R.id.btn_to_view:
                UIHelper.showActivity(this, ViewTestActivity.class);
                break;
            case R.id.btn_to_define_scale:
                UIHelper.showActivity(this, DefineScaleActivity.class);
                break;
            case R.id.btn_to_glide:
                UIHelper.showActivity(this, GlideTestActivity.class);
                break;
            case R.id.btn_to_uil:
                UIHelper.showActivity(this, UniversalImageLoaderTestActivity.class);
                break;
            case R.id.btn_to_toutiao:
                UIHelper.showActivity(this, AppBarLayoutTestActivity.class);
                break;
            case R.id.btn_to_camera:
                UIHelper.showActivity(this, CameraActivity.class);
                break;
            case R.id.btn_to_pullShow:
                UIHelper.showActivity(this, PullShowActivity.class);
                break;
            case R.id.btn_to_goMarket:
                UIHelper.showActivity(this, GoMarketActivity.class);
                break;
            case R.id.btn_to_chartView:
                UIHelper.showActivity(this, ChartViewActivity.class);
                break;
            case R.id.btn_to_search:
                UIHelper.showActivity(this, SearchActivity.class);
                break;
            case R.id.btn_to_viewpager:
                UIHelper.showActivity(this, ViewpagerActivity.class);
                break;
            case R.id.btn_to_weibo:
                UIHelper.showActivity(this, WeiboViewPagerActivity.class);
                break;
            case R.id.btn_to_thread:
                UIHelper.showActivity(this, ThreadTestActivity.class);
                break;
            case R.id.btn_to_sw:
                UIHelper.showActivity(this, SwTestActivity.class);
                break;
            case R.id.btn_to_constraint:
                UIHelper.showActivity(this, ConstraintLayoutTestActivity.class);
                break;
            case R.id.btn_to_drawlayout:
                UIHelper.showActivity(this, DrawLayoutActivity.class);
                break;
            case R.id.btn_to_dialogfragment:
                UIHelper.showActivity(this, DialogFragmentActivity.class);
                break;
            case R.id.btn_to_service:
                UIHelper.showActivity(this, ServiceTestActivity.class);
                break;
            case R.id.btn_to_image_loader:
                UIHelper.showActivity(this, ImageLoaderActivity.class);
                break;
            default:
                break;
        }
    }

    private void showNotification() {

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, ViewTestActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("Hello World")
                .setContentText("Show me the world")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());

    }


    /**
     * ThreadLocal的简单测试
     */
    private void testThreadLocal() {
        final ThreadLocal<Boolean> mThreadLocal = new ThreadLocal<>();
        mThreadLocal.set(true);
        Logger.error(TAG, "Thread[#main] threadLocal=" + mThreadLocal.get());

        new Thread("Thread[#1]") {
            @Override
            public void run() {
                mThreadLocal.set(false);
                Logger.error(TAG, "Thread[#1] threadLocal=" + mThreadLocal.get());

            }
        }.start();

        new Thread("Thread[#2]") {
            @Override
            public void run() {
                Logger.error(TAG, "Thread[#2] threadLocal=" + mThreadLocal.get());

            }
        }.start();
    }

    /**
     * 4中线程池的典型使用方法
     */
    private void testThreadPool() {
        Runnable command = new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
            }
        };

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        fixedThreadPool.submit(command);

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.submit(command);

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);
        //2000ms后执行command
        scheduledThreadPool.schedule(command, 2000, TimeUnit.MILLISECONDS);
        //延迟10ms后，每隔1000ms执行一次command
        scheduledThreadPool.scheduleAtFixedRate(command, 10, 1000, TimeUnit.MILLISECONDS);
        //延迟10ms后执行command,再下一个任务开始执行前需等上一个任务结束1000ms再开始执行
        scheduledThreadPool.scheduleWithFixedDelay(command, 10, 1000, TimeUnit.MILLISECONDS);

        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        singleThreadPool.submit(command);
    }
}
