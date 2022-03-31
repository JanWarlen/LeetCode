package com.janwarlen.ac.dynamicProgramming;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    /**
     * 通过dp来记录目前的最长递增子序列
     * 当遇到元素不递增时，在保存的已有dp中判断是否存在
     * 此时使用的是Arrays.binarySearch，当元素不存在时，返回的是第一个大于元素下标+1
     * 然后用当前元素(更小值)替换temp(第一个大于当前元素)，此举是为了保证后续的递增序列可被识别
     */
    public static int lengthOfLISPro(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int index = 0;
        int ans = 1;
        int temp;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > dp[index]) {
                dp[++index] = nums[i];
                ans++;
            } else {
                temp = Arrays.binarySearch(dp, 0, index, nums[i]);
                if (temp < 0) {
                    temp = -temp - 1;
                }
                dp[temp] = nums[i];
            }
        }
        return ans;
    }

    public static int lengthOfLIS(int[] nums) {
        int res = 1;
        int[] path = new int[nums.length];
        path[path.length - 1] = 1;
        for (int i = nums.length - 2; i > -1; i--) {
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[i] < nums[i + j]) {
                    path[i] = Math.max(path[i], path[i + j] + 1);
                } else {
                    path[i] = Math.max(path[i], 1);
                }
                if (path[i] > res) {
                    res = path[i];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(lengthOfLISPro(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));//4
//        System.out.println(lengthOfLIS(new int[]{1}));//1
//        System.out.println(lengthOfLISPro(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));//6
        System.out.println(lengthOfLISPro(new int[]{1, 3, 6, 2, 4, 6, 7, 5, 6}));//5
    }
}
