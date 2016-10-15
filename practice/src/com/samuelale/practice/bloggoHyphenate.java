package com.samuelale.practice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 Problem Statement

 Bloggo is a system for managing weblog content.
 It offers a number of natural-language editing services, among which is a hyphenation checker.
 Hyphenation is the insertion of a hyphen character, '-', between two syllables of a word.
 Although it is possible to use an automatic hyphenation algorithm to achieve uniform line length,
 some users prefer to hyphenate manually. These users can set the hyphenation checker to lazy mode,
 which restricts it to verifying words that already contain a hyphen.

 Your fellow bloggo developers have already written code that identifies hyphenated
 words in a user's file. Your job is to verify these words and correct them as necessary.
 As a guide to proper hyphenation, you are given a named dictionary, each element of which
 contains a word separated into its syllables by single spaces. For example, the syllabization
 of the words "syllable", "hyphenate", and "antidisestablishmentarianism" would be represented as follows.


 syl la ble

 hy phen ate

 an ti dis es tab lish men tar i an ism

 You are given another , candidates, each element of which contains a word drawn from dictionary,
 but without the spaces and with the addition of a single hyphen. If the hyphen is correctly
 positioned between two syllables of the word, this is to be signaled with the result "correct".
 Otherwise, move the hyphen from its erroneous position to the nearest correct position.
 In the event of a tie, choose the position nearer to the left.
 If the word is actually monosyllabic, remove the hyphen altogether.
 Return a containing, for each element of candidates, the corresponding result .

 Definition
 Class:
 bloggoHyphenate
 String[] correct(String[] dictionary, String[] candidates)
 (be sure your method is public)
 Limits
 Time limit (s):
 840.000
 Memory limit (MB):
 64

 Constraints
 - dictionary contains between 1 and 50 elements, inclusive
 - each element of dictionary is between 1 and 50 characters long, inclusive
 - in each element of dictionary, neither the first nor the last character may be a space
 - in each element of dictionary, there may not be two consecutive spaces
 - the only characters allowed in dictionary are the space character and the lowercase letters 'a' to 'z'
 - each element of dictionary is unique after removal of spaces
 - candidates contains between 1 and 50 elements, inclusive
 - each element of candidates is between 1 and 50 characters long, inclusive
 - each element of candidates contains exactly one hyphen
 - for each element of candidates, if the hyphen were removed, it would match an element of dictionary after removal of spaces
 Examples
 0)
 {"syl la ble", "dic tion a ry", "mole"}
 {"mo-le", "syl-lable"}
 Returns: { "mole", "correct" }
 The word "mole" is monosyllabic, so we remove the hyphen. The word "syllable" is correctly hyphenated.
 1)
 {"syl la ble", "dic tion a ry", "mole"}
 {"dictio-nary", "syll-able"}
 Returns: { "diction-ary", "syl-lable" }
 We correct "dictio-nary" by moving the hyphen one position to the right. The hyphen in "syll-able" could be moved one position to the left or to the right, but we prefer the left.
 2)
 {"syl la ble", "dic tion a ry", "mole"}
 {"dictionary-", "-syllable"}
 Returns: { "dictiona-ry", "syl-lable" }
 Poorly placed hyphens indeed.
 3)
 {"zsx kd ds jply rf", "auca wb ill", "kvqm kpd p kqi xpbk j", "cqf ccr ewq", "srl vu m u d b",
 "h qae qzge", "jdn qqjf y", "zpa hbr", "d uft ctsm qjg", "l hgcp ik twuy bsi nlmu iyx",
 "cjjz jn td zg", "h bd q hi", "obsr kci hjdm h yar zsq q xyr hxb", "izq iibd yuh tgx nkq",
 "cjgd wfns", "ohc bn duc", "pydv a geki s bo avdi wd", "zygp gqt p", "xq pqqb ufoe",
 "cvm yamq moqj vl zoig w", "ahz b lf ixjv d", "qpg ven d", "m cbxz yf tv djf", "kti wacd uvgb kmr ew r",
 "ng tfb unw ndl", "qruv ctw a", "d hk v pcxh zoxy", "etbo wabk", "x hxiu n", "wp qq u dvpi tclh ac l",
 "f am brs lk uss uyb pg", "dtfx h ict if koe jhj", "nl shp yeji b rw vrg", "bz qfnc u",
 "t ceoy eb ss myoe zaaj z jey", "j d uju mb irvu", "anmo emjs syam ta bfiy kk", "gl ej egla tha jbug",
 "zk u sz raij d", "kw lbya atdb pd", "no id hqum u wny qcy", "phv nv l f oq m", "b xup tnq fziw yth sn",
 "pvc hnr oky lzj", "ew wtwt gyty s uu pfe kif my", "t cw fsau", "esk gqtd o yh jbx wed fl z",
 "vvr hqw wo gxwm", "lei akz nzz bumb", "bc hlmw"}
 {"-duftctsmqjg", "-leiakznzzbumb", "-xhxiun", "-zpahbr", "ahzbl-fixjvd", "anmo-emjssyamtabfiykk",
 "aucawb-ill", "bchlmw-", "bxuptnqfziwyth-sn", "bzq-fncu", "c-vmyamqmoqjvlzoigw", "cjg-dwfns", "cjjzjn-tdzg",
 "cqfccre-wq", "dhkvpcxhzox-y", "dtfx-hictifkoejhj", "eskgqtdoyhjbx-wedflz", "etbowab-k",
 "ewwtwtgytysuupfe-kifmy", "fambrslkuss-uybpg", "glejegl-athajbug", "hbdqhi-", "hq-aeqzge",
 "izqiibd-yuhtgxnkq", "jd-nqqjfy", "jdujumb-irvu", "ktiwacduvgbkmr-ewr", "kv-qmkpdpkqixpbkj",
 "kwlbya-atdbpd", "lhgcpiktwuybsi-nlmuiyx", "mcbx-zyftvdjf", "ngtf-bunwndl", "nlshpyejibrwvrg-",
 "no-idhqumuwnyqcy", "o-hcbnduc", "obsr-kcihjdmhyarzsqqxyrhxb", "ph-vnvlfoqm", "pvchnrok-ylzj",
 "pydvageki-sboavdiwd", "qpgv-end", "qruvctw-a", "srlvumu-db", "tceoyebssmyo-ezaajzjey", "tcwfsau-",
 "vvrhqwwo-gxwm", "wpqqudvpit-clhacl", "xqpq-qbufoe", "zku-szraijd", "zsxkddsjp-lyrf", "zygpgqt-p"}
 Returns: { "d-uftctsmqjg", "lei-akznzzbumb", "x-hxiun", "zpa-hbr", "ahzb-lfixjvd", "correct",
 "correct", "bc-hlmw", "correct", "bz-qfncu", "cvm-yamqmoqjvlzoigw", "cjgd-wfns", "correct",
 "cqfccr-ewq", "dhkvpcxh-zoxy", "correct", "correct", "etbo-wabk", "correct", "correct",
 "glejegla-thajbug", "hbdq-hi", "h-qaeqzge", "correct", "jdn-qqjfy", "correct", "correct",
 "kvqm-kpdpkqixpbkj", "correct", "correct", "mcbxz-yftvdjf", "ngtfb-unwndl", "nlshpyejibrw-vrg",
 "correct", "ohc-bnduc", "correct", "phv-nvlfoqm", "pvchnroky-lzj", "correct", "qpg-vend", "correct",
 "correct", "tceoyebssmyoe-zaajzjey", "tcw-fsau", "correct", "wpqqudvpi-tclhacl", "xq-pqqbufoe",
 "correct", "zsxkdds-jplyrf", "correct" }

 */
