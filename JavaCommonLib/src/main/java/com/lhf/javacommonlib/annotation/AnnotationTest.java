package com.lhf.javacommonlib.annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * Created by Joshua on 2020/10/26.
 */
@MyAnnotation(value = "annotation value")
public class AnnotationTest {

    @Test
    public void testAnnotation() {
        Class<AnnotationTest> annotationTestClass = AnnotationTest.class;
        Annotation[] annotations = annotationTestClass.getAnnotations();
        System.out.println("AnnotationTest.testAnnotation: annotations = [" + Arrays.toString(annotations) + "]");
        // getDeclaredAnnotations 包括从父类继承的Annotation
        Annotation[] declaredAnnotations = annotationTestClass.getDeclaredAnnotations();
        System.out.println("AnnotationTest.testAnnotation: declaredAnnotations = [" + Arrays.toString(declaredAnnotations) + "]");
        boolean isAnnotationPresent = annotationTestClass.isAnnotationPresent(MyAnnotation.class);
        System.out.println("AnnotationTest.testAnnotation: isAnnotationPresent = [" + isAnnotationPresent + "]");
        if (isAnnotationPresent) {
            MyAnnotation myAnnotation = annotationTestClass.getAnnotation(MyAnnotation.class);
            String value = myAnnotation.value();
            System.out.println("AnnotationTest.testAnnotation: value = [" + value + "]");
        }

    }

}
