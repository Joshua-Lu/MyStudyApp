package com.joshua.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.joshua.myapplication.R;
import com.joshua.myapplication.utils.BitmapUtil;
import com.joshua.myapplication.utils.DisplayUtil;

/**
 * for testing Shaders of Paint
 * BitmapShader、LinearGradient、SweepGradient、RadialGradient、ComposeShader
 * <p>
 * Created by Joshua on 2020/1/4 22:07.
 */
public class TestShaderView extends View {

    private static final String TAG = "TestShaderView";
    private Paint mPaint;
    private RectF mRectF;
    private String text;
    private int textX = 200;
    private int textY = 500;
    private BitmapShader mBitmapShader;
    private LinearGradient linearGradient;
    private SweepGradient sweepGradient;
    private RadialGradient radialGradient;
    private ComposeShader composeShader;

    public TestShaderView(Context context) {
        this(context, null);
    }

    public TestShaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestShaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mRectF = new RectF(0, 0, 0, 0);

        // BitmapShader使用图像进行填充，选择不同Shader.TileMode，实现不同效果
        Bitmap mBitmap = BitmapUtil.readBitmapFromResource(getContext(), R.drawable.myicon, 200, 200);
        Log.d(TAG, "init: mBitmap.getWidth() = [" + mBitmap.getWidth() + "], mBitmap.getHeight() = [" + mBitmap.getHeight() + "]");
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);

        // LinearGradient实现线性渐变效果
        text = "我是渐变文字，使用LinearGradient实现";
        Log.d(TAG, "init: mPaint.getTextSize() default = [" + mPaint.getTextSize() + "]");
        mPaint.setTextSize(50);
        linearGradient = new LinearGradient(textX, textY,
                mPaint.measureText(text) + textX, textY,
                Color.RED, Color.GREEN, Shader.TileMode.REPEAT);

        sweepGradient = new SweepGradient(DisplayUtil.getScreenWidth() / 2, 200, Color.RED, Color.GREEN);
        radialGradient = new RadialGradient(DisplayUtil.getScreenWidth() / 2, 200,
                100, Color.RED, Color.GREEN, Shader.TileMode.REPEAT);
        // 使用PorterDuff.Mode，综合两个Shader
        composeShader = new ComposeShader(sweepGradient, radialGradient, PorterDuff.Mode.MULTIPLY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mRectF.right = getWidth();
        mRectF.bottom = 400;
        mPaint.setShader(mBitmapShader);
        canvas.drawRect(mRectF, mPaint);

        mPaint.setShader(linearGradient);
        canvas.drawText(text, textX, textY, mPaint);

        canvas.translate(0, 600);
        mPaint.setShader(sweepGradient);
        canvas.drawRect(mRectF, mPaint);

        canvas.translate(0, 500);
        mPaint.setShader(radialGradient);
        canvas.drawRect(mRectF, mPaint);

        canvas.translate(0, 500);
        mPaint.setShader(composeShader);
        canvas.drawRect(mRectF, mPaint);
    }
}
