package com.janwarlen.recursion.first;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Memoization {

    public static void main(String[] args) {
        System.out.println(climbStairs(44));
    }

    // Fibonacci Number
    HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();

    public int fib(int N) {
        if (cache.containsKey(N)) {
            return cache.get(N);
        }
        int result;
        if (N < 2) {
            result = N;
        } else {
            result = fib(N - 1) + fib(N - 2);
        }
        // keep the result in cache.
        cache.put(N, result);
        return result;
    }

    // Climbing Stairs
    /**
     * You are climbing a stair case. It takes n steps to reach to the top.
     *
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     *
     * Note: Given n will be a positive integer.
     */
    /**
     * Input: 2
     * Output: 2
     * Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     */
    /**
     * 利用统计学
     * t=n/2为2的最大个数，则
     * Ct(n-t) + C(t-1)(n-t-1) + ... + C1(n-1) + 1
     */
    // 阶乘值过大，影响计算结果，需要优化阶乘计算部分
    /*public static int climbStairs(int n) {
        if (n < 4) {
            return n;
        }
        int times = n / 2;
        int sum = 0;
        for (int i = 1; i <= times; i++) {
            sum += calculateFactorial(n - i) / (calculateFactorial(i) * calculateFactorial(n - 2 * i));
        }
        return sum + 1;
    }

    private static HashMap<Integer, Integer> factorialMap = new HashMap<>();

    public static int calculateFactorial(int n) {
        Integer factorial = factorialMap.get(n);
        if (null != factorial) {
            return factorial;
        } else {
            factorial = 1;
        }
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        factorialMap.put(n, factorial);
        return factorial;
    }*/
    public static int climbStairs(int n) {
        if (n < 4) {
            return n;
        }
        int times = n / 2;
        int sum = 0;
        for (int i = 1; i <= times; i++) {
            sum += calculate(i, n - i);
        }
        return sum + 1;
    }

    /**
     * m!/(n!·(m-n)!)
     * Long是因为过程计算中依然存在超标情况，主要是由于约去min时，有部分无法约除
     */
    private static Long calculate(int n, int m) {
        int tmp = m - n;
        int max = Math.max(tmp, n);
        int min = Math.min(tmp, n);
        List<Integer> eleList = new ArrayList<>();
        List<Integer> dividList = new ArrayList<>();
        for (int j = m; j > max; j--) {
            eleList.add(j);
        }
        for (int i = 1; i <= min; i++) {
            boolean moved = false;
            for (int j = 0; j < eleList.size(); j++) {
                if (i <= eleList.get(j) && (eleList.get(j) % i) == 0) {
                    eleList.set(j, eleList.get(j) / i);
                    moved = true;
                    break;
                }
            }
            if (!moved) {
                dividList.add(i);
            }
        }
        Long res = 1L;
        for (Integer ele : eleList) {
            res *= ele;
        }
        for (Integer divid : dividList) {
            res /= divid;
        }
        return res;
    }
    /**
     * 官方解法:https://leetcode.com/explore/learn/card/recursion-i/255/recursion-memoization/1662/
     * 一共有六种
     * 选取第一种作为示范对比，此种解法是利用递归进行攀登阶梯，穷举进行+1操作
     * 可以理解为二叉树的做法
     */
    public int climbStairs_offical(int n) {
        return climb_Stairs(0, n);
    }
    public int climb_Stairs(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
    }


}
