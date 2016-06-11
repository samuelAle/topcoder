package com.samuelale.practice;

public class ABC {

    public static String createString(int n, int k) {
        char[] result = new char[n];
        // we need more pairs than there are spaces
        if(k > n) {

            // initialize string with A's
            for(int i=0; i<n-1; i++) result[i] = 'A';

            // track c-index and number of pairs
            int cIdx = n-1;
            int cPairs;

            // while we haven't exceeded the number of pairs
            // and we haven't filled more than half of the string with C's
            do {
                // place a C at the end of the string and update vars
                result[cIdx] = 'C';
                cPairs = (n-cIdx) * cIdx;
                cIdx--;
            }
            while(cPairs < k && cIdx > n/2);

            // if we haven't reached our goal then lets place some B's
            if(cPairs != k) {
                // track the b-index
                int bN = cIdx+1;
                int bK = k-cPairs;
                int bPairs = 0;
                int bIdx = bN-1;
                do {
                    result[bIdx] = 'B';
                    bPairs = (bN-bIdx) * bIdx;
                    bIdx--;
                }
                while(bPairs < bK && bIdx > bN/2);

                if(bPairs+cPairs != k) {
                    if(bIdx <= bN/2) {
                        return "";
                    }
                    bIdx++;
                    result[bIdx] = 'A';
                    bPairs = (cIdx - bIdx)*bIdx;
                    int pairsLeft = bK-bPairs;
                    result[pairsLeft] = 'B';
                }
            }

        }
        // we have more spaces than there are pairs
        else if (k < n) {
            int gap = n-k-1;
            for(int i=0; i<gap; i++) result[i] = 'C';
            for(int i=gap; i<k; i++) result[i] = 'A';
            result[n-1] = 'B';
        } else {
            result[0] = 'A';
            result[1] = 'B';
            result[2] = 'C';
            for(int i=3; i<n; i++) result[i] = 'B';
        }

        return String.valueOf(result);
    }

    public static void main(String[] args) {
        System.out.println("Answer: " + createString(5,10));
    }
}

/*
Problem Statement

You are given two s: N and K. Lun the dog is interested in strings that satisfy the following conditions:

    The string has exactly N characters, each of which is either 'A', 'B' or 'C'.
    The string s has exactly K pairs (i, j) (0 <= i < j <= N-1) such that s[i] < s[j].

If there exists a string that satisfies the conditions, find and return any such string. Otherwise, return an empty string.
Definition
Class:
ABC
Method:
createString
Parameters:
int, int
Returns:
String
Method signature:
String createString(int N, int K)
(be sure your method is public)
Limits
Time limit (s):
2.000
Memory limit (MB):
256
Constraints
- N will be between 3 and 30, inclusive.
- K will be between 0 and N(N-1)/2, inclusive.
Examples
0)
3
3
Returns: "ABC"
This string has exactly three pairs (i, j) mentioned in the statement: (0, 1), (0, 2) and (1, 2).
1)
3
0
Returns: "CBA"
Please note that there are valid test cases with K = 0.
2)
5
10
Returns: ""
Five characters is too short for this value of K.
3)
15
36
Returns: "CABBACCBAABCBBB"
Please note that this is an example of a solution; other valid solutions will also be accepted.

 */