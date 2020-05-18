import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("easy_dijkstra.txt"));
        int cases = scan.nextInt();
        for (int iterations = 0; iterations < cases; iterations++) {
            int V = scan.nextInt();
            int K = scan.nextInt();
            int[][] graph = new int[V + 1][V + 1];

            for (int i = 0; i < K; i++) {
                int a = scan.nextInt();
                int b = scan.nextInt();
                int c = scan.nextInt();

                graph[a][b] = c;
                graph[b][a] = c;
            }

            HashMap<Integer, Vertex> map = new HashMap<>();

            for (int i = 1; i <= V; i++)
                map.put(i, new Vertex(i));
            map.get(1).distance = 0;

            PriorityQueue<Vertex> queue = new PriorityQueue<>(map.values());

            while (!queue.isEmpty()) {
                Vertex vertex = queue.poll();
                for (int i = 1; i < graph.length; i++) {
                    int weight = graph[vertex.name][i];
                    if (weight > 0 && vertex.distance + weight < map.get(i).distance) {
                        queue.remove(map.get(i));
                        Vertex temp = map.get(i);
                        temp.distance = vertex.distance + weight;
                        temp.previous = vertex.name;
                        queue.add(map.get(i));
                    }
                }
            }

            scan.nextInt();
            int b = scan.nextInt();
            if (map.get(b).distance == Integer.MAX_VALUE)
                System.out.println("NO");
            else {
                System.out.print(map.get(b).distance + " ");
                printPath(b, map);
                System.out.println();
            }
        }
    }

    private static void printPath(int vertex, HashMap<Integer, Vertex> map) {
        if (map.get(vertex).previous == Integer.MAX_VALUE)
            System.out.print(vertex);
        else {
            printPath(map.get(vertex).previous, map);
            System.out.print(">" + vertex);
        }
    }

    static class Vertex implements Comparable {

        int name, distance, previous;

        public Vertex(int name) {
            this.name = name;
            distance = Integer.MAX_VALUE;
            previous = Integer.MAX_VALUE;
        }

        @Override
        public int compareTo(Object other) {
            return distance - ((Vertex) other).distance;
        }
    }
}
