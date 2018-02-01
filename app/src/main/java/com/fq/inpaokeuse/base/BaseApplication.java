package com.fq.inpaokeuse.base;

import android.app.Application;

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
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
