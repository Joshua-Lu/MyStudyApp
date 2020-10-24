package com.lhf.javacommonlib.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Joshua on 2020/10/24 13:13
 */
public class SortTest {

    private SortUtil sortUtil;
    private int[] inputArray;

    @Before
    public void beforeSetSort() {
        inputArray = new int[]{3, 5, 10, 8, 6, 1, 4, 9, 2, 7};
        sortUtil = new SortUtil();
    }

    @After
    public void afterSetSort() {
        sortUtil.sort(inputArray);
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
}
