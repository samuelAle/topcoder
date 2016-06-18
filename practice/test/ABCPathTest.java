import com.samuelale.practice.ABCPath;
import org.junit.Assert;
import org.junit.Test;

public class ABCPathTest {

    @Test
    public void testExampleResult4() {
        String[] s = {
                "ABE",
                "CFG",
                "BDH",
                "ABC" };
        int res = new ABCPath().length(s);
        Assert.assertEquals(4, res);
    }

    @Test
    public void testExampleResult1() {
        String[] s = { "A" };
        int res = new ABCPath().length(s);
        Assert.assertEquals(1, res);
    }

    @Test
    public void testExampleResult0() {
        String[] s = { "BCDEFGHIJKLMNOPQRSTUVWXYZ" };
        int res = new ABCPath().length(s);
        Assert.assertEquals(0, res);
    }

    @Test
    public void testExampleResult2() {
        String[] s = { "C", "D", "B", "A" };
        int res = new ABCPath().length(s);
        Assert.assertEquals(2, res);
    }

    @Test
    public void testExampleResult19() {
        String[] s = {
                "KCBVNRXSPVEGUEUFCODMOAXZYWEEWNYAAXRBKGACSLKYRVRKIO",
                "DIMCZDMFLAKUUEPMPGRKXSUUDFYETKYQGQHNFFEXFPXNYEFYEX",
                "DMFRPZCBOWGGHYAPRMXKZPYCSLMWVGMINAVRYUHJKBBRONQEXX",
                "ORGCBHXWMTIKYNLFHYBVHLZFYRPOLLAMBOPMNODWZUBLSQSDZQ",
                "QQXUAIPSCEXZTTINEOFTJDAOBVLXZJLYOQREADUWWSRSSJXDBV",
                "PEDHBZOVMFQQDUCOWVXZELSEBAMBRIKBTJSVMLCAABHAQGBWRP",
                "FUSMGCSCDLYQNIXTSTPJGZKDIAZGHXIOVGAZHYTMIWAIKPMHTJ",
                "QMUEDLXSREWNSMEWWRAUBFANSTOOJGFECBIROYCQTVEYGWPMTU",
                "FFATSKGRQJRIQXGAPLTSXELIHXOPUXIDWZHWNYUMXQEOJIAJDH",
                "LPUTCFHYQIWIYCVOEYHGQGAYRBTRZINKBOJULGYCULRMEOAOFP",
                "YOBMTVIKVJOSGRLKTBHEJPKVYNLJQEWNWARPRMZLDPTAVFIDTE",
                "OOBFZFOXIOZFWNIMLKOTFHGKQAXFCRZHPMPKGZIDFNBGMEAXIJ",
                "VQQFYCNJDQGJPYBVGESDIAJOBOLFPAOVXKPOVODGPFIYGEWITS",
                "AGVBSRLBUYOULWGFOFFYAAONJTLUWRGTYWDIXDXTMDTUYESDPK",
                "AAJOYGCBYTMXQSYSPTBWCSVUMNPRGPOEAVVBGMNHBXCVIQQINJ",
                "SPEDOAHYIDYUJXGLWGVEBGQSNKCURWYDPNXBZCDKVNRVEMRRXC",
                "DVESXKXPJBPSJFSZTGTWGAGCXINUXTICUCWLIBCVYDYUPBUKTS",
                "LPOWAPFNDRJLBUZTHYVFHVUIPOMMPUZFYTVUVDQREFKVWBPQFS",
                "QEASCLDOHJFTWMUODRKVCOTMUJUNNUYXZEPRHYOPUIKNGXYGBF",
                "XQUPBSNYOXBPTLOYUJIHFUICVQNAWFMZAQZLTXKBPIAKXGBHXX" };

        int res = new ABCPath().length(s);
        Assert.assertEquals(19, res);
    }

    @Test
    public void testExampleResult3() {
        String[] s = { "EDCCBA", "EDCCBA" };
        int res = new ABCPath().length(s);
        Assert.assertEquals(3, res);
    }

    @Test
    public void testExampleResult26() {
        String[] s = { "AMNOPA", "ALEFQR", "KDABGS", "AJCHUT", "AAIWVA", "AZYXAA" };
        int res = new ABCPath().length(s);
        Assert.assertEquals(26, res);
    }

    @Test
    public void testBottomLeftCornerResult9() {
        String[] s = {
                "ABCDE",
                "BUVWX",
                "CZLIF",
                "DERSI",
                "EFGHI"
        };
        int res = new ABCPath().length(s);
        Assert.assertEquals(9, res);
    }

    @Test
    public void testBottomRightCornerResult9() {
        String[] s = {
                "EBCDA",
                "XUVWB",
                "FZLIC",
                "IERSD",
                "IHGFE"
        };
        int res = new ABCPath().length(s);
        Assert.assertEquals(9, res);
    }

    @Test
    public void testTopLeftCornerResult9() {
        String[] s = {
                "EFGHI",
                "DUVWX",
                "CZLIF",
                "BERSI",
                "AFGHI"
        };
        int res = new ABCPath().length(s);
        Assert.assertEquals(9, res);
    }

    @Test
    public void testTopRightCornerResult9() {
        String[] s = {
                "ABCDE",
                "BUVWF",
                "CZLIG",
                "DERSH",
                "ZFGHI"
        };
        int res = new ABCPath().length(s);
        Assert.assertEquals(9, res);
    }

    @Test
    public void testDrawOnThePerimeter() {
        String[] s = {
                "ABCDE",
                "BUVWF",
                "CZLIG",
                "DERSH",
                "EFGHI"
        };
        int res = new ABCPath().length(s);
        Assert.assertEquals(9, res);
    }

    @Test
    public void testCenterResult6() {
        String[] s = {
                "ABCBD",
                "ADEFA",
                "CACIB",
                "DBRSL",
                "DRXAC"
        };
        int res = new ABCPath().length(s);
        Assert.assertEquals(6, res);
    }

    @Test
    public void testDrawOnTheInside() {
        String[] s = {
                "ZIZIZ",
                "ZABCZ",
                "IABCI",
                "ZABCZ",
                "ZIZYI"
        };
        int res = new ABCPath().length(s);
        Assert.assertEquals(3, res);
    }

    @Test
    public void testFullGrid() {
        String[] s = {
                "APONM",
                "BQXYL",
                "CRWVK",
                "DSTUJ",
                "EFGHI"
        };
        int res = new ABCPath().length(s);
        Assert.assertEquals(25, res);
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