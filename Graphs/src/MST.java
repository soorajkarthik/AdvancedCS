import java.io.File;
import java.io.IOException;
import java.util.*;

public class MST {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("paver.dat"));
        int cases = scan.nextInt();

        for (int iterations = 0; iterations < cases; iterations++) {
            int price = scan.nextInt();
            int buildings = scan.nextInt();
            int streets = scan.nextInt();

            HashMap<Integer, Vertex> unvisited = new HashMap<>();
            PriorityQueue<Edge> edgeQueue = new PriorityQueue<>();

            for (int i = 1; i <= buildings; i++)
                unvisited.put(i, new Vertex(i));

            for (int i = 0; i < streets; i++) {
                int a = scan.nextInt();
                int b = scan.nextInt();
                int c = scan.nextInt();

                unvisited.get(a).addEdge(new Edge(a, b, c));
                unvisited.get(b).addEdge(new Edge(b, a, c));
            }

            Vertex curr = unvisited.remove(1);
            int sum = 0;
            while (!unvisited.isEmpty()) {
                edgeQueue.addAll(curr.edges);
                Edge next = edgeQueue.poll();
                while (!unvisited.containsKey(next.endVertex))
                    next = edgeQueue.poll();
                sum += next.weight;
                curr = unvisited.remove(next.endVertex);
            }

            System.out.println(sum * price);

        }
    }

}

class Vertex {
    public int name;
    public List<Edge> edges;

    public Vertex(int name) {
        this.name = name;
        edges = new ArrayList<>();
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }
}

class Edge implements Comparable {
    public int startVertex, endVertex, weight;

    public Edge(int start, int end, int weight) {
        startVertex = start;
        endVertex = end;
        this.weight = weight;
    }

    public int compareTo(Object other) {
        return weight - ((Edge) other).weight;
    }
}