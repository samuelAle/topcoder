package com.samuelale.practice;
import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/quicksort3
 */
public class QuickSortIP {

    public static void main(String[] args) {
        int[] arr = new int[] {
                0, 3, 5, 1, 2, 4
        };
        System.out.println(Arrays.toString(sort(arr)));
    }

    /**
     * How it is supposed to go:
     * // choose an element in the range to be the divider
     * // put items less than the divider to the left, and ones greater than to the right
     * // do this in place by swapping the smaller values with the larger values
     * // repeat process with new ranges for the left, and the right
     *
     * // upon return of the repeat (recursion) you can assume that the left and right are sorted
     * // TODO determine how to sort in place
     * @param arr the array to sort
     * @return sorted array
     */
    private static int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
        return arr;
    }

    private static void quickSort(int[] arr, int start, int end) {
        if(start < end) {
            int pIdx = partition(arr, start, end);
            quickSort(arr, start, pIdx-1);
            quickSort(arr, pIdx+1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int piv = arr[end];
        System.out.print("P=" + piv + " | " + Arrays.toString(arr));
        int i = start;
        for(int j=start; j<end; j++) {
            if(arr[j] <= piv) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
            }
        }
        int tmp = arr[i];
        arr[i] = arr[end];
        arr[end] = tmp;
        System.out.println(" --> " + Arrays.toString(arr));
        return i;
    }

}
