import com.samuelale.practice.ABoardGame;
import org.junit.Assert;
import org.junit.Test;


public class ABoardGameTest {
    private static final String ALICE = "Alice";
    private static final String BOB = "Bob";
    private static final String DRAW = "Draw";

    /**
     Both Alice and Bob have 1 piece in region 1, so they are tied there.
     In region 2, they have no pieces at all, so a tie again.
     Finally, in region 3 Alice has 1 piece, while Bob has none.
     So Alice is the winner of this game.
     */
    @Test
    public void testExample1() {
        String[] board = {
                ".....A",
                "......",
                "..A...",
                "...B..",
                "......",
                "......"
        };

        String res = new ABoardGame().whoWins(board);
        Assert.assertEquals(ALICE, res);
    }

    /**
     Even though Alice has 12 pieces and Bob just one, this one piece is enough for him to win.
     */
    @Test
    public void testExample2() {
        String[] board = {
                "AAAA",
                "A.BA",
                "A..A",
                "AAAA"
        };

        String res = new ABoardGame().whoWins(board);
        Assert.assertEquals(BOB, res);
    }

    /**
     The board can be entirely empty.
     */
    @Test
    public void testExample3() {
        String[] board = {
                "..", ".."
        };

        String res = new ABoardGame().whoWins(board);
        Assert.assertEquals(DRAW, res);
    }

    @Test
    public void testExample4() {
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
        Assert.assertEquals(ALICE, res);
    }

    @Test
    public void testExample5() {
        String[] board = {
                "..A..AAA..AA",
                "ABABB..AAAAA",
                "ABBBBBBBBBA.",
                "AABBBABABBAA",
                "...BABABABBA",
                "B.BA..A.BBA.",
                "AA.A..B.AB.B",
                "..BA.B.AABAA",
                "..ABABBBABA.",
                ".ABB.BBBBBAA",
                "ABAAA.AA.A.A",
                "A..AAA.AAA.A"
        };

        String res = new ABoardGame().whoWins(board);
        Assert.assertEquals(BOB, res);
    }

    @Test
    public void testExample6() {
        String[] board = {
                "B..ABAABBB",
                "B.........",
                "A..A.AA..B",
                "A.BBBAA..A",
                "B.AAAAB...",
                "A..BBBBB.A",
                "B..ABAABBA",
                "A......B.B",
                "B......A.A",
                "BA.AABBB.A"
        };

        String res = new ABoardGame().whoWins(board);
        Assert.assertEquals(DRAW, res);
    }
}
