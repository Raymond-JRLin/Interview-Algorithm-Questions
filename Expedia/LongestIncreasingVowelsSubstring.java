public class LongestIncreasingVowelsSubstring {
    public static void main(String[] args) {
        String s1 = "aeiaaioooaauuaeiou";
        String s2 = "eeeeuuuuaeiou";
        System.out.println(longestIncreasingVowelsSubstring(s1));
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(longestIncreasingVowelsSubstring(s2));
        for (String s : list) {
            System.out.println(s);
        }
    }

    private static int longestIncreasingVowelsSubstring(String s) {
        Map<Character, Character> map = new HashMap<>();
        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
        for (int i = 0; i < vowels.length - 1; i++) {
            map.put(vowels[i], vowels[i + 1]);
        }
        // result = 0;
        list = new ArrayList<>();
        int n = s.length();
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return recursion(s, 0, map, 0, '$', new StringBuilder(), 0, memo);
        // return result;
    }

    static List<String> list;
    private static int recursion(String s, int index, Map<Character, Character> map, int len, char prev, StringBuilder sb, int count, int[] memo) {
        if (index == s.length()) {
            if (count == 5) {
                list.add(new String(sb.toString()));
                return len;
            }

            return 0;
        }
        if (memo[index] != -1) {
            System.out.println("visited " + index + " == " + memo[index]);
            return memo[index];
        }
        char curr = s.charAt(index);
        int result = 0;
        if (prev == '$') {
            if (curr == 'a') {
                sb.append('a');
                result = Math.max(result, recursion(s, index + 1, map, len + 1, curr, sb, count + 1, memo));
                sb.deleteCharAt(sb.length() - 1);
            } else {
                result = Math.max(result, recursion(s, index + 1, map, len, prev, sb, count, memo));
            }
        } else if (prev == curr) {
            sb.append(curr);
            result = Math.max(result, recursion(s, index + 1, map, len + 1, curr, sb, count, memo));
            sb.deleteCharAt(sb.length() - 1);
        } else if (map.containsKey(prev) && map.get(prev) == curr) {
            sb.append(curr);
            result = Math.max(result, recursion(s, index + 1, map, len + 1, curr, sb, count + 1, memo));
            sb.deleteCharAt(sb.length() - 1);
            result = Math.max(result, recursion(s, index + 1, map, len, prev, sb, count, memo));
        } else {
            result = Math.max(result, recursion(s, index + 1, map, len, prev, sb, count, memo));
        }
        return memo[index] = result;
    }
}
