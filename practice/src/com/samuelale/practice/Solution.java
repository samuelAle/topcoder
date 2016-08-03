package com.samuelale.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        List<List<Integer>> lists = new ArrayList<>();

        for(int t=0; t<T; t++) {
            int N = input.nextInt();
            ArrayList<Integer> list = new ArrayList<>();
            for(int n=0; n<N; n++) {
                list.add(input.nextInt());
            }
            lists.add(list);
        }

        for(List<Integer> list : lists) {
            int S = 1;
            int P = 0;

            list.sort(Integer::compareTo);
            int sum = list.stream().reduce((i, j) -> i+j).orElseThrow(RuntimeException::new);

            for(int min : list) {
                // what is greater? S * sum(list) or (S+1) * (sum(list)-min(list))
                int battleP = S*sum;
                int eatP = (S+1)*(sum-min);

                if(battleP > eatP) {
                    P+=S*sum;
                    break;
                } else {
                    S++;
                }

                sum-=min;
            }
            System.out.println(P);
        }
    }

    private static void meat(List<Integer> list) {
        int S = 1;
        int P = 0;

        list.sort(Integer::compareTo);
        int sum = list.stream().reduce((i, j) -> i+j).orElseThrow(RuntimeException::new);

        for(int min : list) {
            // what is greater? S * sum(list) or (S+1) * (sum(list)-min(list))
            int battleP = S*sum;
            int eatP = (S+1)*(sum-min);

            if(battleP > eatP) {
                P+=S*sum;
                break;
            } else {
                S++;
            }

            sum-=min;
        }
        System.out.println(P);
    }
}