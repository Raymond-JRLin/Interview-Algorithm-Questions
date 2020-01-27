import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Snaps have text on them  - stream of inccoming posted stories
// "i just ate oranges" -> no
// "i am at a Beyonce concert" -> yes (lowercase doesn't matter)
// "i just watched the superbowl!!" -> yes (superbowl is a substring that is contained)

// trending topic terms -  {"superbowl", "beyonce", "🦊", "tom brady"}
// Max length of a snap text = 1000 chars
// Max length of a trending term -> 20 chars
// Number of trending terms (T) = 100,000
// Number of snap texts -> Inifinite stream

// init(Set<Terms>) -> Void  (once on startup/setup)
// contains(String snapText) -> Boolean (for every snap text) # optimize for this case

//

// for temp in Terms {
//  snapText.contains(term)
// }


// terms = {" "}
// text= "hello world"
// output? = TRUE

// terms = {"🦊"}
// text= "hello🦊world"
// output? = True

// terms = {"superbowl", "superbowl sunday"}
// text= "i am watching the superbowl"
// output? = True


public class Solution {
    public static void main(String args[] ) throws Exception {
        TrendingTerms tt = new TrendingTerms();
        Set<String> set = new HashSet<>();
        set.add("a");
        tt.init(set);
        System.out.println(tt.contains("a"));
    }


}
class TrendingTerms {
    TrieNode root;

    void init(Set<String> terms) {
        this.root = new TrieNode();
        for (String term : terms) {
            TrieNode curr = root;
            for (char c : term.toCharArray()) {
                if (!curr.map.containsKey(c)) {
                    curr.map.put(c, new TrieNode());
                }
                curr = curr.map.get(c);
            }
            curr.term = term;
        }
    }

    boolean contains(String snapText) {
        for (int i = 0; i < snapText.length(); i++) {
            TrieNode curr = this.root;
            int start = i;
            if (!curr.map.containsKey(snapText.charAt(start))) {
                System.out.println("no char: " + snapText.charAt(start) + " at " + start);
                continue;
            }
            while (start < snapText.length()) {
                System.out.println(curr.term);
                if (!curr.term.equals("")) {
                    return true;
                }

                if (!curr.map.containsKey(snapText.charAt(start))) {
                    System.out.println("doens't have " + snapText.charAt(start) + " at " + start);
                    break;
                }
                curr = curr.map.get(snapText.charAt(start));

                start++;
            }
            if (!curr.term.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}

class TrieNode {
    Map<Character, TrieNode> map;
    String term;

    TrieNode() {
        map = new HashMap<>();
        term = "";
    }
}
