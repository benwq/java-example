package com.wqb.leetcode;

public class LUniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) arr[i][0] = 1;
        for (int i = 0; i < n; i++) arr[0][i] = 1;
        for (int i = 1; i < m; i++) {
            for (int k = 1; k < n; k++) {
                arr[i][k] = arr[i - 1][k] + arr[i][k - 1];
            }
        }
        return arr[m - 1][n - 1];
    }
}
