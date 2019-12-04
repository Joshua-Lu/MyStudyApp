package com.joshua.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by Joshua on 2019/12/4.
 */
public class PlayIconView extends View {
    private RectF mRectF;
    private Paint mPaint;
    private int mMeasuredWidth;
    private int mMeasuredHeight;
    private final int RECT_COUNT = 10;
    private float rectWith;
    private float offset;

    public PlayIconView(Context context) {
        this(context, null);
    }

    public PlayIconView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayIconView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRectF = new RectF();
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasuredWidth = getMeasuredWidth();
        mMeasuredHeight = getMeasuredHeight();
        rectWith = mMeasuredWidth * 0.9f / RECT_COUNT;
        offset = mMeasuredWidth * 0.1f / RECT_COUNT;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < RECT_COUNT; i++) {
            mRectF.left = (rectWith + offset) * i;
            mRectF.right = mRectF.left + rectWith;
            mRectF.top = getRectTop(i);
            mRectF.bottom = mMeasuredHeight;
            canvas.drawRect(mRectF, mPaint);
        }
        postInvalidateDelayed(300);
    }

    /**
     * 获取矩形条的top值
     * 要根据具体需求来实现
     *
     * @param i 矩形条位置
     * @return 矩形条的top值
     */
    private float getRectTop(int i) {
        return (float) (Math.random() * mMeasuredHeight);
    }
}
