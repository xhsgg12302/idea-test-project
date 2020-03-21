package draft;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-02-08
 * @Desc:
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * _12302_2020-02-08
 *
 * @desc 仔细阅读以下两个类代码，完成指定处的代码，在eclipse上运行出正确结果。
 */
public class Node {
    public Node() {
    }


    public Node(String text, Node parentNode) {
        this.text = text;
        this.parentNode = parentNode;
    }

    private String text;
    private Node parentNode;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }


    public static List<Node> GenerateNodes() {
        Node nodeRoot = new Node("nodeRoot", null);
        Node node1 = new Node("node1", nodeRoot);
        Node node1_1 = new Node("node1_1", node1);
        Node node1_2 = new Node("node1_2", node1);
        Node node2 = new Node("node2", nodeRoot);

        List<Node> nodes = new ArrayList<Node>();
        nodes.add(nodeRoot);
        nodes.add(node1);
        nodes.add(node1_1);
        nodes.add(node1_2);
        nodes.add(node2);

        return nodes;

    }

}


class Test1 {

    public static Node getRoot(Node node) {
        // 请修改方法中不正确的地方，使方法返回根节点
        if (node.getParentNode() != null) {
            return getRoot(node.getParentNode());
        }
        return node;
    }


    public static Node getRootNode(Node node) {
        while (node.getParentNode() != null) {
            // 请补充完剩余代码，使方法返回根节点
            node = node.getParentNode();
        }
        return node;
    }


    public static void main(String[] args) {
        List<Node> nodes = Node.GenerateNodes();
        Random rand = new Random();
        int index = rand.nextInt(nodes.size());
        Node randomNode = nodes.get(index);
        // 请修改或补充完方法，使以下两个对象返回值均为根节点
        Node root = getRoot(randomNode);
        System.out.println(root.getText());
        Node rootNode = getRootNode(randomNode);
        System.out.println(rootNode.getText());
    }
}
