package com.janwarlen.ac.math;

// Divide Two Integers
public class DivideTwoIntegers {

    // without using multiplication, division, and mod operator.

    /**
     * 利用位运算替代乘法，解题思路算是二分
     * divisor 乘2直到比 dividend 大，res加上2的此时次数-1方，然后在 dividend 中减去该部分再循环，直到二者无差距或 divisor 大于 dividend
     */
    public int divide(int dividend, int divisor) {
        if (dividend == 1 << 31 && divisor == -1) return (1 << 31) - 1;
        int a = Math.abs(dividend), b = Math.abs(divisor), res = 0;
        while (a - b >= 0) {
            int x = 0;
            for (; a - (b << x << 1) >= 0; x++) ;
            res += 1 << x;
            a -= b << x;
        }
        return (dividend > 0) == (divisor > 0) ? res : -res;
    }
}
