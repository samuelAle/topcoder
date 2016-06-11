package com.samuelale.practice;

import java.util.HashSet;
import java.util.Set;


public class ABBADiv1 {

    private static final String FAIL = "Impossible";
    private static final String PASS = "Possible";

    private static String canObtain(String initial, String target) {
        int len = target.length();
        Set<String> temp = new HashSet<>();
        temp.add(target);

        while(initial.length() != len) {
            Set<String> nTemp = new HashSet<>();
            for(String t : temp) {
                if(t.endsWith("A")) nTemp.add(t.substring(0,t.length()-1));
                if(t.startsWith("B")) nTemp.add(reverse(t.substring(1)));
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
        System.out.println("Test0: " + canObtain("A", "BABA"));
        System.out.println("Test1: " + canObtain("BAAAAABAA", "BAABAAAAAB"));
        System.out.println("Test2: " + canObtain("A", "ABBA"));
        System.out.println("Test3: " + canObtain("AAABBAABB", "BAABAAABAABAABBBAAAAAABBAABBBBBBBABB"));
        System.out.println("Test4: " + canObtain("AAABAAABB", "BAABAAABAABAABBBAAAAAABBAABBBBBBBABB"));
        System.out.println("Test5:" + canObtain("B","BBBBB"));
        System.out.println("Test6: " + canObtain("BA","ABB"));
    }
}

/*
One day, Jamie noticed that many English words only use the letters A and B. Examples of such words include "AB" (short for abdominal), "BAA" (the noise a sheep makes), "AA" (a type of lava), and "ABBA" (a Swedish pop sensation).

Inspired by this observation, Jamie created a simple game. You are given two s: initial and target. The goal of the game is to find a sequence of valid moves that will change initial into target. There are two types of valid moves:

    Add the letter A to the end of the string.
    Add the letter B to the end of the string and then reverse the entire string. (After the reversal the newly-added B becomes the first character of the string).

Return "Possible" (quotes for clarity) if there is a sequence of valid moves that will change initial into target. Otherwise, return "Impossible".
Definition
Class:
ABBADiv1
Method:
canObtain
Parameters:
String, String
Returns:
String
Method signature:
String canObtain(String initial, String target)
(be sure your method is public)
Limits
Time limit (s):
2.000
Memory limit (MB):
256
Constraints
- The length of initial will be between 1 and 49, inclusive.
- The length of target will be between 2 and 50, inclusive.
- target will be longer than initial.
- Each character in initial and each character in target will be either 'A' or 'B'.

Examples
0)
"A"
"BABA"
Returns: "Possible"
Jamie can perform the following moves:

    Initially, the string is "A".
    Jamie adds a 'B' to the end of the string and then reverses the string. Now the string is "BA".
    Jamie adds a 'B' to the end of the string and then reverses the string. Now the string is "BAB".
    Jamie adds an 'A' to the end of the string. Now the string is "BABA".

Since there is a sequence of moves which starts with "A" and creates the string "BABA", the answer is "Possible".
1)
"BAAAAABAA"
"BAABAAAAAB"
Returns: "Possible"
Jamie can add a 'B' to the end of the string and then reverse the string.
2)
"A"
"ABBA"
Returns: "Impossible"
3)
"AAABBAABB"
"BAABAAABAABAABBBAAAAAABBAABBBBBBBABB"
Returns: "Possible"
4)
"AAABAAABB"
"BAABAAABAABAABBBAAAAAABBAABBBBBBBABB"
Returns: "Impossible"


 */