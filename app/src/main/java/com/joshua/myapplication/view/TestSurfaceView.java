package com.joshua.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.joshua.myapplication.utils.DisplayUtil;

/**
 * 实现自动绘制正弦曲线，以及绘画板功能
 * Created by Joshua on 2020/1/5 22:54.
 */
public class TestSurfaceView extends SurfaceViewTemplate {

    private int x;
    private int y;
    private Path mPath;
    private Paint mPaint;

    public TestSurfaceView(Context context) {
        super(context);
    }

    public TestSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
        mPath = new Path();
        mPath.moveTo(0, 400);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void beforeDraw() {
        super.beforeDraw();
    }

    @Override
    public void run() {
        super.run();
        try {
            // 防止刷新过快，影响性能
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSurfaceViewDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void afterDraw() {
        super.afterDraw();
        // 绘制正弦曲线
        x++;
        y = (int) (100 * Math.sin(x * 2 * Math.PI / 360) + 400);
        if (x <= DisplayUtil.getScreenWidth()) {
            mPath.lineTo(x, y);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
        }
        return true;
    }
}
