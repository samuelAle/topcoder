package com.samuelale.practice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/maxsubarray
 */
public class MaxSubArray {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.valueOf(r.readLine());
        for(int t=0; t<T; t++) {
            r.readLine(); // discard arr length line
            int[] src = Arrays.stream(r.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            System.out.println(maxContigSubArray(src) + " " + maxSubArray(src));
        }
    }

    /**
     * @param src an array of integers ranging between -10^4 - 10^4
     * @return the largest sum of a non-contiguous sub-array of src
     */
    private static int maxSubArray(int[] src) {
        int res = Arrays.stream(src).filter(val -> val > 0).sum();
        if(res == 0) {
            res = Arrays.stream(src).max().getAsInt();
        }
        return res;
    }


    /* returns the max sub-array value ending at index limit */
    private static int maxContigSubArray(int[] src) {
        int curMax = src[0];
        int bestMax = src[0];
        for(int i=1; i<src.length; i++) {
            int nextVal = src[i];
            curMax = Math.max(nextVal, curMax+nextVal);
            bestMax = Math.max(curMax, bestMax);
        }
        return bestMax;
    }
}
