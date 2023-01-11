package com.lhf.javacommonlib.interview.hwod;

import java.util.Arrays;

/**
 * 华为OD机试2
 * <p>
 * 输入：
 * int m = 3;
 * int n = 5;
 * int[][] array = new int[][]{
 * {0, 3, 5, 4, 2},
 * {2, 5, 7, 8, 3},
 * {2, 5, 4, 2, 4},
 * };
 * <p>
 * 输出：
 * [[-1, 4, 2, 3, 3], [1, 1, -1, -1, 4], [1, 1, 2, 3, 2]]
 * <p>
 * 找到相等的值的最近距离（距离 = x距离 + y距离），没有相等的输出 -1
 *
 * @author Joshua
 * @date 2022/12/13 14:42
 */
public class Main2 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            int m = sc.nextInt();
//            int n = sc.nextInt();
//            // 初始化
//            int[][] array = new int[m][n];
//            for (int i = 0; i < m; i++) {
//                for (int j = 0; j < n; j++) {
//                    array[i][j] = sc.nextInt();
//                }
//            }

        int m = 3;
        int n = 5;
        int[][] array = new int[][]{
                {0, 3, 5, 4, 2},
                {2, 5, 7, 8, 3},
                {2, 5, 4, 2, 4},
        };

        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = -1;
            }
        }
        // 遍历
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int target = array[i][j];
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < m; k++) {
                    for (int l = 0; l < n; l++) {
                        // 跳过相同位置的数
                        if (k == i && l == j) {
                            continue;
                        }
                        if (array[k][l] == target) {
                            int dis = Math.abs(k - i) + Math.abs(l - j);
                            min = Math.min(dis, min);
                        }
                    }
                }
                if (min != Integer.MAX_VALUE) {
                    res[i][j] = min;
                }

            }
        }
        System.out.println(Arrays.deepToString(res));
//        }
    }
}
