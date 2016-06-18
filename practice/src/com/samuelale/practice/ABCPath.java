package com.samuelale.practice;

/**
 * Created by samuel on 6/11/16.
 */
public class ABCPath {
    private int runningScore = 0;
    private int maxScore = 0;

    /**
     * Method to find the length of the longest path of consecutive letters, starting at 'A'.
     * @param grid the grid to traverse
     * @return longest path of consecutive letters
     */
    public int length(String[] grid) {
        // find the index of all 'A' chars
        for(int row=0; row < grid.length; row++) {
            for(int col=0; col < grid[0].length(); col++) {
                // for each 'A' index, begin a search looking up, down, right, left for greater chars
                if('A' == grid[row].charAt(col)) {
                    beginSearch(grid, row, col);
                }
            }
        }

        return maxScore;
    }

    /*
    Given a valid row/col, this method will search for the next char in the sequence
    updating runningScore until its impossible, at which point maxScore will be
    updated if the runningScore has exceeded it.
     */
    private void beginSearch(final String[] grid, final int row, final int col) {
        runningScore++;

        // check up
        if(row>0 && grid[row].charAt(col)+1 == grid[row-1].charAt(col)) {
            beginSearch(grid, row-1, col);
        }
        // check down
        if(row<grid.length-1 && grid[row].charAt(col)+1 == grid[row+1].charAt(col)) {
            beginSearch(grid, row+1, col);
        }
        // check left
        if(col>0 && grid[row].charAt(col)+1 == grid[row].charAt(col-1)) {
            beginSearch(grid, row, col-1);
        }
        // check right
        if(col<grid[0].length()-1 && grid[row].charAt(col)+1 == grid[row].charAt(col+1)) {
            beginSearch(grid, row, col+1);
        }
        // check upper left
        if(row>0 && col>0 && grid[row].charAt(col)+1 == grid[row-1].charAt(col-1)) {
            beginSearch(grid, row-1, col-1);
        }
        // check upper right
        if(row>0 && col<grid[0].length()-1 && grid[row].charAt(col)+1 == grid[row-1].charAt(col+1)) {
            beginSearch(grid, row-1, col+1);
        }
        // check bottom left
        if(row<grid.length-1 && col>0 && grid[row].charAt(col)+1 == grid[row+1].charAt(col-1)) {
            beginSearch(grid, row+1, col-1);
        }
        // check bottom right
        if(row<grid.length-1 && col<grid[0].length()-1 && grid[row].charAt(col)+1 == grid[row+1].charAt(col+1)) {
            beginSearch(grid, row+1, col+1);
        }

        // update maxScore
        if(!(row>0 && grid[row].charAt(col)+1 == grid[row-1].charAt(col))
                && !(row<grid.length-1 && grid[row].charAt(col)+1 == grid[row+1].charAt(col))
                && !(col>0 && grid[row].charAt(col)+1 == grid[row].charAt(col-1))
                && !(col<grid[0].length()-1 && grid[row].charAt(col)+1 == grid[row].charAt(col+1))
                && !(row>0 && col>0 && grid[row].charAt(col)+1 == grid[row-1].charAt(col-1))
                && !(row>0 && col<grid[0].length()-1 && grid[row].charAt(col)+1 == grid[row-1].charAt(col+1))
                && !(row<grid.length-1 && col>0 && grid[row].charAt(col)+1 == grid[row+1].charAt(col-1))
                && !(row<grid.length-1 && col<grid[0].length()-1 && grid[row].charAt(col)+1 == grid[row+1].charAt(col+1))
                ) {
            if(maxScore<runningScore)
                maxScore = runningScore;
            runningScore = 0;
        }
    }
}

