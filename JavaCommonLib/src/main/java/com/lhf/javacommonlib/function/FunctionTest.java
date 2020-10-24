package com.lhf.javacommonlib.function;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 函数式接口的使用
 * Created by Joshua on 2020/10/23 21:03
 */
public class FunctionTest {

    @Test
    public void testSupplier() {
        String s = testSupplier(() -> "Supplier");
        System.out.println("FunctionTest.main: s = [" + s + "]");
    }

    @Test
    public void testConsumer() {
        // 一次处理
        testConsumer("Consumer", data ->
                // 可以对data进行一些处理
                System.out.println("FunctionTest.main: data.toUpperCase() = [" + data.toUpperCase() + "]")
        );

        // 两次处理
        String[] strings = {"路飞，男", "索隆，男", "娜美，女"};
        testConsumer(strings, data -> {
            // 可以对data进行一些处理
            String[] split = data.split("，");
            System.out.print("姓名：" + split[0]);
        }, data -> {
            // 可以对data进行一些处理
            String[] split = data.split("，");
            System.out.println(" 性别：" + split[1]);
        });
    }

    @Test
    public void testPredicate() {
        testPredicate("acd", s -> s.length() > 4, s -> s.contains("ab"));
    }

    @Test
    public void testFunction() {
        // 一次转换
        testFunction(10, integer -> String.valueOf(integer));
        // 可以简写为直接传到方法String::valueOf
        testFunction(10, String::valueOf);

        // 两次转换
        testFunction(100, integer -> String.valueOf(integer), s -> Integer.valueOf(s + s));
    }

    @Test
    public void testBiFunction() {
        testBiFunction(10, 'a', (integer, character) -> "String: " + (char) (integer + character));
    }

    /**
     * 对两个数据类型进行转换成一个数据类型
     * Function的升级版：参数是两个
     *
     * @param data1      需要转换的数据1
     * @param data2      需要转换的数据2
     * @param biFunction 转换方式
     * @return 转换后的结果
     */
    public String testBiFunction(int data1, char data2, BiFunction<Integer, Character, String> biFunction) {
        String result = biFunction.apply(data1, data2);
        System.out.println("FunctionTest.testBiFunction: result = [" + result + "]");
        return result;
    }

    /**
     * 对数据类型进行转换（使用两次转换）
     *
     * @param data      需要转换的数据
     * @param function1 转换方式1
     * @param function2 转换方式2
     * @return 两次转换后的结果
     */
    public Integer testFunction(Integer data, Function<Integer, String> function1, Function<String, Integer> function2) {
        Integer result = function1.andThen(function2).apply(data);
        System.out.println("FunctionTest.testFunction: function1.andThen(function2).apply(data) = [" + result + "]");
        // 相当于以下代码 compose与andThen执行顺序相反
        result = function2.compose(function1).apply(data);
        System.out.println("FunctionTest.testFunction: function2.compose(function1).apply(data) = [" + result + "]");
        return result;
    }

    /**
     * 对数据类型进行转换
     *
     * @param data     需要转换的数据
     * @param function 转换方式
     * @return 转换后的结果
     */
    public String testFunction(Integer data, Function<Integer, String> function) {
        String result = function.apply(data);
        System.out.println("FunctionTest.testFunction: result = [" + result + "]");
        return result;
    }

    /**
     * 对某一类型对象判断真假
     *
     * @param data       需要判断的数据
     * @param predicate1 判断条件1
     * @param predicate2 判断条件2
     * @return 判断结果
     */
    public boolean testPredicate(String data, Predicate<String> predicate1, Predicate<String> predicate2) {
        System.out.println("FunctionTest.testPredicate: predicate1.test(data) = [" + predicate1.test(data) + "]");
        System.out.println("FunctionTest.testPredicate: predicate2.test(data) = [" + predicate2.test(data) + "]");
        // negate：非运算！
        System.out.println("negate：非运算！");
        System.out.println("FunctionTest.testPredicate: predicate1.negate().test(data) = [" + predicate1.negate().test(data) + "]");
        System.out.println("FunctionTest.testPredicate: !predicate1.test(data) = [" + !predicate1.test(data) + "]");
        // and：与运算&&
        System.out.println("and：与运算&&");
        System.out.println("FunctionTest.testPredicate: predicate1.and(predicate2).test(data) = [" + predicate1.and(predicate2).test(data) + "]");
        System.out.println("FunctionTest.testPredicate: (predicate1.test(data) && predicate2.test(data)) = [" + (predicate1.test(data) && predicate2.test(data)) + "]");
        // or：或运算||
        System.out.println("or：或运算||");
        System.out.println("FunctionTest.testPredicate: predicate1.or(predicate2).test(data) = [" + predicate1.or(predicate2).test(data) + "]");
        System.out.println("FunctionTest.testPredicate: (predicate1.test(data) || predicate2.test(data)) = [" + (predicate1.test(data) || predicate2.test(data)) + "]");
        return predicate1.and(predicate2).test(data);
    }

    /**
     * 消费某一类型的对象
     *
     * @param data      要消费的对象
     * @param consumer1 消费的方式1
     * @param consumer2 消费的方式2
     */
    public void testConsumer(String[] data, Consumer<String> consumer1, Consumer<String> consumer2) {
        for (String item : data) {
            consumer1.andThen(consumer2).accept(item);
            // 相当于以下两行
//        consumer1.accept(data);
//        consumer2.accept(data);
        }
    }

    /**
     * 消费某一类型的对象
     *
     * @param data     要消费的对象
     * @param consumer 消费的方式
     */
    public void testConsumer(String data, Consumer<String> consumer) {
        consumer.accept(data);
    }

    /**
     * 获取指定类型的对象
     * 注：个人感觉跟直接传对象，没什么区别，但这也是一种函数式编程
     *
     * @param supplier Supplier接口的实现
     * @return supplier的泛型指定类型的对象
     */
    public String testSupplier(Supplier<String> supplier) {
        return supplier.get();
    }
}
