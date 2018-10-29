package com.fq.inpaokeuse.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * log打印工具类
 *
 * @author fengqing
 * @date 2018/7/18
 */

public class Logger {

    private static final String TAG = "Logger";

    private static final String INFO = "INFO";

    private static final String ERROR = "ERROR";

    private static Handler handler;

    private static boolean ENABLE_LOG = true;

    public static void info(String message) {
        info(TAG, message);
    }

    public static void info(String tag, String message) {
        log(INFO, tag, message);
    }

    public static void error(String message) {
        error(TAG, message);
    }

    public static void error(String tag, String message) {
        log(ERROR, tag, message);
    }

    public static void setHandler(Handler handler) {
        Logger.handler = handler;
    }

    private static void log(String level, String tag, String message) {
        if (!ENABLE_LOG) {
            return;
        }
        if (level.equals(INFO)) {
            Log.i(tag, message);

        } else if (level.equals(ERROR)) {
            Log.e(tag, message);
        }
        if (handler != null) {
            Message msg = Message.obtain();
            msg.obj = "[" + level + "]" + message + "\n";
            handler.sendMessage(msg);
        }
    }
}