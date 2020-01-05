package com.joshua.myapplication.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * 显示尺寸相关工具类
 * Created by Joshua on 2020/1/5 21:33.
 */
public class DisplayUtil {

    private static final String TAG = "DisplayUtil";
    private static float density;
    private static int densityDpi;
    private static int pixelHeight;
    private static int pixelWidth;
    private static float dpiHeight;
    private static float dpiWidth;
//    private static final float UIDesign_H = 1080;
//    private static final float UIDesign_W = 1920;

    /**
     * 该方法必须调用，且最好在Application的onCreate()方法里调用
     *
     * @param context Context
     */
    public static void init(Context context) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(localDisplayMetrics);

        density = localDisplayMetrics.density;
        densityDpi = localDisplayMetrics.densityDpi;
        Log.d(TAG, "init: density = [" + density + "], densityDpi = [" + densityDpi + "]");

        pixelHeight = localDisplayMetrics.heightPixels;
        pixelWidth = localDisplayMetrics.widthPixels;
        Log.d(TAG, "init: pixelHeight = [" + pixelHeight + "], pixelWidth = [" + pixelWidth + "]");

        dpiHeight = pixelHeight / density;
        dpiWidth = pixelWidth / density;
        Log.d(TAG, "init: dpiHeight = [" + dpiHeight + "], dpiWidth = [" + dpiWidth + "]");
    }

    public float getMasterTextSize() {
        return (float) (dpiHeight / 23.0);
    }

    public float getFloatTextSize() {
        return (float) (dpiHeight / 32.0);
    }

//    public static int heightOf(int original) {
//        return (int) (pixelHeight * original / UIDesign_H + 0.5f);
//    }
//
//    public static int widthOf(int original) {
//        return (int) (pixelWidth * original / UIDesign_W + 0.5f);
//    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 获取屏幕的高度，单位px
     * @return pixelHeight
     */
    public static int getScreenHeight() {
        return pixelHeight;
    }

    /**
     * 获取屏幕的宽度，单位px
     * @return pixelWidth
     */
    public static int getScreenWidth() {
        return pixelWidth;
    }

}
