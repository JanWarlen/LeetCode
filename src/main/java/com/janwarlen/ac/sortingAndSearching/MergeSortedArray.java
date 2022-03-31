package com.janwarlen.ac.sortingAndSearching;

public class MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (0 == m) {
            System.arraycopy(nums2, 0, nums1, 0, n);
        }
        if (0 == n) {
            return;
        }
        int idx = 0;
        int ex = 0;
        for (int i = 0; i < m + ex && idx < n; i++) {
            if (nums1[i] > nums2[idx]) {
                ex++;
                for (int j = m + ex - 1; j > i; j--) {
                    nums1[j] = nums1[j - 1];
                }
                nums1[i] = nums2[idx++];
            }
        }
        for (int i = m + ex, j = idx; i < m + n; i++, j++) {
            nums1[i] = nums2[j];
        }
    }

    public static void mergePro(int[] nums1, int m, int[] nums2, int n) {
        if (0 == m) {
            System.arraycopy(nums2, 0, nums1, 0, n);
        }
        if (0 == n) {
            return;
        }
        int[] tmp = new int[nums1.length];
        int idx = 0;
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                tmp[idx++] = nums1[i++];
            } else {
                tmp[idx++] = nums2[j++];
            }
        }
        if (i < m) {
            for (; i < m; i++) {
                tmp[idx++] = nums1[i];
            }
        }
        if (j < n) {
            for (; j < n; j++) {
                tmp[idx++] = nums2[j];
            }
        }
        System.arraycopy(tmp, 0, nums1, 0, m + n);
    }

    public static void main(String[] args) {
//        merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
//        merge(new int[]{1, 3, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
//        merge(new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3);
//        merge(new int[]{4, 0, 0, 0, 0, 0}, 1, new int[]{1, 2, 3, 5, 6}, 5);
        merge(new int[]{0, 0, 3, 0, 0, 0, 0, 0, 0}, 3, new int[]{-1, 1, 1, 1, 2, 3}, 6);
//        merge(new int[]{1}, 1, new int[]{}, 0);
    }
}
