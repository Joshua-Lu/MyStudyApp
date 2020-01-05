package com.joshua.myapplication.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * 位图处理相关工具类
 * Created by Joshua on 2020/1/5 20:16.
 */
public class BitmapUtil {

    private static final String TAG = "BitmapUtil";

    /**
     * 根据图片文件路径，获取指定宽高的Bitmap
     *
     * @param filePath  图片文件路径
     * @param reqWidth  要求生成的图片的宽度
     * @param reqHeight 要求生成的图片的高度
     * @return 文件路径对应的Bitmap
     */
    public static Bitmap readBitmapFromFile(String filePath, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 根据资源id，获取指定宽高的Bitmap
     *
     * @param context   Context
     * @param id        图片资源id
     * @param reqWidth  要求生成的图片的宽度
     * @param reqHeight 要求生成的图片的高度
     * @return 资源id对应的Bitmap
     */
    public static Bitmap readBitmapFromResource(Context context, int id, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), id, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        Log.d(TAG, "readBitmapFromFile: inSampleSize = [" + options.inSampleSize + "]");
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(), id, options);
    }

    /**
     * 计算inSampleSize的值
     *
     * @param options   原图的options，用于获取原图的宽高
     * @param reqWidth  要求生成的图片的宽度
     * @param reqHeight 要求生成的图片的高度
     * @return 最接近要求生成图片宽高的inSampleSize的值
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > width && height > reqHeight) {
            inSampleSize = height / reqHeight;
        } else if (height < width && width > reqWidth) {
            inSampleSize = width / reqWidth;
        }
        if (inSampleSize <= 0) {
            inSampleSize = 1;
        }
        return inSampleSize;
    }
}
