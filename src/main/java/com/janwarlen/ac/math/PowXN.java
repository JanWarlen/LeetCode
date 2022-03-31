package com.janwarlen.ac.math;

//  Pow(x, n)
public class PowXN {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n > 0) {
            // 此处是减少stack overflow的核心，直接减少一半以上stack使用,如果直接递归，会报错
            return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
        } else {
            return 1 / x * myPow(1 / x, -(n + 1));
        }
    }

    public static void main(String[] args) {
        PowXN test = new PowXN();
        System.out.println(test.myPow(2, 10));
        System.out.println(test.myPow(2, -1));
    }
}
