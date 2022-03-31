package com.janwarlen.ac.design;

import java.util.Random;

/**
 * 水塘抽样算法
 */
public class ShuffleAnArray {

    private int[] original;

    private int[] cases;

    private final Random rd = new Random();

    public ShuffleAnArray(int[] nums) {
        this.original = nums;
        cases = new int[nums.length];
        System.arraycopy(original, 0, cases, 0, nums.length);
    }

    public int[] reset() {
        return this.original;
    }

    public int[] shuffle() {
        for (int i = 0; i < cases.length; i++) {
            int swap = i + rd.nextInt(cases.length - i);
            int tmp = cases[i];
            cases[i] = cases[swap];
            cases[swap] = tmp;
        }
        return cases;
    }
}
