package com.joshua.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.joshua.myapplication.utils.ImageHelper;

import androidx.appcompat.app.AppCompatActivity;

public class TestImagePsActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, Spinner.OnItemSelectedListener {

    private static int MAX_VALUE = 255;
    private static int MID_VALUE = 127;
    private ImageView mImageView;
    private SeekBar mSeekbarhue, mSeekbarSaturation, mSeekbarLum;
    private float mHue, mStauration, mLum;
    private Bitmap bitmap;

    private float[] mColorMatrix = new float[20];
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_image_ps);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.myicon);
        mImageView = (ImageView) findViewById(R.id.imageview);
        mSeekbarhue = (SeekBar) findViewById(R.id.seekbarHue);
        mSeekbarSaturation = (SeekBar) findViewById(R.id.seekbarSaturation);
        mSeekbarLum = (SeekBar) findViewById(R.id.seekbarLum);
        mSeekbarhue.setOnSeekBarChangeListener(this);
        mSeekbarSaturation.setOnSeekBarChangeListener(this);
        mSeekbarLum.setOnSeekBarChangeListener(this);
        mSeekbarhue.setMax(MAX_VALUE);
        mSeekbarSaturation.setMax(MAX_VALUE);
        mSeekbarLum.setMax(MAX_VALUE);
        mSeekbarhue.setProgress(MID_VALUE);
        mSeekbarSaturation.setProgress(MID_VALUE);
        mSeekbarLum.setProgress(MID_VALUE);
        mImageView.setImageBitmap(bitmap);
        spinner = (Spinner) findViewById(R.id.spinner_colorMatrixs);
        spinner.setOnItemSelectedListener(this);
        mColorMatrix = ImageHelper.initMatrix;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar,
                                  int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekbarHue:
                mHue = (progress - MID_VALUE) * 1.0F / MID_VALUE * 180;
                break;
            case R.id.seekbarSaturation:
                mStauration = progress * 1.0F / MID_VALUE;
                break;
            case R.id.seekbarLum:
                mLum = progress * 1.0F / MID_VALUE;
                break;
        }
        mImageView.setImageBitmap(ImageHelper.handleImageEffect(
                bitmap, mHue, mStauration, mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    // 将矩阵值设置到图像
    private void setImageMatrix(float[] matrix) {
        Bitmap bmp = Bitmap.createBitmap(
                bitmap.getWidth(),
                bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(matrix);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        mImageView.setImageBitmap(bmp);
    }

    // 作用矩阵效果
    public void btnChange(View view) {
        setImageMatrix(mColorMatrix);
    }

    // 重置矩阵效果
    public void btnReset(View view) {
        setImageMatrix(ImageHelper.initMatrix);
    }

    public void handleImageNegative(View view) {
        mImageView.setImageBitmap(ImageHelper.handleImageNegative(bitmap));
    }

    public void handleImagePixelsOldPhoto(View view) {
        mImageView.setImageBitmap(ImageHelper.handleImagePixelsOldPhoto(bitmap));
    }

    public void handleImagePixelsRelief(View view) {
        mImageView.setImageBitmap(ImageHelper.handleImagePixelsRelief(bitmap));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mColorMatrix = ImageHelper.getColorMatrix(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mColorMatrix = ImageHelper.getColorMatrix(0);
    }
}
