package com.fq.inpaokeuse.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import com.fq.inpaokeuse.util.Arith;
import com.fq.inpaokeuse.util.PhoneUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fengq on 2017/7/27.
 */

public class HeartRateDefineChart extends View {

    private static final String TAG = "HeartRateDefineChart";

    private final static String X_KEY = "Xpos";
    private final static String Y_KEY = "Ypos";

    private Context context;
    /**
     * View 宽高
     */
    private int mWidth;
    private int mHeight;

    private int trueHeight;//图表实际高度
    private int trueWidth;//图表实际宽度

    private int marginLeft = 0;

    private int marginTop = 30;
    private int marginBottom = 80;

    private int minNum;//最大心率值*0.4
    private int maxNum;//最大心率值*1.0

    private int xPoint = 120;//x轴区分点

    private Paint XYLinePaint;//轴线画笔
    private Paint HeartNumTextPaint;//左边心率值画笔
    private Paint HeartInfoTextPaint;//右边心率描述画笔
    private Paint LineChartPaint;//折线画笔
    private Paint XTimePain;//时间画笔

//    private String[] yLeftNum = new String[]{"93", "111", "130", "148", "167", "185"};
//    private String[] yRightText = new String[]{"热身", "燃脂", "心肺", "耐力", "极限", ""};

    private List<String> yLeftNumList = new ArrayList<>();
    private List<String> yRightTextList = new ArrayList<>();
    private List<Integer> pointList = new ArrayList<>();
    private List<Map<String, Integer>> mListPoint = new ArrayList<Map<String, Integer>>();


    public HeartRateDefineChart(Context context) {
        this(context, null);
    }

