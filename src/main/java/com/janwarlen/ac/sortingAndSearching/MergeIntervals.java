package com.janwarlen.ac.sortingAndSearching;

import java.util.*;

public class MergeIntervals {

    /**
     * 官方解法
     * 思路基本一直，但是代码简洁，思路清晰
     */
    public int[][] mergePro(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        Map<Integer, Integer> merge = new HashMap<>();
        int start = intervals[0][0], end = intervals[0][1];
        boolean flag = false;
        for (int i = 0; i < intervals.length - 1; i++) {
            if (end >= intervals[i + 1][0]) {
                start = Math.min(start, intervals[i + 1][0]);
                end = Math.max(end, intervals[i + 1][1]);
                flag = true;
            } else {
                if (flag) {
                    flag = false;
                    merge.put(start, end);
                } else {
                    merge.put(intervals[i][0], intervals[i][1]);
                }
                start = intervals[i + 1][0];
                end = intervals[i + 1][1];
            }
        }
        if (null == merge.get(start)) {
            merge.put(start, end);
        } else {
            merge.put(intervals[intervals.length - 1][0], intervals[intervals.length - 1][1]);
        }
        int[][] res = new int[merge.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : merge.entrySet()) {
            res[index][0] = entry.getKey();
            res[index++][1] = entry.getValue();
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 4}, {4, 5}})));
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 4}, {0, 4}})));
        System.out.println(Arrays.deepToString(merge(new int[][]{{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}})));
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        System.out.println(Arrays.deepToString(merge(new int[][]{{2, 3}, {2, 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2}, {4, 6}})));
        System.out.println(Arrays.deepToString(merge(new int[][]{{4,5},{2,4},{4,6},{3,4},{0,0},{1,1},{3,5},{2,2}})));
    }
}
