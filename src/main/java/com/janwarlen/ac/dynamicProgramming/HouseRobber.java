package com.janwarlen.ac.dynamicProgramming;

public class HouseRobber {
    public int rob(int[] nums) {
        if (1 == nums.length) {
            return nums[0];
        }
        if (2 == nums.length) {
            return Math.max(nums[0], nums[1]);
        }
        int[] cur = new int[]{nums[0], Math.max(nums[0], nums[1])};
        for (int i = 2; i < nums.length; i++) {
            int tmp = Math.max(cur[0] + nums[i], cur[1]);
            cur[0] = cur[1];
            cur[1] = tmp;
        }
        return Math.max(cur[0], cur[1]);
    }

    public int robBest(int[] nums) {
        int preMax = 0;
        int curMax = 0;
        for (int i = 0; i < nums.length; i++) {
            int tmp = curMax;
            curMax = Math.max(curMax, preMax + nums[i]);
            preMax = tmp;
        }
        return curMax;
    }
}
