package com.fq.inpaokeuse.activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fq.inpaokeuse.R;

/**
 * @author fengqing
 * @date 2018/1/23
 */

public class AnimationActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_animation);
    }

    public void animator(final View view) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);

        ValueAnimator animator = ValueAnimator.ofFloat(view.getLayoutParams().width, 500);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (float) animation.getAnimatedValue();
                view.getLayoutParams().width = (int) value;
                view.requestLayout();
            }
        });
        animator.start();
    }
}
