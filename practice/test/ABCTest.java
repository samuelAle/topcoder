import com.samuelale.practice.ABC;
import org.junit.Assert;
import org.junit.Test;

public class ABCTest {

    @Test
    public void testNAndKEqual3() {
        String s = ABC.createString(3,3);
        Assert.assertEquals("ABC", s);
    }

    @Test
    public void testNis3AndKis0() {
        String s = ABC.createString(3,0);
        Assert.assertEquals("CCB", s);
    }

    @Test
    public void testNis5AndKis10() {
        String s = ABC.createString(5,10);
        Assert.assertEquals("", s);
    }

    @Test
    public void testNis15AndKis36() {
        String s = ABC.createString(15,36);
        Assert.assertEquals("AAAAAAAAAAAACCC", s);
    }

    @Test
    public void testNis15AndKis69() {
        String s = ABC.createString(15,69);
        //                   3-B 10-B' 56-C
        Assert.assertEquals("AAABAABBCCCCCCC", s);
    }

    @Test
    public void testNis15AndKis72() {
        String s = ABC.createString(15,72);
        Assert.assertEquals("AAAABBBBCCCCCCC", s);
    }

    @Test
    public void testNis15AndKis63() {
        String s = ABC.createString(15,63);
        Assert.assertEquals("AAAAAAABCCCCCCC", s);
    }

    @Test
    public void testNis15AndKis47() {
        String s = ABC.createString(15,47);
        Assert.assertEquals("AAABAAAAAAACCCC", s);
    }

    @Test
    public void testNis16AndKis80() {
        String s = ABC.createString(16,80);
        Assert.assertEquals("AAAABBBBCCCCCCCC", s);
    }

    @Test
    public void testNis16AndKis81() {
        String s = ABC.createString(16,81);
        Assert.assertEquals("", s);
    }

    @Test
    public void testNis16AndKis47() {
        String s = ABC.createString(16,47);
        //                           8-B 39-C
        Assert.assertEquals("AAAAAAAABAAAACCC", s);
    }

    @Test
    public void testNis16AndKis69() {
        String s = ABC.createString(16,69);
        Assert.assertEquals("AAAAABAACCCCCCCC", s);
    }

    @Test
    public void testNis16AndKis13() {
        String s = ABC.createString(16,11);
        Assert.assertEquals("CCCCAAAAAAAAAAAB", s);
    }

    @Test
    public void testNis8AndKis8() {
        String s = ABC.createString(8,8);
        Assert.assertEquals("ABCBBBBB", s);
    }

    @Test
    public void testNis28AndKis210() {
        String s = ABC.createString(28,210);
        Assert.assertEquals("AABAAAAAAAAAABCCCCCCCCCCCCCC", s);
    }

    @Test
    public void test10000RandomEntries() {
        for(int i=0; i<10000; i++) {
            int n = (int) (Math.random()*28) + 3;
            int maxK;
            if(n%2==0) {
                maxK = (n/2)*(n/2) + (n/4)*(n/4);
            } else {
                maxK = (n/2)*(n/2+1) + (n/4)*(n/4);
            }
            int k = (int) (Math.random() * maxK);
            String s = ABC.createString(n, k);
            int expK = getK(s);

            Assert.assertEquals("\nn="+n+"\nk="+k+"\ns="+s+"\n", n, s.length());
            Assert.assertEquals("\nn="+n+"\nk="+k+"\ns="+s+"\n", k, expK);
        }
    }

    private int getK(String s) {
        char[] chars = s.toCharArray();
        int result = 0;

        for(int i=0; i<chars.length-1; i++) {
            for(int j=i+1; j<chars.length; j++) {
                if(chars[i] < chars[j]) result++;
            }
        }

        return result;
    }

}

/*
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
