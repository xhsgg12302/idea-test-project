package _algorithm.tree.demo;

public class DemoTree {

    static class Node {

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        private int value;
        private Node left;
        private Node right;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }


    public static Node generaterTree(){

        Node node7 = new Node(7, null,null);
        Node node8 = new Node(8, null,null);

        Node node6 = new Node(6, node7,node8);

        Node node4 = new Node(4,null,node6);

        Node node2 = new Node(2,node4,null);

        Node node5 = new Node(5,null,null);
        Node node3 = new Node(3,null,node5);

        Node root = new Node(1,node2,node3);

        return root;
    }


    public static void recursionPreOrderTraversal(Node tree){
        if(tree!= null){
            System.out.print(tree.value + "\t");
            recursionPreOrderTraversal(tree.left);
            recursionPreOrderTraversal(tree.right);
        }
    }


    public static void main(String[] args) {
        recursionPreOrderTraversal(generaterTree());
    }



}
