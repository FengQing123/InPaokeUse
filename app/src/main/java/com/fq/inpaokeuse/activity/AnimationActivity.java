package com.fq.inpaokeuse.activity;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    private void performAnimate(final View target, final int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        final IntEvaluator evaluator = new IntEvaluator();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (int) animation.getAnimatedValue();
                Log.d("animator", "current value=" + currentValue);
                float fraction = animation.getAnimatedFraction();
                target.getLayoutParams().width = evaluator.evaluate(fraction, start, end);
                target.requestLayout();
            }
        });
        valueAnimator.setDuration(5000).start();
    }


    /**
     * 使用一个类来包装原始对象，间接为其提供get和set方法，改变button的宽度
     *
     * @param mButton
     */
    private void performAnimate(Button mButton) {
        ViewWrapper wrapper = new ViewWrapper(mButton);
        ObjectAnimator.ofInt(wrapper, "width", 300).setDuration(500).start();
    }

    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }
}
