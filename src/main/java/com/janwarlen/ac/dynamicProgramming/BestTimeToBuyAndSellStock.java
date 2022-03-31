package com.janwarlen.ac.dynamicProgramming;

public class BestTimeToBuyAndSellStock {
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int buy = 0;
        int sell = 0;
        for (; sell < prices.length; sell++) {
            //当前价格更小了，更新 buy
            if (prices[sell] < prices[buy]) {
                buy = sell;
            } else {
                maxProfit = Math.max(maxProfit, prices[sell] - prices[buy]);

            }
        }
        return maxProfit;
    }

    /**
     * Kadane's Algorithm
     */
    public static int maxProfitPro(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        maxProfit(new int[]{7, 3, 5, 1, 6, 4});
    }
}
