package com.joshua.myapplication.common;

import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

import androidx.annotation.NonNull;

/**
 * Glide图片转换成圆角
 *
 * @author JoshuaLu
 * @date 2021/4/21 11:31
 */
public class GlideRoundCornerTransform extends BitmapTransformation {
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return null;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }

}