public class bloggoHyphenate {

    public String[] correct(String[] dictionary, String[] candidate) {
        // make a real dictionary
        HashMap<String, Integer[]> wordToSyl = new HashMap<>(dictionary.length*2);
        for(String d : dictionary) {
            List<Integer> sylIdxs = new ArrayList<>();
            for(int i=0; i<d.length(); i++)
                if(d.charAt(i) == ' ')
                    sylIdxs.add(i-sylIdxs.size());

            wordToSyl.put(d.replaceAll(" ", ""), sylIdxs.toArray(new Integer[sylIdxs.size()]));
        }

        int count = 0;
        String[] result = new String[candidate.length];
        for(String cand : candidate) {
            Integer[] sylIdxs = wordToSyl.get(cand.replaceAll("-", ""));
            int candIdx = cand.indexOf('-');

            int closestIdx = -1;
            int bestDist = Integer.MAX_VALUE;
            for(int idx : sylIdxs) {
                int dist = Math.abs(idx-candIdx);
                if(dist < bestDist || (dist == bestDist && idx < closestIdx)) {
                    bestDist = dist;
                    closestIdx = idx;
                }
            }

            String candRes = cand.replaceAll("-", "");
            if(closestIdx != -1)
                candRes = candRes.substring(0, closestIdx) + "-" + candRes.substring(closestIdx);
            result[count] = candRes.equals(cand) ? "correct" : candRes;
            count++;
        }

        return result;
    }

