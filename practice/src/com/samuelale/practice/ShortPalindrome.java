package com.samuelale.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.hackerrank.com/challenges/short-palindrome
 */
public class ShortPalindrome {

    private static int MOD = 1000000007;
    private static String abc = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = in.readLine().toCharArray();

        // matrix to track the count of pairs each letter has with another: alphabet chars X alphabet chars
        long[][] pairs = new long[26][26];
        int[] curLeft = new int[26];
        // matrix: input chars X alphabet chars
        long[][] data = new long[arr.length][26];
        
        long result = 0;


        for (int a0 = 0; a0 < arr.length; a0++) {
            // get the alphabetical index of the char iterating at the end of the input string to the front
            int idx = abc.indexOf(arr[arr.length - a0 - 1]);
            // exclude the first loop
            if (a0 > 0) {
                // for each letter in the alphabet
                for (int i = 0; i < abc.length(); i++) {
                    data[a0][i] = data[a0-1][i];
                }
            }
            data[a0][idx]++;
        }
        
        for (int a0 = 0; a0 < arr.length; a0++) {
            int idx = abc.indexOf(arr[a0]);
            if (a0<arr.length-1) {
                for (int i = 0; i < abc.length(); i++) {
                    result = (result + (pairs[i][idx] * data[arr.length - a0 - 2][i]) % MOD) % MOD;
                }
            }
            for (int i = 0; i < abc.length(); i++) {
                pairs[i][idx] = (pairs[i][idx]+ curLeft[i]) % MOD;
            }
            curLeft[idx]++;
        }
        System.out.println(result);
    }
    
}
