package com.fq.inpaokeuse.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fq.inpaokeuse.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

/**
 * @author fengqing
 * @date 2018/4/20
 */

public class ChartViewActivity extends AppCompatActivity {

    private LineChart mChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view);

        mChart = (LineChart) findViewById(R.id.line_chart_weight);
        // 选择7个数据点（沿x坐标轴）
        makeLineData(27);
//        setChart(mChart);
//        setX(mChart);
//        setY(mChart);
    }

    private void setChart(LineChart mChart) {
        mChart.setNoDataText("数据加载中...");  // 没有数据时显示，类似于 ListView 的 EmptyView
        Description description = new Description();
        description.setText("");
        mChart.setDescription(description);    //设置图表描述信息
        mChart.setBackgroundColor(Color.WHITE); //设置图表背景颜色
        mChart.setDrawGridBackground(false);  //设置是否显示表格
        mChart.setDragEnabled(true);//设置可以拖曳
        mChart.setScaleEnabled(false); //设置图表是否可缩放
        mChart.setPinchZoom(false); // 设置能否在屏幕上做多指手势
        mChart.setTouchEnabled(true);// 设置能否触摸
        mChart.setDoubleTapToZoomEnabled(false);//设置双击不可缩放
        mChart.setHighlightPerTapEnabled(false);//XY标尺消失
//        mChart.setMaxVisibleValueCount(7);//设置最大可见(设置后就无法看到点的值)
        mChart.animateX(1000); // 沿 x 轴运动 2000毫秒,也就是绘制完所有的数据所需要的时间
    }

    private void setX(LineChart mChart) {
        XAxis xAxis = mChart.getXAxis();     //得到图表的X轴实例
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的显示位置
        xAxis.setAvoidFirstLastClipping(false); //设置x轴起点和终点label不超出屏幕
        xAxis.setDrawAxisLine(false);//设置是否显示x轴
        xAxis.setDrawLabels(true);//设置是否显示X轴值（默认显示）
        xAxis.setDrawGridLines(false);  //设置是否显示X轴表格

    }

    private void setY(LineChart mChart) {
        YAxis leftAxis = mChart.getAxisLeft();  //得到图表的左侧Y轴实例
//        leftAxis.setAxisMaxValue(80.0f); // 设置Y轴最大值
//        leftAxis.setAxisMinValue(40.0f);// 设置Y轴最小值。
        leftAxis.setLabelCount(3);//设置Y轴显示个数
//        leftAxis.setStartAtZero(true);   //设置图表起点从0开始
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawGridLines(false);//设置是否显示Y轴表格
        mChart.getAxisRight().setEnabled(false); //设置右侧Y轴不可用（这里可以向得到左侧Y轴那样，得到右侧Y轴实例去处理）
    }

    private LineDataSet setLineDataSet(LineDataSet mLineDataSet) {
        mLineDataSet.setLineWidth(1.0f);// 线宽
        mLineDataSet.setColor(getResources().getColor(R.color.weight_color)); // 折线的颜色
        mLineDataSet.setCircleSize(3.0f);// 显示的圆形的大小
        mLineDataSet.setDrawCircleHole(true);//是否是空心圆
        mLineDataSet.setCircleColor(getResources().getColor(R.color.line_color_1));// 圆球的颜色

//        mLineDataSet.setDrawCubic(true);// 改变折线样式，用曲线（默认是直线）
        mLineDataSet.setCubicIntensity(0.2f);// 曲线的平滑度，值越大越平滑。
        mLineDataSet.setDrawFilled(true); // 填充曲线下方的区域，红色，半透明。
        mLineDataSet.setFillAlpha(128);
        mLineDataSet.setFillColor(getResources().getColor(R.color.weight_color));

        mLineDataSet.setValueTextSize(10.0f);// 设置这项上显示的数据点的字体大小
        mLineDataSet.setValueTextColor(getResources().getColor(R.color.weight_color));
        // 设置折线上显示数据的格式。如果不设置，将默认显示float数据格式。
//        mLineDataSet.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                int num = (int) value;
//                return "y:" + num;
//            }
//        });
        return mLineDataSet;
    }


    /**
     * 数据点
     */
    private LineData makeLineData(int count) {
        ArrayList<Entry> yVals = new ArrayList<>();
        final ArrayList<String> xVals = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                xVals.add("0");
                yVals.add(new Entry(0, 0));
            } else {
                xVals.add("3/" + i);
            }
        }

        yVals.add(new Entry(1, 60));
        yVals.add(new Entry(2, 70));
        yVals.add(new Entry(3, 60));
        yVals.add(new Entry(4, 50));
        yVals.add(new Entry(5, 75));
        yVals.add(new Entry(6, 70));
        yVals.add(new Entry(7, 40));
        yVals.add(new Entry(8, 50));
        yVals.add(new Entry(9, 60));
        yVals.add(new Entry(10, 40));

        /**
         * 设置X轴样式
         */
        final XAxis xAxis = mChart.getXAxis();     //得到图表的X轴实例
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的显示位置
        xAxis.setAvoidFirstLastClipping(true); //设置x轴起点和终点label不超出屏幕
        xAxis.setDrawAxisLine(false);//设置是否显示x轴
        xAxis.setDrawLabels(true);//设置是否显示X轴值（默认显示）
        xAxis.setDrawGridLines(false);  //设置是否显示X轴表格
        xAxis.setValueFormatter(new MyXAxisValueFormatter(xVals));

        /**
         * 设置Y轴样式
         */
        YAxis leftAxis = mChart.getAxisLeft();  //得到图表的左侧Y轴实例
