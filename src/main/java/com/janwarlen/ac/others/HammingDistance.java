package com.janwarlen.ac.others;

public class HammingDistance {
    public int hammingDistance(int x, int y) {
        int count = 0;
        String s = Integer.toString(x ^ y, 2);
        for (int i = 0; i < s.length(); i++) {
            if ('1' == s.charAt(i)) {
                count++;
            }
        }
        return  count;
    }

    /**
     * 位运算方式
     */
    public int hammingDistanceBest(int x, int y) {
        int count = 0;
        int result = x ^ y;
        for (int i = 0; i < 32; i++) {
            count = count + (result & 1);
            result = result >>> 1;
        }

        return count;
    }
}
