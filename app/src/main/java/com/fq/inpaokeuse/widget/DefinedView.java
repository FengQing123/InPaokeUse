package com.fq.inpaokeuse.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.fq.inpaokeuse.R;

/**
 * @author fengqing
 * @date 2018/8/20
 */

public class DefinedView extends View {

    Paint paint = new Paint();
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
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect rect = new Rect(0, 0, 200, 200);
        RectF rectF = new RectF(200, 200, 400, 400);

        Path dashPath = new Path();
        dashPath.lineTo(20, 20);
        dashPath.lineTo(40, 0);
        dashPath.close();

        path.moveTo(200, 200);
        path.lineTo(300, 300);
        path.lineTo(400, 200);
        path.lineTo(500, 300);
        path.lineTo(600, 200);

//        PathEffect effect = new CornerPathEffect(20);
//        DiscretePathEffect effect = new DiscretePathEffect(1, 5);
//        PathEffect effect = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
//        PathEffect effect = new PathDashPathEffect(dashPath, 40, 0, PathDashPathEffect.Style.TRANSLATE);

//        PathEffect dashEffect = new DashPathEffect(new float[]{20, 10}, 0);
//        PathEffect discreteEffect = new DiscretePathEffect(20, 5);
//        PathEffect effect = new ComposePathEffect(dashEffect,discreteEffect);
//        paint.setPathEffect(effect);
//        canvas.drawPath(path, paint);

//        paint.setShadowLayer(10, 0, 0, Color.BLUE);
//        paint.setTextSize(100);
//        canvas.drawText("Hello World", 300, 300, paint);

//        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));
//        canvas.drawBitmap(mPicBitmap, 100, 100, paint);
//
//        paint.setTextSize(50);
//        paint.setMaskFilter(null);
//        canvas.drawText("NORMAL", 200, 500, paint);
//
//        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID));
//        canvas.drawBitmap(mPicBitmap, 600, 100, paint);
//
//        paint.setTextSize(50);
//        paint.setMaskFilter(null);
//        canvas.drawText("SOLID", 700, 500, paint);
//
//        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER));
//        canvas.drawBitmap(mPicBitmap, 100, 600, paint);
//
//        paint.setTextSize(50);
//        paint.setMaskFilter(null);
//        canvas.drawText("INNER", 200, 1000, paint);
//
//
//        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.OUTER));
//        canvas.drawBitmap(mPicBitmap, 600, 600, paint);
//
//        paint.setTextSize(50);
//        paint.setMaskFilter(null);
//        canvas.drawText("OUTER", 700, 1000, paint);

        paint.setMaskFilter(new EmbossMaskFilter(new float[]{10, 10, 10}, 0, 50, 5));
        paint.setTextSize(300);
        paint.setColor(Color.GREEN);
        canvas.drawText("浮雕", 300, 300, paint);

    }
}
