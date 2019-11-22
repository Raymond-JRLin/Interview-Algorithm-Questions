// maze
// some blocks - not enter
// [
//   [1, 2, 3, 4],
//   [2, 5, 7, 3],
//   [3, 7, 3, 9]
// ]
// 7 is block
// start point 2, end point 9
// left right up down

public class Solution {

  // [0, 1](2) -> neighbors: up - out of bond, down [1, 1](5), left [0, 1](1), right [0, 2](3)
  // [1, 1](5), [0, 1](1), [0, 2](3) :
  //  [0, 1](1) -> up: out, left: out, right : visited, down: [1, 0](2)
  //  [1, 1](5) -> up: visited, left: visited, right: block , down: block
  //  [0, 2](3) -> up: out, left, visited, down: block, right: [0, 3](4)

  // [1, 0](2), [0, 3](4)

  private boolean achieveEndPoint(int[][] maze, int[] start, int[] end, int block) {
    int n = maze.length;
    int m = maze[0].length;
    Queue<int[]> queue = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    queue.offer(start);
    visited.add(start[0] * m + start[1]);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] curr = queue.poll();
        if (curr[0] == end[0] && curr[1] == end[1]) {
          // reach end point
          return true;
        }
        for (int k = 0; k < 4; k++) {
          int x = curr[0] + dx[k];
          int y = curr[1] + dy[k];
          if (x < 0 || x >= n || y < 0 || y >= m) {
            continue;
          }
          if (visited.contains(x * m + y)) {
            continue;
          }
          if (maze[x][y] == block) {
            continue;
          }
          int[] next = new int[]{x, y};
          queue.offer(next);
          visited.add(x * m + y);
        }
      }
    }
    return false;
  }
}

// itentity
// deploy on Azure
// Docker framework, C#, .NEt core,
// coding , care about details/completness
// coding, system design
