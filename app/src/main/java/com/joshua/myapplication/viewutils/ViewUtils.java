package com.joshua.myapplication.viewutils;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 实现ViewUtils注解功能的关键类
 * <p>
 * 通过反射的方法，解析注解，并实现View的绑定
 * <p>
 * Created by Joshua on 2021/3/13 17:17
 */
public class ViewUtils {

    private static final String TAG = "ViewUtils";

    public static void init(Activity activity) {
        Log.d(TAG, "init() called with: activity = [" + activity + "]");
        bindView(activity);
        bindClickListener(activity);
    }

    /**
     * 解析 FindViewById 注解，并找到对应的View，绑定到对应的变量上
     */
    private static void bindView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        // 找到activity中所有定义的成员变量
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            // 判断该变量上是否有加 FindViewById 注解
            FindViewById annotation = field.getAnnotation(FindViewById.class);
            if (annotation != null) {
                // 有该注解，取得注解上的值
                int viewId = annotation.value();
                // 在这里执行 findViewById 找到对应的View
                View view = activity.findViewById(viewId);
                field.setAccessible(true);
                try {
                    // 将找到的View设给对应的变量
                    field.set(activity, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 解析 SetOnClickListener 注解，并找到对应的View，
     * 为该View设置OnClickListener，并在onClick方法里调用对应添加了注解的方法
     */
    private static void bindClickListener(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        // 找到activity中所有定义的方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            SetOnClickListener annotation = method.getAnnotation(SetOnClickListener.class);
            // 判断该方法上是否有加 SetOnClickListener 注解
            if (annotation != null) {
                // 有该注解，取得注解上的值
                int viewId = annotation.value();
                // 在这里执行 findViewById 找到对应的View
                View view = activity.findViewById(viewId);
                method.setAccessible(true);
                // 在这里为对应的View执行 setOnClickListener
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            // 调用添加了注解的对应方法
                            method.invoke(activity, view);
                        } catch (Exception e) {
                            try {
                                method.invoke(activity);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
            }
        }
    }
}
