import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  public static void main(String[] args) {
    Map<Integer, List<Integer>> map = new HashMap<>();
    map.put(0, Arrays.asList(1));
    map.put(1, Arrays.asList(0, 2, 5));
    map.put(2, Arrays.asList(1, 3, 4));
    // 0, 1, 2, 3, 4, 5
    // 0, 1, X
    int n = 6;
    List<Integer> result = getOrder(n, map);
    for (int num : result) {
      System.out.print(num + " ");
    }
  }
  private static List<Integer> getOrder(int n, Map<Integer, List<Integer>> map) {
    // intput n := total number of service
    // input map := : <service, list <following services>>
    int[] indegree = new int[n];
    for (int key : map.keySet()) {
      for (int next : map.get(key)) {
        indegree[next]++;
      }
    }
    Queue<Integer> queue = new LinkedList<>();
    List<Integer> result = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();
    for (int i = 0; i < n; i++) {
      if (indegree[i] == 0) {
        queue.offer(i);
        visited.add(i);
      }
    }
    if (queue.isEmpty()) {
      return new ArrayList<>();
    }
    // start BFS
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        // get current service with indegree of 0
        int currService = queue.poll();
        result.add(currService);
        if (map.containsKey(currService)) {
          for (int nextService : map.get(currService)) {
            // already visited
            if (visited.contains(nextService)) {
              continue;
            }
            // remove 1 dependency
            indegree[nextService]--;
            if (indegree[nextService] == 0) {
              // we can start this service
              queue.offer(nextService);
              visited.add(nextService);
            }
          }
        }
      }
    }
    // check if we request all services
    if (result.size() == n) {
      return result;
    } else {
      return new ArrayList<>();
    }
  }
}
