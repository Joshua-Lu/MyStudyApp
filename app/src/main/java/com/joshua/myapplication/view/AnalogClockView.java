package com.joshua.myapplication.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.TimeZone;

import androidx.annotation.Nullable;

/**
 * 模拟时钟
 * <p>
 * Created by Joshua on 2019/12/24.
 */
public class AnalogClockView extends View {

    private static final String TAG = "AnalogClockView";

    private Paint mCirclePaint;
    private Paint mLongDegreePaint;
    private Paint mShortDegreePaint;
    private Paint mHourPaint;
    private Paint mMinutePaint;

    private Time mCalendar;
    private float mHour;
    private float mMinutes;

    private int mDialWidth;
    private int mDialHeight;
    private int r;
    private final int longDegreeLength = 60;// 3,6,9,12点的刻度长度
    private final int shortDegreeLength = 30;// 其他时间点的刻度长度
    private final int hourLength = 200;// 时针长度
    private final int minuteLength = 300;// 分针长度
    private boolean mAttached;

    public AnalogClockView(Context context) {
        super(context);
        init();
    }

    public AnalogClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnalogClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.BLACK);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(3);
        mLongDegreePaint = new Paint();
        mLongDegreePaint.setColor(Color.BLACK);
        mLongDegreePaint.setStrokeWidth(5);
        mLongDegreePaint.setTextSize(60);
        mShortDegreePaint = new Paint();
        mShortDegreePaint.setColor(Color.BLACK);
        mShortDegreePaint.setStrokeWidth(3);
        mShortDegreePaint.setTextSize(30);
        mHourPaint = new Paint();
        mHourPaint.setColor(Color.RED);
        mHourPaint.setStrokeWidth(5);
        mMinutePaint = new Paint();
        mMinutePaint.setColor(Color.BLUE);
        mMinutePaint.setStrokeWidth(3);

        mCalendar = new Time();
        onTimeChanged();
    }

    private void onTimeChanged() {
        mCalendar.setToNow();

        int hour = mCalendar.hour;
        int minute = mCalendar.minute;
        int second = mCalendar.second;

        mMinutes = minute + second / 60.0f;
        mHour = hour + mMinutes / 60.0f;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged() called with: w = [" + w + "], h = [" + h + "], oldw = [" + oldw + "], oldh = [" + oldh + "]");
        mDialWidth = w;
        mDialHeight = h;
        r = mDialWidth / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 将坐标系移到View的中心，可以简化坐标计算
        canvas.translate(mDialWidth / 2, mDialHeight / 2);
        // 画圆盘
        canvas.save();
        canvas.drawCircle(0, 0, r, mCirclePaint);
        for (int i = 0; i < 12; i++) {
            String degree = String.valueOf(i);
            if (i % 3 == 0) {
                canvas.drawLine(0, -r, 0, -r + longDegreeLength, mLongDegreePaint);
                canvas.drawText(degree, 0, -r + longDegreeLength + 50, mLongDegreePaint);
            } else {
                canvas.drawLine(0, -r, 0, -r + shortDegreeLength, mShortDegreePaint);
                canvas.drawText(degree, 0, -r + shortDegreeLength + 30, mShortDegreePaint);
            }
            canvas.rotate(30);
        }
        canvas.restore();
        // 画时针
        canvas.save();
        canvas.rotate(mHour / 12 * 360);
        canvas.drawLine(0, 0, 0, -hourLength, mHourPaint);
        canvas.restore();
        // 画分针
        canvas.save();
        canvas.rotate(mMinutes / 60 * 360);
        canvas.drawLine(0, 0, 0, -minuteLength, mMinutePaint);
        canvas.restore();
    }

    private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "onReceive: action = [" + action + "]");
            if (action != null && action.equals(Intent.ACTION_TIMEZONE_CHANGED)) {
                String tz = intent.getStringExtra("time-zone");
                mCalendar = new Time(TimeZone.getTimeZone(tz).getID());
            }
            onTimeChanged();
            invalidate();
        }
    };

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow() called");
        if (!mAttached) {
            mAttached = true;
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_TIME_TICK);
            filter.addAction(Intent.ACTION_TIME_CHANGED);
            filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
            getContext().registerReceiver(mIntentReceiver, filter);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow() called");
        if (mAttached) {
            getContext().unregisterReceiver(mIntentReceiver);
            mAttached = false;
        }
    }
}
