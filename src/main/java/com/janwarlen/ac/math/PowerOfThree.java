package com.janwarlen.ac.math;

public class PowerOfThree {

    /**
     * 将int转为3进制的字符串，如果是符合规则，则一定是以1开头，后续全部是0到结尾
     */
    public static boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }
        String s = Integer.toString(n, 3);
        if ('1' != s.charAt(0)) {
            return false;
        }
        for (int i = 1; i < s.length(); i++) {
            if ('0' != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPowerOfThreeFast(int n) {
        if (n == 0) {
            // 1/3  2/3
            return false;
        }
        if (n == 1) {
            return true;
        }
        if (n % 3 == 0) {
            return isPowerOfThreeFast(n / 3);
        }
        // 4 5
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(isPowerOfThree(19682));
        System.out.println(isPowerOfThree(36));
    }
}
