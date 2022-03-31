package com.janwarlen.ac.treesAndGraphs;

public class NumberOfIslands {
    public static int numIslands(char[][] grid) {
        int nums = 0;
        int[][] landMap = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if ('1' == grid[i][j]) {
                    if (0 == landMap[i][j]) {
                        // 新大陆，地图染色
                        if (landScan(landMap, i, j)) {
                            landMap[i][j] = ++nums;
                        } else {
                            landMap[i][j] = nums;
                        }
                        colorLand(grid, landMap, i, j, nums);
                    }
                }
            }
        }

        return nums;
    }

    /**
     * 上下左右给同一块大陆进行追踪染色
     */
    private static void colorLand(char[][] grid, int[][] landMap, int i, int j, int color) {
        if ('0' == grid[i][j]) {
            return;
        }
        // 上
        if (i > 0 && '1' == grid[i - 1][j] && 0 == landMap[i - 1][j]) {
            landMap[i - 1][j] = color;
            colorLand(grid, landMap, i - 1, j, color);
        }
        //下
        if (i + 1 < grid.length && '1' == grid[i + 1][j] && 0 == landMap[i + 1][j]) {
            landMap[i + 1][j] = color;
            colorLand(grid, landMap, i + 1, j, color);
        }
        //左
        if (j > 0 && '1' == grid[i][j - 1] && 0 == landMap[i][j - 1]) {
            landMap[i][j - 1] = color;
            colorLand(grid, landMap, i, j - 1, color);
        }
        //右
        if (j + 1 < grid[0].length && '1' == grid[i][j + 1] && 0 == landMap[i][j + 1]) {
            landMap[i][j + 1] = color;
            colorLand(grid, landMap, i, j + 1, color);
        }
    }

    /**
     * 判断是否是新大陆
     * landMap中0代表海洋或未探索的新大陆
     */
    private static boolean landScan(int[][] landMap, int i, int j) {
        boolean res = true;
        // 上
        if (i > 0 && 0 != landMap[i - 1][j]) {
            res = false;
        }
        //下
        if (i + 1 < landMap.length && 0 != landMap[i + 1][j]) {
            res = false;
        }
        //左
        if (j > 0 && 0 != landMap[i][j - 1]) {
            res = false;
        }
        //右
        if (j + 1 < landMap[0].length && 0 != landMap[i][j + 1]) {
            res = false;
        }
        return res;
    }

    public static void main(String[] args) {
        char[][] test = {{'1', '1', '1'}, {'0', '1', '0'}, {'1', '1', '1'}};
        char[][] test2 = {{'1', '0', '1', '1', '1'}, {'1', '0', '1', '0', '1'}, {'1', '1', '1', '0', '1'}};
        char[][] test3 = {
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1'},
                {'1', '0', '1', '0', '1', '1', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '0', '1', '1', '1', '1', '1'},
                {'1', '1', '0', '1', '1', '0', '0', '0', '0', '1'},
                {'1', '0', '1', '0', '1', '0', '0', '1', '0', '1'},
                {'1', '0', '0', '1', '1', '1', '0', '1', '0', '0'},
                {'0', '0', '1', '0', '0', '1', '1', '1', '1', '0'},
                {'1', '0', '1', '1', '1', '0', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '0', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '1', '1', '0'}};
        numIslands(test3);
    }
}
