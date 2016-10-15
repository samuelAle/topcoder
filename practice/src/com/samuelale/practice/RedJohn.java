package com.samuelale.practice;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/red-john-is-back
 */
public class RedJohn {
    private static boolean[] isPrime = new boolean[200_000_000];

    public static void main(String[] args) {
        Arrays.fill(isPrime, true);
        Scanner s = new Scanner(System.in);
        long T = s.nextInt();
        for(long t=0; t<T; t++) {
            long N = s.nextInt();
            long m = 0;

            // calculate number of arrangements with both 4x1's vs 1x4's
            long maxFlats = N/4;
            for(long i=0; i<=maxFlats; i++) {
                // k is the total number of vertical bricks to be placed
                long k = N-i*4;
                long n = k+i;
                // perform n choose k
                m += fact(n).divide(fact(k).multiply(fact(n-k))).longValue();
            }
            System.out.println((int) countPrimeUpTo(m));
        }
    }

    /**
     * Sieve of Eratosthenes
     * @param m prime count limit
     * @return number of isPrime counted up to limit m
     */
    private static long countPrimeUpTo(long m) {
        // while we haven't visited values below sqrt(m)
        double limit = Math.sqrt(m);
        for(int i=2; i <= limit; i++) {
            // if we find a prime
            if(isPrime[i]) {
                // the values i^2, i^2+i, i^2+2i, i^2+3i ... not exceeding m, are now known to NOT be prime.
                // example i=2: 4,    6,     8,    10 ... <=m
                for(int j = i * i; j<=m && j<isPrime.length; j+= i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;
        for(int i=2; i<=m; i++) {
            if(isPrime[i]) count++;
        }
        return count;
    }

    private static BigInteger fact(long l) {
        BigInteger fact = BigInteger.ONE;
        for(long i=1; i<=l; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }

}
