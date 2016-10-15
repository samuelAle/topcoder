package com.samuelale.practice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/maximum-subarray-sum
 */
public class MaxSubArraySum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.valueOf(reader.readLine());
        for(int q=0; q<Q; q++) {
            String[] sizeAndMod = reader.readLine().split(" ");
            int n = Integer.valueOf(sizeAndMod[0]);
            long m = Long.valueOf(sizeAndMod[1]);
            long[] arr = new long[n];

            String[] inputs = reader.readLine().split(" ");
            for(int i=0; i<n; i++)
                arr[i] = Long.valueOf(inputs[i]) % m;


            long[] prefixSums = new long[n];
            prefixSums[0] = arr[0];
            for(int i=1; i<n; i++) {
                prefixSums[i] = (arr[i] + prefixSums[i-1]) % m;
            }

            // TODO figure out how to find the minimum differenc ebetween all the prefix sums
            // TODO such that the first pfx sum is < the next one
            prefixSums = Arrays.stream(prefixSums).sorted().toArray();
            long[] diffs = new long[n-1];
            for(int i=0; i<n-1; i++) {
                diffs[i] = Math.abs(prefixSums[i]-prefixSums[i+1]);
                if(diffs[i] == 0) {
                    diffs[i] = Long.MAX_VALUE;
                }
            }
            long min = Arrays.stream(diffs).min().orElse(m);
            System.out.println(m-min);

            // N square don't care... jk too slow
//            long maxSubArrSum = Integer.MIN_VALUE;
//            long[] sums = new long[n];
//            for(int i=0; i<n; i++) {
//                for(int j=0; j<n-i; j++) {
//                    sums[i] = (sums[i] + arr[i+j]) % m;
//                    if(maxSubArrSum < sums[i]) {
//                        maxSubArrSum = sums[i];
//                    }
//                }
//            }
//            System.out.println(maxSubArrSum);

        }
    }
}
