package com.janwarlen.ac.sortingAndSearching;

public class FirstBadVersion {
    public static int firstBadVersion(int n) {
        return solution(1, n);
    }

    private static int solution(int start, int end) {
        if (start == end) {
            return start;
        }
        if (start + 1 == end) {
            return isBadVersion(start) ? start : end;
        }
        int mid = (start + end) >>> 1;
        if (isBadVersion(mid)) {
            return solution(start, mid);
        } else {
            return solution(mid + 1, end);
        }
    }

    public static int firstBadVersionPro(int n) {
        int start = 1;
        int end = n;
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private static boolean isBadVersion(int i) {
        return i >= 5;
    }

    public static void main(String[] args) {
        System.out.println(firstBadVersion(5));
    }
}
