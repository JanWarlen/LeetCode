package com.janwarlen.ac.dynamicProgramming;

import java.util.Arrays;

public class CoinChange {

    public int coinChangePro(int[] coins, int amount) {
        final int[] values = new int[amount + 1];
        // 硬币最小也就是1，因此最多不会超过amount
        // 避免使用Integer.MAX_VALUE造成的数值溢出问题，减少了运算量
        Arrays.fill(values, amount + 1);
        values[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; ++i) {
                values[i] = Math.min(1 + values[i - coin], values[i]);
            }
        }
        return values[amount] < amount + 1 ? values[amount] : -1;
    }

    public static int coinChange(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        f[0] = 0;
        for (int i = 1; i < f.length; i++) {
            f[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    f[i] = Math.min(f[i], f[i - coin] == Integer.MAX_VALUE ? f[i - coin] : f[i - coin] + 1);
                }
            }
        }
        if (f[amount] == Integer.MAX_VALUE) {
            f[amount] = -1;
        }
        return f[amount];
    }

    public static void main(String[] args) {
//        System.out.println(coinChange(new int[]{1,2,5}, 11));
        System.out.println(coinChange(new int[]{2}, 3));
    }
}
