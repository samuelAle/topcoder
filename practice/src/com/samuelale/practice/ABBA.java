package com.samuelale.practice;

import java.util.HashSet;
import java.util.Set;

public class ABBA {

    private static final String FAIL = "Impossible";
    private static final String PASS = "Possible";

//    private static String canObtain(String initial, String target) {
//        if(initial.equals(target)) return PASS;
//        else if(initial.length() == target.length()) return FAIL;
//
//        if(canObtain(initial+"A", target).equals(PASS)) return PASS;
//        if(canObtain(reverse(initial) + "B", target).equals(PASS)) return PASS;
//
//        return FAIL;
//    }

    private static String canObtain(String initial, String target) {
        int len = target.length();
        Set<String> temp = new HashSet<>();
        temp.add(target);

        while(initial.length() != len) {
            Set<String> nTemp = new HashSet<>();
            for(String t : temp) {
                if(t.endsWith("A")) nTemp.add(t.substring(0,t.length()-1));
                else if(t.endsWith("B")) nTemp.add(reverse(t.substring(0,t.length()-1)));
            }
            len--;

            temp.clear();
            temp.addAll(nTemp);
        }

        if(temp.contains(initial)) return PASS;
        else return FAIL;
    }

    private static String reverse(String initial) {
        int len = initial.length();
        char[] arr = initial.toCharArray();
        for(int i=0; i<len/2; i++) {
            char temp = arr[i];
            arr[i] = arr[len-i-1];
            arr[len-i-1] = temp;
        }
        return String.valueOf(arr);
    }

    public static void main(String[] args) {
        System.out.println("Test1: " + canObtain("AB","ABB"));
        System.out.println("Test2: " + canObtain("BBAB", "ABABABABB"));
        System.out.println("Test3: " + canObtain("BBBBABABBBBBBA", "BBBBABABBABBBBBBABABBBBBBBBABAABBBAA"));
        System.out.println("Test4: " + canObtain("A","BB"));
        System.out.println("Test5: " + canObtain("B","BBBBB"));
        System.out.println("Test6: " + canObtain("BA","ABB"));
    }
}


/*

Problem Statement

One day, Jamie noticed that many English words only use the letters A and B. Examples of such words include "AB" (short for abdominal), "BAA" (the noise a sheep makes), "AA" (a type of lava), and "ABBA" (a Swedish pop sensation).

Inspired by this observation, Jamie created a simple game. You are given two s: initial and target. The goal of the game is to find a sequence of valid moves that will change initial into target. There are two types of valid moves:

    Add the letter A to the end of the string.
    Reverse the string and then add the letter B to the end of the string.

Return "Possible" (quotes for clarity) if there is a sequence of valid moves that will change initial into target. Otherwise, return "Impossible".

Definition
Class:
    ABBA
Method signature:
    public String canObtain(String initial, String target)
    (be sure your method is public)
Limits
Time limit (s):
2.000
Memory limit (MB):
256
Constraints
- The length of initial will be between 1 and 999, inclusive.
- The length of target will be between 2 and 1000, inclusive.
- target will be longer than initial.
- Each character in initial and each character in target will be either 'A' or 'B'.

Since there is a sequence of moves which starts with "B" and creates the string "ABBA", the answer is "Possible".
1)
"AB"
"ABB"
Returns: "Impossible"
The only strings of length 3 Jamie can create are "ABA" and "BAB".
2)
"BBAB"
"ABABABABB"
Returns: "Impossible"
3)
"BBBBABABBBBBBA"
"BBBBABABBABBBBBBABABBBBBBBBABAABBBAA"
Returns: "Possible"
4)
"A"
"BB"
Returns: "Impossible"


 */