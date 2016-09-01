package com.samuelale.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/missing-numbers
 */
public class MissingNumbers {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine(); // throw away
        int[] arrA = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        reader.readLine(); // throw away
        int[] arrB = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();

        int min = Arrays.stream(arrB).min().orElseThrow(RuntimeException::new);
        int[] radixA = getRadix(arrA, min);
        int[] radixB = getRadix(arrB, min);

        for(int i=0; i<101; i++) {
            if(radixA[i] != radixB[i]) {
                System.out.print(i+min + " ");
            }
        }
    }

    private static int[] getRadix(int[] arr, int min) {
        int[] radix = new int[101];
        for(int val : arr) radix[val-min]++;
        return radix;
    }
}
