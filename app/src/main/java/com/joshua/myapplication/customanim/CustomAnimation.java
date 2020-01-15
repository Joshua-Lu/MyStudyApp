package com.joshua.myapplication.customanim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * 通过覆写applyTransformation，实现自定义动画
 * <p>
 * Created by Joshua on 2020/1/15 23:12.
 */
public class CustomAnimation extends Animation {
    private int mCenterWith;
    private int mCenterHeight;
    private Camera mCamera;
    private float mRotateX = 0.0f;
    private float mRotateY = 0.0f;
    private float mRotateZ = 0.0f;

    public void setmRotateX(float mRotateX) {
        this.mRotateX = mRotateX;
    }

    public void setmRotateY(float mRotateY) {
        this.mRotateY = mRotateY;
    }

    public void setmRotateZ(float mRotateZ) {
        this.mRotateZ = mRotateZ;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        // 设置默认动画时长
        setDuration(5000);
        // 设置动画结束后保留状态
        setFillAfter(true);
        mCenterWith = width / 2;
        mCenterHeight = height / 2;
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        // 获取动画变换矩阵
        final Matrix matrix = t.getMatrix();
        mCamera.save();
        mCamera.rotateX(mRotateX * interpolatedTime);
        mCamera.rotateY(mRotateY * interpolatedTime);
        mCamera.rotateZ(mRotateZ * interpolatedTime);
        // 将旋转变换作用到matrix上
        mCamera.getMatrix(matrix);
        mCamera.restore();
        // 通过pre方法设置矩阵作用前的偏移量来改变旋转中心
        matrix.preTranslate(-mCenterWith, -mCenterHeight);
        matrix.postTranslate(mCenterWith, mCenterHeight);
    }
}
