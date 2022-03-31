package com.janwarlen.ac.arrayAndStrings;

import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeroes {

    /**
     * 优化点在于减少遍历次数进行0赋值
     * 提炼赋0规律，减少同行同列0的重复赋0
     */
    public void setZeroesPro(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> cols = new HashSet<Integer>();

        // Essentially, we mark the rows and columns that are to be made zero
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        // Iterate over the array once again and using the rows and cols sets, update the elements.
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void setZeroes(int[][] matrix) {
        boolean[][] record = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                record[i][j] = true;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (record[i][j] && matrix[i][j] == 0) {
                    setZeroByCoordinate(matrix, i, j, record);
                }
            }
        }
    }

    private void setZeroByCoordinate(int[][] matrix, int i, int j, boolean[][] record) {
        for (int k = 0; k < matrix[i].length; k++) {
            if (0 != matrix[i][k]) {
                matrix[i][k] = 0;
                record[i][k] = false;
            }
        }
        for (int k = 0; k < matrix.length; k++) {
            if (0 != matrix[k][j]) {
                matrix[k][j] = 0;
                record[k][j] = false;
            }
        }
    }
}
