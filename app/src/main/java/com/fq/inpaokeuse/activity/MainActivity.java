package com.fq.inpaokeuse.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PersistableBundle;
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
}
