package com.fq.inpaokeuse.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fq.inpaokeuse.R;

/**
 * @author fengqing
 * @date 2018/7/19
 */

public class MyViewPagerIndicator extends LinearLayout {

    private static final String TAG = "MyViewPagerIndicator";
    private Context mContext;

    /**
     * 初始化画笔
     */
    private Paint mPaint;

    private int mViewHeight, mViewWidth, mLineHieght = 3, mWidth, mLeft;

    private int mTabVisibleCount = 0;
    private int mColorNormal;
    private int mColorSelect;
    private float mTitleSize = 14;

    private ViewPager mViewPager;
    private int mPos;

    private String[] mTitles;

    private boolean isAddTitle;

    public MyViewPagerIndicator(Context context) {
        this(context, null);
    }

    public MyViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        /**
         * 存放自定义视图属性的数组容器,需要在attrs文件夹中定义属性
         * 属性支持：
         * reference string color dimension boolean integer float fraction enum flag
         * */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyViewPagerIndicator);
        /**
         * 获取数组容器中属性数量
         * */
        mTabVisibleCount = typedArray.getInt(R.styleable.MyViewPagerIndicator_item_count, 2);
        mColorNormal = typedArray.getColor(R.styleable.MyViewPagerIndicator_item_color_normal, Color.BLACK);
        mColorSelect = typedArray.getColor(R.styleable.MyViewPagerIndicator_item_color_select, Color.BLUE);
        mTitleSize = typedArray.getDimension(R.styleable.MyViewPagerIndicator_item_text_size, 14);
        /**
         * 调用recycle()函数
         * */
        typedArray.recycle();


        /**
         * 初始化画笔
         * */
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColorSelect);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewHeight = getMeasuredHeight();
        mViewWidth = getMeasuredWidth();
        int height = mViewHeight + dip2px(mContext, mLineHieght);
        mWidth = mViewWidth / mTabVisibleCount;
        setMeasuredDimension(mViewWidth, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        /**
         * 绘制线条,可以绘制多样性的,也可以绘制三角形等等
         * */
        Rect rect = new Rect(mLeft, mViewHeight, mLeft + mWidth, mViewHeight + dip2px(mContext, mLineHieght));
        canvas.drawRect(rect, mPaint);

        if (!isAddTitle) {
            isAddTitle = true;
            addViews(mTitles);
            mViewPager.setCurrentItem(mPos);
            highLightTextView(mPos);
        }
        super.dispatchDraw(canvas);
    }

    /**
     * 指示符滚动
     */
    public void scroll(int position, float offset) {
        mLeft = (int) ((position + offset) * mWidth);
        Log.e(TAG, "scroll: mLeft=" + mLeft + ",position=" + position + ",offset=" + offset + ",mWidth=" + mWidth);
        /**
         * 重新绘制视图
         * */
        invalidate();
    }

    /**
     * 设置关联的ViewPager
     */
    public void setViewPager(ViewPager mViewPager, int pos) {
        this.mViewPager = mViewPager;
        this.mPos = pos;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                scroll(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextViewColor();
                highLightTextView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 高亮文本
     */
    protected void highLightTextView(int position) {
        View view = getChildAt(position);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(mColorSelect);
        }
    }

    /**
     * 重置文本颜色
     */
    private void resetTextViewColor() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(mColorNormal);
            }
        }
    }

    /**
     * 设置点击事件
     */
    public void setItemClickEvent() {
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            final int j = i;
            View view = getChildAt(i);
            Log.e(TAG, "setItemClickEvent: view widht=" + view.getWidth());
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(j);
                }
            });
        }
    }

    public void setTabItemTitles(String[] titles) {
        if (titles != null && titles.length > 0) {
            this.mTitles = titles;
        }
    }

    protected void addViews(String[] titles) {
        if (titles != null && titles.length > 0) {
            this.removeAllViews();
            for (String title : titles) {
                addView(generateTextView(title));
            }
            setItemClickEvent();
        }
    }

    /**
     * 根据标题生成我们的TextView
     */
    private TextView generateTextView(String text) {
        TextView tv = new TextView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.width = getMeasuredWidth() / mTabVisibleCount;
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(mColorNormal);
        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleSize);
        tv.setLayoutParams(lp);
        Log.e(TAG, "generateTextView: textViewWidth=" + tv.getWidth());
        return tv;
    }

    private static int dip2px(Context context, float dpValue) {
        float scale = 2.0f;
        if (context != null) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获得屏幕的宽度
     *
     * @return
     */
    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
