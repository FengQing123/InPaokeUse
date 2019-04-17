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


public class DispatchRelativeLayoutTwo extends RelativeLayout {

    private static final String TAG = "RelativeLayoutTwo";

    public static boolean mIntercept;

    public DispatchRelativeLayoutTwo(Context context) {
        super(context);
    }

    public DispatchRelativeLayoutTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchRelativeLayoutTwo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "RelayoutTwo:onTouchEvent: down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "RelayoutTwo:onTouchEvent: move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "RelayoutTwo:onTouchEvent:  up");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "RelayoutTwo:onInterceptTouchEvent: down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "RelayoutTwo:onInterceptTouchEvent: move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "RelayoutTwo:onInterceptTouchEvent:  up");
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "RelayoutTwo dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "RelayoutTwo dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "RelayoutTwo dispatchTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean performClick() {
        Log.e(TAG, "RelayoutTwo performClick");
        return super.performClick();
    }
}
