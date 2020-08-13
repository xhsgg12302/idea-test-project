package _algorithm.tree.demo;

public class MaxTreeDepth {

    public static void main(String[] args) {
        System.out.println(getMaxDepth(TreeUtils.generater()));
    }

    private static int getMaxDepth(Node root) {
        if(root == null){return 0;}

        return Math.max(getMaxDepth(root.left),getMaxDepth(root.right)) + 1 ;
    }

}
