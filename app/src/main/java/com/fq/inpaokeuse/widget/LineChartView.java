package com.fq.inpaokeuse.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.fq.inpaokeuse.util.PhoneUtil;

/**
 * @author fengqing
 * @date 2018/4/20
 */

public class LineChartView extends View {

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


    private Paint XYLinePaint;//轴线画笔

    public LineChartView(Context context) {
        this(context, null);
    }

    public LineChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initPaint();
    }

    protected void initPaint() {
        XYLinePaint = new Paint();
        XYLinePaint.setAntiAlias(true);
        XYLinePaint.setColor(Color.parseColor("#999999"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        if (wMode == MeasureSpec.EXACTLY) {
            mWidth = wSize;
        } else {
            mWidth = PhoneUtil.dip2px(mContext, 300);
        }
        if (hMode == MeasureSpec.EXACTLY) {
            mHeight = hSize;
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
        drawXLine(canvas);
        drawYLine(canvas);
    }

    protected void drawXLine(Canvas canvas) {
        canvas.drawLine(marginLR, (mHeight - marginBottom), (mWidth - marginLR), (mHeight - marginBottom), XYLinePaint);
    }

    protected void drawYLine(Canvas canvas) {
        canvas.drawLine(marginLR, marginTop, marginLR, mHeight - marginBottom, XYLinePaint);//Y轴左边
    }
}
