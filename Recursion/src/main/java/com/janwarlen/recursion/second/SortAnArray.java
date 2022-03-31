package com.janwarlen.recursion.second;

import java.util.Arrays;

/**
 * Given an array of integers nums, sort the array in ascending order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 104
 * -5 * 104 <= nums[i] <= 5 * 104
 */
public class SortAnArray {

    /**
     * 分治
     */
    public static int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        if (nums.length == 1) {
            return nums;
        }
        // divide
        int flat = nums.length / 2;
        int[] left = sortArray(Arrays.copyOf(nums, flat));
        int[] right = sortArray(Arrays.copyOfRange(nums, flat, nums.length));
        // merge
        return merge(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        // prepare new array to box
        int[] res = new int[left.length + right.length];
        // record cursor
        int leftCur = 0;
        int rightCur = 0;
        int resCur = 0;
        // compare and sort
        while (leftCur < left.length && rightCur < right.length) {
            // compare
            if (left[leftCur] < right[rightCur]) {
                res[resCur++] = left[leftCur++];
            } else {
                res[resCur++] = right[rightCur++];
            }
        }
        // in case left size is not equal right size
        while (leftCur < left.length) {
            res[resCur++] = left[leftCur++];
        }
        while (rightCur < right.length) {
            res[resCur++] = right[rightCur++];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = new int[] {
            5, 1, 1, 2, 0, 0
        } ;
        System.out.println(Arrays.toString(sortArray(test)));
    }
}
