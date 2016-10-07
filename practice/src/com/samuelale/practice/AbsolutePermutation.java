package com.samuelale.practice;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/absolute-permutation
 */
public class AbsolutePermutation {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        for(int t=0; t<T; t++) {
            int N = s.nextInt();
            int K = s.nextInt();
            // so looking at permutations of ints 1->N,
            // we need to find the smallest permutation such that
            // for(int i=0; i<perm.length; i++) abs(perm[i] - i) = K;

            // from this, we know that the only permutations we should consider, are ones
            // that list elements in increasing or decreasing order by 1
            int[] asc = new int[N];
            int[] des = new int[N];
            for(int i=1; i<=N; i++) {
                asc[i-1] = i;
                des[N-i] = i;
            }

            boolean success = true;
            for(int i=0; i<asc.length; i++) {
                if(Math.abs(asc[i] - (i+1)) != K) {
                    success = false;
                    break;
                }
            }
            if(success) {
                for(int i=0; i<asc.length; i++) System.out.print(asc[i] + " ");
                System.out.println();
                continue;
            }
            success = true;
            for(int i=0; i<des.length; i++) {
                if(Math.abs(des[i] - (i+1)) != K) {
                    success = false;
                    break;
                }
            }
            if(success) {
                for(int i=0; i<des.length; i++) System.out.print(des[i] + " ");
                System.out.println();
            } else {
                System.out.println("-1");
            }
        }
    }
}
