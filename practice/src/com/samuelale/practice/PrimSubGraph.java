package com.samuelale.practice;

import java.util.*;

/**
 * https://www.hackerrank.com/challenges/primsmstsub
 */
public class PrimSubGraph {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int E = s.nextInt();
        Node[] graph = new Node[N];
        for(int n=0; n<N; n++) graph[n] = new Node(n);
        for(int e=0; e<E; e++) {
            Node n1 = graph[s.nextInt()-1];
            Node n2 = graph[s.nextInt()-1];
            int cost = s.nextInt();
            n1.edges.add(new Edge(n2, cost));
            n2.edges.add(new Edge(n1, cost));
        }

        int total = findMinSubGraph(graph[s.nextInt()-1]);
        System.out.println(total);
    }

    private static int findMinSubGraph(Node start) {
        int total = 0;
        Set<Node> completedNodes = new HashSet<Node>();
        completedNodes.add(start);
        List<Edge> queue = new ArrayList<>();
        queue.addAll(start.edges);
        while(!queue.isEmpty()) {
            Edge min = queue.stream().min((a,b) -> Integer.compare(a.cost, b.cost)).orElse(start.edges.get(1));
            total+= min.cost;
            completedNodes.add(min.node);
            queue.addAll(min.node.edges);
            queue.removeIf(e -> completedNodes.contains(e.node));
        }
        return total;
    }

    private static class Node {
        int val;
        ArrayList<Edge> edges = new ArrayList<>();
        Node(int val) {
            this.val = val;
        }
        public int hashcode() {
            return val;
        }
        public boolean equals(Object other) {
            return (other instanceof Node) && ((Node) other).val == val;
        }
    }
    private static class Edge {
        Node node;
        int cost;
        Edge(Node n, int cost) {
            this.node = n;
            this.cost = cost;
        }
    }
}
