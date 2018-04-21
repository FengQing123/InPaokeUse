package com.fq.inpaokeuse.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fq.inpaokeuse.R;
import com.fq.inpaokeuse.util.PhoneUtil;

/**
 * @author fengqing
 * @date 2018/4/9
 */

public class PullShowActivity extends AppCompatActivity {

    private static final String TAG = "PullShowActivity";

    private LinearLayout mHiddenLayout;

    private float mDensity;

    private int mHiddenViewMeasuredHeight;

    private ImageView mIv;


    private LinearLayout mLinearBlue, mLinearRed;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_show);

        mLinearBlue = findViewById(R.id.ll_blue);
        mLinearRed = findViewById(R.id.ll_red);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showBlue(mLinearBlue, mLinearRed);
//                showRed(mLinearRed, mLinearBlue);
            }
        }, 3000);


//        mHiddenLayout = (LinearLayout) findViewById(R.id.linear_hidden);
//        mIv = (ImageView) findViewById(R.id.my_iv);
//
//        mDensity = getResources().getDisplayMetrics().density;
//
//        mHiddenViewMeasuredHeight = PhoneUtil.dip2px(this, 120);
//        Log.e(TAG, "onCreate: mHidden=" + mHiddenViewMeasuredHeight + ",mDensity=" + mDensity);
    }


    private void showBlue(final LinearLayout mLinearBlue, final LinearLayout mLinearRed) {
        ValueAnimator animatorBlue = createDropAnimator(mLinearBlue, mLinearBlue.getHeight(), PhoneUtil.getScreenHeight());
        animatorBlue.start();

        ValueAnimator animatorRed = createDropAnimator(mLinearRed, mLinearRed.getHeight(), 0);
        animatorRed.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLinearRed.setVisibility(View.GONE);
            }
        });
        animatorRed.start();


//        ObjectAnimator animator = ObjectAnimator.ofFloat(mLinearBlue, "y", mLinearBlue.getHeight(), PhoneUtil.getScreenHeight());
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float value = (float) animation.getAnimatedValue();
//                ViewGroup.LayoutParams layoutParams = mLinearBlue.getLayoutParams();
//                layoutParams.height = (int) value;
//                mLinearBlue.setLayoutParams(layoutParams);
//                Log.e(TAG, "onAnimationUpdate: value=" + value);
//            }
//        });
//        animator.start();
    }

    private void showRed(final LinearLayout mLinearRed, final LinearLayout mLinearBlue) {
        ValueAnimator animateRed = createDropAnimator(mLinearRed, mLinearRed.getHeight(), PhoneUtil.getScreenHeight());
        animateRed.start();

        int origHeight = mLinearBlue.getHeight();
        ValueAnimator animatorBlue = createDropAnimator(mLinearBlue, origHeight, 0);
        animatorBlue.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLinearBlue.setVisibility(View.GONE);
            }
        });
        animatorBlue.start();
    }


    //    public void onClick(View v) {
//        if (mHiddenLayout.getVisibility() == View.GONE) {
//            animateOpen(mHiddenLayout);
//            animationIvOpen();
//        } else {
//            animateClose(mHiddenLayout);
//            animationIvClose();
//        }
//    }
//
//    private void animateOpen(View v) {
//        v.setVisibility(View.VISIBLE);
//        ValueAnimator animator = createDropAnimator(v, 0, mHiddenViewMeasuredHeight);
//        animator.start();
//    }
//
//    private void animationIvOpen() {
//        RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        animation.setFillAfter(true);
//        animation.setDuration(100);
//        mIv.startAnimation(animation);
//    }
//
//    private void animationIvClose() {
//        RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        animation.setFillAfter(true);
//        animation.setDuration(100);
//        mIv.startAnimation(animation);
//    }
//
//    private void animateClose(final View view) {
//        int origHeight = view.getHeight();
//        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                view.setVisibility(View.GONE);
//            }
//        });
//        animator.start();
//    }
//
    private ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);
                Log.e(TAG, "onAnimationUpdate: value=" + value);
            }
        });
        return animator;
    }
}
