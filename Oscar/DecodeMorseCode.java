public class DecodeMorseCode {
    public static void main(String args[] ) throws Exception {

        String code1 = "--."; // ['T,T,E', 'T,N', 'G', 'M,E']
        String code2 = "..."; // [S, EI, IE, EEE]

        List<String> result1 = decodeMorse(code1);
        for (String s : result1) {
            System.out.print(s + ", ");
        }
        System.out.println();

        List<String> result2 = decodeMorse(code2);
        for (String s : result2) {
            System.out.print(s + ", ");
        }
        System.out.println();
    }

    private static List<String> decodeMorse(String code) {
        Map<Character, String> charToMorse = getCharToMorseMap();
        Map<String, Character> morseToChar = new HashMap<>();
        for (char key : charToMorse.keySet()) {
            morseToChar.put(charToMorse.get(key), key);
        }
        List<String> result = new ArrayList<>();
        dfs(code, morseToChar, 0, result, "");
        return result;
    }
    private static void dfs(String code, Map<String, Character> map, int index, List<String> result, String s) {
        if (index > code.length()) {
            return;
        }
        if (index == code.length()) {
            // System.out.println("get " + s);
            result.add(new String(s));
            return;
        }
        // take i as length of substring
        // for (int i = 1; i <= 4; i++) {
        //     if (index + i > code.length()) {
        //         break;
        //     }
        //     String next = code.substring(index, index + i);
        //     if (!map.containsKey(next)) {
        //         continue;
        //     }
        //     dfs(code, map, index + i, result, s + map.get(next));
        // }
        // or: take i as end index
        for (int i = index + 1; i <= Math.min(index + 4, code.length()); i++) {
            String sub = code.substring(index, i);
            if (!map.containsKey(sub)) {
                continue;
            }
            dfs(code, map, i, result, s + map.get(sub));
        }
    }
    private static Map<Character, String> getCharToMorseMap() {
        Map<Character, String> map = new HashMap<>();
        map.put('A', ".-");
        map.put('B', "-...");
        map.put('C', "-.-.");
        map.put('D', "-..");
        map.put('E', ".");
        map.put('F', "..-.");
        map.put('G', "--.");
        map.put('H', "....");
        map.put('I', "..");
        map.put('J', ".---");
        map.put('K', "-.-");
        map.put('L', ".-..");
        map.put('M', "--");
        map.put('N', "-.");
        map.put('O', "---");
        map.put('P', ".--.");
        map.put('Q', "--.-");
        map.put('R', ".-.");
        map.put('S', "...");
        map.put('T', "-");
        map.put('U', "..-");
        map.put('V', "...-");
        map.put('W', ".--");
        map.put('X', "-..-");
        map.put('Y', "-.--");
        map.put('Z', "--..");
        return map;
    }
}
