package com.joshua.myapplication;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.joshua.myapplication.utils.DisplayUtil;

/**
 * Created by Joshua on 2020/1/5 16:23.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DisplayUtil.init(this);

        // Fresco初始化
        Fresco.initialize(this);

    }
}
