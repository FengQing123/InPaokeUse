package com.fq.inpaokeuse.util;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.fq.inpaokeuse.base.BaseApplication;

/**
 * @author fengqing
 * @date 2018/1/22
 */


public class PhoneUtil {
    /**
     * 获取手机屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 获取手机屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }


    public static int getPhoneDpi() {
        return getDisplayMetrics().densityDpi;
    }


    /**
     * A structure describing general information about a display, such as its
     * size, density, and font scaling.
     *
     * @return
     */
    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) BaseApplication.getInstance().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics;
    }

    /**
     * 根据手机的分辨率从 dip的单位转成为 px(像素)
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = 2.0f;
        if (context != null) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取手机厂商
     *
     * @return
     */
    public static String getPhoneBrand() {
        return Build.BRAND;
    }


    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;

    /**
     * 今日头条Android屏幕适配方法
     * 在Activity#onCreate 方法中调用下
     *
     * @param activity
     * @param application
     */
    public static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application) {
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            sNoncompatDensity = appDisplayMetrics.density;
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        /**
         * 以设计师宽度为360dp来适配
         */
        final float targetDensity = appDisplayMetrics.widthPixels / 360;
        final float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        final int targetDensityDpi = (int) (160 * targetDensity);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;


        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

}
