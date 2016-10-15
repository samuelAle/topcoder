package com.samuelale.practice;

public class BinaryPairSearch {
    private static class Pair implements Comparable {
        int x, y;

        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Object o) {
            if(o instanceof Integer) {
                int i = (Integer) o;
                if(i >= x && i <= y) return 0;
                else if(i < x) return -1;
                else if(i > y) return 1;
            }
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        Pair[] pairs = new Pair[] {
                new Pair(0,3), new Pair(5,6), new Pair(9,12)
        };

        for(int i=0; i<13; i++) System.out.println(contains(i, pairs));
    }

    private static boolean contains(int i, Pair[] pairs) {
        int lo = 0, hi = pairs.length;
        while(lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if(pairs[mid].compareTo(i) == 0) {
                return true;
            }
            else if(pairs[mid].compareTo(i) == -1) {
                hi = mid-1;
            } else if(pairs[mid].compareTo(i) == 1) {
                lo = mid+1;
            }
        }
        return false;
    }
}
