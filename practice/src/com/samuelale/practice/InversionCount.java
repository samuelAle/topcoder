package com.samuelale.practice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class InversionCount {

    private static int total;

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.valueOf(r.readLine());
        for(int t=0; t<T; t++) {
            int size = Integer.valueOf(r.readLine());
            int[] src = new int[size];
            int[] dest = new int[size];

            int count=0;
            for(String s : r.readLine().split(" ")) {
                src[count++] = Integer.valueOf(s);
            }

            total = 0;
            inversionCount(src, 0, size, dest);
            System.out.println(total);
            System.out.println(Arrays.toString(dest));
        }
    }

    // A[1 ... n]; (i, j) s.t. i<j && A[i] > A[j]
    private static void inversionCount(int[] src, int start, int end, int[] dest) {
        if(end-1==start) {
            dest[start] = src[start];
            return;
        }

        int mid = start+(end-start)/2;
        inversionCount(src, start, mid, dest);
        inversionCount(src, mid, end, dest);

        int count = start;
        int rightCount = 0;
        int leftIdx = start;
        int rightIdx = mid;
        while(count != end) {
            if(leftIdx >= mid) {
                // left is out of bounds. do right
                dest[count] = src[rightIdx];
                rightIdx++;
                rightCount++;
            } else if(rightIdx>=end) {
                // right is out of bounds. do left
                dest[count] = src[leftIdx];
                leftIdx++;
                total+=rightCount;
            } else if(src[leftIdx] > src[rightIdx]) {
                // right side element gets placed in array because its smaller
                dest[count] = src[rightIdx];
                rightIdx++;
                rightCount++;
            } else {
                // left side element gets placed in array because its less or equal to right
                dest[count] = src[leftIdx];
                total += rightCount;
                leftIdx++;
            }
            count++;
        }

//        System.out.println("Merging: (" + start + ", "+ (mid-1) + ") & (" + mid + ", " + (end-1) + ")");
        System.arraycopy(dest, start, src, start, end-start);
    }
}
