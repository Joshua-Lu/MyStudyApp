package com.lhf.javacommonlib.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 反射方式获取类的变量、构造方法、成员方法等
 * Created by Joshua on 2020/10/26.
 */
public class ReflectTest {

    /**
     * 反射方式获取变量，修改、获取变量值
     */
    @Test
    public void testFieldReflect() {
        Class<ReflectClass> reflectClass = ReflectClass.class;
        ReflectClass reflectClassObj = null;
        try {
            reflectClassObj = reflectClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // getFields 和 getField 只能获取到 public 的变量
        Field[] fields = reflectClass.getFields();
        System.out.println("ReflectTest.testFieldReflect: fields = [" + Arrays.toString(fields) + "]");
        // getDeclaredFields 和 getDeclaredField 可以获取到所有定义的变量，包括public private 和 protected
        Field[] declaredFields = reflectClass.getDeclaredFields();
        System.out.println("ReflectTest.testFieldReflect: declaredFields = [" + Arrays.toString(declaredFields) + "]");
        System.out.println("===============================");

        try {
            // 获取public对象
            Field publicField = reflectClass.getField("publicField");
            System.out.println("ReflectTest.testFieldReflect: publicField = [" + publicField + "]");
            // 修改、获取 public 变量
            publicField.set(reflectClassObj, "publicFieldValue");
            Object publicFieldValue = publicField.get(reflectClassObj);
            System.out.println("ReflectTest.testFieldReflect: publicFieldValue = [" + publicFieldValue + "]");

            // 该变量不是public或没有该变量时，就会报 NoSuchFieldException 异常
//            Field privateField = reflectClass.getField("privateField");
//            System.out.println("ReflectTest.testFieldReflect: privateField = [" + privateField + "]");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("===============================");

        try {
            Field declaredPrivateField = reflectClass.getDeclaredField("privateField");
            System.out.println("ReflectTest.testFieldReflect: declaredPrivateField = [" + declaredPrivateField + "]");
            // 修改、获取 private 变量前，要 setAccessible 为 true，否则会报 IllegalAccessException
            declaredPrivateField.setAccessible(true);
            declaredPrivateField.set(reflectClassObj, "privateFieldValue");
            Object privateFieldValue = declaredPrivateField.get(reflectClassObj);
            System.out.println("ReflectTest.testFieldReflect: privateFieldValue = [" + privateFieldValue + "]");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射方式获取成员方法，调用方法
     */
    @Test
    public void testMethodReflect() {
        Class<ReflectClass> reflectClass = ReflectClass.class;
        ReflectClass reflectClassObj = null;
        try {
            reflectClassObj = reflectClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Method[] methods = reflectClass.getMethods();
        System.out.println("ReflectTest.testMethodReflect: methods = [" + Arrays.toString(methods) + "]");
        Method[] declaredMethods = reflectClass.getDeclaredMethods();
        System.out.println("ReflectTest.testMethodReflect: declaredMethods = [" + Arrays.toString(declaredMethods) + "]");
        try {
            Method setPrivateField = reflectClass.getMethod("setPrivateField", String.class);
            System.out.println("ReflectTest.testMethodReflect: setPrivateField = [" + setPrivateField + "]");
            setPrivateField.invoke(reflectClassObj, "aaa");

            Method privateMethod = reflectClass.getDeclaredMethod("privateMethod");
            System.out.println("ReflectTest.testMethodReflect: privateMethod = [" + privateMethod + "]");
            privateMethod.setAccessible(true);
            privateMethod.invoke(reflectClassObj);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射方式获取构造方法，创建对象
     */
    @Test
    public void testConstructorReflect() {
        Class<ReflectClass> reflectClass = ReflectClass.class;

        Constructor<?>[] constructors = reflectClass.getConstructors();
        System.out.println("ReflectTest.testConstructorReflect: constructors = [" + Arrays.toString(constructors) + "]");
        try {
            // 获取无参public构造方法
            Constructor<ReflectClass> constructor = reflectClass.getConstructor();
            System.out.println("ReflectTest.testConstructorReflect: constructor = [" + constructor + "]");
            // 获取指定参数类型的public构造方法
            Constructor<ReflectClass> constructor1 = reflectClass.getConstructor(String.class, String.class, String.class, String.class);
            System.out.println("ReflectTest.testConstructorReflect: constructor1 = [" + constructor1 + "]");
            System.out.println("===============================");
            // 创建对象
            try {
                ReflectClass reflectClass1 = constructor.newInstance();
                System.out.println("ReflectTest.testConstructorReflect: reflectClass1 = [" + reflectClass1 + "]");
                // 想调用无参的构造方法，可直接使用 Class 对象的 newInstance
                ReflectClass reflectClass2 = reflectClass.newInstance();
                System.out.println("ReflectTest.testConstructorReflect: reflectClass2 = [" + reflectClass2 + "]");
//                constructor1.setAccessible(true);// 如果构造方法非public，需要setAccessible(true)
                ReflectClass reflectClass3 = constructor1.newInstance("1", "2", "3", "4");
                System.out.println("ReflectTest.testConstructorReflect: reflectClass3 = [" + reflectClass3 + "]");
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // getDeclaredConstructors() 同 getConstructors() ，区别在前者不止能获取到 public 的构造方法
        // getDeclaredConstructor(Class<?>... parameterTypes) 同 getConstructor(Class<?>... parameterTypes) ，区别在前者不止能获取到 public 的构造方法
    }
}
