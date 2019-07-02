package com.fq.inpaokeuse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fq.inpaokeuse.R;
import com.fq.inpaokeuse.widget.FollowView;

/**
 * @author fengqing
 * @date 2018/2/1
 */

public class ViewTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        FollowView mFollowView = findViewById(R.id.follow_view);
    }





}
