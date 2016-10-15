package com.samuelale.practice;

/**
 * Use binary search to find the first reading of an earthquake
 */
public class RichterSearch {
    public static void main(String[] args) {
        int[] arr = new int[]{
                0,0,1,1
        };
        System.out.println(firstShock(arr));
    }
    /**
     * Given an array that contains some number of zeros, then
     * some number of 1's, then some number of zeros. Find the index of the first 1
     * @param readings the earthquake readings
     * @return index of the start of the earthquake
     */
    public static int firstShock(int[] readings) {
        // my strategy will be to use binary search to find the instance of [0,1]
        int lo=0, hi=readings.length-2;
        while(lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if(readings[mid] == 0 && readings[mid+1] == 1) {
                return mid+1;
            } else if(readings[mid] == 1 && readings[mid+1] == 1) {
                hi = mid-1;
            } else if(readings[mid] == 1 && readings[mid+1] == 0) {
                hi = mid-1;
            } else if(readings[mid] == 0 && readings[mid+1] == 0) {
                lo = mid+1;
            }
        }
        return -1;
    }
}






