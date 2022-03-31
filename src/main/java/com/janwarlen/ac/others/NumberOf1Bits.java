package com.janwarlen.ac.others;

public class NumberOf1Bits {
    public static int hammingWeight(int n) {
        int count = 0;
        String s = Integer.toBinaryString(n);
        for (int i = 0; i < s.length(); i++) {
            if ('1' == s.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 位运算
     */
    public int hammingWeightBest(int n) {
        int count =0; int mask=1;
        while(n!=0){
            if((n&mask)!=0){
                count++;
            }
            n>>>=1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight(-3));
    }
}
