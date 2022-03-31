package com.janwarlen.ac.others;

public class ReverseBits {
    public static int reverseBits(int n) {
        if (0 == n) {
            return n;
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res += n & 1;
            n >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(reverseBits(-3));
    }
}
