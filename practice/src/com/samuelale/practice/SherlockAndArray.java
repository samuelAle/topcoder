package com.samuelale.practice;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/sherlock-and-array
 */
public class SherlockAndArray {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        for(int t=0; t<T; t++) {
            int N = s.nextInt();
            if(N == 1) {
                s.nextInt();
                System.out.println("YES");
                continue;
            }
            int[] arr = new int[N];
            for(int n=0; n<N; n++) arr[n] = s.nextInt();

            // i == 0, left side is 0, right side is arr[1] +...+ arr[n-1]
            long rightSum = Arrays.stream(arr).sum() - arr[0];
            long leftSum = 0;
            // from i==1 to i==n-1
            for(int idx=1; idx<=N; idx++) {
                if(rightSum==leftSum) break;

                leftSum+=arr[idx-1];
                if(idx==N) rightSum = 0;
                else rightSum-=arr[idx];
            }
            System.out.println(rightSum==leftSum ? "YES" : "NO");
        }
    }

}
