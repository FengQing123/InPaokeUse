package com.fq.inpaokeuse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fq.inpaokeuse.R;

/**
 * CoordinatorLayout和AppBarLayout的结合使用
 *
 * @author fengqing
 * @date 2018/3/17
 */

public class AppBarLayoutTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_appbarlayout);
//        setContentView(R.layout.activity_appbarlayout_two);
        setContentView(R.layout.activity_appbarlayout_three);
    }
}



