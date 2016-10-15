package com.samuelale.practice;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://www.hackerrank.com/challenges/floyd-city-of-blinding-lights
 */
public class BlindingLights {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int E = s.nextInt();

        Node[] graph = new Node[N];
        for (int i = 0; i < N; i++) graph[i] = new Node(i + 1);
        for (int i = 0; i < E; i++) {
            Node n1 = graph[s.nextInt() - 1];
            Node n2 = graph[s.nextInt() - 1];
            n1.edges.put(n2.id, s.nextInt());

            if(n2.edges.containsKey(n1.id)) n2.edges.remove(n1.id);
        }

        int[][] costMatrix = new int[N][N];
        for(int i=0; i<N; i++) for(int j=0; j<N; j++) costMatrix[i][j] = -1;

        for(Node n : graph) findShortestPaths(n, graph, costMatrix);

        int Q = s.nextInt();
        for (int q = 0; q < Q; q++) {
            Node n1 = graph[s.nextInt() - 1];
            Node n2 = graph[s.nextInt() - 1];

            System.out.println(costMatrix[n1.id-1][n2.id-1] + " ");
        }
    }

    private static void findShortestPaths(Node n, Node[] graph, int[][] costMatrix) {
        PriorityQueue<Path> paths = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();

        paths.add(new Path(n.id, 0));
        while(!paths.isEmpty()) {
            Path min = paths.poll();

            visited.add(min.id);
            costMatrix[n.id-1][min.id-1] = min.cost;

            paths.addAll(graph[min.id-1].edges.entrySet().stream()
                    .map(e -> new Path(e.getKey(), e.getValue()+min.cost))
                    .collect(Collectors.toList()));
            paths.removeIf(e -> visited.contains(e.id));
        }
    }

    private static class Node {
        int id;
        HashMap<Integer, Integer> edges = new HashMap<>();

        Node(int id) { this.id = id; }

        public boolean equals(Object other) {
            return other instanceof Node && ((Node)other).id == id;
        }
        public int hashCode() { return id; }
    }

    private static class Path implements Comparable {
        int cost;
        int id;

        Path(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }

        @Override
        public int compareTo(Object o) {
            if(!(o instanceof Path)) throw new RuntimeException();
            return Integer.compare(cost, ((Path)o).cost);
        }
    }

}
