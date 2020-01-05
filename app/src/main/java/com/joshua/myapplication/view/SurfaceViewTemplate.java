package com.joshua.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * SurfaceViewTemplate for other SurfaceView to extends
 * Created by Joshua on 2020/1/5 22:28.
 */
public class SurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    // 子线程标志位
    private boolean mIsDrawing;

    public SurfaceViewTemplate(Context context) {
        this(context, null);
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    protected void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
//        mHolder.setFormat(PixelFormat.OPAQUE);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            beforeDraw();
            draw();
            afterDraw();
        }
    }

    protected void beforeDraw() {

    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            onSurfaceViewDraw(mCanvas);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // unlockCanvasAndPost放在finally中，保证每次都能将内容提交
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    protected void onSurfaceViewDraw(Canvas canvas) {
        // draw something here

    }

    protected void afterDraw() {

    }
}
