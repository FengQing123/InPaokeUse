package com.fq.inpaokeuse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fq.inpaokeuse.R;
import com.fq.inpaokeuse.widget.HeartRateDefineChart;

/**
 * @author fengqing
 * @date 2018/1/22
 */

public class ZhiHuHeadActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ZhiHuHeadActivity";
    private HeartRateDefineChart mHeartRateDefineChart;
    private int i = 160;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu);

        /**
         * 以下代码是水波向外扩散
         */
//        mHeartRateDefineChart = findViewById(R.id.heartRate_defineChart);
//        mHeartRateDefineChart.setMaxHeartInterval(188);
//        new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        while (true) {
//                            try {
//                                if (i < 100000) {
//                                    i++;
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            Log.e(TAG, "run: i="+i );
//                                            mHeartRateDefineChart.setLinePoint(i);
//                                        }
//                                    });
//                                }
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//        ).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_click:
                createThread();
                break;
            default:
                break;
        }
    }

    private void createThread() {
//        Executors.newCachedThreadPool();
//        Executors.newCachedThreadPool(new ThreadFactory() {
//            @Override
//            public Thread newThread(@NonNull Runnable r) {
//                return null;
//            }
//        });
//        ExecutorService service = Executors.newFixedThreadPool(5);
//        service.shutdown();
//     ExecutorService service1=   Executors.newSingleThreadExecutor();
//        Executors.newScheduledThreadPool(5);
    }
}
