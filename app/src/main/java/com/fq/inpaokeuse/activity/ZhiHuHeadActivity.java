package com.fq.inpaokeuse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fq.inpaokeuse.R;
import com.fq.inpaokeuse.widget.HeartRateDefineChart;

/**
 * @author fengqing
 * @date 2018/1/22
 */

public class ZhiHuHeadActivity extends AppCompatActivity {

    private static final String TAG = "ZhiHuHeadActivity";
    private HeartRateDefineChart mHeartRateDefineChart;
    private int i = 160;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu);

        mHeartRateDefineChart = findViewById(R.id.heartRate_defineChart);
        mHeartRateDefineChart.setMaxHeartInterval(188);
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                if (i < 100000) {
                                    i++;
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.e(TAG, "run: i="+i );
                                            mHeartRateDefineChart.setLinePoint(i);
                                        }
                                    });
                                }
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();
    }
}
