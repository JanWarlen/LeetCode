package com.janwarlen.ac.array;

public class PlusOne {

    public int[] plusOne(int[] digits) {
        int plus = 1;
        for (int i = digits.length - 1; i >= 0 && 1 == plus; i--) {
            if (9 == digits[i]) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                plus = 0;
            }
        }
        if (1 == plus) {
            int[] res = new int[digits.length + 1];
            res[0] = plus;
            return res;
        }
        return digits;
    }

    /**
     * 官网另一种解法
     * 巧妙在于除非数组全是9，否则无法走完for循环
     * 并且，全是9的结局，只需要开头的1即可，数组初始化，每个元素是0，不用再去拷贝
     */
    public static int[] plusOneQ(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] newArray = new int[n + 1];
        newArray[0] = 1;
        return newArray;
    }

    public static void main(String[] args) {
        plusOneQ(new int[]{1,2,9});
    }
}
