package com.joshua.myapplication.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.joshua.myapplication.R;
import com.joshua.myapplication.utils.ImageHelper;

import androidx.appcompat.app.AppCompatActivity;

public class TestImagePsActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, Spinner.OnItemSelectedListener {

    private static final String TAG = "TestImagePsActivity";

    private static int MAX_VALUE = 255;
    private static int MID_VALUE = 127;
    private ImageView mImageView;
    private SeekBar mSeekbarhue, mSeekbarSaturation, mSeekbarLum;
    private float mHue, mStauration, mLum;
    private EditText mEtX, mEtY, mEtDegree;
    private Bitmap bitmap;

    private float[] mColorMatrix = new float[20];
    private float[] mTransformMatrix = new float[9];
    Spinner colorSpinner;
    Spinner transformSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_image_ps);
        // 获取到的bitmap大小会随设备dpi和图片存放的不同dpi文件夹按比例变化
        // bitmap对象的大小=原图大小*（设备dpi/图片所在文件夹dpi）
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.myicon);
        mImageView = (ImageView) findViewById(R.id.imageview);
        mSeekbarhue = (SeekBar) findViewById(R.id.seekbarHue);
        mSeekbarSaturation = (SeekBar) findViewById(R.id.seekbarSaturation);
        mSeekbarLum = (SeekBar) findViewById(R.id.seekbarLum);
        mEtX = (EditText) findViewById(R.id.et_x);
        mEtY = (EditText) findViewById(R.id.et_y);
        mEtDegree = (EditText) findViewById(R.id.et_degree);
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
        colorSpinner = (Spinner) findViewById(R.id.spinner_colorMatrixs);
        colorSpinner.setOnItemSelectedListener(this);
        transformSpinner = (Spinner) findViewById(R.id.spinner_transformMatrixs);
        transformSpinner.setOnItemSelectedListener(this);
        mColorMatrix = ImageHelper.initColorMatrix;
        mTransformMatrix = ImageHelper.initTransformMatrix;
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

    // 将颜色矩阵值设置到图像
    private void setImageColorMatrix(float[] matrix) {
        Bitmap bmp = ImageHelper.handleImageColorMatrix(bitmap, matrix);
        mImageView.setImageBitmap(bmp);
    }

    // 作用颜色矩阵效果
    public void btnChange(View view) {
        setImageColorMatrix(mColorMatrix);
    }

    // 将变换矩阵值设置到图像
    private void setImageTransformMatrix(float[] matrix) {
        Bitmap bmp = ImageHelper.handleImageTransformMatrix(bitmap, matrix);
        mImageView.setImageBitmap(bmp);
    }

    // 作用变换矩阵效果
    public void btnTransformChange(View view) {
        setImageTransformMatrix(mTransformMatrix);
    }

    // 重置进度条和图片
    public void btnReset(View view) {
        mSeekbarhue.setProgress(MID_VALUE);
        mSeekbarSaturation.setProgress(MID_VALUE);
        mSeekbarLum.setProgress(MID_VALUE);
        mImageView.setImageBitmap(bitmap);
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
        switch (parent.getId()) {
            case R.id.spinner_colorMatrixs:
                mColorMatrix = ImageHelper.getColorMatrix(position);
                break;
            case R.id.spinner_transformMatrixs:
                mTransformMatrix = ImageHelper.getTransformMatrix(position);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        switch (parent.getId()) {
            case R.id.spinner_colorMatrixs:
                mColorMatrix = ImageHelper.getColorMatrix(0);
                break;
            case R.id.spinner_transformMatrixs:
                mTransformMatrix = ImageHelper.getTransformMatrix(0);
                break;
        }
    }

    public float getX() {
        String x = mEtX.getText().toString();
        return TextUtils.isEmpty(x) ? 0 : Float.parseFloat(x);
    }

    public float getY() {
        String y = mEtY.getText().toString();
        return TextUtils.isEmpty(y) ? 0 : Float.parseFloat(y);
    }

    public float getDegree() {
        String degree = mEtDegree.getText().toString();
        return TextUtils.isEmpty(degree) ? 0 : Float.parseFloat(degree);
    }

    public void handleImageTrans(View view) {
        mImageView.setImageBitmap(ImageHelper.handleImageTrans(bitmap, getX(), getY()));
    }

    public void handleImageScale(View view) {
        mImageView.setImageBitmap(ImageHelper.handleImageScale(bitmap, getX(), getY()));
    }

    public void handleImageSkew(View view) {
        mImageView.setImageBitmap(ImageHelper.handleImageSkew(bitmap, getX(), getY()));
    }

    public void handleImageRotate(View view) {
        mImageView.setImageBitmap(ImageHelper.handleImageRotate(bitmap, getDegree()));
    }
}
