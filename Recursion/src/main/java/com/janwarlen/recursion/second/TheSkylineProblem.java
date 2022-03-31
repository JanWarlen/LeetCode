package com.janwarlen.recursion.second;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * The Skyline Problem
 */
public class TheSkylineProblem {


    public static List<int[]> getSkyline(int[][] buildings) {
        List<int[]> heights = new ArrayList<>();
        for (int[] b : buildings) {
            heights.add(new int[]{b[0], -b[2]});
            heights.add(new int[]{b[1], b[2]});
        }
        heights.sort((a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>(Collections.reverseOrder());
        heightMap.put(0, 1);
        int prevHeight = 0;
        List<int[]> skyLine = new LinkedList<>();
        for (int[] h : heights) {
            if (h[1] < 0) {
                Integer cnt = heightMap.get(-h[1]);
                cnt = (cnt == null) ? 1 : cnt + 1;
                heightMap.put(-h[1], cnt);
            } else {
                Integer cnt = heightMap.get(h[1]);
                if (cnt == 1) {
                    heightMap.remove(h[1]);
                } else {
                    heightMap.put(h[1], cnt - 1);
                }
            }
            int currHeight = heightMap.firstKey();
            if (prevHeight != currHeight) {
                skyLine.add(new int[]{h[0], currHeight});
                prevHeight = currHeight;
            }
        }
        return skyLine;
    }

    /**
     * 从左至右依次访问顶角，访问左顶角时，记录下当前访问的建筑的高度(需升序排序)，排序的目的是为了判断
     * - 左顶点是否在别的建筑体内(轮廓不记录)
     * - 天台是否和别的建筑相交(别的建筑的右x座标，当前建筑的高度y)
     * - 右顶点是否在别的建筑体内(轮廓不记录)
     * 访问右顶角时，离开当前建筑，并判断
     * - 是否与下一个高度的建筑有重叠部分
     * - 是否离开当前建筑群(需要记录右下角,y=0)
     */
    public static List<List<Integer>> getSkylineCut(int[][] buildings) {
        List<List<Integer>> skyline = new ArrayList<>();
        // 存储所有左右顶点
        List<List<Integer>> vertexs = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            List<Integer> vertexLeft = new ArrayList<>();
            // 左顶点
            // x
            vertexLeft.add(buildings[i][0]);
            // y,此处保存为-，便于后续区分左右
            vertexLeft.add(-buildings[i][2]);
            vertexs.add(vertexLeft);
            // 右顶点
            List<Integer> vertexRight = new ArrayList<>();
            // x
            vertexRight.add(buildings[i][1]);
            // y
            vertexRight.add(buildings[i][2]);
            vertexs.add(vertexRight);
        }
        // 将顶点座标根据x进行升序排序
        vertexs = vertexs.stream().sorted((a, b) -> {
            // 注意，此处必须使用equals,因为是封装的Integer
            // == 在超过128后将不再准确
            if (a.get(0).equals(b.get(0))) {
                return a.get(1) - b.get(1);
            } else {
                return a.get(0) - b.get(0);
            }
        }).collect(Collectors.toList());
        // 高度记录，key为高度，value为该高度建筑计数
        TreeMap<Integer, Integer> heightRecords = new TreeMap<>(Collections.reverseOrder());
        // 最开始无访问建筑，高度为0
        heightRecords.put(0, 1);
        int preHighest = 0;
        for (int i = 0; i < vertexs.size(); i++) {
            List<Integer> vertex = vertexs.get(i);
            int x = vertex.get(0);
            int y = vertex.get(1);
            // 记录当前建筑高度
            if (y < 0) {
                // 左顶点，代表访问到新建筑
                Integer counts = heightRecords.getOrDefault(-y, 0);
                heightRecords.put(-y, counts + 1);
            } else {
                // 右顶点，代表离开一个建筑
                Integer counts = heightRecords.get(y);
                if (1 == counts) {
                    // 无该高度建筑
                    heightRecords.remove(y);
                } else {
                    heightRecords.put(y, counts - 1);
                }
            }
            // 当前建筑群最高高度
            Integer cur = heightRecords.firstKey();
            if (preHighest != cur) {
                // 左顶点需要记录的情况 cur > preHighest
                // - 当前建筑高于之前访问并未离开的最高高度
                // 右顶点需要记录的情况 cur < preHighest
                // - 前一个建筑访问时的最高高度小于当前高度
                // - 当前高度建筑无相连，即当前高度记录小于当前建筑高度
                List<Integer> tmp = new ArrayList<>();
                tmp.add(x);
                tmp.add(cur);
                skyline.add(tmp);
                preHighest = cur;
            }
        }
        return skyline;
    }

    /**
     * 官网最快答案
     */
    public static List<List<Integer>> getSkylineBest(int[][] build) {
        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[2] - a[2]);

        int next = 0;
        int[] point = null;

        while (point != null || next < build.length) {
            if (point == null) {
                point = build[next];
                List<Integer> tmp = new ArrayList<>();
                tmp.add(point[0]);
                tmp.add(point[2]);
                result.add(tmp);
            } else if (next < build.length && build[next][0] <= point[1]) {
                // 下一个建筑的左x在point的右壁左侧
                if (build[next][2] > point[2]) {
                    // 下一个建筑的高度大于当前建筑高度
                    if (build[next][0] == point[0]) {
                        // 左x相同，只记录高度更高的左顶点
                        // 需要移除先前记录，避免高度问题
                        result.remove(result.size() - 1);
                    }
                    if (build[next][1] <= point[1]) {
                        // 当前建筑的右壁在下一个建筑的右侧
                        // 意味着需要计算右壁的交点，需要特殊处理
                        queue.add(point);
                    }
                    point = build[next];
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(point[0]);
                    tmp.add(point[2]);
                    result.add(tmp);
                } else if (build[next][1] > point[1]) {
                    // 下一个建筑与当前建筑存在间隔，需要特殊处理
                    // 即当前建筑群的最右侧需要记录y=0的点
                    queue.add(build[next]);
                }
                next++;
            } else {
                // 下一个建筑与当前建筑存在间隙
                int[] cur = queue.poll();
                while (cur != null && cur[1] <= point[1]) {
                    // cur[1] <= point[1]的条件时筛选queue中右壁在当前建筑右壁右侧的，用于找需要计算交点建筑
                    cur = queue.poll();
                }
                if (cur == null) {
                    // 当前建筑群已无需计算交点建筑，即已经到头，仅需记录右下角y=0的点
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(point[1]);
                    tmp.add(0);
                    result.add(tmp);
                } else if (cur[2] < point[2]) {
                    // 计算交点
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(point[1]);
                    tmp.add(cur[2]);
                    result.add(tmp);
                }
                point = cur;
            }

        }
        return result;
    }

