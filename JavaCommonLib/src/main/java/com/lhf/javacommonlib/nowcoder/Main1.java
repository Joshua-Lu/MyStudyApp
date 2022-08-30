package com.lhf.javacommonlib.nowcoder;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[4];
        for (int i = 0; i < 4; i++) {
            nums[i] = sc.nextInt();
        }
        int[] visit = new int[4];
        System.out.println(dfs(nums, visit, 0));
    }

    public static boolean dfs(int[] nums, int[] visit, int sum) {
        for (int i = 0; i < nums.length; i++) {
            if (visit[i] == 1) {
                continue;
            }
            visit[i] = 1;
            if (dfs(nums, visit, sum + nums[i])) {
                System.out.println(sum + "+" + nums[i]);
                return true;
            }
            if (dfs(nums, visit, sum - nums[i])) {
                System.out.println(sum + "-" + nums[i]);
                return true;
            }
            if (dfs(nums, visit, sum * nums[i])) {
                System.out.println(sum + "*" + nums[i]);
                return true;
            }
            if (dfs(nums, visit, sum / nums[i])) {
                System.out.println(sum + "/" + nums[i]);
                return true;
            }
            visit[i] = 0;
        }
        System.out.println(sum);
        if (sum == 24) {
            return true;
        }
        return false;
    }
}