package com.janwarlen.ac.array;

import java.util.Arrays;

public class RotateImage {

    /**
     * 1 2 3       7 4 1
     * 4 5 6       8 5 2
     * 7 8 9       9 6 3
     */
    public static void rotate(int[][] matrix) {
        int[][] res = new int[matrix.length][matrix.length];
        for (int j = 0; j < matrix.length; j++) {
            for (int i = matrix.length - 1; i >= 0; i--) {
                res[j][matrix.length - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = res[i][j];
            }
        }
    }

    /*
     * clockwise rotate
     * first reverse up to down, then swap the symmetry
     * 1 2 3     7 8 9     7 4 1
     * 4 5 6  => 4 5 6  => 8 5 2
     * 7 8 9     1 2 3     9 6 3
     */

    /**
     * 空间上对比少了一个同大小数组
     * 时间上直观的少了一半
     * 来自官网网友的最佳解
     */
    public void rotateBest(int[][] matrix) {
        reverse(matrix);
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = i + 1; j < matrix[i].length; ++j) {
                swap(matrix, i, j);
            }
        }
    }

    private void swap(int[][] matrix, int i, int j) {
        matrix[i][j] += matrix[j][i];
        matrix[j][i] = matrix[i][j] - matrix[j][i];
        matrix[i][j] -= matrix[j][i];
    }

    public void reverse(int[][] matrix) {
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] += matrix[matrix.length - i - 1][j];
                matrix[matrix.length - i - 1][j] = matrix[i][j] - matrix[matrix.length - i - 1][j];
                matrix[i][j] -= matrix[matrix.length - i - 1][j];
            }
        }
    }

    public static void main(String[] args) {
        int[][] ints = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(ints);
        System.out.println(Arrays.deepToString(ints));
    }
}
