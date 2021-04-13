package com.lhf.javacommonlib.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Created by Joshua on 2020/10/24 13:13
 */
public class SortTest {

    private SortUtil sortUtil;
    private int[] inputArray;
    public static boolean randomTest = true;
    private static final int TEST_COUNT = 1000;
    private static final int ARRAY_LENGTH_MAX = 20;
    public static final int ARRAY_VALUE_MAX = 100;

    @Before
    public void beforeSetSort() {
        inputArray = new int[]{3, 5, 10, 8, 6, 1, 4, 9, 2, 7};
//        inputArray = new int[]{3, 5, 10, 8, 6, 1, 4, 9, 2, 7, 0};
//        inputArray = new int[]{3, 5, 10, 8, 6, 1, 4, 9, 2, 7, 0, 10};
//        inputArray = new int[]{3, 5, 10, 8, 6, 1, 4, 9, 2, 7, 0, 10, 6};
//        inputArray = new int[]{6, 4};
//        inputArray = new int[]{27, 55, 92, 84};
        sortUtil = new SortUtil();
    }

    @After
    public void afterSetSort() {
        sortUtil.sort(inputArray);
        if (randomTest) {
            Random random = new Random();
            for (int i = 0; i < TEST_COUNT; i++) {
                System.out.println("SortTest.afterSetSort: random test count ==========> [" + (i + 1) + "]");
                int length = random.nextInt(ARRAY_LENGTH_MAX);
                inputArray = new int[length];
                for (int j = 0; j < length; j++) {
                    inputArray[j] = random.nextInt(ARRAY_VALUE_MAX);
                }
                sortUtil.sort(inputArray);
            }
        }
    }

    @Test
    public void testBubbleSort() {
        sortUtil.setSort(new BubbleSort());//冒泡排序
    }

    @Test
    public void testSelectionSort() {
        sortUtil.setSort(new SelectionSort());// 选择排序
    }

    @Test
    public void testInsertionSort() {
        sortUtil.setSort(new InsertionSort());// 插入排序
    }

    @Test
    public void testShellSort() {
        sortUtil.setSort(new ShellSort());// 希尔排序
    }

    @Test
    public void testMergeSort() {
        sortUtil.setSort(new MergeSort());// 归并排序
    }

    @Test
    public void testQuickSort() {
        sortUtil.setSort(new QuickSort());// 快速排序
    }

    @Test
    public void testHeapSort() {
        sortUtil.setSort(new HeapSort());// 堆排序
    }

    @Test
    public void testCountSort() {
        sortUtil.setSort(new CountSort());// 计数排序
    }

    @Test
    public void testMySort() {
        sortUtil.setSort(new QuickSort1());
    }
}
