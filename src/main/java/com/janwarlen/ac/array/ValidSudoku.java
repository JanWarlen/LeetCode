package com.janwarlen.ac.array;

import java.util.HashMap;
import java.util.Map;

public class ValidSudoku {

    public static boolean isValidSudoku(char[][] board) {
        // check row
        for (int i = 0; i < 9; i++) {
            Map<Integer, Boolean> tmp = new HashMap<>();
            tmp.put(49, true);
            tmp.put(50, true);
            tmp.put(51, true);
            tmp.put(52, true);
            tmp.put(53, true);
            tmp.put(54, true);
            tmp.put(55, true);
            tmp.put(56, true);
            tmp.put(57, true);
            for (int j = 0; j < 9; j++) {
                if (tmp.containsKey((int) board[i][j]) && tmp.get((int) board[i][j])) {
                    tmp.put((int) board[i][j], false);
                } else if ('.' != board[i][j]) {
                    return false;
                }
            }
        }
        // check col
        for (int i = 0; i < 9; i++) {
            Map<Integer, Boolean> tmp = new HashMap<>();
            tmp.put(49, true);
            tmp.put(50, true);
            tmp.put(51, true);
            tmp.put(52, true);
            tmp.put(53, true);
            tmp.put(54, true);
            tmp.put(55, true);
            tmp.put(56, true);
            tmp.put(57, true);
            for (int j = 0; j < 9; j++) {
                if (tmp.containsKey((int) board[j][i]) && tmp.get((int) board[j][i])) {
                    tmp.put((int) board[j][i], false);
                } else if ('.' != board[j][i]) {
                    return false;
                }
            }
        }
        // check sub-boxes
        for (int i = 0; i < 9; i++) {
            int startRow = 3 * (i / 3);
            int startCol = 3 * (i % 3);
            Map<Integer, Boolean> tmp = new HashMap<>();
            tmp.put(49, true);
            tmp.put(50, true);
            tmp.put(51, true);
            tmp.put(52, true);
            tmp.put(53, true);
            tmp.put(54, true);
            tmp.put(55, true);
            tmp.put(56, true);
            tmp.put(57, true);
            for (int j = startRow; j < startRow + 3; j++) {
                // row
                for (int k = startCol; k < startCol + 3; k++) {
                    // col
                    if (tmp.containsKey((int) board[j][k]) && tmp.get((int) board[j][k])) {
                        tmp.put((int) board[j][k], false);
                    } else if ('.' != board[j][k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 空间时间皆优化一半有余
     */
    public boolean isValidSudokuPro(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ('.' != board[i][j] && !check(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean check(char[][] board, int row, int col) {
        // row
        for (int i = 0; i < 9; i++) {
            if (col != i && board[row][i] == board[row][col]) {
                return false;
            }
        }
        // col
        for (int i = 0; i < 9; i++) {
            if (row != i && board[i][col] == board[row][col]) {
                return false;
            }
        }
        // sub-box
        int startRow = 3 * (row / 3);
        int startCol = 3 * (col / 3);
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (i != row && j != col && board[i][j] == board[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        isValidSudoku(board);
    }
}
