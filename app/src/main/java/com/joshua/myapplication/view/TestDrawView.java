package com.joshua.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.joshua.myapplication.R;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * 测试各个draw方法的使用
 * <p>
 * Created by Joshua on 2019/12/22.
 */
public class TestDrawView extends View {

    Paint paint;
    private RectF rectF;
    private Path path;

    public TestDrawView(Context context) {
        super(context);
        init();
    }

    public TestDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        rectF = new RectF(100, 700, 500, 900);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        path = new Path();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画点
        canvas.drawPoint(100, 100, paint);
        // 画线
        canvas.drawLine(100, 150, 500, 150, paint);
        // 画多条线
        float[] pts = {100, 200, 100, 400,
                200, 200, 200, 400,
                300, 200, 300, 400};
        canvas.drawLines(pts, paint);
        // 画实心矩形
        paint.setStyle(Paint.Style.FILL);// Style 默认是 FILL
        canvas.drawRect(100, 450, 500, 650, paint);
        // 画空心矩形
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(600, 450, 1000, 650, paint);
        // 画圆角矩形
//        canvas.drawRoundRect(100, 700, 500, 900, 20, 20, paint);//@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//        RectF rectF = new RectF(100, 700, 500, 900);
        canvas.drawRoundRect(rectF, 20, 20, paint);
        // 画圆
        canvas.drawCircle(800, 800, 100, paint);
        // 画椭圆
        rectF.top = 950;
        rectF.bottom = 1250;
        canvas.drawOval(rectF, paint);
        // 画弧形、扇形
        rectF.left += 10;
        rectF.top += 10;
        rectF.right -= 10;
        rectF.bottom -= 10;
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        // sweepAngle 是顺时针方向的旋转角度
        // useCenter true：扇形，false：弧形
        canvas.drawArc(rectF, 0, 80, true, paint);
        canvas.drawArc(rectF, 90, 80, false, paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF, 180, 80, true, paint);
        canvas.drawArc(rectF, 270, 80, false, paint);
        // 画文本
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);
        paint.setTextSize(50);
        // 设置drawText()中(x,y)对应文本水平方向的参考位置，竖直方向默认是文本底部
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("JoshuaLu", 600, 1000, paint);
        // 包含start,不包含end
        canvas.drawText("JoshuaLu", 0, 6, 600, 1100, paint);
        // 画路径
        paint.setStyle(Paint.Style.STROKE);
        path.moveTo(100, 1300);
        path.lineTo(200, 1300);
        // Path.Direction.CW:顺时针方向，Path.Direction.CCW:逆时针方向
        path.addCircle(200, 1300, 20, Path.Direction.CW);
        path.lineTo(600, 1425);
        path.addArc(400, 1350, 600, 1500, 0, 135);
        path.lineTo(500, 1600);
        canvas.drawPath(path, paint);
    }
}
