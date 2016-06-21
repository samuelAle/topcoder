package com.samuelale.practice;

/**
 Problem Statement

 Your friends Alice and Bob are playing a board game.
 They have asked you to help them to determine the winner.
 The game is played on a square board with 2N rows and 2N columns.
 The exact rules of the game itself are not important for this problem.
 Once the game is over, each cell of the board is either empty or contains
 a single piece that belongs to either Alice or Bob. You are given board,
 where the j-th character in i-th element (0-based indices) describes the
 contents of the cell in row i, column j: '.' represents an empty cell, 'A'
 a cell with Alice's piece and 'B' a cell with Bob's piece.

 The entire board is divided into N regions.
 Region 1 occupies the 4 central cells of the board.
 Each next region contains all cells that are horizontally,
 vertically or diagonally adjacent to cells of the immediately previous
 region and do not belong to any of the previous regions. For example, when N = 4,
 here is how the regions look:

 44444444
 43333334
 43222234
 43211234
 43211234
 43222234
 43333334
 44444444

 The winner is determined as follows. Consider the lowest numbered region
 that contains a different number of Alice's and Bob's pieces. The player
 who has more pieces in this region is the winner. If all regions contain
 the same number of Alice's and Bob's pieces, the game ends in a draw.

 Return "Alice" if Alice wins the given game,
 "Bob" if Bob wins and "Draw" if the game ends in a draw. Note that return values are case-sensitive.

 Definition
 Class:
 ABoardGame
 Method:
 whoWins
 Parameters:
 String[]
 Returns:
 String
 Method signature:
 String whoWins(String[] board)
 (be sure your method is public)
 Limits
 Time limit (s):
 2.000
 Memory limit (MB):
 256
 Constraints
 - board will contain between 2 and 50 elements, inclusive.
 - The number of elements in board will be even.
 - Each element of board will contain the same number of characters as the number of elements in board.
 - Each character in board will be 'A', 'B' or '.'.

*/
public class ABoardGame {
    private static final String ALICE = "Alice";
    private static final String BOB = "Bob";
    private static final String DRAW = "Draw";

    public String whoWins(String[] board) {
        int bLen = board.length-1;
        int region = bLen/2;

        while(region >= 0) {
            int bCount = 0;
            int aCount = 0;
            // top row, left to right
            for(int i=region; i<bLen-region; i++) {
                if(board[region].charAt(i) == 'A') aCount++;
                else if(board[region].charAt(i) == 'B') bCount++;
            }
            // right column, top to bottom
            for(int i=region; i<bLen-region; i++) {
                if(board[i].charAt(bLen-region) == 'A') aCount++;
                else if(board[i].charAt(bLen-region) == 'B') bCount++;
            }
            // bottom row, right to left
            for(int i=bLen-region; i>region; i--) {
                if(board[bLen-region].charAt(i) == 'A') aCount++;
                else if(board[bLen-region].charAt(i) == 'B') bCount++;
            }
            // left column, bottom to top
            for(int i=bLen-region; i>region; i--) {
                if(board[i].charAt(region) == 'A') aCount++;
                else if(board[i].charAt(region) == 'B') bCount++;
                System.out.println(Runtime.getRuntime().totalMemory());
            }

            if(aCount > bCount) return ALICE;
            else if(aCount < bCount) return BOB;
            else region--;
                System.out.println(Runtime.getRuntime().totalMemory());
        }
                System.out.println(Runtime.getRuntime().freeMemory());

        return DRAW;
    }


    public static void main(String[] args) {
        String[] board = {
                "BBB..BAB...B.B",
                ".AAAAAAAAAAAA.",
                "AA.AA.AB..A.AB",
                "..........B.AB",
                ".A..BBAB.A.BAB",
                ".AB.B.......A.",
                ".A..A.AB.A..AB",
                ".ABAA.BA...BA.",
                "BAAAB.....ABA.",
                ".A....B..A..B.",
                "B...B....B..A.",
                "BA.B..A.ABA.A.",
                "BAAAA.AAAAA.A.",
                "B.B.B.BB.B...."
        };

        String res = new ABoardGame().whoWins(board);
        System.out.println(res);
    }
}








































