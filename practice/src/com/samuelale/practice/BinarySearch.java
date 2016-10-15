package com.samuelale.practice;
/**
 * Simple binary search
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[] {
                0, 2, 3, 4, 6, 7, 10, 11, 32
        };

        for(int i=0; i<arr.length; i++)
            System.out.println(search(i, arr));
    }

    private static int search(int val, int[] arr) {
        int low = 0, high = arr.length;
        while(low <= high) {
            int mid = low + (high-low)/2;
            if(val == arr[mid]) {
                return mid;
            } else if(val < arr[mid]) {
                high = mid-1;
            } else if(val > arr[mid]) {
                low = mid+1;
            }
        }
        return -1;
    }
}
