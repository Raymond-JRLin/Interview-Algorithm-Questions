// Input:
// board = [
//   ['o','a','a','n'],
//   ['e','t','a','e'],
//   ['i','h','k','r'],
//   ['i','f','l','v']
// ]
// words = ["oath","pea","eat","rain"]

// Output: ["pea","rain"]


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        // System.out.println("Hello World");
        char[][] matrix = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        List<String> words = new ArrayList<>(Arrays.asList("oath", "pea", "eat", "rain"));
        List<String> result = findNoWords(matrix, words);

        for (String s : result) {
            System.out.println(s);
        }
    }

    private static List<String> findNoWords(char[][] matrix, List<String> words) {

        List<String> result = new ArrayList<>();
        for (String word : words) {
            // if (!word.equals("eat")) {
            //     continue;
            // }
            if (valid(matrix, word)) {
                // System.out.println("found  " + word);
                continue;
            }
            result.add(word);
        }
        return result;
    }
    private static boolean valid(char[][] matrix, String word) {
        int n = matrix.length;
        int m = matrix[0].length;
        char c = word.charAt(0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != c) {
                    continue;
                }
                // System.out.println("start with [" + i + ", " + j+ "]");
                if (dfs(matrix, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean dfs(char[][] matrix, String word, int i, int j, int index) {
        // System.out.println("index = " + index);
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length) {
            // System.out.println("out of bound");
            return false;
        }
        if (word.charAt(index) != matrix[i][j]) {
            // System.out.println("char not == ");
            return false;
        }
        if (matrix[i][j] == '$') {
            // System.out.println("mark " + i + ", " + j + " as $");
            return false;
        }
        char ori = matrix[i][j];
        matrix[i][j] = '$';
        if (dfs(matrix, word, i + 1, j, index + 1)|| dfs(matrix, word, i - 1, j, index + 1) ||
            dfs(matrix, word, i, j + 1, index + 1) || dfs(matrix, word, i, j - 1, index + 1)) {
                matrix[i][j] = ori; // backtracking here!!!
                return true;
        }
        matrix[i][j] = ori;
        return false;
    }
}
