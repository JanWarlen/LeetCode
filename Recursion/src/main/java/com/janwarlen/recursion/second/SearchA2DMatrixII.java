package com.janwarlen.recursion.second;

import java.util.Arrays;

/**
 * Write an efficient algorithm that searches for a target value in an m x n integer matrix. The matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * <p>
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 * <p>
 * <p>
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * All the integers in each row are sorted in ascending order.
 * All the integers in each column are sorted in ascending order.
 * -109 <= target <= 109
 */
public class SearchA2DMatrixII {


    public static boolean searchMatrix(int[][] matrix, int target) {
        if (null == matrix) {
            return Boolean.FALSE;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int currentRow = rows - 1;
        int currentColumn = 0;
        // 初始位置是第一列最大值，最后一行最小值
        while (currentRow >= 0 && currentColumn < columns) {
            int value = matrix[currentRow][currentColumn];
            if (value == target) {
                return Boolean.TRUE;
            } else if (value < target) {
                // 整列是升序的，此时只能行右移
                currentColumn++;
            } else {
                // 整行是升序的，只能列上移
                currentRow--;
            }
        }
        return Boolean.FALSE;
    }

    public static void main(String[] args) {

    }
}
