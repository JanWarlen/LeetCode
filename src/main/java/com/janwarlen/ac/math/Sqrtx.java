package com.janwarlen.ac.math;

// Sqrt(x)
public class Sqrtx {

    /**
     * 因为部分数平方后会溢出，因此判断不用平方和x对比
     * 思路都是二分查找
     * 2ms
     */
    public int sqrt(int x) {
        if (x == 0)
            return 0;
        int left = 1, right = Integer.MAX_VALUE;
        while (true) {
            int mid = left + (right - left) / 2;
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }

    // 1 ms
    public int mySqrt1(int x) {
        int l = 1, r = 46440;
        while (l != r) {
            int tmp = (l + r) / 2;
            int tar = tmp * tmp;
            if (tar < 0) {
                r = tmp;
                continue;
            }
            if (tar < x) {
                l = tmp + 1;
            } else if (tar > x) {
                r = tmp;
            } else {
                return tmp;
            }
        }
        return l - 1;
    }

    /**
     * 投机取巧了，将边界提前确立
     * 3ms
     */
    public int mySqrt(int x) {
        int l = 1, r = 46440;
        while (l != r) {
            int tmp = (l + r) / 2;
            int tar = x / tmp;
            if (tmp < tar) {
                l = tmp + 1;
            } else if (tmp > tar) {
                r = tmp;
            } else {
                return tmp;
            }
        }
        return l - 1;
    }

    public static void main(String[] args) {
        Sqrtx test = new Sqrtx();
        System.out.println(test.mySqrt(4));
        System.out.println(test.mySqrt(8));
        System.out.println(test.mySqrt(16));
        System.out.println(test.mySqrt(225));
        // 46339
        System.out.println(test.mySqrt(2147395599));
    }
}
