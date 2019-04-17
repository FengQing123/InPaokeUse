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


public class DispatchRelativeLayoutOne extends RelativeLayout {

    private static final String TAG = "RelativeLayoutOne";

    public static boolean mIntercept;

    public DispatchRelativeLayoutOne(Context context) {
        super(context);
    }

    public DispatchRelativeLayoutOne(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchRelativeLayoutOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "RelayoutOne:onTouchEvent: down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "RelayoutOne:onTouchEvent: move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "RelayoutOne:onTouchEvent: up");
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
                Log.e(TAG, "RelayoutOne:onInterceptTouchEvent: down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "RelayoutOne:onInterceptTouchEvent: move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "RelayoutOne:onInterceptTouchEvent: up");
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
                Log.e(TAG, "RelayoutOne dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "RelayoutOne dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "RelayoutOne dispatchTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean performClick() {
        Log.e(TAG, "RelayoutOne performClick");
        return super.performClick();
    }
}