//        leftAxis.setAxisMaxValue(80.0f); // 设置Y轴最大值
//        leftAxis.setAxisMinValue(40.0f);// 设置Y轴最小值。
        leftAxis.setLabelCount(3);//设置Y轴显示个数
        leftAxis.setStartAtZero(true);   //设置图表起点从0开始
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawGridLines(false);//设置是否显示Y轴表格
        mChart.getAxisRight().setEnabled(false); //设置右侧Y轴不可用（这里可以向得到左侧Y轴那样，得到右侧Y轴实例去处理）

        /**
         * 设置图表样式
         */
        mChart.setNoDataText("数据加载中...");  // 没有数据时显示，类似于 ListView 的 EmptyView
        Description description = new Description();
        description.setText("");
        mChart.setDescription(description);    //设置图表描述信息
        mChart.setBackgroundColor(Color.WHITE); //设置图表背景颜色
        mChart.setDrawGridBackground(false);  //设置是否显示表格
        mChart.setDragEnabled(true);//设置可以拖曳
        mChart.setScaleEnabled(false); //设置图表是否可缩放
        mChart.setPinchZoom(false); // 设置能否在屏幕上做多指手势
        mChart.setTouchEnabled(true);// 设置能否触摸
        mChart.setDoubleTapToZoomEnabled(false);//设置双击不可缩放
        mChart.setHighlightPerTapEnabled(false);//XY标尺消失
        mChart.animateX(1000); // 沿 x 轴运动 2000毫秒,也就是绘制完所有的数据所需要的时间


        /**
         * 设置折线样式
         */
        LineDataSet mLineDataSet = new LineDataSet(yVals, "label");
        mLineDataSet.setLineWidth(1.0f);// 线宽
        mLineDataSet.setColor(getResources().getColor(R.color.weight_color)); // 折线的颜色
        mLineDataSet.setCircleRadius(3.0f);// 显示的圆形的大小
        mLineDataSet.setDrawCircleHole(true);//是否是空心圆
        mLineDataSet.setCircleColor(getResources().getColor(R.color.line_color_1));// 圆球的颜色

        mLineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);// 改变折线样式，用曲线（默认是直线）
        mLineDataSet.setCubicIntensity(0.2f);// 曲线的平滑度，值越大越平滑。
        mLineDataSet.setDrawFilled(true); // 填充曲线下方的区域，红色，半透明。
        mLineDataSet.setFillDrawable(getResources().getDrawable(R.drawable.shape_weight_gradient));//设置自定义渐变色
//        mLineDataSet.setFillColor(getResources().getColor(R.color.weight_color));//设置颜色

        mLineDataSet.setDrawValues(true);//在点上设置value(默认true)
        mLineDataSet.setValueTextSize(10.0f);// 设置这项上显示的数据点的字体大小
        mLineDataSet.setValueTextColor(getResources().getColor(R.color.weight_color));

        Legend legend = mChart.getLegend();
        legend.setEnabled(false);//设置label不可用

        LineData mLineData = new LineData(mLineDataSet);
        mChart.setData(mLineData);

        mChart.setVisibleXRangeMaximum(7); //设置X轴最大可以显示的条数，需要在设置数据源后生效
        mChart.moveViewToX(xVals.size());//图表移动到x轴指定位置，需要在设置数据源后生效
        mChart.setViewPortOffsets(50,50,50,50);//设置图表的margin
        mChart.invalidate();
        return mLineData;
    }

    public class MyXAxisValueFormatter implements IAxisValueFormatter {

        private ArrayList<String> mValues;

        public MyXAxisValueFormatter(ArrayList<String> values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            // "value" represents the position of the label on the axis (x or y)
            return mValues.get((int) value);
        }

    }
}
