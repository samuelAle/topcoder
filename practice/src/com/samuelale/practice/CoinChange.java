package com.samuelale.practice;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/coin-change
 */
public class CoinChange {

    private static int answer = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();

        // get rid of the next line
        s.nextLine();
        int[] hasAmount = new int[N+1];
        int[] coins = Arrays.stream(s.nextLine().split(" ")).mapToInt(Integer::valueOf).sorted().toArray();

        for(int coin : coins) {
            for(int i=1; i<hasAmount.length; i++) {
                if(i % coin == 0) {
                    hasAmount[i]++;
                }
                else if (hasAmount[i]>0 && i+coin < hasAmount.length) {
                    hasAmount[i+coin] += hasAmount[i];
                }

                if (hasAmount[i]>0 && i+coin == N) {
                    answer += hasAmount[i];
                }
            }
        }

        System.out.println(answer);
    }

}
