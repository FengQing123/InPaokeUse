package com.fq.inpaokeuse.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author fengqing
 * @date 2018/9/5
 */

public class DispatchImageView extends AppCompatImageView {

    private static final String TAG = "DefinedImageView";

    public DispatchImageView(Context context) {
        this(context, null);
    }

    public DispatchImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DispatchImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "ImageView:onTouchEvent: down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "ImageView:onTouchEvent: move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "ImageView:onTouchEvent:  up");
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
                Log.e(TAG, "ImageView: dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "ImageView: dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "ImageView: dispatchTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        Log.e(TAG, "ImageView performClick");
        return super.performClick();
    }
}
