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

public class DefinedImageView extends AppCompatImageView {

    private static final String TAG = "DefinedImageView";

    public DefinedImageView(Context context) {
        this(context, null);
    }

    public DefinedImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefinedImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
                break;
            default:
                break;
        }
        return true;
    }
}
