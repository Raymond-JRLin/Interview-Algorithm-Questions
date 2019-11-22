import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class WishRelativeProblem {
  public static void main(String args[] ) throws Exception {
      wishRelative();
  }


  private static void wishRelative() {
      // input: List<List<String>> := [[name1, relationship, name2]], String name1, String name2
      // output: List<String>
      // e.g.
      // [
      //  ["Bart", "brother", "Lisa"],
      //  ["Bart", "son", "Homer"],
      //  ["Marge", "wife", "Homer"],
      //  ["Lisa", "daughter", "Homer"]
      // ]
      // input: "Bart", "Homer"
      // return: ["Bart son Homer", "Bart brother Lisa daughter Homer"]

      List<List<String>> relations = new ArrayList<>();
      List<String> l1 = Arrays.asList("Bart", "brother", "Lisa");
      List<String> l2 = Arrays.asList("Bart", "son", "Homer");
      List<String> l3 = Arrays.asList("Marge", "wife", "Homer");
      List<String> l4 = Arrays.asList("Lisa", "daughter", "Homer");
      // cycle
      List<String> l5 = Arrays.asList("Lisa", "sister", "Bart");
      List<String> l6 = Arrays.asList("Bart", "brother", "Mike");
      List<String> l7 = Arrays.asList("Mike", "brother", "Bart");
      List<String> l8 = Arrays.asList("Mike", "son", "Homer");

      List<String> l9 = Arrays.asList("Lisa", "sister", "Mike");
      List<String> l10 = Arrays.asList("Mike", "brother", "Lisa");

      // multiple relations
      List<String> l11 = Arrays.asList("Bart", "classmate", "Mike");
      List<String> l12 = Arrays.asList("Mike", "friend", "Lisa");

      relations.add(l1);
      relations.add(l2);
      relations.add(l3);
      relations.add(l4);
      // cycle
      relations.add(l5);
      relations.add(l6);
      relations.add(l7);
      relations.add(l8);

      relations.add(l9);
      relations.add(l10);

      // multiple relations
      relations.add(l11);
      relations.add(l12);


      List<List<String>> relations1on1 = new ArrayList<>();
      relations1on1.add(l1);
      relations1on1.add(l2);
      relations1on1.add(l3);
      relations1on1.add(l4);
      
      // List<String> result = getRelationsDfs(relations, "Bart", "Homer");
      // for (String s : result) {
      //     System.out.println(s);
      // }

      List<String> result = getRelationsFloyd(relations1on1, "Bart", "Homer");
      for (String s : result) {
          System.out.println(s);
      }
  }

  private static List<String> getRelationsFloyd(List<List<String>> relations, String name1, String name2) {
      Map<String, Integer> mapping = new HashMap<>();
      int n = 0;
      for (List<String> relation : relations) {
          String from = relation.get(0);
          String to = relation.get(2);
          if (!mapping.containsKey(from)) {
              mapping.put(from, n++);
          }
          if (!mapping.containsKey(to)) {
              mapping.put(to, n++);
          }
      }

      List<String>[][] adj = new List[n][n];
      for (List<String> relation : relations) {
          int from = mapping.get(relation.get(0));
          String rel = relation.get(1);
          int to = mapping.get(relation.get(2));
          List<String> list = adj[from][to] == null ? new ArrayList<>() : adj[from][to];
          list.add(rel + " " + relation.get(2));
          adj[from][to] = list;
      }

      // for (int i = 0; i < n; i++) {
      //     for (int j = 0; j < n; j++) {
      //         System.out.print(adj[i][j] + "\t\t");
      //     }
      //     System.out.println();
      // }

      for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
              for (int k = 0; k < n; k++) {
                  if (adj[i][k] != null && adj[k][j] != null) {
                      List<String> list = adj[i][j] == null ? new ArrayList<>() : adj[i][j];
                      for (String s1 : adj[i][k]) {
                          for (String s2 : adj[k][j]) {
                              list.add(s1 + " " + s2);
                          }
                      }
                      adj[i][j] = list;
                  }
              }
          }
      }
      // for (int i = 0; i < n; i++) {
      //     for (int j = 0; j < n; j++) {
      //         System.out.print(adj[i][j] + "\t\t");
      //     }
      //     System.out.println();
      // }
      List<String> result = new ArrayList<>();
      for (String s : adj[mapping.get(name1)][mapping.get(name2)]) {
          result.add(name1 + " " + s);
      }
      return result;
  }

  private static List<String> getRelationsDfs(List<List<String>> relations, String name1, String name2) {
      // Map<String, Map<String, String>> adj = new HashMap<>(); // <name1, <name2, relation>>
      Map<String, Map<String, List<String>>> adj = new HashMap<>(); // <name1, <name2, List of <relation>>>
      Set<String> names = new HashSet<>(); // <names>

      for (List<String> relation : relations) {
          String from = relation.get(0);
          String rel = relation.get(1);
          String to = relation.get(2);

          // Map<String, String> map = adj.computeIfAbsent(from, dummy -> (new HashMap<>()));
          Map<String, List<String>> map = adj.computeIfAbsent(from, dummy -> (new HashMap<>()));
          // map.put(to, rel);
          List<String> list = map.computeIfAbsent(to, dummy -> (new ArrayList<>()));
          list.add(rel);
          names.add(from);
          names.add(to);
      }
      // no name1 or name2
      if (!names.contains(name1) || !names.contains(name2)) {
          return Collections.emptyList();
      }

      List<String> result = new ArrayList<>();
      dfs(adj, name1, name2, name1, result, names);
      return result;
  }

  private static void dfs(Map<String, Map<String, List<String>>> adj, String curr, String name2, String s, List<String> result, Set<String> names) {
      // System.out.println("curr name is " + curr + ", s: " + s);
      if (curr.equals(name2)) {
          // System.out.println("already found");
          result.add(s.trim());
          return;
      }
      // cycle
      if (!names.contains(curr)) {
          // System.out.println("visited");
          return;
      }
      if (!adj.containsKey(curr)) {
          // System.out.println("no such name");
          return;
      }
      Map<String, List<String>> map = adj.get(curr);
      names.remove(curr);
      // for (String next : map.keySet()) {
      //     // System.out.println("go next name: " + next);
      //     dfs(adj, next, name2, s + " " + map.get(next) + " " + next, result, names);
      // }
      // multiple relationship
      for (String next : map.keySet()) {
          for (String relation : map.get(next)) {
              // System.out.println("go next name: " + next);
              dfs(adj, next, name2, s + " " + relation + " " + next, result, names);
          }
      }
      names.add(curr);
  }
}
