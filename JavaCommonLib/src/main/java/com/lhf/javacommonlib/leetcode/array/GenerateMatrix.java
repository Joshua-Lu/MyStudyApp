package com.lhf.javacommonlib.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/1/26.
 */
public class GenerateMatrix {
    /*
    https://leetcode-cn.com/problems/spiral-matrix-ii/
    59. 螺旋矩阵 II
    给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

    示例:
        输入: 3
        输出:
            [
            [ 1, 2, 3 ],
            [ 8, 9, 4 ],
            [ 7, 6, 5 ]
            ]
    */

    @Test
    public void test() {
        int n;
        int[][] result;
        int[][] expected;

        // 示例 1
        n = 3;
        expected = new int[][]{
                {1, 2, 3},
                {8, 9, 4},
                {7, 6, 5}};
        result = generateMatrix(n);
        Assert.assertArrayEquals(expected, result);

        // 示例 2
        n = 4;
        expected = new int[][]{
                {1, 2, 3, 4},
                {12, 13, 14, 5},
                {11, 16, 15, 6},
                {10, 9, 8, 7}};
        result = generateMatrix(n);
        Assert.assertArrayEquals(expected, result);


    }

    public int[][] generateMatrix(int n) {
//        return generateMatrix1(n);
        return generateMatrix2(n);
    }

    /**
     * 思路：从最外圈开始顺时针遍历，每个方向上都遍历到头，遍历完收缩边界，调整位置，继续另一个方向遍历
     * <p>
     * 时间：O(n)
     * 空间：O(n^2)
     */
    private int[][] generateMatrix1(int n) {
        System.out.println("GenerateMatrix.generateMatrix() called with: n = [" + n + "]");
        // 边界
        int left = -1;
        int top = -1;
        int right = n;
        int bottom = n;
        // 坐标
        int x = 0;
        int y = 0;
        // 循环次数
        int loop = n / 2;
        // n为奇数时，最后一圈是个点，特殊处理
        // （不用这个变量，在循环里判断num==n^2时，直接返回结果也行，但是这样要在【从左往右】和【从下往上】，两个循环里加判断）
        boolean hasCenterPoint = n % 2 == 1;
        // 填充的值
        int num = 1;
        int[][] result = new int[n][n];
        for (int i = 0; i < loop; i++) {
            // 从左往右
            while (y < right) {
                result[x][y++] = num++;
            }
            y--;// y多加了1，要减掉
            top++;// 上面一行遍历完，则边界top要加1
            x++;// x加1，移到下一行
            // 从上往下
            while (x < bottom) {
                result[x++][y] = num++;
            }
            x--;
            right--;
            y--;
            // 从右往左
            while (y > left) {
                result[x][y--] = num++;
            }
            y++;
            bottom--;
            x--;
            // 从下往上
            while (x > top) {
                result[x--][y] = num++;
            }
            x++;
            left++;
            y++;
        }
        if (hasCenterPoint) {
            result[x][y] = num;// 最后中间的位置
        }
        System.out.println("GenerateMatrix.generateMatrix() returned: \n" + ArrayUtils.toMatrixString(result));
        return result;
    }

    /**
     * 思路：从最外圈开始顺时针遍历，每个方向上都不遍历到头，剩一个，这样可以保证同一圈的每条边，遍历长度相同，
     * 相比于{@link #generateMatrix1(int)}（同一圈的每条边，遍历长度不同），
     * 这样能保证各个方向上的遍历规则一致，方便逻辑控制。
     * <p>
     * 时间：O(n)
     * 空间：O(n^2)
     */
    private int[][] generateMatrix2(int n) {
        System.out.println("GenerateMatrix.generateMatrix() called with: n = [" + n + "]");
        // 每条边上的遍历长度，最外圈 n -1
        int offset = n - 1;
        // 坐标
        int x = 0;
        int y = 0;
        // 循环次数
        int loop = n / 2;
        // n为奇数时，最后一圈是个点，特殊处理
        // （不用这个变量，在循环里判断num==n^2时，直接返回结果也行，但是这样要在【从左往右】和【从下往上】，两个循环里加判断）
        boolean hasCenterPoint = n % 2 == 1;
        // 填充的值
        int num = 1;
        int[][] result = new int[n][n];
        while (loop > 0) {
            // 从左往右
            for (int i = 0; i < offset; i++) {
                result[x][y++] = num++;
            }
            // 从上往下
            for (int i = 0; i < offset; i++) {
                result[x++][y] = num++;
            }
            // 从右往左
            for (int i = 0; i < offset; i++) {
                result[x][y--] = num++;
            }
            // 从下往上
            for (int i = 0; i < offset; i++) {
                result[x--][y] = num++;
            }
            // 坐标移到下一圈的开始位置
            x++;
            y++;
            offset -= 2;// 下一圈的每条边遍历长度是要减2
            loop--;
        }
        if (hasCenterPoint) {
            result[x][y] = num;// 最后中间的位置
        }
        System.out.println("GenerateMatrix.generateMatrix() returned: \n" + ArrayUtils.toMatrixString(result));
        return result;
    }
}
