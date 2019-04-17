package com.fq.inpaokeuse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fq.inpaokeuse.R;

/**
 * 学习事件分发机制
 *
 * @author fengqing
 * @date 2019/1/8
 */

public class DispatchTouchEventActivity extends AppCompatActivity {

    private static final String TAG = "DispatchTouchEventActiv";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_view);
        Button mBtn = findViewById(R.id.btn_dispatch);
        mBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "Button:OnTouch: down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "Button:OnTouch: move");

                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "Button:OnTouch: up");
                        break;
                }
                return false;
            }
        });

        TextView mText = findViewById(R.id.tv_dispatch);
        mText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "TextView:OnTouch: down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "TextView:OnTouch: move");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "TextView:OnTouch: up");
                        break;
                }
                return false;
            }
        });

        ImageView image = findViewById(R.id.image_dispatch);
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "ImageView:OnTouch: down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "ImageView:OnTouch: move");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "ImageView:OnTouch: up");
                        break;
                }
                return false;
            }
        });
    }
}
