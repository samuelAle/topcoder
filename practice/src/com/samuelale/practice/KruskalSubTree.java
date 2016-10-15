package com.samuelale.practice;

import java.util.*;

/**
 * https://www.hackerrank.com/challenges/kruskalmstrsub
 */
public class KruskalSubTree {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int E = s.nextInt();
        Node[] graph = new Node[N];
        for(int n=0; n<N; n++) graph[n] = new Node(n+1);
        for(int e=0; e<E; e++) {
            Node n1 = graph[s.nextInt()-1];
            Node n2 = graph[s.nextInt()-1];
            int cost = s.nextInt();
            n1.edges.add(new Edge(n1, n2, cost));
            n2.edges.add(new Edge(n2, n1, cost));
        }
        Node start = graph[s.nextInt()-1];
        int total = findMinSubGraph(start);
        System.out.println(total);
    }

    private static int findMinSubGraph(Node start) {
        int total = 0;
        Set<Node> completedNodes = new HashSet<>();
        completedNodes.add(start);
        Set<Edge> queue = new HashSet<>();
        queue.addAll(start.edges);
        while(!queue.isEmpty()) {
            Edge min = queue.stream().min((a,b) -> {
                int res = Integer.compare(a.cost, b.cost);
                if(res == 0) {
                    return Integer.compare(a.from.id + a.cost + a.to.id,
                            b.from.id + b.cost + b.to.id);
                } else {
                    return res;
                }
            }).orElse(queue.iterator().next());
            total+= min.cost;
            completedNodes.add(min.to);
            queue.addAll(min.to.edges);
            queue.removeIf(e -> completedNodes.contains(e.to));
        }
        return total;
    }
    private static class Node {
        Set<Edge> edges = new HashSet<>();
        int id;
        Node(int id) { this.id = id; }
        public boolean equals(Object other) {
            return (other instanceof Node) && ((Node) other).id == id;
        }
        public int hashCode(){ return id; }
    }
    private static class Edge {
        int cost;
        Node from, to;
        Edge(Node from, Node to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
