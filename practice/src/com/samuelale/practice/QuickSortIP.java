package com.samuelale.practice;
import java.util.Arrays;
import java.io.*;

public class QuickSortIP {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        sort(arr);
    }

    private static void sort(int[] arr) {
        quicksort(arr, 0, arr.length-1);
    }

    private static void quicksort(int[] arr, int start, int end) {
        if(start < end) {
            int pivot = partition(arr, start, end);
            quicksort(arr, start, pivot-1);
            quicksort(arr, pivot+1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int i = start;
        for(int j=start; j<end; j++) {
            if(arr[j] < pivot) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
            }
        }

        int tmp = arr[i];
        arr[i] = pivot;
        arr[end] = tmp;
        System.out.println(Arrays.toString(arr).replace(",","").replace("[", "").replace("]", ""));
        return i;
    }
}
