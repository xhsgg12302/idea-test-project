package _algorithm.tree.demo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BuildBinaryTree {

    private  Map<Integer,Integer> inMap = new HashMap<>();

    private int index_root = 0;
    private int [] postOrder = null;
    public void init(int[] inOrder,int[] postOrder){
        for (int i = 0; i < inOrder.length; i++) {
            inMap.put(inOrder[i],i);
        }
        this.postOrder = postOrder;
        index_root = postOrder.length - 1;
    }

    public Node build(int index_left,int index_right){


        if(index_left > index_right) return null;

        int root_value = postOrder[index_root--];
        Node root = new Node(root_value);
        int _left = inMap.get(root_value);
        root.right = build(_left + 1,index_right);

        root.left = build(index_left,_left - 1);


        return root;
    }

    @Test
    public void test(){
        int[] postOrder = {9,15,7,20,3};
        int[] inOrder = {9,3,15,20,7};

        init(inOrder,postOrder);
        Node root = build(0,inOrder.length - 1);

        DemoTree.delElement2(null,root,20,0);
        DemoTree.recursionPreOrderTraversal(root);
    }

}
