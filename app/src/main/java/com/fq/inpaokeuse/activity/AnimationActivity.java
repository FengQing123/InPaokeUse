package com.fq.inpaokeuse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fq.inpaokeuse.R;
import com.fq.inpaokeuse.util.AnimatorUtil;

/**
 * @author fengqing
 * @date 2018/1/23
 */

public class AnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
    }

    public void animator(View view) {
        AnimatorUtil.AnimationUseViewPropertyAnimator(view);
    }
}
