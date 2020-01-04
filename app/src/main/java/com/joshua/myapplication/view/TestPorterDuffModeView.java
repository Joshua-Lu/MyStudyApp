package com.joshua.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.joshua.myapplication.R;

/**
 * test PorterDuffMode of Paint
 * 实现刮刮乐效果
 * <p>
 * Created by Joshua on 2019/12/30 .
 */
public class TestPorterDuffModeView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap mBgBitmap;
    private Bitmap mFgBitmap;
    private Paint mClearPaint;
    private Path mPath;
    private Canvas mCanvas;

    public TestPorterDuffModeView(Context context) {
        super(context);
        init(null, 0);
    }

    public TestPorterDuffModeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TestPorterDuffModeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        mPath = new Path();
        mClearPaint = new Paint();
        mClearPaint.setAntiAlias(true);
        mClearPaint.setStyle(Paint.Style.STROKE);
        mClearPaint.setStrokeWidth(50);
        // 使用 PorterDuff.Mode.CLEAR ，实现清除效果
        mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.myicon);
        mBgBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBgBitmap);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mCanvas.drawRoundRect(new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight()), 80, 80, mPaint);
        // 修改Mode，可以生成各种不同的效果
        // 使用 PorterDuff.Mode.SRC_IN ，实现将方形图片，变成圆角矩形
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mCanvas.drawBitmap(mBitmap, 0, 0, mPaint);

        mFgBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas.setBitmap(mFgBitmap);
        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mBitmap.getWidth(), mBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBgBitmap, 0, 0, null);
        canvas.drawBitmap(mFgBitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                break;
        }
        mCanvas.drawPath(mPath, mClearPaint);
        invalidate();
        return true;
    }
}
