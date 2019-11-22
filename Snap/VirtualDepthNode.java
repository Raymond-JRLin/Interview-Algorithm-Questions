public class Solution {
  public static void main(String[] args) {

      Node root = new Node(0, 0);
      Node node2 = new Node(2, 1);
      Node node4 = new Node(4, 1);
      Node node3 = new Node(3, 2);
      Node node5 = new Node(5, 2);
      Node node6 = new Node(6, 2);
      Node node7 = new Node(7, 2);

      root.children.add(node2);
      root.children.add(node3);
      root.children.add(node4);
      node2.children.add(node5);
      node2.children.add(node6);
      node4.children.add(node7);

      List<List<Integer>> result = virtualDepthNode(root);
      for (List<Integer> list : result) {
          printList(list);
      }
  }
  private static List<List<Integer>> virtualDepthNode(Node root) {
      TreeMap<Integer, List<Integer>> map = new TreeMap<>(); // <virtual depth, list of nodes>
      dfs(root, map);
      List<List<Integer>> result = new ArrayList();
      for (int depth : map.keySet()) {
          result.add(new ArrayList<>(map.get(depth)));
      }
      return result;
  }
  private static void dfs(Node root, TreeMap<Integer, List<Integer>> map) {
      List<Integer> list = map.computeIfAbsent(root.depth, dummy -> (new ArrayList<>()));
      list.add(root.val);
      for (Node child : root.children) {
          dfs(child, map);
      }
  }
}
class Node {
    int val;
    int depth;
    List<Node> children;

    Node(int val, int depth) {
        this.val = val;
        this.depth = depth;
        children = new ArrayList();
    }
}
