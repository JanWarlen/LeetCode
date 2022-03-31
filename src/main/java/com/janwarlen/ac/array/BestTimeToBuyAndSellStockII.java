package com.janwarlen.ac.array;

public class BestTimeToBuyAndSellStockII {

    /**
     * 一开始想用回溯去迭代解，暴力的每层迭代判断是否买入和是否卖出，然后再各种情况中取最大值
     * 然后简单的回想了下，只要考虑赚钱的部分，下跌行情不考虑就可以了，所以就是个累计盈利的问题
     */
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - prices[i - 1];
            if (profit > 0) {
                max += profit;
            }
        }
        return max;
    }

    public static void main(String[] args) {

    }
}
