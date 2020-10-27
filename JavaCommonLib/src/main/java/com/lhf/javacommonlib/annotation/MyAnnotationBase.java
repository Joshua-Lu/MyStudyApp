package com.lhf.javacommonlib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，可以被子类继承的注解
 * Created by Joshua on 2020/10/26.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited // 可以被子类继承
public @interface MyAnnotationBase {
    String value() default "";
}