/*
Problem Statement

You will be given a 2-dimensional grid of letters.
Write a method to find the length of the longest path of consecutive letters, starting at 'A'.
Paths can step from one letter in the grid to any adjacent letter (horizontally, vertically, or diagonally).

For example, in the following grid, there are several paths from 'A' to 'D', but none from 'A' to 'E':

    { "ABE",
      "CFG",
      "BDH",
      "ABC" }

One such path is:

    A B .
    C . .
    . D .
    . . .
    (spaces are for clarity only)

so, for this grid, your method should return 4.
Definition
Class:
ABCPath
Method:
length
Parameters:
String[]
Returns:
int
Method signature:
int length(String[] grid)
(be sure your method is public)
Limits
Time limit (s):
840.000
Memory limit (MB):
64
Notes
- The longest path may start at any 'A' character in the input.
Constraints
- grid will contain between 1 and 50 elements, inclusive.
- Each element of grid will be between 1 and 50 characters long, inclusive.
- Each element of grid will have the same length.
- grid will contain only uppercase letters ('A'-'Z').
Examples
0)
{ "ABE", "CFG", "BDH", "ABC" }
Returns: 4
This is the example from the problem statement.
1)
{ "A" }
Returns: 1
2)
{ "BCDEFGHIJKLMNOPQRSTUVWXYZ" }
Returns: 0
Paths must start with an 'A'.
3)
{ "C", "D", "B", "A" }
Returns: 2
4)
{ "KCBVNRXSPVEGUEUFCODMOAXZYWEEWNYAAXRBKGACSLKYRVRKIO", "DIMCZDMFLAKUUEPMPGRKXSUUDFYETKYQGQHNFFEXFPXNYEFYEX", "DMFRPZCBOWGGHYAPRMXKZPYCSLMWVGMINAVRYUHJKBBRONQEXX", "ORGCBHXWMTIKYNLFHYBVHLZFYRPOLLAMBOPMNODWZUBLSQSDZQ", "QQXUAIPSCEXZTTINEOFTJDAOBVLXZJLYOQREADUWWSRSSJXDBV", "PEDHBZOVMFQQDUCOWVXZELSEBAMBRIKBTJSVMLCAABHAQGBWRP", "FUSMGCSCDLYQNIXTSTPJGZKDIAZGHXIOVGAZHYTMIWAIKPMHTJ", "QMUEDLXSREWNSMEWWRAUBFANSTOOJGFECBIROYCQTVEYGWPMTU", "FFATSKGRQJRIQXGAPLTSXELIHXOPUXIDWZHWNYUMXQEOJIAJDH", "LPUTCFHYQIWIYCVOEYHGQGAYRBTRZINKBOJULGYCULRMEOAOFP", "YOBMTVIKVJOSGRLKTBHEJPKVYNLJQEWNWARPRMZLDPTAVFIDTE", "OOBFZFOXIOZFWNIMLKOTFHGKQAXFCRZHPMPKGZIDFNBGMEAXIJ", "VQQFYCNJDQGJPYBVGESDIAJOBOLFPAOVXKPOVODGPFIYGEWITS", "AGVBSRLBUYOULWGFOFFYAAONJTLUWRGTYWDIXDXTMDTUYESDPK", "AAJOYGCBYTMXQSYSPTBWCSVUMNPRGPOEAVVBGMNHBXCVIQQINJ", "SPEDOAHYIDYUJXGLWGVEBGQSNKCURWYDPNXBZCDKVNRVEMRRXC", "DVESXKXPJBPSJFSZTGTWGAGCXINUXTICUCWLIBCVYDYUPBUKTS", "LPOWAPFNDRJLBUZTHYVFHVUIPOMMPUZFYTVUVDQREFKVWBPQFS", "QEASCLDOHJFTWMUODRKVCOTMUJUNNUYXZEPRHYOPUIKNGXYGBF", "XQUPBSNYOXBPTLOYUJIHFUICVQNAWFMZAQZLTXKBPIAKXGBHXX" }
Returns: 19
5)
{ "EDCCBA", "EDCCBA" }
Returns: 3
6)
{ "AMNOPA", "ALEFQR", "KDABGS", "AJCHUT", "AAIWVA", "AZYXAA" }
Returns: 26

 */