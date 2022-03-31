package com.janwarlen.recursion.second;

public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        solver(board);
    }

    public static boolean solver(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    // 空位，需要填值
                    for (char k = '1'; k <= '9'; k++) {
                        if (isValid(board, i, j, k)) {
                            board[i][j] = k;
                            if (solver(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 校验新填入值是否符合规则
     * @param board 棋盘
     * @param row 新值行index
     * @param col 新值列index
     * @param e 新值
     * @return true为符合规则
     */
    private static boolean isValid(char[][] board, int row, int col, int e) {
        // 校验行规则
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == e) {
                return false;
            }
        }
        // 校验列规则
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == e) {
                return false;
            }
        }
        // 校验子框规则
        // 确定子框左上顶点座标索引
        int subRow = (row / 3) * 3;
        int subCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[subRow+i][subCol+j] == e) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 最优校验
     */
    private boolean isValidB(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
