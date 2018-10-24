package com.fq.inpaokeuse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import com.fq.inpaokeuse.R;
import com.fq.inpaokeuse.util.PhoneUtil;

/**
 * @author fengqing
 * @date 2018/10/23
 */


public class SwTestActivity extends AppCompatActivity {

    LinearLayout mLinearOne, mLinearTwo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sw);
        mLinearOne = findViewById(R.id.ll_one);
        mLinearTwo = findViewById(R.id.ll_two);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Log.e("----", "dpi=" + PhoneUtil.getPhoneDpi());
            Log.e("----", "width1=" + mLinearOne.getWidth());
            Log.e("----", "width2=" + mLinearTwo.getWidth());
        }
    }
}
