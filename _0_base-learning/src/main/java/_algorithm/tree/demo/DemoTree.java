package _algorithm.tree.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DemoTree {

    public static int post_idx = 0;
    public static Map<Integer,Integer> inmap = new HashMap();
    public static int [] postOrder ;
    public static Node buildTree(int[] inorder,int[] postorder){

        for (int i = 0; i < inorder.length; i++) {
            inmap.put(inorder[i],i);
        }

        postOrder = postorder;

        post_idx = postorder.length -1;

        return helper(0,inorder.length-1);
    }

    public static Node helper(int in_left,int in_right){

        if(in_left > in_right){return null;}

        int root_val = postOrder[post_idx--];
        int index = inmap.get(root_val);
        Node root = new Node(root_val);
        root.right = helper(index+1,in_right);
        root.left = helper(in_left,index -1 );

        return root;
    }


    public static Map<Integer,Integer> inOrderMap = new HashMap();
    public static int pre_idx = 0;
    public static int[] pre ;
    public static Node buildTree1(int[] preorder, int[] inorder) {
        for(int i = 0 ; i < inorder.length ; i++ ){
            inOrderMap.put(inorder[i],i);
        }
        pre = preorder;
        return helper1(0,preorder.length -1 );
    }

    public static void main(String[] args) {
        //buildTree1(new int[]{-1},new int[]{-1});
        levelTraversal(TreeUtils.generater());
    }

    public static Node helper1(int _left, int _right){
        if(_left > _right){return null;}
        int root_val = pre[ pre_idx ++ ];
        int index = inOrderMap.get(root_val);
        Node root = new Node(root_val);
        root.left = helper1(_left,index -1);
        root.right = helper1 (index + 1 , _right);
        return root;
    }


    public static void recursionPreOrderTraversal(Node tree){
        if(tree!= null){
            System.out.print(tree.value + ",");
            recursionPreOrderTraversal(tree.left);
            recursionPreOrderTraversal(tree.right);
        }
    }
    public static void recursionInOrderTraversal(Node tree){
        if(tree!= null){
            recursionInOrderTraversal(tree.left);
            System.out.print(tree.value + ",");
            recursionInOrderTraversal(tree.right);
        }
    }
    public static void recursionPostOrderTraversal(Node tree){
        if(tree!= null){

            recursionPostOrderTraversal(tree.left);
            recursionPostOrderTraversal(tree.right);
            System.out.print(tree.value + ",");
        }
    }

    public static void delElement(Node node,int value){
        if(node == null ) return;
        if(node.value == value){
            node = null;
            return;
        }
        delElement(node.left,value);
        delElement(node.right,value);
    }


    public static void delElement2(Node parent,Node node,int value ,int flag){
        if(node == null ) return;
        if(node.value == value){
            if(parent == null){
                // 只能在外部置null , root 元素
            }else{
                if(flag == 0){
                    parent.left = null;
                }else{
                    parent.right = null;
                }
            }
            return;
        }
        delElement2(node,node.left,value,0);
        delElement2(node,node.right,value,1);
    }

    public static void reallyDeeElement(Node node,int value){

    }


    public static void levelTraversal(Node tree){
        Node[] temp = new Node[]{tree};
        Node[] tempReally = temp;
        while (temp.length > 0){
            List<Node> list = new ArrayList<>();
            for (Node node : tempReally) {
                if(node !=null){
                    System.out.print(node.value + "\t");
                    list.add(node.left);
                    list.add(node.right);
                }else{
                    System.out.print("null\t");
                    list.add(null);
                    list.add(null);
                }
            }
            tempReally = list.toArray(new Node[list.size()]);
            List<Node>  a = list.stream().filter(node -> node != null).collect(Collectors.toList());
            temp = a.toArray(new Node[a.size()]);
        }
    }


    public static void main1(String[] args) {


        Node root1 = TreeUtils.generater();
        recursionPreOrderTraversal(root1);
        System.out.println();
        recursionInOrderTraversal(root1);
        System.out.println();
        recursionPostOrderTraversal(root1);
        System.out.println();

        Node root2 = buildTree(new int[]{4,7,6,8,2,1,3,5},new int[]{7,8,6,4,2,5,3,1});
        recursionPreOrderTraversal(root2);
        System.out.println();
        recursionInOrderTraversal(root2);
        System.out.println();
        recursionPostOrderTraversal(root2);
        System.out.println();
    }



}
