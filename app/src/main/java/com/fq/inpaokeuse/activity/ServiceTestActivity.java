package com.fq.inpaokeuse.activity;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fq.inpaokeuse.util.Logger;

/**
 * @author fengqing
 * @date 2019/7/4
 */

public class ServiceTestActivity extends AppCompatActivity {

    private static final String TAG = "ServiceTestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 这里发起3个后台任务请求
         */
        Intent service = new Intent(this, LocalIntentService.class);
        service.putExtra("task_action", "com.fq.inpaokeuse.TASK1");
        startService(service);
        service.putExtra("task_action", "com.fq.inpaokeuse.TASK2");
        startService(service);
        service.putExtra("task_action", "com.fq.inpaokeuse.TASK3");
        startService(service);

    }

    public static class LocalIntentService extends IntentService {

        private static final String TAG = "LocalIntentService";

        public LocalIntentService() {
            super(TAG);
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            String action = intent.getStringExtra("task_action");
            Logger.error(TAG, "receive task=" + action);

            //模拟耗时操作
            SystemClock.sleep(3000);

            //模拟处理指定的后台任务
            if ("com.fq.inpaokeuse.TASK1".equals(action)) {
                Logger.error(TAG, "handle task:" + action);
            }
        }

        @Override
        public void onCreate() {
            Logger.error(TAG, "service onCreate");
            super.onCreate();
        }

        @Override
        public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
            Logger.error(TAG, "service onStartCommand");
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public void onDestroy() {
            //验证IntentService的停止时机
            Logger.error(TAG, "service destroy");
            super.onDestroy();
        }
    }
}

