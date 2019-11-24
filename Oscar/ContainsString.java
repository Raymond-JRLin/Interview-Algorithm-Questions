public class Solution {
    public static void main(String args[] ) throws Exception {
        // A = "abc"，B = "abckk" => return True
        // A = "abc"，B = "akbkc" => return True
        // A = "abc"，B = "akkcb" => return False

        List<String> strings = Arrays.asList("abc", "abckk", "abc", "akbkc", "abc", "akkcb");

        for (int i = 0; i < strings.size(); i += 2) {
            System.out.println(containsString(strings.get(i), strings.get(i + 1)));
        }
    }

    private static boolean containsString(String a, String b) {
        int n = a.length();
        int m = b.length();
        int i = 0;
        int j = 0;
        while (i < n) {
            if (j >= m) {
                return false;
            }
            if (a.charAt(i) == b.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return true;
    }
}
