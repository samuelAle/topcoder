package com.samuelale.practice;

/**
 * Created by samuel on 8/30/16.
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
        balanceTree(root);
        return n;
    }

    private static void balanceTree(Node root) {
        if(root == null) return;

        balanceTree(root.left);
        balanceTree(root.right);

        if(root.left == null && root.right == null) {
            // its balanced. do nothing
        } else if(root.left == null && root.right.ht > 1) {
            rotateRight(root);
        } else if(root.right == null && root.left.ht > 1) {
            rotateLeft(root);
        } else if(root.right == null || root.left == null) {
            // do nothing. its balanced
        } else if(root.left.ht-root.right.ht > 1) {
            rotateRight(root.left);
            rotateLeft(root);
        } else if(root.right.ht-root.left.ht > 1) {
            rotateLeft(root.right);
            rotateRight(root);
        }
    }

    private static void rotateRight(Node root) {
        // trade values
        int tmpV = root.val;
        root.val = root.right.val;
        root.right.val = tmpV;

        // fix children
        Node tmpL = root.left;
        root.left = root.right;
        root.right = root.right.right;
        root.left.right = root.left.left;
        root.right.right = tmpL;

        fixHeight(root, root.ht-1);
    }

    private static void rotateLeft(Node root) {
        // trade values
        int tmpV = root.val;
        root.val = root.left.val;
        root.left.val = tmpV;

        // fix children
        root.right = root.left;
        root.left = root.left.left;
        root.right.left = root.right.right;
        root.left.left = null;
    }

    private static void fixHeight(Node root, int ht) {
        if(root == null) return;
        root.ht = ht;
        fixHeight(root.left, ht-1);
        fixHeight(root.right, ht-1);
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
}
