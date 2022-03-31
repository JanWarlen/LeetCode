package com.janwarlen.recursion.second;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Largest Rectangle in Histogram
 */
public class LargestRectangleInHistogram {

    /**
     * 核心优化在于找左右边界座标
     * 使用空间换时间
     * 最重要的优化是`p = lessFromLeft[p];`,当左边/右边的高度大于当前高度，则可以直接跳到左边/右边的边界进行再次计算
     * 达到节省重复计算优化时间
     */
    public static int largestRectangleArea_1(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        // idx of the first bar the left that is lower than current
        int[] lessFromLeft = new int[heights.length];
        // idx of the first bar the right that is lower than current
        int[] lessFromRight = new int[heights.length];
        lessFromRight[heights.length - 1] = heights.length;
        lessFromLeft[0] = -1;

        for (int i = 1; i < heights.length; i++) {
            int p = i - 1;

            while (p >= 0 && heights[p] >= heights[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for (int i = heights.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < heights.length && heights[p] >= heights[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return maxArea;
    }

    /**
     * [2, 1, 5, 6, 2, 3]
     * 此方法也是利用空间换时间，不过不同的是此方法将完整的切块进行计算
     * 切换的理论基础是，从左向右遍历，遇到比当前柱高的继续，比左柱低的停止遍历，进行区块计算
     * 停止遍历的理由是，当前柱低于左柱，因此左柱无法再右扩张，并且右侧柱往左侧扩张时存在3种情况
     * - 高于当前柱，则当前柱将会成为左边界
     * - 与当前柱等高，则之前遍历的最低柱将会成为左边界(此情况由数据结构解决如何获取之前遍历的最低柱问题)
     * - 低于当前柱，同上
     *
     * 遍历核心:
     * 遍历时，如果当前块高于前一块，则保存当前块索引
     *
     * 切块后计算逻辑：
     * 此时区域内柱成递增趋势，但因右边界切块是与最高柱进行比对，因此只能从右向左进行计算
     * 计算时，先从保存的数据结构中取出最高索引(队列还是栈都可以，取数符合后出先进即可，方便减少取数时运算)
     * 此时右边界固定(*)
     * 左边界依次往左递减，因区域内时递增，左遍历一定是递减，因此最低柱一定是当前柱，因此宽度直接时固定右边界索引-当前柱索引
     * 重点：如果此时发现左边界低于右边界，则不进行计算，继续重复寻找右边界过程(即保留最低柱)
     * 区域内计算仅计算高于边界部分
     *
     */
    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Deque<Integer> s = new ArrayDeque<>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : heights[i]);
            if (s.isEmpty() || h >= heights[s.peek()]) {
                s.push(i);
            } else {
                int tp = s.pop();
                maxArea = Math.max(maxArea, heights[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                // 找到右边界时，需要此操作确保右边界索引固定
                i--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] ints = {2, 1, 5, 6, 2, 2, 2, 2, 2, 2};
        System.out.println(largestRectangleArea(ints));
    }
}
