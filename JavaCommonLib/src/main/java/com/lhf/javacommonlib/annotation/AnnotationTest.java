package com.lhf.javacommonlib.annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * Created by Joshua on 2020/10/26.
 */
@MyAnnotation(value = "annotation value")
public class AnnotationTest extends AnnotationTestBase {

    /**
     * 通过反射，解析注解
     */
    @Test
    public void testAnnotation() {
        Class<AnnotationTest> annotationTestClass = AnnotationTest.class;

        // getAnnotations 包括从父类继承的Annotation
        Annotation[] annotations = annotationTestClass.getAnnotations();
        System.out.println("AnnotationTest.testAnnotation: annotations = [" + Arrays.toString(annotations) + "]");

        Annotation[] declaredAnnotations = annotationTestClass.getDeclaredAnnotations();
        System.out.println("AnnotationTest.testAnnotation: declaredAnnotations = [" + Arrays.toString(declaredAnnotations) + "]");

        // 判断是否有某一个注解
        boolean isAnnotationPresent = annotationTestClass.isAnnotationPresent(MyAnnotation.class);
        System.out.println("AnnotationTest.testAnnotation: isAnnotationPresent = [" + isAnnotationPresent + "]");
        if (isAnnotationPresent) {
            // 获取指定类型的注解，返回的其实是一个该类注解（注解本质是接口）的实现类
            MyAnnotation myAnnotation = annotationTestClass.getAnnotation(MyAnnotation.class);
            // 调用注解的相关方法，获取对应的值
            String value = myAnnotation.value();
            System.out.println("AnnotationTest.testAnnotation: value = [" + value + "]");
        }
    }

}
