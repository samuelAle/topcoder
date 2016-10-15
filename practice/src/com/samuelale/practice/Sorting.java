package com.samuelale.practice;
import java.util.Arrays;

/**
 * Created by yq867d on 9/19/16.
 */
public class Sorting {

    public static void main(String[] args) {
        int N = 10000;
        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = ((int)(Math.random() * 10000));
        }

        String expected = Arrays.toString(Arrays.stream(arr).sorted().toArray());
        String actual = Arrays.toString(mergeSort(arr));

        System.out.println(expected.equals(actual));
    }

    public static int[] mergeSort(int[] arr){
        int[] dest = new int[arr.length];
        splitAndMerge(arr, dest, 0, arr.length-1);
        return dest;
    }

    private static void splitAndMerge(int[] src, int[] dest, int start, int end) {
        // base case: if the end is equal to the start, then it is already sorted/merged
        if(end == start) return;

        // split
        int mid = start + (end-start)/2;
        splitAndMerge(src, dest, start, mid);
        splitAndMerge(src, dest, mid+1, end);

        // merge
        int lhs = start;
        int rhs = mid+1;
        for(int i=start; i<=end; i++) {
            if(lhs > mid) {
                // ran out of lhs elements, just use rhs ones
                while(rhs <= end) {
                    dest[i] = src[rhs];
                    rhs++;
                    i++;
                }
            } else if(rhs > end) {
                // ran out of rhs elements, just use lhs ones
                while(lhs <= mid) {
                    dest[i] = src[lhs];
                    lhs++;
                    i++;
                }
            } else if(src[lhs] < src[rhs]) {
                // lhs is smaller, so we place that one
                dest[i] = src[lhs];
                lhs++;
            } else {
                // rhs is smaller, so we place that one
                dest[i] = src[rhs];
                rhs++;
            }
        }

        System.arraycopy(dest, start, src, start, end-start+1);
    }
}