    /**
     * 二分法
     */
    public List<List<Integer>> getSkylineDQ(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        if (buildings.length == 0) return res;
        int n = buildings.length;
        return findSkyline(buildings, 0, n - 1);
    }

    private List<List<Integer>> findSkyline(int[][] buildings, int lo, int hi) {
        List<List<Integer>> res = new LinkedList<>();
        if (lo == hi) {
            res.add(Arrays.asList(buildings[lo][0], buildings[lo][2]));
            res.add(Arrays.asList(buildings[lo][1], 0));
            return res;
        }
        int mid = lo + (hi - lo) / 2;
        List<List<Integer>> skyline1 = findSkyline(buildings, lo, mid);
        List<List<Integer>> skyline2 = findSkyline(buildings, mid + 1, hi);
        return mergeSkyline(skyline1, skyline2);
    }

    private List<List<Integer>> mergeSkyline(List<List<Integer>> skyline1, List<List<Integer>> skyline2) {
        List<List<Integer>> res = new LinkedList<>();
        int i = 0, j = 0;
        int h1 = 0, h2 = 0;

        while (i < skyline1.size() && j < skyline2.size()) {
            int x1 = skyline1.get(i).get(0);
            int x2 = skyline2.get(j).get(0);
            int x;
            if (x1 < x2) {
                h1 = skyline1.get(i++).get(1);
                x = x1;
            } else if (x1 > x2) {
                h2 = skyline2.get(j++).get(1);
                x = x2;
            } else {
                h1 = skyline1.get(i++).get(1);
                h2 = skyline2.get(j++).get(1);
                x = x1;
            }
            int h = Math.max(h1, h2);

            if (res.isEmpty() || h != res.get(res.size() - 1).get(1)) {
                res.add(Arrays.asList(x, h));
            }
        }

        while (i < skyline1.size()) res.add(skyline1.get(i++));
        while (j < skyline2.size()) res.add(skyline2.get(j++));

        return res;
    }

    public static void main(String[] args) {
//        int[][] ints = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        int[][] ints = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
//        int[][] ints = {{1, 2, 3}, {1, 2, 2}, {1, 2, 1}};
//        int[][] ints = {{1, 2, 1}, {1, 2, 2}, {1, 2, 3}, {2, 3, 1}, {2, 3, 2}, {2, 3, 3}};
//        int[][] ints = {{4,9,10},{4,9,15},{4,9,12},{10,12,10},{10,12,8}};
//        int[][] ints = {{4,10,10},{5,10,9},{6,10,8},{7,10,7},{8,10,6}};
        getSkylineBest(ints).stream().forEach(e -> {
            e.stream().forEach(ele -> {
                System.out.print(ele + ",");
            });
            System.out.print("|");
        });
    }
}
