package com.joshua.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 测试Paint相关属性
 * <p>
 * Created by Joshua on 2019/12/29 .
 */
public class TestPaintActivity extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap mInBitmap;
    private Bitmap mOutBitmap;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_paint);
        imageView = findViewById(R.id.imageView);

        mInBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.myicon);
        mOutBitmap = Bitmap.createBitmap(mInBitmap.getWidth(), mInBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(mOutBitmap);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        canvas.drawRoundRect(new RectF(0, 0, mInBitmap.getWidth(), mInBitmap.getHeight()), 80, 80, mPaint);

        // 修改Mode，可以生成各种不同的效果
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mInBitmap, 0, 0, mPaint);

        imageView.setImageBitmap(mOutBitmap);

    }
}
