package com.joshua.myapplication.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;

public class ImageHelper {

    private static final String TAG = "ImageHelper";

    // 初始颜色矩阵，不会对原有颜色值进行任何变化
    public static float[] initColorMatrix = new float[]{
            1, 0, 0, 0, 0,
            0, 1, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 1, 0
    };
    // 底片效果
    public static float[] negativeMatrix = new float[]{
            -1, 0, 0, 0, 255,
            0, -1, 0, 0, 255,
            0, 0, -1, 0, 255,
            0, 0, 0, 1, 0
    };
    // 老照片效果
    public static float[] oldPhotoMatrix = new float[]{
            0.393f, 0.769f, 0.189f, 0, 0,
            0.349f, 0.686f, 0.168f, 0, 0,
            0.272f, 0.534f, 0.131f, 0, 0,
            0, 0, 0, 1, 0
    };
    // 灰度效果
    public static float[] grayMatrix = new float[]{
            0.33f, 0.59f, 0.11f, 0, 0,
            0.33f, 0.59f, 0.11f, 0, 0,
            0.33f, 0.59f, 0.11f, 0, 0,
            0, 0, 0, 1, 0
    };
    // 反转效果
    public static float[] reverseMatrix = new float[]{
            -1, 0, 0, 1, 1,
            0, -1, 0, 1, 1,
            0, 0, -1, 1, 1,
            0, 0, 0, 1, 0
    };
    // 去色效果
    public static float[] decolorizeMatrix = new float[]{
            1.5f, 1.5f, 1.5f, 0, -1,
            1.5f, 1.5f, 1.5f, 0, -1,
            1.5f, 1.5f, 1.5f, 0, -1,
            0, 0, 0, 1, 0
    };
    // 高饱和度效果
    public static float[] highSaturationMatrix = new float[]{
            1.438f, -0.122f, -0.016f, 0, -0.03f,
            -0.026f, 1.378f, -0.016f, 0, 0.05f,
            -0.026f, -0.122f, 1.483f, 0, -0.02f,
            0, 0, 0, 1, 0
    };

    // 初始变换矩阵，不会对图片进行任何变化T
    public static float[] initTransformMatrix = new float[]{
            1, 0, 0,
            0, 1, 0,
            0, 0, 1
    };
    // 平移变换矩阵
    public static float[] transMatrix = new float[]{
            1, 0, 500,
            0, 1, 500,
            0, 0, 1
    };
    // 缩放变换矩阵
    public static float[] scaleMatrix = new float[]{
            0.5f, 0, 0,
            0, 0.5f, 0,
            0, 0, 1
    };
    // 错切变换矩阵
    public static float[] skewMatrix = new float[]{
            1, 0.5f, 0,
            0.5f, 1, 0,
            0, 0, 1
    };
    // 旋转变换矩阵
    public static float[] rotateMatrix = new float[]{
            (float) Math.cos(45), -(float) Math.sin(45), 0,
            (float) Math.sin(45), (float) Math.cos(45), 0,
            0, 0, 1
    };

    /**
     * 改变图片的色调、饱和度、亮度
     *
     * @param bm         原始图片Bitmap对象
     * @param hue        色调 [0,255)
     * @param saturation 饱和度 [0,255)
     * @param lum        亮度 [0,255)
     * @return 改变后的图片Bitmap对象
     */
    public static Bitmap handleImageEffect(Bitmap bm,
                                           float hue,
                                           float saturation,
                                           float lum) {
        Bitmap bmp = Bitmap.createBitmap(
                bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bm, 0, 0, paint);
        return bmp;
    }

    /**
     * 将图片处理成：底片效果
     *
     * @param bm 原始图片Bitmap对象
     * @return 改变后的图片Bitmap对象
     */
    public static Bitmap handleImageNegative(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color;
        int r, g, b, a;

        Bitmap bmp = Bitmap.createBitmap(width, height
                , Bitmap.Config.ARGB_8888);

        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);

