package com.samuelale.practice;


import java.util.ArrayList;

/**
 * https://www.hackerrank.com/challenges/self-balancing-tree
 */
public class BalancedTree {

    private static class Node {
        int val;
        int ht;
        Node left, right;
    }

    static Node insert(Node root, int val)
    {
        Node n = new Node();
        n.val = val;
        addNode(root, n);

        root = balanceTree(root);
        fixHeight(root);
        printTree(root);

        return root;
    }

    private static Node balanceTree(final Node root) {
        if(root == null) return null;

        root.left = balanceTree(root.left);
        root.right = balanceTree(root.right);

        // dealing with null children
        if(root.left == null && root.right == null) {
            // its balanced. do nothing
            return root;
        } else if(root.left == null && root.right.ht >= 1) {
            root.right = (root.right.left != null) ? rotateLeftChild(root.right) : root.right;
            return rotateRightChild(root);
        } else if(root.right == null && root.left.ht >= 1) {
            root.left = (root.left.right != null) ? rotateRightChild(root.left) : root.left;
            return rotateLeftChild(root);
        }

        // dealing with non-null children
        if(root.right == null || root.left == null) {
            // do nothing. its balanced
            return root;
        } else if(root.left.ht-root.right.ht > 1) {
            // the left is higher, lets see which child needs to be rotated.
            int leftH = (root.left.left != null) ? root.left.left.ht : -1;
            int rightH = (root.left.right != null) ? root.left.right.ht : -1;
            root.left = (leftH < rightH) ? rotateRightChild(root.left) : root.left;
            return rotateLeftChild(root);
        } else if(root.right.ht-root.left.ht > 1) {
            // the right is higher, lets see which child needs to be rotated.
            int leftH = (root.right.left != null) ? root.right.left.ht : -1;
            int rightH = (root.right.right != null) ? root.right.right.ht : -1;
            root.right = (leftH > rightH) ? rotateLeftChild(root.right) : root.right;
            return rotateRightChild(root);
        }

        return root;
    }

    /**
     * Takes a Node as root and rotates its right child upwards so that
     * the root becomes the left child of its former right child.
     *
     * After the former root takes the former right child's left link,
     * the node that it replaced would then move to the former root's right link.
     * @param root the node to perform rotation on.
     */
    private static Node rotateRightChild(Node root) {
        int formerRightVal = root.right.val;
        Node formerRightRight = root.right.right;
        Node formerRightLeft = root.right.left;

        Node n = new Node();
        n.val = formerRightVal;
        n.left = root;
        n.left.right = formerRightLeft;
        n.right = formerRightRight;

        return n;
    }

    /**
     * Takes a Node as root and rotates its left child upwards so that
     * the root becomes the right child of its former left child.
     *
     * After the former root takes the former left child's right link,
     * the node that it replaced would then move to the former root's left link.
     * @param root the node to perform rotation on.
     */
    private static Node rotateLeftChild(Node root) {
        int formerLeftVal = root.left.val;
        Node formerLeftRight = root.left.right;
        Node formerLeftLeft = root.left.left;

        Node n = new Node();
        n.val = formerLeftVal;
        n.right = root;
        n.right.left = formerLeftRight;
        n.left = formerLeftLeft;

        return n;
    }

    private static void fixHeight(Node root) {
        if(root == null) {
            return;
        }
        else if(root.left == null && root.right == null) {
            root.ht = 0;
            return;
        }

        fixHeight(root.left);
        fixHeight(root.right);

        if(root.left == null) {
            root.ht = root.right.ht+1;
        } else if(root.right == null) {
            root.ht = root.left.ht+1;
        } else {
            root.ht = Math.max(root.left.ht, root.right.ht) + 1;
        }
    }

    private static void addNode(Node root, Node n) {
        if(root.ht < 0) throw new RuntimeException("Unexpected path");

        root.ht++;
        if(n.val > root.val && root.right != null) addNode(root.right, n);
        else if(n.val < root.val && root.left != null) addNode(root.left, n);
        else if(n.val > root.val && root.right == null) root.right = n;
        else if(n.val < root.val && root.left == null) root.left = n;
        else throw new RuntimeException("Unexpected path");
    }


    private static void printTree(Node root) {
        ArrayList<Node> list = new ArrayList<>();
        list.add(root);
        while(!list.isEmpty()) {
            Node n = list.remove(0);
            System.out.println(n.val + " h= " + n.ht);
            if(n.left != null) list.add(n.left);
            if(n.right != null) list.add(n.right);
        }
    }
}
