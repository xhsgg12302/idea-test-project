package _algorithm.tree.demo;

public class TreeUtils {

    public static Node generater() {
        Node node7 = new Node(7, null, null);
        Node node8 = new Node(8, null, null);
        Node node6 = new Node(6, node7, node8);
        Node node4 = new Node(4, null, node6);
        Node node2 = new Node(2, node4, null);
        Node node5 = new Node(5, null, null);
        Node node3 = new Node(3, null, node5);
        Node root = new Node(1, node2, node3);

        return root;
    }
}

class Node {

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int value;
    public Node left;
    public Node right;
}
