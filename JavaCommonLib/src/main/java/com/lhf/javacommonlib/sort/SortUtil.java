package com.lhf.javacommonlib.sort;

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
        long start = System.currentTimeMillis();
        int[] outputArray = sort.sort(inputArray);
        long end = System.currentTimeMillis();
//        System.out.println("SortUtil.sort: spend time = [" + (end - start) + "]");
        System.out.println("SortUtil.sort: [" + sort.getClass().getSimpleName() + "] return outputArray = " + Arrays.toString(outputArray));
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
