package com.samuelale.practice;
/**
 * https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor
 */
public class LowestCommonAncestor {
    public static void main(String[] args) {

    }

    private static class Node {
        int data;
        Node left;
        Node right;
    }

    private static Node lca(Node root, int v1, int v2) {
        while(true) {
            if(root == null) {
                break;
            } else if(root.data >= v1 && root.data < v2) {
                break;
            } else if(root.data < v1 && root.data >= v2) {
                break;
            } else if(root.data >= v1 && root.data >= v2) {
                root = root.left;
            } else if(root.data < v1 && root.data < v2){
                root = root.right;
            }
        }
        return root;
    }

    // huffman decoding
    void decode(String S, Node root)
    {
        char[] arr = S.toCharArray();

        Node cur = root;
        for(char c : arr) {
            if(c == '1') {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
            if(cur.data != '\u0000') {
                System.out.print(cur.data);
                cur = root;
            }
        }
    }
}
