public class BinaryTreeBottomeView {
    public static List<Integer> getBottomView(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 0));
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                int col = curr.col;
                map.put(curr.col, curr.val);
                if (curr.treeNode.left != null) {
                    queue.offer(curr.treeNode.left, col - 1);
                }
                if (curr.treeNode.right != null) {
                    queue.offer(curr.treeNode.right, col + 1);
                }
            }
        }
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        List<Integer> result = new ArrayList<>();
        for (int key : keys) {
            result.add(map.get(key));
        }

        return result;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.right = new TreeNode(22);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(25);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);
        List<integer> result = getBottomView(root);
        printList(result);
    }
    public static void printList(List<Integer> list){
        for (int num : list) {
            System.out.print(num + " ");
        }
    }
}
class Node {
    public TreeNode treeNode;
    public int col;
    public Node(TreeNode treeNode, int col) {
        this.treeNode = treeNode;
        this.col = col;
    }

}
class TreeNode {
    public int val;
    public left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
