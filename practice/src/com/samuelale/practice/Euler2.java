package com.samuelale.practice;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler002
 */
public class Euler2 {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int tests = s.nextInt();
        Set<Double> fibSeq = new HashSet<>();
        double tn = 0;
        double tnn = 1;
        fibSeq.add(tn);
        for(int t=0; t<tests; t++) {
            double lim = s.nextInt();
            if(tnn < lim) {
                while(tnn < lim) {
                    double tnnn = tn + tnn;
                    if(tnnn %2 == 0) fibSeq.add(tnnn);
                    tn = tnn;
                    tnn = tnnn;
                }
            }
            System.out.println(fibSeq.stream().filter(n -> n < lim).mapToDouble(n->n).sum());
        }
    }
}

