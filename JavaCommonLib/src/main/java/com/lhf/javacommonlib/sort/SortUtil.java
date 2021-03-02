package com.lhf.javacommonlib.sort;

import org.junit.Assert;

import java.util.Arrays;

/**
 * Created by Joshua on 2020/4/30.
 */
public class SortUtil {
    ISort sort;

    public SortUtil() {
    }

    public SortUtil(ISort sort) {
        this.sort = sort;
    }

    public void setSort(ISort sort) {
        this.sort = sort;
    }

    public int[] sort(int[] inputArray) {
        System.out.println("SortUtil.sort() called with: inputArray = " + Arrays.toString(inputArray));
        // 复制一份，用系统方法计算
        int[] expected = Arrays.copyOf(inputArray, inputArray.length);
        Arrays.sort(expected);

        // 自己的算法计算
        long start = System.currentTimeMillis();
        int[] outputArray = sort.sort(inputArray);
        long end = System.currentTimeMillis();
//        System.out.println("SortUtil.sort: spend time = [" + (end - start) + "]");
        System.out.println("SortUtil.sort: [" + sort.getClass().getSimpleName() + "] return    expected = " + Arrays.toString(expected));
        System.out.println("SortUtil.sort: [" + sort.getClass().getSimpleName() + "] return outputArray = " + Arrays.toString(outputArray));

        // 检查结果是否正确
        Assert.assertArrayEquals(expected, outputArray);
        return outputArray;
    }

    /**
     * 交换数组两个位置的值
     *
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
    }
}
