package com.joshua.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Samples for testing PorterDuffMode of Paint
 * <p>
 * Created by Joshua on 2020/01/04 .
 */
public class PorterDuffModeSampleView extends View {

    private Paint mPaint;
    private Paint mTextPaint;
    private List<PorterDuffXfermode> modeList;
    private List<String> modeNameList;
    private Bitmap mSrcB;
    private Bitmap mDstB;
    private int W = 300;
    private int H = 300;
    private int OFFSET_W = 30;
    private int OFFSET_H = 70;

    public PorterDuffModeSampleView(Context context) {
        super(context);
        init(null, 0);
    }

    public PorterDuffModeSampleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public PorterDuffModeSampleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        mPaint = new Paint();
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setTextSize(50);

        modeList = new ArrayList<>();
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.DST));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.ADD));
        modeList.add(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));

        modeNameList = new ArrayList<>();
        modeNameList.add("CLEAR");
        modeNameList.add("SRC");
        modeNameList.add("DST");
        modeNameList.add("SRC_OVER");
        modeNameList.add("DST_OVER");
        modeNameList.add("SRC_IN");
        modeNameList.add("DST_IN");
        modeNameList.add("SRC_OUT");
        modeNameList.add("DST_OUT");
        modeNameList.add("SRC_ATOP");
        modeNameList.add("DST_ATOP");
        modeNameList.add("XOR");
        modeNameList.add("DARKEN");
        modeNameList.add("LIGHTEN");
        modeNameList.add("MULTIPLY");
        modeNameList.add("SCREEN");
        modeNameList.add("ADD");
        modeNameList.add("OVERLAY");

        mSrcB = makeSrc(W, H);
        mDstB = makeDst(W, H);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(4 * (W + OFFSET_W), 5 * (H + OFFSET_H));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int canvasWidth = getWidth();
        int canvasHeight = getHeight();
        canvas.drawColor(Color.GRAY);
        for (int i = 0; i < modeList.size(); i++) {
            int row = i / 4;
            int col = i % 4;
            int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
            canvas.translate((W + OFFSET_W) * col, (H + OFFSET_H) * row);
            canvas.drawBitmap(mDstB, 0, 0, mPaint);
            // 修改Mode，可以生成各种不同的效果
            mPaint.setXfermode(modeList.get(i));
            canvas.drawBitmap(mSrcB, 0, 0, mPaint);
            canvas.drawText(modeNameList.get(i), 0, H + 50, mTextPaint);
            // 最后将画笔去除Xfermode
            mPaint.setXfermode(null);
            canvas.restoreToCount(layerId);
        }
    }

    // create a bitmap with a circle, used for the "dst" image
    static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFFFFCC44);
        c.drawOval(new RectF(0, 0, w * 3 / 4, h * 3 / 4), p);
        return bm;
    }

    // create a bitmap with a rect, used for the "src" image
    static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFF66AAFF);
        c.drawRect(w / 3, h / 3, w * 19 / 20, h * 19 / 20, p);
        return bm;
    }

}
