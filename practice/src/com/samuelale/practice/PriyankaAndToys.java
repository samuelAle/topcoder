package com.samuelale.practice;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/priyanka-and-toys
 */
public class PriyankaAndToys {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int[] arr = new int[N];
        for(int n = 0; n<N; n++) arr[n] = input.nextInt();

        arr = Arrays.stream(arr).sorted().toArray();

        int count = 0;
        int range = -1;
        for(int idx=0; idx<arr.length; idx++){
            int val = arr[idx];

            if(val > range) {
                count++;
                range = val + 4;
            }
        }
        System.out.println(count);
        System.a
    }
}
