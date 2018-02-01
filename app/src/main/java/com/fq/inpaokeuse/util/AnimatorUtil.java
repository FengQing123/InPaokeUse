package com.fq.inpaokeuse.util;

import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.View;
import android.view.Window;

/**
 * @author fengqing
 * @date 2018/1/23
 */

public class AnimatorUtil {

    /**
     * X轴旋转
     *
     * @param view
     */
    public static void ObjectAnimatorRotationX(View view) {
        ObjectAnimator.ofFloat(view, "rotationX", 0.0F, 360.0F).setDuration(500).start();
    }

    /**
     * XY轴旋转
     *
     * @param view
     */
    public static void ObjectAnimatorRotation(View view) {
        ObjectAnimator.ofFloat(view, "rotation", 0.0f, 360f).setDuration(3000).start();
    }

    /**
     * 水平X轴移动
     *
     * @param activity
     * @param view
     */
    public static void ObjectAnimatorTranslateX(Activity activity, View view) {
        View mDecorViewContent = activity.getWindow().getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
        int width = mDecorViewContent.getWidth();
        float end = width - view.getWidth();
        ObjectAnimator.ofFloat(view, "x", 0.0F, end).setDuration(500).start();
    }

    /**
     * 垂直Y轴移动
     *
     * @param activity
     * @param view
     */
    public static void ObjectAnimatorTranslateY(Activity activity, View view) {
        View mDecorViewContent = activity.getWindow().getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
        int height = mDecorViewContent.getHeight();
        float end = height - view.getHeight();
        ObjectAnimator.ofFloat(view, "y", 0.0F, end).setDuration(500).start();
    }


    /**
     * ValueAnimator的使用
     *
     * @param view
     */
    public static void ValueAnimatorOne(final View view) {
        ValueAnimator animation = ValueAnimator.ofFloat(0f, 100f);
        animation.setDuration(1000);
        animation.start();
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                view.setTranslationX(animatedValue);//X轴移动
                view.setTranslationY(animatedValue);//Y轴移动
                view.setAlpha(animatedValue);//透明度变化
                view.setRotationX(animatedValue);//X轴旋转
                view.setRotationY(animatedValue);//Y轴旋转
                view.setScaleX(animatedValue);//X轴缩放
                view.setScaleY(animatedValue);//Y轴缩放
            }
        });
    }

    /**
     * 使用AnimatorListenerAdapter可以不重写方法
     *
     * @param view
     */
    public static void ObjectAnimatorAlpha(final View view) {
        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        fadeAnim.setDuration(250);
        fadeAnim.addListener(new AnimatorListenerAdapter() {
        });
    }

    /**
     * 使用keyframe
     *
     * @param target
     */
    public static void ObjectAnimatorUseKeyFrame(View target) {
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.5f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
        ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(target, pvhRotation);
        rotationAnim.setDuration(5000).start();
    }

    /**
     * AnimatorSet的使用
     *
     * @param view
     */
    public static void AnimationUseAnimatorSet(View view) {
        ObjectAnimator objectAnimatorOne = ObjectAnimator.ofFloat(view, "rotation", 0.0f, 360f).setDuration(3000);
        ObjectAnimator objectAnimatorTwo = ObjectAnimator.ofFloat(view, "rotation", 360f, 0.0f).setDuration(3000);
        ObjectAnimator objectAnimatorThree = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.0f).setDuration(3000);
        ObjectAnimator objectAnimatorFour = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f).setDuration(3000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimatorOne).before(objectAnimatorTwo);
        animatorSet.play(objectAnimatorOne).with(objectAnimatorThree);
        animatorSet.play(objectAnimatorTwo).with(objectAnimatorFour);
        animatorSet.start();
    }


    /**
     * 使用PropertyValuesHolder
     *
     * @param view
     */
    public static void AnimatinoUsePropertyValuesHolder(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 50f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 100f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY).start();
    }

    /**
     * 使用ViewPropertyAnimator
     *
     * @param view
     */
    public static void AnimationUseViewPropertyAnimator(View view) {
        view.animate().x(50f).y(100f);
    }
}
