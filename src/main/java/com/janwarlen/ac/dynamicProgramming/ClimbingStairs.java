package com.janwarlen.ac.dynamicProgramming;

public class ClimbingStairs {
    public int climbStairs(int n) {
        if (0 == n) {
            return 1;
        }
        if (0 > n) {
            return 0;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public int climbStairsPro(int n) {
        int a = 1, b = 1;
        while (n-- > 0)
            a = (b += a) - a;
        return a;
    }
}
