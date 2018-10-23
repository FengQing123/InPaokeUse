package com.fq.inpaokeuse.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.fq.inpaokeuse.R;

/**
 * @author fengqing
 * @date 2018/8/20
 */

public class DefinedView extends View {

    private static final String TAG = "DefinedView";

    TextPaint mTextPaint = new TextPaint();
    Paint mPaint = new Paint();
    Path path = new Path();

    Bitmap mSrcBitmap, mDstBitmap, mPicBitmap;

    public DefinedView(Context context) {
        this(context, null);
    }

    public DefinedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefinedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.src_blue);
        mDstBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.dst_red);
        mPicBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.pic);

        mTextPaint.setTextSize(50);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.save();
//        canvas.clipRect(100, 100, 500, 500);
//        canvas.drawColor(Color.RED);
//        canvas.drawBitmap(mPicBitmap, 100, 100, mPaint);
//        canvas.restore();


        int width = mPicBitmap.getWidth();//337
        int height = mPicBitmap.getHeight();//300


//        canvas.drawBitmap(mPicBitmap, 100, 100, mPaint);

//        Matrix matrix = new Matrix();
//
//        float[] pointSrc = {0, 0,100,100};
//        float[] pointDst = {10, 0,120,130};
//
//        matrix.reset();
//        matrix.setPolyToPoly(pointSrc, 0, pointDst, 0, 2);
//
//        canvas.save();
//        canvas.concat(matrix);
//        canvas.drawBitmap(mPicBitmap, 0, 0, mPaint);
//        canvas.restore();


//        Matrix matrix = new Matrix();
//        matrix.reset();
//
//
//        matrix.preTranslate(500, 0);
//        matrix.postRotate(30);
//        canvas.save();
//        canvas.concat(matrix);
//        canvas.drawBitmap(mPicBitmap, 100, 100, mPaint);
//        canvas.restore();


//        RectF rectF = new RectF(200, 200, 800, 800);
//
//        mPaint.setColor(Color.BLACK);
//        canvas.drawRect(rectF, mPaint);
//
//        canvas.save();
//        mPaint.setColor(Color.YELLOW);
//        canvas.scale(0.5f, 0.5f, 500, 500);
//        canvas.drawRect(rectF, mPaint);
//        canvas.restore();


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
