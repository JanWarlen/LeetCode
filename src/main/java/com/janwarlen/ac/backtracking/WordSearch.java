package com.janwarlen.ac.backtracking;

public class WordSearch {
    int res = 0;

    public boolean exist(char[][] board, String word) {
        int length = word.length();
        int[][] path = new int[board.length][board[0].length];
        for (int i = 0; length != res && i < board.length; i++) {
            for (int j = 0; length != res && j < board[0].length; j++) {
                path[i][j] = 1;
                check(board, path, i, j, word, 0, 0);
                path[i][j] = 0;
            }
        }
        return length == res;
    }

    private void check(char[][] board, int[][] path, int row, int col, String word, int index, int matchedNum) {
        int length = word.length();
        if (index >= length) {
            res = length;
            return;
        }
        if (board[row][col] == word.charAt(index)) {
            matchedNum++;
            res = matchedNum;
            // 上
            if (length != res && row > 0 && 0 == path[row - 1][col]) {
                path[row][col] = 1;
                check(board, path, row - 1, col, word, index + 1, matchedNum);
                path[row][col] = 0;
            }
            // 下
            if (length != res && row < board.length - 1 && 0 == path[row + 1][col]) {
                path[row][col] = 1;
                check(board, path, row + 1, col, word, index + 1, matchedNum);
                path[row][col] = 0;
            }
            // 左
            if (length != res && col > 0 && 0 == path[row][col - 1]) {
                path[row][col] = 1;
                check(board, path, row, col - 1, word, index + 1, matchedNum);
                path[row][col] = 0;
            }
            // 右
            if (length != res && col < board[0].length - 1 && 0 == path[row][col + 1]) {
                path[row][col] = 1;
                check(board, path, row, col + 1, word, index + 1, matchedNum);
                path[row][col] = 0;
            }
        }
    }

    public static void main(String[] args) {
        char[][] test = {{'A'}};
        char[][] test2 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        char[][] test3 = {{'C','A','A'},{'A','A','A'},{'B','C','D'}};
        char[][] test4 = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        System.out.println(new WordSearch().exist(test, "A"));
        System.out.println(new WordSearch().exist(test2, "ABCCED"));
        System.out.println(new WordSearch().exist(test2, "ABCB"));
        System.out.println(new WordSearch().exist(test3, "AAB"));
        System.out.println(new WordSearch().exist(test4, "ABCESEEEFS"));
    }
}
