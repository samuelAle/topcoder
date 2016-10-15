package com.samuelale.practice;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/sparse-arrays
 */
public class SparseArrays {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        String[] input = new String[N];
        for(int n=0; n<N; n++) {
            input[n] = s.next();
        }
        int Q = s.nextInt();
        for(int q=0; q<Q; q++) {
            String qStr = s.next();
            System.out.println(Arrays.stream(input).filter(n -> n.equals(qStr)).count());
        }
    }
}
