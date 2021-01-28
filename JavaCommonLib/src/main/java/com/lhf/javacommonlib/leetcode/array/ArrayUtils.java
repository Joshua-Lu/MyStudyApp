package com.lhf.javacommonlib.leetcode.array;

import java.util.Arrays;

/**
 * Created by Joshua on 2021/1/27.
 */
public class ArrayUtils {

    /**
     * 将二维数组转换成矩阵形式输出
     *
     * @param a 二维数组
     * @return 矩阵形式字串
     */
    public static String toMatrixString(int[][] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append("[\n");
        for (int i = 0; ; i++) {
            b.append(Arrays.toString(a[i]));
            if (i == iMax)
                return b.append("\n]").toString();
            b.append(", \n");
        }
    }
}
