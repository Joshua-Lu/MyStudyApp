package com.joshua.myapplication.viewutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动 findViewById 的注解
 * 只要在成员变量上添加该注解，并填上id即可
 * <p>
 * Created by Joshua on 2021/3/13 17:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FindViewById {
    int value();
}
