package com.fq.inpaokeuse.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.fq.inpaokeuse.R;
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
//        //设置没有标题
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //设置全屏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e(TAG, "onCreate: 屏幕高度(不包含状态栏)=" + PhoneUtil.getScreenHeight());//1920

        View decorView = getWindow().getDecorView();

        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int statusBarHeight = rect.top;
        Log.e(TAG, "onWindowFocusChanged: 状态栏高度statusBarHeight=" + statusBarHeight);//60

        int decorViewHeight = decorView.getHeight();
        Log.e(TAG, "onCreate: DecorView高度=" + decorViewHeight);//1920

        int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        Log.e(TAG, "onWindowFocusChanged: 内容顶部contentTop=" + contentTop);//168

        int titleBarHeight = contentTop - statusBarHeight;
        Log.e(TAG, "onWindowFocusChanged: 标题栏titleBarHeight=" + titleBarHeight);//108
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_to_define_view:
                UIHelper.showActivity(this, DefinedViewActivity.class);
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
            case R.id.btn_to_contact_list:
                UIHelper.showActivity(this, ContactsListActivity.class);
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
                UIHelper.showActivity(this, TouTiaoHeadActivity.class);
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
            default:
                break;
        }
    }
}
