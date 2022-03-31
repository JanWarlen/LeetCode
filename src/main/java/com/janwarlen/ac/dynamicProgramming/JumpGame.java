package com.janwarlen.ac.dynamicProgramming;

public class JumpGame {

    /**
     * 思路一致，节省了一个for循环的计算耗时
     */
    public boolean canJumpPro(int[] nums) {
        int lastInd = nums.length - 1;
        for (int i = lastInd; i >= 0; i--) {
            if (i + nums[i] >= lastInd) {
                lastInd = i;
            }
        }
        return lastInd == 0;
    }

    public static boolean canJump(int[] nums) {
        boolean[] path = new boolean[nums.length];
        path[nums.length - 1] = true;
        for (int i = nums.length - 1; i > -1; i--) {
            for (int j = 0; j <= nums[i] && j + i < nums.length; j++) {
                if (path[i + j]) {
                    path[i] = true;
                    break;
                }
            }
        }
        return path[0];
    }

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));
    }
}
