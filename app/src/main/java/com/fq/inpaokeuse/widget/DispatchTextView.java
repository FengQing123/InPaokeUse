package com.fq.inpaokeuse.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author fengqing
 * @date 2019/1/9
 */

public class DispatchTextView extends AppCompatTextView {

    private static final String TAG = "DispatchTextView";

    public DispatchTextView(Context context) {
        super(context);
    }

    public DispatchTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "TextView:onTouchEvent: down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "TextView:onTouchEvent: move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "TextView:onTouchEvent:  up");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "TextView: dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "TextView: dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "TextView: dispatchTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        Log.e(TAG, "TextView performClick");
        return super.performClick();
    }
}
