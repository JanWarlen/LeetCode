package com.janwarlen.ac.others;

import java.util.Arrays;

// Majority Element
public class MajorityElement {

    /**
     * 因题限定元素会超过n/2个，因此排序后的n/2处一定是最多元素
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * Boyer-Moore Voting Algorithm
     */
    public int majorityElementPro(int[] nums) {
        int ele = nums[0];
        int max = 1;

        for (int i = 1; i < nums.length; i++) {
            if (ele == nums[i]) {
                max++;
            } else {
                max--;
                if (0 == max) {
                    ele = nums[++i];
                    max = 1;
                } else if (max > nums.length - i) {
                    return ele;
                }
            }
        }
        return ele;
    }

    public static void main(String[] args) {
        MajorityElement test = new MajorityElement();
        System.out.println(test.majorityElementPro(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

}
