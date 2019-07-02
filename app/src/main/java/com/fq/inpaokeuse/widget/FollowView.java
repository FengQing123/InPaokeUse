package com.fq.inpaokeuse.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.fq.inpaokeuse.util.Logger;

/**
 * @author fengqing
 * @date 2019/4/17
 */

public class FollowView extends View {


    float mLastX, mLastY;
    float mWidth, mHeight;

    private Scroller mScroller;

    public FollowView(Context context) {
        this(context, null);
    }

    public FollowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FollowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = this.getMeasuredWidth();
        mHeight = this.getMeasuredHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getRawX();
        float y = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = x - mLastX;
                float deltaY = y - mLastY;

                float translationX = getTranslationX() + deltaX;
                float translationY = getTranslationY() + deltaY;

                setTranslationX(translationX);
                setTranslationY(translationY);

                break;
            case MotionEvent.ACTION_UP:
//                smoothScrollTo(-500, -500);
//                smoothScrollUseAnimation(0, -600);
                break;
            default:
                break;
        }

        mLastX = x;
        mLastY = y;

        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            int mCurrX = mScroller.getCurrX();
            int mCurrY = mScroller.getCurrY();
            Logger.error("LLLLLLL", "mCurrX=" + mCurrX + ",mCurrY=" + mCurrY);
            scrollTo(mCurrX, mCurrY);
            postInvalidate();
        }
    }

    private void smoothScrollTo(float destX, float destY) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int deltaX = (int) (destX - scrollX);
        int deltaY = (int) (destY - scrollY);
        Logger.error("hhhhhh", "deltaX=" + deltaX + ",deltaY=" + deltaY);
        mScroller.startScroll(scrollX, scrollY, deltaX, deltaY, 1000);
        invalidate();
    }

    private void smoothScrollUseAnimation(final int startX, final int deltaX) {
        final ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animator.getAnimatedFraction();
                Logger.error("QQQQ", "fraction=" + fraction);
                scrollTo((int) (startX + deltaX * fraction), 0);
            }
        });
        animator.start();
    }

}
