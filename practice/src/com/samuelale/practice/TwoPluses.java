package com.samuelale.practice;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/two-pluses
 */
public class TwoPluses {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int M = s.nextInt();
        boolean[][] grid = new boolean[N][M];
        for(int row=0; row<N; row++) {
            String str = s.next();
            for(int col=0; col<M; col++) {
                char c = str.charAt(col);
                if(c == 'G') {
                    grid[row][col] = true;
                }
            }
        }

        int area = findLargestPlus(grid);
        area *= findLargestPlus(grid);

        System.out.print(area);
    }

    private static int findLargestPlus(boolean[][] grid) {
        int bestSize = 0;
        int bestRow = -1;
        int bestCol = -1;
        for(int i=1; i<grid.length-1; i++) {
            for(int j=1; j<grid[0].length-1; j++) {
                int size = computePlus(grid, i, j);
                if(size >= bestSize) {
                    bestSize = size;
                    bestRow = i;
                    bestCol = j;
                }
            }
        }

        for(int i=0; i<=bestSize; i++) {
            grid[bestRow+i][bestCol] = false;
            grid[bestRow][bestCol+i] = false;
            grid[bestRow-i][bestCol] = false;
            grid[bestRow][bestCol-i] = false;
        }

        return bestSize*4+1;
    }

    private static int computePlus(boolean[][] grid, int i, int j) {
        int len = 1;
        while(true) {
            if(i+len >= grid.length || j+len >= grid[0].length || i-len < 0 || j-len < 0) break;
            if(!grid[i+len][j]) break;
            if(!grid[i][j+len]) break;
            if(!grid[i-len][j]) break;
            if(!grid[i][j-len]) break;
            len++;
        }
        return len-1;
    }
}
