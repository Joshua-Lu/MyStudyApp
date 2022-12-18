package com.joshua.myapplication.view;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

/**
 * 测试属性动画的View
 *
 * @author Joshua
 * @date 2022/12/7 16:18
 */
public class PointAnimView extends View {

    private static final String TAG = "PointAnimView";

    public static final int RADIUS = 20;
    private Point currentPoint;
    private Paint mPaint;
    private Paint linePaint;
    private AnimatorSet animSet;
    private TimeInterpolator interpolatorType = new LinearInterpolator();
    /**
     * 实现关于color 的属性动画
     */
    private int color;
    private float radius = RADIUS;

    private boolean lineDraw = false;

    public PointAnimView(Context context) {
        super(context);
        init();
    }

    public PointAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PointAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        mPaint.setColor(this.color);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.TRANSPARENT);
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            drawCircle(canvas);
        } else {
            drawCircle(canvas);
        }
        drawLine(canvas);
    }

    private void drawLine(Canvas canvas) {
        Log.d(TAG, "drawLine: getHeight() / 2 = [" + (getHeight() / 2) + "]");
        canvas.drawLine(10, getHeight() / 2, getWidth(), getHeight() / 2, linePaint);
        canvas.drawLine(10, getHeight() / 2 - 150, 10, getHeight() / 2 + 150, linePaint);
        canvas.drawPoint(currentPoint.x, currentPoint.y, linePaint);

    }

    public void startMyAnimation() {
        Point startP = new Point(RADIUS, getHeight() / 2);
        Point endP = new Point(getWidth() - RADIUS, getHeight() / 2);
        Log.d(TAG, "startMyAnimation: startP = [" + startP + "], endP = [" + endP + "]");
        final ValueAnimator pathAnimator = ValueAnimator.ofObject(new PointSinEvaluator(), startP, endP);
        pathAnimator.setRepeatCount(-1);
        pathAnimator.setRepeatMode(ValueAnimator.REVERSE);
        pathAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: currentPoint = [" + currentPoint + "]");
                postInvalidate();
            }
        });

        ObjectAnimator animColor = ObjectAnimator.ofObject(this, "color", new ArgbEvaluator(), Color.GREEN, Color.YELLOW, Color.BLUE, Color.WHITE, Color.RED);
        animColor.setRepeatCount(-1);
        animColor.setRepeatMode(ValueAnimator.REVERSE);
        ValueAnimator animScale = ValueAnimator.ofFloat(20f, 80f, 60f, 10f, 35f, 55f, 10f);
        animScale.setRepeatCount(-1);
        animScale.setRepeatMode(ValueAnimator.REVERSE);
        animScale.setDuration(5000);
        animScale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radius = (float) animation.getAnimatedValue();
            }
        });
        animSet = new AnimatorSet();
        animSet.play(pathAnimator).with(animColor).with(animScale);
        animSet.setDuration(5000);
        animSet.setInterpolator(interpolatorType);
        animSet.start();
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.x;
        float y = currentPoint.y;
        canvas.drawCircle(x, y, radius, mPaint);
    }

    public void setInterpolatorType(int type) {
        switch (type) {
            case 1:
                interpolatorType = new BounceInterpolator();
                break;
            case 2:
                interpolatorType = new AccelerateDecelerateInterpolator();
                break;
            case 3:
                interpolatorType = new DecelerateInterpolator();
                break;
            case 4:
                interpolatorType = new AnticipateInterpolator();
                break;
            case 5:
                interpolatorType = new LinearInterpolator();
                break;
            case 6:
                interpolatorType = new LinearOutSlowInInterpolator
                        ();
                break;
            case 7:
                interpolatorType = new OvershootInterpolator();
            default:
                interpolatorType = new LinearInterpolator();
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void pauseAnimation() {
        if (animSet != null) {
            animSet.pause();
        }
    }

    public void stopMyAnimation() {
        if (animSet != null) {
            animSet.cancel();
            this.clearAnimation();
        }
    }

    public class PointSinEvaluator implements TypeEvaluator {

        @Override

        public Object evaluate(float fraction, Object startValue, Object endValue) {

            Point startPoint = (Point) startValue;

            Point endPoint = (Point) endValue;
            // fraction代表当前动画完成的百分比

            int x = (int) (startPoint.x + fraction * (endPoint.x - startPoint.x));

            int y = (int) (Math.sin(x * Math.PI / 180) * 100) + endPoint.y;

            Point point = new Point(x, y);

            return point;

        }

    }
}
