package com.samuelale.practice;

import java.util.*;

/**
 * A data structure which performs insert, remove, and getRandom in O(1)
 */
public class RandDataSet {
    private HashMap<Integer, Set<Integer>> valToIdx = new HashMap<>();
    private ArrayList<Integer> list = new ArrayList<>();

    public boolean insert(int val) {
        Set<Integer> idxSet;
        if(valToIdx.containsKey(val)) {
            idxSet = valToIdx.get(val);
        } else {
            idxSet = new HashSet<>();
        }

        list.add(val);
        idxSet.add(list.size()-1);
        valToIdx.put(val, idxSet);
        return true;
    }

    public boolean remove(int val) {
        if(!valToIdx.containsKey(val)) return false;
        Set<Integer> idxSet = valToIdx.get(val);
        int idx = idxSet.iterator().next();
        idxSet.remove(idx);

        // if that was the only element, then we should remove it from the other data structs
        if(idxSet.size() == 0) {
            valToIdx.remove(val);
        }

        // switch the last element in the list with the removed element
        // but if there was only 1 element in the list, we can skip this step
        if(list.size() > 1) {
            int last = list.remove(list.size() - 1);
            list.add(idx, last);
            list.remove(idx+1);
            Set<Integer> lastIdxSet = valToIdx.get(last);
            lastIdxSet.remove(list.size()-1);
            lastIdxSet.add(idx);
            valToIdx.put(last, lastIdxSet);
        } else {
            list.remove(0);
        }

        return true;
    }

    public int getRandom() {
        if(list.size() == 0) return -1;
        int randIdx = (int) (Math.random() * list.size());
        return list.get(randIdx);
    }

    // small test
    public static void main(String[] args) {
        RandDataSet rds = new RandDataSet();
        rds.insert(1);
        rds.insert(1);
        rds.insert(3);
        rds.insert(0);
        rds.insert(1);
        rds.remove(5);
        rds.insert(5);
        rds.insert(3);
        rds.remove(1);
        rds.insert(3);
        rds.insert(0);
        rds.remove(0);
        rds.remove(0);
        System.out.println(rds.list);
        int zc = 0, fc = 0, tc = 0, oc = 0;
        for(int i=0; i<10000; i++) {
            int r = rds.getRandom();
            if(r == 0) {
                zc++;
            } else if(r == 1) {
                oc++;
            } else if(r == 3) {
                tc++;
            } else if(r == 5) {
                fc++;
            }
        }
        System.out.println(zc);
        System.out.println(oc);
        System.out.println(tc);
        System.out.println(fc);
    }
}
