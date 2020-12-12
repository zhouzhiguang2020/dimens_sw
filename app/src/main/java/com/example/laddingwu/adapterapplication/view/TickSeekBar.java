package com.example.laddingwu.adapterapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.example.laddingwu.adapterapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by .
 * User: ASUS
 * Date: 2020/5/19
 * Time: 17:03
 */
public class TickSeekBar extends AppCompatSeekBar {
    private int mPaddingLeft;
    private float mSeekBlockLength;//每个片段的宽度
    private float mTrackY;//时间点y坐标
    private int mMaxProgress;//进度条最大进度
    private float mThumbCenterX;//滑块位置
    private float mLeft, mRight;//seekbar的左右位置
    private int mMeasureHeight;

    //节点时间
    //节点大小--属性
    private int mTickWith;//单位：dp
    private int mTickHeight;
    //自动吸附--属性
    private boolean isAutoAdjustTick;
    //画笔
    private Paint mStockPaint;
    private List<TickData> mTickDataList;

    public TickSeekBar(Context context) {
        super(context);
    }

    public TickSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initStrokePaint();
    }

    public TickSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //初始化属性
    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TickSeekBar);
        mTickWith = (int) ta.getInt(R.styleable.TickSeekBar_tick_with, 2);
        mTickHeight = (int) ta.getInt(R.styleable.TickSeekBar_tick_height, 2);
        isAutoAdjustTick = ta.getBoolean(R.styleable.TickSeekBar_auto_adjust_tick, true);
        ta.recycle();
    }

    //初始化画笔
    private void initStrokePaint() {
        if (mStockPaint == null) {
            mStockPaint = new Paint();
        }
        mStockPaint.setAntiAlias(true);
    }

    private void initSeekBarInfo() {
        int mMeasuredWidth = getMeasuredWidth();
        mMeasureHeight = getMeasuredHeight();
        mPaddingLeft = getPaddingLeft();
        int mPaddingRight = getPaddingRight();
        int mPaddingTop = getPaddingTop();
        float mSeekLength = mMeasuredWidth - mPaddingLeft - mPaddingRight;
        mSeekBlockLength = mSeekLength / mMaxProgress;
        mTrackY = mPaddingTop;
        mMaxProgress = getMax();
        mLeft = getPaddingLeft();
        mRight = getMeasuredWidth() - getPaddingRight();
    }

    private void initTickLocation(List<TickData> tickDataList) {
        if (mTickDataList == null) {
            mTickDataList = new ArrayList<>();
        } else {
            mTickDataList.clear();
        }
        mTickDataList = tickDataList;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initSeekBarInfo();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTicks(canvas);
    }

    private void drawTicks(Canvas canvas) {
        if (mTickDataList == null || mTickDataList.size() == 0) {
            return;
        }

        for (int i = 0; i < mTickDataList.size(); i++) {
            float locationX = mSeekBlockLength * mTickDataList.get(i).getLocation();
//            if (getThumbPosOnTick() == i) {//Seekbar滑块和tick点重合则不绘制tick点
//                continue;
//            }

            int rectWidth = mTickWith;
            float top = mTrackY + mMeasureHeight / 2f - mTickWith / 2f;
            RectF roundRect = new RectF(locationX - rectWidth, top, locationX + rectWidth, top +
                    mTickHeight);
            mStockPaint.setColor(getResources().getColor(mTickDataList.get(i).getColor()));
            canvas.drawRoundRect(roundRect, 5, 5, mStockPaint);
        }
    }

    //设置时间点
    public void setTicks(List<TickData> tickDataList) {
        initStrokePaint();
        initTickLocation(tickDataList);
        //requestLayout();
        invalidate();
    }

    public static class TickData {
        private float location;
        private int color;

        public TickData(float location, int color) {
            this.location = location;
            this.color = color;
        }

        public float getLocation() {
            return location;
        }

        public void setLocation(float location) {
            this.location = location;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }
}