    public HeartRateDefineChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeartRateDefineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initPaint();
    }

    private void initPaint() {
        HeartNumTextPaint = new Paint();
        HeartNumTextPaint.setTextSize(25);
        HeartNumTextPaint.setStrokeWidth(3);
        HeartNumTextPaint.setAntiAlias(true);
        HeartNumTextPaint.setColor(Color.parseColor("#5DAFFC"));

        HeartInfoTextPaint = new Paint();
        HeartInfoTextPaint.setColor(Color.parseColor("#01C296"));
        HeartInfoTextPaint.setTextSize(25);
        HeartInfoTextPaint.setAntiAlias(true);
        HeartInfoTextPaint.setStrokeWidth(3);

        LineChartPaint = new Paint();
        LineChartPaint.setAntiAlias(true);
        LineChartPaint.setColor(Color.RED);
        LineChartPaint.setStrokeWidth(6);

        XYLinePaint = new Paint();
        XYLinePaint.setAntiAlias(true);
        XYLinePaint.setColor(Color.parseColor("#999999"));

        XTimePain = new Paint();
        XTimePain.setAntiAlias(true);
        XTimePain.setColor(Color.parseColor("#999999"));
        XTimePain.setStrokeWidth(3);
        XTimePain.setTextSize(30);
        XTimePain.setTextAlign(Paint.Align.CENTER);

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
            mWidth = PhoneUtil.dip2px(context, 300);
        }
        if (hMode == MeasureSpec.EXACTLY) {
            mHeight = hSize;
        } else {
            mHeight = PhoneUtil.dip2px(context, 500);
        }
        setMeasuredDimension(mWidth, mHeight);

        marginLeft = PhoneUtil.dip2px(context, 25);
        trueWidth = mWidth - 2 * marginLeft;
        trueHeight = mHeight - marginTop - marginBottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawXLine(canvas);
        drawYLine(canvas);
        drawXDashLineAndHeart(canvas);
        drawLineChart(canvas);
        drawXTime(canvas);
    }

    protected void drawXLine(Canvas canvas) {
        //单纯画X轴
        canvas.drawLine(marginLeft, mHeight - marginBottom, (mWidth - marginLeft), mHeight - marginBottom, XYLinePaint);
        //        Log.e(TAG, "drawXLine: marginleft=" + marginLeft + ",marginTop=" + marginTop + ",marginBottom=" + marginBottom + ",mWidth=" + mWidth + ",mHeight=" + mHeight);

    }

    protected void drawYLine(Canvas canvas) {
        canvas.drawLine(marginLeft, marginTop, marginLeft, mHeight - marginBottom, XYLinePaint);//Y轴左边
        canvas.drawLine((mWidth - marginLeft), marginTop, (mWidth - marginLeft), mHeight - marginBottom, XYLinePaint);//Y轴右边
    }

    protected void drawXDashLineAndHeart(Canvas canvas) {
        Path path = new Path();
        XYLinePaint.setStyle(Paint.Style.STROKE);//必须设置这个才可以画虚线
        HeartNumTextPaint.setTextAlign(Paint.Align.RIGHT);//设置X坐标在右边
        if (yLeftNumList.size() <= 0) {
            return;
        }
        float interval = (float) Arith.div((mHeight - marginTop - marginBottom), yLeftNumList.size());//Y轴等分区间大小
        for (int i = 1; i <= yLeftNumList.size(); i++) {
            path.moveTo(marginLeft, (float) Arith.sub((mHeight - marginBottom), interval * i));
            path.lineTo((mWidth - marginLeft), (float) Arith.sub((mHeight - marginBottom), interval * i));
            PathEffect effects = new DashPathEffect(new float[]{12, 10}, 0);
            XYLinePaint.setPathEffect(effects);
            canvas.drawPath(path, XYLinePaint);

            canvas.drawText(yLeftNumList.get(i - 1), marginLeft - 3, (mHeight - marginBottom - interval * (i - 1)), HeartNumTextPaint);//画左边心率值
            canvas.drawText(yRightTextList.get(i - 1), (mWidth - marginLeft) + 3, (mHeight - marginBottom - interval * i) - (interval / 3), HeartInfoTextPaint);//画右边心率描述
        }
    }

    protected synchronized void drawLineChart(Canvas canvas) {
        try {
            List<Map<String, Integer>> list = mListPoint;
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    float x1 = (float) (marginLeft);
                    float y1 = (float) Arith.sub((mHeight - marginBottom), Arith.mul(Arith.div((minNum - minNum), (maxNum - minNum)), Arith.div(trueHeight, yLeftNumList.size()) * 6));
                    float x2 = (float) (Arith.mul(Arith.div(list.get(i).get(X_KEY), xPoint), trueWidth) + marginLeft);
                    float y2 = (float) Arith.sub((mHeight - marginBottom), Arith.mul(Arith.div((list.get(i).get(Y_KEY) - minNum), (maxNum - minNum)), Arith.div(trueHeight, yLeftNumList.size()) * 6));
                    canvas.drawLine(x1, y1, x2, y2, LineChartPaint);
                    canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
                } else if (i > 0) {
                    float x1 = (float) (Arith.mul(Arith.div(list.get(i - 1).get(X_KEY), xPoint), trueWidth) + marginLeft);
                    float y1 = (float) Arith.sub((mHeight - marginBottom), Arith.mul(Arith.div((list.get(i - 1).get(Y_KEY) - minNum), (maxNum - minNum)), Arith.div(trueHeight, yLeftNumList.size()) * 6));
                    float x2 = (float) (Arith.mul(Arith.div(list.get(i).get(X_KEY), xPoint), trueWidth) + marginLeft);
                    float y2 = (float) Arith.sub((mHeight - marginBottom), Arith.mul(Arith.div((list.get(i).get(Y_KEY) - minNum), (maxNum - minNum)), Arith.div(trueHeight, yLeftNumList.size()) * 6));
                    canvas.drawLine(x1, y1, x2, y2, LineChartPaint);
                    canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
                }
                if (list.get(i).get(X_KEY) >= xPoint) {
                    xPoint = 2 * xPoint;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void drawXTime(Canvas canvas) {
        switch (xPoint / 120) {
            case 1:
                canvas.drawText("0:00:00", marginLeft, mHeight - marginBottom / 2, XTimePain);
                canvas.drawText("0:05:00", trueWidth / 2 + marginLeft, mHeight - marginBottom / 2, XTimePain);
                canvas.drawText("0:10:00", mWidth - marginLeft, mHeight - marginBottom / 2, XTimePain);
                break;
            case 2:
                canvas.drawText("0:00:00", marginLeft, mHeight - marginBottom / 2, XTimePain);
                canvas.drawText("0:10:00", trueWidth / 2 + marginLeft, mHeight - marginBottom / 2, XTimePain);
                canvas.drawText("0:20:00", mWidth - marginLeft, mHeight - marginBottom / 2, XTimePain);
                break;
            case 4:
                canvas.drawText("0:00:00", marginLeft, mHeight - marginBottom / 2, XTimePain);
                canvas.drawText("0:20:00", trueWidth / 2 + marginLeft, mHeight - marginBottom / 2, XTimePain);
                canvas.drawText("0:40:00", mWidth - marginLeft, mHeight - marginBottom / 2, XTimePain);
                break;
            case 8:
                canvas.drawText("0:00:00", marginLeft, mHeight - marginBottom / 2, XTimePain);
                canvas.drawText("0:40:00", trueWidth / 2 + marginLeft, mHeight - marginBottom / 2, XTimePain);
                canvas.drawText("1:20:00", mWidth - marginLeft, mHeight - marginBottom / 2, XTimePain);
                break;
            case 16:
                canvas.drawText("0:00:00", marginLeft, mHeight - marginBottom / 2, XTimePain);
                canvas.drawText("1:20:00", trueWidth / 2 + marginLeft, mHeight - marginBottom / 2, XTimePain);
                canvas.drawText("2:40:00", mWidth - marginLeft, mHeight - marginBottom / 2, XTimePain);
                break;
            case 32:
                canvas.drawText("0:00:00", marginLeft, mHeight - marginBottom / 2, XTimePain);
                canvas.drawText("2:40:00", trueWidth / 2 + marginLeft, mHeight - marginBottom / 2, XTimePain);
                canvas.drawText("5:20:00", mWidth - marginLeft, mHeight - marginBottom / 2, XTimePain);
                break;
        }
    }

    public void setMaxHeartInterval(int maxHeartRate) {
        if (maxHeartRate <= 0) {
            return;
        }
        minNum = Arith.doubleToInt(maxHeartRate * 0.4);
        maxNum = maxHeartRate;
        yLeftNumList.add(Arith.saveZeroPoint(maxHeartRate * 0.4));
        yLeftNumList.add(Arith.saveZeroPoint(maxHeartRate * 0.5));
        yLeftNumList.add(Arith.saveZeroPoint(maxHeartRate * 0.6));
        yLeftNumList.add(Arith.saveZeroPoint(maxHeartRate * 0.7));
        yLeftNumList.add(Arith.saveZeroPoint(maxHeartRate * 0.8));
        yLeftNumList.add(Arith.saveZeroPoint(maxHeartRate * 0.9));
        yLeftNumList.add(Arith.saveZeroPoint(maxHeartRate * 1.0));

        yRightTextList.add("热身");
        yRightTextList.add("燃脂");
        yRightTextList.add("心肺");
        yRightTextList.add("耐力");
        yRightTextList.add("极限");
        yRightTextList.add("");
        yRightTextList.add("");
    }

    public void setLinePoint(int nowHeartRate) {
        if (nowHeartRate <= minNum) {
            nowHeartRate = minNum;
        }
        Map<String, Integer> temp = new HashMap<>();
        pointList.add(nowHeartRate);
        temp.put(X_KEY, pointList.size());
        temp.put(Y_KEY, nowHeartRate);
        mListPoint.add(temp);
        invalidate();
    }
}
