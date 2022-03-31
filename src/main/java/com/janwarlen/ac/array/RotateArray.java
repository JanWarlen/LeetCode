package com.janwarlen.ac.array;

public class RotateArray {

    public void rotate_1(int[] nums, int k) {
        k=k%nums.length;
        if (0 == k) {
            return;
        }
        int[] tmp = new int[nums.length];
        for (int i = 0; i < k; i++) {
            tmp[i] = nums[nums.length - k + i];
        }
        for (int i = k; i < nums.length; i++) {
            tmp[i] = nums[i - k];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = tmp[i];
        }
    }

    /**
     * 时间未优化，优化了空间
     */
    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (0 == k) {
            return;
        }
        int[] tmp = new int[k];
        for (int i = 0; i < k; i++) {
            tmp[i] = nums[nums.length - k + i];
        }
        int i = 0;
        while (i < nums.length) {
            int j = 0;
            for (; j < k && i < nums.length; j++) {
                int ttt = tmp[j];
                tmp[j] = nums[i];
                nums[i++] = ttt;
            }
        }
    }

    public static void main(String[] args) {
        rotate(new int[]{1,2,3,4,5,6,7}, 3);
    }
}
