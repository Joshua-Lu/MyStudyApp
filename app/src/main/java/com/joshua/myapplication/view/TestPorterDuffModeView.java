package com.joshua.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * test PorterDuffMode for Paint
 * <p>
 * Created by Joshua on 2019/12/30 .
 */
public class TestPorterDuffModeView extends View {

    private Paint mDstPaint;
    private Paint mSrcPaint;
    private Paint mTextPaint;
    private float mWidth;
    private float mHeight;
    private List<PorterDuffXfermode> modeList;
    private List<String> modeNameList;

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
//        setBackgroundColor(Color.GRAY);
        mDstPaint = new Paint();
        mDstPaint.setColor(Color.RED);
        mSrcPaint = new Paint();
        mSrcPaint.setColor(Color.GREEN);
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
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
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float r = 110;
        int canvasWidth = getWidth();
        int canvasHeight = getHeight();
        canvas.drawColor(Color.GRAY);

        for (int i = 0; i < modeList.size(); i++) {
            int row = i / 4;
            int col = i % 4;
            canvas.save();

            int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);

            canvas.translate((3 * r + 30) * col + r, (3 * r + 100) * row + r);
            mDstPaint.setColor(Color.RED);
            canvas.drawCircle(0, 0, r, mDstPaint);

            // 修改Mode，可以生成各种不同的效果
            mDstPaint.setXfermode(modeList.get(i));
            mDstPaint.setColor(Color.GREEN);
            canvas.drawRect(0, 0, 2 * r, 2 * r, mDstPaint);
            canvas.drawText(modeNameList.get(i), 0.5f * r, 2 * r + 50, mTextPaint);
            // 最后将画笔去除Xfermode
            mDstPaint.setXfermode(null);

            canvas.restoreToCount(layerId);

            canvas.restore();
        }
//        canvas.translate(r, r);
//        mDstPaint.setColor(Color.RED);
//        canvas.drawCircle(0, 0, r, mDstPaint);
//        // 修改Mode，可以生成各种不同的效果
//        mDstPaint.setColor(Color.GREEN);
//        mDstPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//        canvas.drawRect(0, 0, 2 * r, 2 * r, mDstPaint);

    }

}
