package com.lhf.javacommonlib.junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 使用JUnit测试
 * Created by Joshua on 2020/10/24 12:33
 */
public class CalculatorTest {

    @Before
    public void doBeforeTest() {
        System.out.println("CalculatorTest.doBeforeTest() called");
    }

    @After
    public void doAfterTest() {
        System.out.println("CalculatorTest.doAfterTest() called");
    }

    @Test
    public void testAdd() {
        System.out.println("CalculatorTest.testAdd() called");
        int result = Calculator.add(1, 2);

        // 可以使用断言对结果进行判断
        Assert.assertEquals(3, result);
    }

    @Test
    public void testSub() {
        System.out.println("CalculatorTest.testSub() called");
        Calculator.sub(1, 2);
    }
}
