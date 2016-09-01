package com.samuelale.practice;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/gena
 */
public class GenaHanoi {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        ArrayList<Integer>[] rods = new ArrayList[4];
        for(int n=0; n<N; n++) rods[s.nextInt()].add(n);

        // TODO
    }
}
