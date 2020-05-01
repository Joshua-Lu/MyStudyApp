package com.lhf.javacommonlib;

import com.lhf.javacommonlib.sort.BubbleSort;
import com.lhf.javacommonlib.sort.HeapSort;
import com.lhf.javacommonlib.sort.InsertionSort;
import com.lhf.javacommonlib.sort.MergeSort;
import com.lhf.javacommonlib.sort.QuickSort;
import com.lhf.javacommonlib.sort.SelectionSort;
import com.lhf.javacommonlib.sort.ShellSort;
import com.lhf.javacommonlib.sort.SortUtil;

public class MyClass {
    public static void main(String[] args) {
        System.out.println("---------------- MyClass.main ---------------");
        // 测试排序算法
//        testSort();
    }

    private static void testSort() {
        int[] inputArray = new int[]{3, 5, 10, 8, 6, 1, 4, 9, 2, 7};
        SortUtil sortUtil = new SortUtil(new BubbleSort());//冒泡排序
        sortUtil.setSort(new SelectionSort());// 选择排序
        sortUtil.setSort(new InsertionSort());// 插入排序
        sortUtil.setSort(new ShellSort());// 希尔排序
        sortUtil.setSort(new MergeSort());// 归并排序
        sortUtil.setSort(new QuickSort());// 快速排序
        sortUtil.setSort(new HeapSort());// 堆排序
        sortUtil.sort(inputArray);
    }
}
