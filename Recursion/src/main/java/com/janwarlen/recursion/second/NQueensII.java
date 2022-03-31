package com.janwarlen.recursion.second;

public class NQueensII {

    public static int totalNQueens(int n) {
        int[][] chessBoard = initChessBoard(n);
        return countQueensNum(chessBoard, 0, 0);
    }

    /**
     * 统计可行方案数
     *
     * @param chessBoard 棋盘
     * @param row        当前行
     * @param count      已统计方案数
     * @return 统计方案数
     */
    public static int countQueensNum(int[][] chessBoard, int row, int count) {
        for (int col = 0; col < chessBoard.length; col++) {
            if (isNotUnderAttack(chessBoard, row, col)) {
                placeQueen(chessBoard, row, col);
                if (chessBoard.length == (row + 1)) {
                    // 最后一行，最后可放置节点
                    count++;
                } else {
                    // 递归下一行统计
                    count = countQueensNum(chessBoard, row + 1, count);
                }
                // 回溯到当前行
                // 清除当前节点放置的皇后
                removeQueen(chessBoard, row, col);
            }
        }
        return count;
    }

    /**
     * 初始化棋盘
     *
     * @param n 规格
     * @return 初始化后的棋盘
     */
    public static int[][] initChessBoard(int n) {
        int[][] chessBoard = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessBoard[i][j] = 0;
            }
        }
        return chessBoard;
    }

    /**
     * 判断是否不在场上皇后攻击范围内
     *
     * @param row 行index
     * @param col 列index
     * @return 是否不在攻击范围内，返回true表示不在任何皇后攻击范围内，可以放置
     */
    public static boolean isNotUnderAttack(int[][] chessBoard, int row, int col) {
        return 0 == chessBoard[row][col];
    }

    /**
     * 判断检索点是否是在目标点的攻击范围内
     *
     * @param i   检索点-行index
     * @param j   检索点-列index
     * @param row 目标点-行index
     * @param col 目标点-列index
     * @return 返回true表示在攻击范围内
     */
    private static boolean rangeCheck(int i, int j, int row, int col) {
        if (i == row) {
            // 行范围判定
            return true;
        }
        if (j == col) {
            // 列范围判定
            return true;
        }
        if ((row - i) == (col - j)) {
            // /斜范围判定
            return true;
        }
        if (0 == ((row - i) + (col - j))) {
            // \斜范围判定
            return true;
        }
        return false;
    }

    /**
     * 放置皇后
     *
     * @param row 行index
     * @param col 列index
     */
    public static void placeQueen(int[][] chessBoard, int row, int col) {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                if (rangeCheck(i, j, row, col)) {
                    // 当前放置皇后节点所有攻击范围节点值-1
                    chessBoard[i][j] -= 1;
                }
            }
        }
        // 皇后节点值强制设置1
        chessBoard[row][col] = 1;
    }

    /**
     * 移除皇后
     *
     * @param row 行index
     * @param col 列index
     */
    public static void removeQueen(int[][] chessBoard, int row, int col) {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                if (rangeCheck(i, j, row, col)) {
                    // 目标皇后节点攻击范围节点值 + 1，重复攻击范围节点值依旧 < 0
                    chessBoard[i][j] += 1;
                }
            }
        }
        // 皇后节点值强制设置0
        chessBoard[row][col] = 0;
    }

    public static void main(String[] args) {
        System.out.println(totalNQueens(4));
    }

    /**
     *最优解
     */
    public class Solution {
        int count = 0;
        public int totalNQueens(int n) {
            boolean[] cols = new boolean[n];     // columns   |
            boolean[] d1 = new boolean[2 * n];   // diagonals \
            boolean[] d2 = new boolean[2 * n];   // diagonals /
            backtracking(0, cols, d1, d2, n);
            return count;
        }

        public void backtracking(int row, boolean[] cols, boolean[] d1, boolean []d2, int n) {
            if(row == n) count++;

            for(int col = 0; col < n; col++) {
                int id1 = col - row + n;
                int id2 = col + row;
                if(cols[col] || d1[id1] || d2[id2]) continue;

                cols[col] = true; d1[id1] = true; d2[id2] = true;
                backtracking(row + 1, cols, d1, d2, n);
                cols[col] = false; d1[id1] = false; d2[id2] = false;
            }
        }
    }
}
