package com.lhf.javacommonlib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * Created by Joshua on 2020/10/26.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface MyAnnotation {
    /* 注解方法支持的返回值类型：
    所有基本类型（int,float,boolean,byte,double,char,long,short）
    String
    Class (如：Class<?> 或 Class<T>)
    enum
    Annotation
    上述类型的数组*/
    String value() default "";

    String className() default "";

    String methodName() default "";
}
