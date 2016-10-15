package com.samuelale.practice;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * https://www.hackerrank.com/challenges/bfsshortreach
 */
public class BfsShortReach {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int Q = s.nextInt();
        for(int q=0; q<Q; q++) {
            int N = s.nextInt();
            int E = s.nextInt();
            Node[] graph = new Node[N];
            for(int n=0; n<N; n++) graph[n] = new Node(n+1);
            for(int e=0; e<E; e++) {
                Node n1 = graph[s.nextInt()-1];
                Node n2 = graph[s.nextInt()-1];
                n1.edges.add(n2);
                n2.edges.add(n1);
            }

            int[] result = new int[N];
            Node start = graph[s.nextInt()-1];
            Set<Node> visited = new HashSet<>();
            Set<Node> breadth = new HashSet<>();
            visited.add(start);
            breadth.addAll(start.edges);
            AtomicInteger cost = new AtomicInteger(0);
            while(!breadth.isEmpty()) {
                cost.addAndGet(6);
                breadth.stream().filter(n->!visited.contains(n)).forEach(n -> {
                    result[n.id-1] = cost.get();
                    visited.add(n);
                });
                breadth = breadth.stream().flatMap(n->n.edges.stream()).filter(n->!visited.contains(n))
                        .collect(Collectors.toSet());
            }

            for(int i=0; i<result.length; i++) {
                if(i == start.id-1) continue;
                if(result[i] == 0) System.out.print(-1 + " ");
                else System.out.print(result[i] + " ");
            }
            System.out.println();
        }
    }

    private static class Node {
        List<Node> edges = new ArrayList<>();
        int id;
        Node(int id) { this.id = id; }
        public boolean equals(Object other) {
            return (other instanceof Node) && ((Node) other).id == id;
        }
        public int hashCode(){ return id; }
    }
}
