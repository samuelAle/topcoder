package com.samuelale.practice;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.jar.Pack200;

/**
 * https://www.hackerrank.com/challenges/balanced-forest
 */
public class BalancedForest {

    private static class Node {
        ArrayList<Node> children = new ArrayList<>();
        int coins,idx, data;
        int total = 0;
        Node left, right;
        Node(int idx, int coins) {
            this.idx = idx;
            this.coins = coins;
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Node[] tree = new Node[n];
        for(int i=0; i<n; i++) tree[i] = new Node(n, s.nextInt());

        for(int j=0; j<n-1; j++) {
            int x = s.nextInt()-1;
            int y = s.nextInt()-1;
            tree[x].children.add(tree[y]);
            tree[y].children.add(tree[x]);
        }

        pruneDoubleEdges(tree[0], new HashSet<>());
        int totalCoins = getTotals(tree[0]);
        // a solution is possible if you can find 3 subtrees where 2 have equal totals, and the 3rd
        // subtree is less than or equal to their total.

        // find independent subtrees with total closest to root-total/3


        // brute force: find all possible forests of size 3 and compare totals.
    }

    private static int getTotals(Node n) {
        n.total = n.coins;
        for(Node child : n.children) n.total+= getTotals(child);
        return n.total;
    }

    private static void pruneDoubleEdges(Node node, HashSet<Node> visited) {
        visited.add(node);
        for(int i=0; i<node.children.size(); i++) {
            if(visited.contains(node.children.get(i))) {
                node.children.remove(i);
                i--;
            }
        }
        for(Node child : node.children) pruneDoubleEdges(child, visited);
    }

    private static Node bs(Node[] arr, int val) {
        int low = 0;
        int high = arr.length;
        while(low < high) {
            int idx = (high-low)/2 + low;
            Node n = arr[idx];
            if(val > n.coins) {
                low = idx;
            } else if (val < n.coins){
                high = idx;
            } else {
                return n;
            }
        }
        return null;
    }


    // do a bfs search
    void top_view(Node root)
    {
        ArrayList<Node> view = new ArrayList<>();
        view.add(root);
        top_view(root, 0, 0, 0, view);
        for(Node n : view) System.out.print(n.data + " ");
    }

    // do a bfs search
    void top_view(Node root, int place, int leftMost, int rightMost, List<Node> view) {
        if(root == null) return;

        if(place < leftMost) {
            leftMost--;
            view.add(0, root);
        }
        if(place > rightMost){
            rightMost++;
            view.add(root);
        }

        top_view(root.left, place-1, leftMost, rightMost, view);
        top_view(root.right, place+1, leftMost, rightMost, view);
    }

}
