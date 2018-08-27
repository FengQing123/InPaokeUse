package com.fq.inpaokeuse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.fq.inpaokeuse.R;

/**
 * @author fengqing
 * @date 2018/7/23
 */

public class ThreadTestActivity extends AppCompatActivity {

    private static final String TAG = "ThreadTestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        final Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.sleep(3000);
                        Log.e(TAG, "run: Hello World");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        findViewById(R.id.btn_run_thread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.start();
            }
        });
    }
}
