package com.fq.inpaokeuse.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.fq.inpaokeuse.R;
import com.fq.inpaokeuse.util.PhoneUtil;

/**
 * @author fengqing
 * @date 2018/4/24
 */

public class MeasureLevelProgressBar extends View {

    private Context mContext;
    //View 宽高
    private int mWidth;
    private int mHeight;
    //图表实际宽高
    private int trueWidth;
    private int trueHeight;

    //左右间距
    private int marginLR = 0;
    //上下间距
    private int marginTop = 30;
    private int marginBottom = 80;
    //笔的粗细
    private int mPaintStrokeWidth = 15;

    //段
    private int section = 4;

    Paint mLowerPaint;
    Paint mStandardPaint;
    Paint mOverHigherPaint;
    Paint mTooHigherPaint;
    Paint mTopDescPaint;
    Paint mBottomDescPaint;
    Paint mTrianglePaint;


    public MeasureLevelProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public MeasureLevelProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MeasureLevelProgressBar(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;

        mLowerPaint = new Paint();
        mLowerPaint.setAntiAlias(true);
        mLowerPaint.setStrokeWidth(mPaintStrokeWidth);
        mLowerPaint.setColor(ContextCompat.getColor(mContext, R.color.measure_lean_lower));

        mStandardPaint = new Paint();
        mStandardPaint.setAntiAlias(true);
        mStandardPaint.setStrokeWidth(mPaintStrokeWidth);
        mStandardPaint.setColor(ContextCompat.getColor(mContext, R.color.measure_standard_normal));

        mOverHigherPaint = new Paint();
        mOverHigherPaint.setAntiAlias(true);
        mOverHigherPaint.setStrokeWidth(mPaintStrokeWidth);
        mOverHigherPaint.setColor(ContextCompat.getColor(mContext, R.color.measure_over_higher));

        mTooHigherPaint = new Paint();
        mTopDescPaint.setAntiAlias(true);
        mTooHigherPaint.setStrokeWidth(mPaintStrokeWidth);
        mTooHigherPaint.setColor(ContextCompat.getColor(mContext, R.color.measure_too_higher));

        mTopDescPaint = new Paint();
        mTopDescPaint.setAntiAlias(true);
        mTopDescPaint.setTextSize(PhoneUtil.dip2px(mContext, 14));
        mTopDescPaint.setTextAlign(Paint.Align.CENTER);
        mTopDescPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTopDescPaint.setColor(ContextCompat.getColor(mContext, R.color.black_333333));

        mBottomDescPaint = new Paint();
        mBottomDescPaint.setAntiAlias(true);
        mBottomDescPaint.setTextSize(PhoneUtil.dip2px(mContext, 12));
        mBottomDescPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mBottomDescPaint.setTextAlign(Paint.Align.CENTER);
        mBottomDescPaint.setColor(ContextCompat.getColor(mContext, R.color.black_999999));

        mTrianglePaint = new Paint();
        mTrianglePaint.setAntiAlias(true);
        mTrianglePaint.setStyle(Paint.Style.FILL);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.EXACTLY) {
            mWidth = widthSpecSize;
        } else {
            mWidth = PhoneUtil.dip2px(mContext, 300);
        }
        if (heightSpecMode == MeasureSpec.EXACTLY) {
            mHeight = heightSpecSize;
        } else {
            mHeight = PhoneUtil.dip2px(mContext, 500);
        }
        setMeasuredDimension(mWidth, mHeight);

        marginLR = PhoneUtil.dip2px(mContext, 25);
        trueWidth = mWidth - 2 * marginLR;
        trueHeight = mHeight - marginTop - marginBottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        switch (section) {
            case 3:

                break;
            case 4:
                float sectionWidth = trueWidth / 4;
                float y = mHeight - marginBottom;
                //画线条
                canvas.drawLine(marginLR, y, marginLR + sectionWidth, y, mLowerPaint);
                canvas.drawLine(marginLR + sectionWidth, y, marginLR + sectionWidth * 2, y, mStandardPaint);
                canvas.drawLine(marginLR + sectionWidth * 2, y, marginLR + sectionWidth * 3, y, mOverHigherPaint);
                canvas.drawLine(marginLR + sectionWidth * 3, y, marginLR + sectionWidth * 4, y, mTooHigherPaint);

                //标底部文字
                String[] level = getResources().getStringArray(R.array.BmiLevel);
                int margin = PhoneUtil.dip2px(mContext, 15);
                canvas.drawText(level[0], marginLR + sectionWidth / 2, y + mPaintStrokeWidth + margin, mBottomDescPaint);
                canvas.drawText(level[1], marginLR + sectionWidth + sectionWidth / 2, y + mPaintStrokeWidth + margin, mBottomDescPaint);
                canvas.drawText(level[2], marginLR + 2 * sectionWidth + sectionWidth / 2, y + mPaintStrokeWidth + margin, mBottomDescPaint);
                canvas.drawText(level[3], marginLR + 3 * sectionWidth + sectionWidth / 2, y + mPaintStrokeWidth + margin, mBottomDescPaint);

                break;
            default:
                break;

        }
    }
}
