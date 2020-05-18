import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Dominoes {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("dominoes.txt"));
        int cases = scan.nextInt();

        for (int iterations = 0; iterations < cases; iterations++) {
            int N = scan.nextInt();
            int M = scan.nextInt();
            int L = scan.nextInt();

            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

            //going to N+1 so domino number can be used as index
            for (int i = 0; i < N + 1; i++)
                graph.add(new ArrayList<>());

            for (int i = 0; i < M; i++)
                graph.get(scan.nextInt()).add(scan.nextInt());

            HashSet<Integer> knockedDown = new HashSet<>();
            for (int i = 0; i < L; i++)
                findKnockedDown(scan.nextInt(), graph, knockedDown);

            //Size of HashSet is number of dominoes that were knocked down
            System.out.println(knockedDown.size());
        }
    }

    //Base cases are if domino has already been knocked down or if the domino doesn't knock anything else down
    private static void findKnockedDown(int domino, ArrayList<ArrayList<Integer>> graph, HashSet<Integer> knockedDown) {
        if (knockedDown.contains(domino))
            return;

        knockedDown.add(domino);
        graph.get(domino).forEach(n -> findKnockedDown(n, graph, knockedDown));
    }
}