package com.fq.inpaokeuse.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.fq.inpaokeuse.R;

/**
 * @author fengqing
 * @date 2018/2/5
 */

public class VerticalRulerView extends View {

    private static final String TAG = "BaseRulerView";
    protected int defaultNum;//默认刻度值
    protected int mMax; // 最大刻度值
    protected int mMin; // 最小刻度值
    protected int mScaleMargin; // 刻度间距

    protected int mRectWidth; // 总宽度
    protected int mRectHeight; // 总高度
    protected int mScaleLength; // 小刻度线的长度
    protected int mScaleMaxLength; // 大刻度线的长度

    protected int mScaleScrollViewRange;//控件的高度
    protected int mTempScale; // 用于判断滑动方向
    protected int mMidCountScale; // 中间刻度

    protected int mCountScale; // 滑动的总刻度

    protected int mScrollLastX;

    protected Scroller mScroller;

    private boolean isFirst = true;


    public VerticalRulerView(Context context) {
        this(context, null);
    }

    public VerticalRulerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalRulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        // 获取自定义属性
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.BaseRuler);
        defaultNum = ta.getInteger(R.styleable.BaseRuler_scale_view_default, 100);
        mMin = ta.getInteger(R.styleable.BaseRuler_scale_view_min, 10);
        mMax = ta.getInteger(R.styleable.BaseRuler_scale_view_max, 1000);
        mScaleMargin = ta.getDimensionPixelOffset(R.styleable.BaseRuler_scale_view_margin, 15);
        mScaleLength = ta.getDimensionPixelOffset(R.styleable.BaseRuler_scale_view_height, 20);
        ta.recycle();
        mScroller = new Scroller(getContext());

        initVar();
    }

    public void initVar() {
        Log.e(TAG, "initVar: ");
        mRectHeight = (mMax - mMin) * mScaleMargin;
        mRectWidth = mScaleLength * 8;
        mScaleMaxLength = mScaleLength * 2;
        Log.e(TAG, "initVar: 总高度：mRectHeight" + mRectHeight + "，总宽度：mRectWidth" + mRectWidth);
        Log.e(TAG, "initVar: 小刻度线长度：mScaleLength" + mScaleLength + "，大刻度线长度：mScaleMaxLength" + mScaleMaxLength);

        // 设置layoutParams
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(mRectWidth, mRectHeight);
        this.setLayoutParams(lp);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG, "onMeasure: ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mScaleScrollViewRange = getMeasuredHeight();
        mTempScale = mScaleScrollViewRange / mScaleMargin / 2 + mMin;
        mMidCountScale = mScaleScrollViewRange / mScaleMargin / 2 + mMin;
        Log.e(TAG, "onMeasure: 控件高度：mScaleScrollViewRange=" + mScaleScrollViewRange + ",中间刻度mMidCountScale" + mMidCountScale);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw: ");
        // 画笔
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        // 抗锯齿
        paint.setAntiAlias(true);
        // 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        paint.setDither(true);
        // 空心
        paint.setStyle(Paint.Style.STROKE);
        // 文字居中
        paint.setTextAlign(Paint.Align.CENTER);

//        onDrawLine(canvas, paint);
        onDrawScale(canvas, paint); // 画刻度
        onDrawPointer(canvas, paint); // 画指针

        super.onDraw(canvas);
    }

    public void onDrawLine(Canvas canvas, Paint paint) {
        canvas.drawLine(0, 0, 0, mRectHeight, paint);
    }

    public void onDrawScale(Canvas canvas, Paint paint) {
        Log.e(TAG, "onDrawScale: ");
        paint.setTextSize(mRectWidth / 4);

//        for (int i = 0, k = mMax; i <= mMax - mMin; i++) {
//            if (i % 10 == 0) {
//                // 整值
//                canvas.drawLine(0, i * mScaleMargin, mScaleMaxLength, i * mScaleMargin, paint);
//                // 整值文字
//                canvas.drawText(String.valueOf(k), mScaleMaxLength + 40, i * mScaleMargin + paint.getTextSize() / 3, paint);
//                k -= 10;
//            } else {
//                canvas.drawLine(0, i * mScaleMargin, mScaleLength, i * mScaleMargin, paint);
//            }
//        }

        for (int i = 0, k = mMin; i <= mMax - mMin; i++) {
            if (i % 10 == 0) {
                // 整值
                canvas.drawLine(0, i * mScaleMargin, mScaleMaxLength, i * mScaleMargin, paint);
                // 整值文字
                canvas.drawText(String.valueOf(k), mScaleMaxLength + 40, i * mScaleMargin + paint.getTextSize() / 3, paint);
                k += 10;
            } else {
                canvas.drawLine(0, i * mScaleMargin, mScaleLength, i * mScaleMargin, paint);
            }
        }
    }

    public void onDrawPointer(Canvas canvas, Paint paint) {
        Log.e(TAG, "onDrawPointer: ");
        paint.setColor(Color.RED);
        // 每一屏幕刻度的个数/2----半个屏幕的刻度的个数
        int countScale = mScaleScrollViewRange / mScaleMargin / 2;
        // 根据滑动的距离，计算指针的位置【指针始终位于屏幕中间】
        int finalY = mScroller.getFinalY();
        // 滑动的刻度--滑动的刻度的个数
        int tmpCountScale = (int) Math.rint((double) finalY / (double) mScaleMargin); // 四舍五入取整
        // 总刻度
        mCountScale = tmpCountScale + countScale + mMin;
        Log.e(TAG, "onDrawPointer: mCountScale=" + mCountScale + ",finalY=" + finalY);
        canvas.drawLine(0, countScale * mScaleMargin + finalY, mScaleMaxLength
                + mScaleLength, countScale * mScaleMargin + finalY, paint);

        if (isFirst) {
            isFirst = false;
            mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), 0, (defaultNum - mCountScale) * mScaleMargin);
            invalidate();
        }
    }

    public void smoothScrollBy(int dx, int dy) {
        Log.e(TAG, "smoothScrollBy: finalX=" + mScroller.getFinalX() + ",finalY=" + mScroller.getFinalY());
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        // 判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            Log.e(TAG, "computeScroll: offset  CurrX=" + mScroller.getCurrX() + ",CurrY=" + mScroller.getCurrX());
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 通过重绘来不断调用computeScroll
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mScroller != null && !mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mScrollLastX = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                int dataY = mScrollLastX - y;
                if (mCountScale - mTempScale < 0) { // 向下边滑动
                    if (mCountScale <= mMin && dataY <= 0) // 禁止继续向下滑动
                        return super.onTouchEvent(event);
                } else if (mCountScale - mTempScale > 0) { // 向上边滑动
                    if (mCountScale >= mMax && dataY >= 0) // 禁止继续向上滑动
                        return super.onTouchEvent(event);
                }
                smoothScrollBy(0, dataY);
                mScrollLastX = y;
                postInvalidate();
                mTempScale = mCountScale;
                return true;
            case MotionEvent.ACTION_UP:
                if (mCountScale < mMin)
                    mCountScale = mMin;
                if (mCountScale > mMax)
                    mCountScale = mMax;
                int finalY = (mCountScale - mMidCountScale) * mScaleMargin;
                Log.e(TAG, "onTouchEvent: mCountScale=" + mCountScale + ",mMidCountScale=" + mMidCountScale + ",finalY=" + finalY);
                mScroller.setFinalY(finalY); // 纠正指针位置
                postInvalidate();
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}
