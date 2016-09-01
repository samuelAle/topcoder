package com.samuelale.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by samuel on 8/19/16.
 */
public class ClosestNumbers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int[] arr = new int[N];
        for(int i=0; i<N; i++) arr[i] = input.nextInt();

        arr = Arrays.stream(arr).sorted().toArray();

        List<Integer> resList = new ArrayList<>();
        int smallestDiff = Integer.MAX_VALUE;
        for(int i=0; i<N-1; i++) {
            int diff = Math.abs(arr[i] - arr[i+1]);
            if(diff < smallestDiff) {
                smallestDiff = diff;
                resList.clear();
                resList.add(arr[i]);
                resList.add(arr[i+1]);
            } else if(diff == smallestDiff) {
                resList.add(arr[i]);
                resList.add(arr[i+1]);
            }
        }

        for(int val : resList) System.out.print(val + " ");
    }
}