    public static void main(String[] args) {
        String[] actual = new bloggoHyphenate().correct(
                new String[]{"zsx kd ds jply rf", "auca wb ill", "kvqm kpd p kqi xpbk j", "cqf ccr ewq", "srl vu m u d b",
                        "h qae qzge", "jdn qqjf y", "zpa hbr", "d uft ctsm qjg", "l hgcp ik twuy bsi nlmu iyx",
                        "cjjz jn td zg", "h bd q hi", "obsr kci hjdm h yar zsq q xyr hxb", "izq iibd yuh tgx nkq",
                        "cjgd wfns", "ohc bn duc", "pydv a geki s bo avdi wd", "zygp gqt p", "xq pqqb ufoe",
                        "cvm yamq moqj vl zoig w", "ahz b lf ixjv d", "qpg ven d", "m cbxz yf tv djf", "kti wacd uvgb kmr ew r",
                        "ng tfb unw ndl", "qruv ctw a", "d hk v pcxh zoxy", "etbo wabk", "x hxiu n", "wp qq u dvpi tclh ac l",
                        "f am brs lk uss uyb pg", "dtfx h ict if koe jhj", "nl shp yeji b rw vrg", "bz qfnc u",
                        "t ceoy eb ss myoe zaaj z jey", "j d uju mb irvu", "anmo emjs syam ta bfiy kk", "gl ej egla tha jbug",
                        "zk u sz raij d", "kw lbya atdb pd", "no id hqum u wny qcy", "phv nv l f oq m", "b xup tnq fziw yth sn",
                        "pvc hnr oky lzj", "ew wtwt gyty s uu pfe kif my", "t cw fsau", "esk gqtd o yh jbx wed fl z",
                        "vvr hqw wo gxwm", "lei akz nzz bumb", "bc hlmw"},
                new String[]{"-duftctsmqjg", "-leiakznzzbumb", "-xhxiun", "-zpahbr", "ahzbl-fixjvd", "anmo-emjssyamtabfiykk",
                        "aucawb-ill", "bchlmw-", "bxuptnqfziwyth-sn", "bzq-fncu", "c-vmyamqmoqjvlzoigw", "cjg-dwfns", "cjjzjn-tdzg",
                        "cqfccre-wq", "dhkvpcxhzox-y", "dtfx-hictifkoejhj", "eskgqtdoyhjbx-wedflz", "etbowab-k",
                        "ewwtwtgytysuupfe-kifmy", "fambrslkuss-uybpg", "glejegl-athajbug", "hbdqhi-", "hq-aeqzge",
                        "izqiibd-yuhtgxnkq", "jd-nqqjfy", "jdujumb-irvu", "ktiwacduvgbkmr-ewr", "kv-qmkpdpkqixpbkj",
                        "kwlbya-atdbpd", "lhgcpiktwuybsi-nlmuiyx", "mcbx-zyftvdjf", "ngtf-bunwndl", "nlshpyejibrwvrg-",
                        "no-idhqumuwnyqcy", "o-hcbnduc", "obsr-kcihjdmhyarzsqqxyrhxb", "ph-vnvlfoqm", "pvchnrok-ylzj",
                        "pydvageki-sboavdiwd", "qpgv-end", "qruvctw-a", "srlvumu-db", "tceoyebssmyo-ezaajzjey", "tcwfsau-",
                        "vvrhqwwo-gxwm", "wpqqudvpit-clhacl", "xqpq-qbufoe", "zku-szraijd", "zsxkddsjp-lyrf", "zygpgqt-p"});

        String[] expected = new String[]{ "d-uftctsmqjg", "lei-akznzzbumb", "x-hxiun", "zpa-hbr", "ahzb-lfixjvd", "correct",
                "correct", "bc-hlmw", "correct", "bz-qfncu", "cvm-yamqmoqjvlzoigw", "cjgd-wfns", "correct",
                "cqfccr-ewq", "dhkvpcxh-zoxy", "correct", "correct", "etbo-wabk", "correct", "correct",
                "glejegla-thajbug", "hbdq-hi", "h-qaeqzge", "correct", "jdn-qqjfy", "correct", "correct",
                "kvqm-kpdpkqixpbkj", "correct", "correct", "mcbxz-yftvdjf", "ngtfb-unwndl", "nlshpyejibrw-vrg",
                "correct", "ohc-bnduc", "correct", "phv-nvlfoqm", "pvchnroky-lzj", "correct", "qpg-vend", "correct",
                "correct", "tceoyebssmyoe-zaajzjey", "tcw-fsau", "correct", "wpqqudvpi-tclhacl", "xq-pqqbufoe",
                "correct", "zsxkdds-jplyrf", "correct" };

        for(int i=0; i<actual.length; i++) {
            if(actual[i].equals(expected[i])) {
                System.out.print("Success");
            } else {
                System.out.println("Fail: " + actual[i] + " " + expected[i]);
            }
        }
    }
}
