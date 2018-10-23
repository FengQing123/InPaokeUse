package com.fq.inpaokeuse.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * @author fengqing
 * @date 2018/9/5
 */


public class DefinedRelativeLayout extends RelativeLayout {

    private static final String TAG = "DefinedRelativeLayout";

    public static boolean mIntercept;

    public DefinedRelativeLayout(Context context) {
        super(context);
    }

    public DefinedRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefinedRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent: down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent: move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent:  up");
                DefinedRelativeLayout.mIntercept = false;
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "onInterceptTouchEvent: mIntercept=" + mIntercept);
        if (mIntercept) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
