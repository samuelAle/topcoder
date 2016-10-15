package com.samuelale.practice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NewYearChaos {

    public static void main(String[] args) throws IOException {
        BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.valueOf(s.readLine());
        for(int t=0; t<T; t++) {
            int N = Integer.valueOf(s.readLine());
            int[] arr = Arrays.stream(s.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();

            // create a mapping from person to current index
            int bribeCount = 0;
            int totalBribes = 0;
            boolean tooChaotic = false;
            boolean done = false;
            while(!tooChaotic && !done) {
                done = true;
                for(int i=0; i<arr.length-1; i++) {
                    if(arr[i] > arr[i+1]) {
                        done = false;
                        int tmp = arr[i];
                        arr[i] = arr[i+1];
                        arr[i+1] = tmp;
                        bribeCount++;
                        if(bribeCount>2) {
                            tooChaotic = true;
                            break;
                        }
                    } else {
                        totalBribes += bribeCount;
                        bribeCount = 0;
                    }
                }
            }
            if(tooChaotic) {
                System.out.println("Too chaotic");
            } else {
                System.out.println(totalBribes);
            }
        }
    }

}