        for (int i = 0; i < width * height; i++) {
            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }
            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }
            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }
            newPx[i] = Color.argb(a, r, g, b);
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }

    /**
     * 将图片处理成：老照片效果
     *
     * @param bm 原始图片Bitmap对象
     * @return 改变后的图片Bitmap对象
     */
    public static Bitmap handleImagePixelsOldPhoto(Bitmap bm) {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(),
                Bitmap.Config.ARGB_8888);
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color = 0;
        int r, g, b, a, r1, g1, b1;

        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];

        bm.getPixels(oldPx, 0, bm.getWidth(), 0, 0, width, height);
        for (int i = 0; i < width * height; i++) {
            color = oldPx[i];
            a = Color.alpha(color);
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);

            r1 = (int) (0.393 * r + 0.769 * g + 0.189 * b);
            g1 = (int) (0.349 * r + 0.686 * g + 0.168 * b);
            b1 = (int) (0.272 * r + 0.534 * g + 0.131 * b);

            if (r1 > 255) {
                r1 = 255;
            }
            if (g1 > 255) {
                g1 = 255;
            }
            if (b1 > 255) {
                b1 = 255;
            }

            newPx[i] = Color.argb(a, r1, g1, b1);
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }

    /**
     * 将图片处理成：浮雕效果
     *
     * @param bm 原始图片Bitmap对象
     * @return 改变后的图片Bitmap对象
     */
    public static Bitmap handleImagePixelsRelief(Bitmap bm) {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(),
                Bitmap.Config.ARGB_8888);
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color = 0, colorBefore = 0;
        int a, r, g, b;
        int r1, g1, b1;

        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];

        bm.getPixels(oldPx, 0, bm.getWidth(), 0, 0, width, height);
        for (int i = 1; i < width * height; i++) {
            colorBefore = oldPx[i - 1];
            a = Color.alpha(colorBefore);
            r = Color.red(colorBefore);
            g = Color.green(colorBefore);
            b = Color.blue(colorBefore);

            color = oldPx[i];
            r1 = Color.red(color);
            g1 = Color.green(color);
            b1 = Color.blue(color);

            r = (r - r1 + 127);
            g = (g - g1 + 127);
            b = (b - b1 + 127);
            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }
            newPx[i] = Color.argb(a, r, g, b);
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }

    /**
     * 根据颜色矩阵，处理图片
     *
     * @param bitmap 原始图片Bitmap对象
     * @param matrix 颜色矩阵（长度为20的浮点数组）
     * @return 改变后的图片Bitmap对象
     */
    public static Bitmap handleImageColorMatrix(Bitmap bitmap, float[] matrix) {
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
        return bmp;
    }

    /**
     * 根据变换矩阵，处理图片，实现旋转、缩放、平移、错切效果
     *
     * @param bitmap       原始图片Bitmap对象
     * @param matrixValues 变换矩阵（长度为9的浮点数组）
     * @return 改变后的图片Bitmap对象
     */
    public static Bitmap handleImageTransformMatrix(Bitmap bitmap, float[] matrixValues) {
        Bitmap bmp = Bitmap.createBitmap(
                bitmap.getWidth(),
                bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Matrix matrix = new Matrix();
        matrix.setValues(matrixValues);
        canvas.drawBitmap(bitmap, matrix, null);
        return bmp;
    }

    /**
     * 平移变换
     *
     * @param bitmap 原始图片Bitmap对象
     * @param dx     水平方向平移距离
     * @param dy     竖直方向平移距离
     * @return 改变后的图片Bitmap对象
     */
    public static Bitmap handleImageTrans(Bitmap bitmap, float dx, float dy) {
        Bitmap bmp = Bitmap.createBitmap(
                bitmap.getWidth(),
                bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Matrix matrix = new Matrix();
        matrix.setTranslate(dx, dy);
        canvas.drawBitmap(bitmap, matrix, null);
        return bmp;
    }

    /**
     * 缩放变换
     *
     * @param bitmap 原始图片Bitmap对象
     * @param sx     水平方向缩放比例
     * @param sy     竖直方向缩放比例
     * @return 改变后的图片Bitmap对象
     */
    public static Bitmap handleImageScale(Bitmap bitmap, float sx, float sy) {
        Bitmap bmp = Bitmap.createBitmap(
                bitmap.getWidth(),
                bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Matrix matrix = new Matrix();
        matrix.setScale(sx, sy, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, matrix, null);
        return bmp;
    }

    /**
     * 错切变换
     *
     * @param bitmap 原始图片Bitmap对象
     * @param kx     水平方向错切比例
     * @param ky     竖直方向错切比例
     * @return 改变后的图片Bitmap对象
     */
    public static Bitmap handleImageSkew(Bitmap bitmap, float kx, float ky) {
        Bitmap bmp = Bitmap.createBitmap(
                bitmap.getWidth(),
                bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Matrix matrix = new Matrix();
        matrix.setSkew(kx, ky, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, matrix, null);
        return bmp;
    }

    /**
     * 旋转变换
     *
     * @param bitmap  原始图片Bitmap对象
     * @param degrees 顺时针方向旋转角度
     * @return 改变后的图片Bitmap对象
     */
    public static Bitmap handleImageRotate(Bitmap bitmap, float degrees) {
        Bitmap bmp = Bitmap.createBitmap(
                bitmap.getWidth(),
                bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, matrix, null);
        return bmp;
    }

    public static float[] getColorMatrix(int position) {
        switch (position) {
            case 0:
                return negativeMatrix;
            case 1:
                return oldPhotoMatrix;
            case 2:
                return grayMatrix;
            case 3:
                return reverseMatrix;
            case 4:
                return decolorizeMatrix;
            case 5:
                return highSaturationMatrix;
            default:
                return initColorMatrix;
        }
    }

    public static float[] getTransformMatrix(int position) {
        switch (position) {
            case 0:
                return transMatrix;
            case 1:
                return scaleMatrix;
            case 2:
                return skewMatrix;
            case 3:
                return rotateMatrix;
            default:
                return initTransformMatrix;
        }
    }
}
