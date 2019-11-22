public class Solution {
  public static void main(String[] args) {

      // String s = "(Hi Hello(Hey(yoyo Haha) ) Beauty)";
      // String s = "";
      // String s = " ";
      // String s = " a ";
      // String s = "( ((a)) )";
      String s = "(ab(cd))(ef)a";
      IndentationPrint(s);
  }
  private static void IndentationPrint(String s) {
      int n = s.length();
      int tab = 0;
      for (int i = 0; i < n; i++) {
          char c = s.charAt(i);
          if (c == '(') {
              printTab(tab);
              System.out.println('(');
              tab++;
          } else if (c == ')') {
              tab--;
              printTab(tab);
              System.out.println(')');
          } else if (c == ' ') {
              continue;
          } else {
              String curr = String.valueOf(c);
              while (i < n - 1 && Character.isLetter(s.charAt(i + 1))) {
                  curr += s.charAt(i + 1);
                  i++;
              }
              printTab(tab);
              System.out.println(curr);
          }
      }
  }
  private static void printTab(int count) {
      for (int i = 0; i < count; i++) {
          System.out.print("\t");
      }
  }
}
