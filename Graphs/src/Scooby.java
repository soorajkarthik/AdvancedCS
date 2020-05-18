import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Scooby {

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(new File("scoobydoo.txt"));
        int cases = scan.nextInt();
        scan.nextLine();

        for (int iterations = 0; iterations < cases; iterations++) {

            ArrayList<HashSet<Character>> clusters = new ArrayList<>();
            String[] passages = scan.nextLine().split(" ");

            char[] temp = passages[0].toCharArray();
            clusters.add(new HashSet<>(Arrays.asList(temp[0], temp[1])));

            for (int i = 1; i < passages.length; i++) {

                temp = passages[i].toCharArray();
                ArrayList<HashSet<Character>> matches = new ArrayList<>();

                //move all clusters that have either of the rooms to the matches ArrayList
                for (int c = 0; c < clusters.size(); c++) {
                    HashSet<Character> cluster = clusters.get(c);
                    if (cluster.contains(temp[0]) || cluster.contains(temp[1])) {
                        matches.add(clusters.remove(c));
                        c--;
                    }
                }

                switch (matches.size()) {
                    //no clusters have either room so make new cluster
                    case 0:
                        clusters.add(new HashSet<>(Arrays.asList(temp[0], temp[1])));
                        break;
                    //one of the clusters has one of the rooms, so add the other room to that cluster
                    case 1:
                        matches.get(0).addAll(Arrays.asList(temp[0], temp[1]));
                        clusters.add(matches.get(0));
                        break;
                    //two of the clusters have one room each, merge the two clusters
                    case 2:
                        matches.get(0).addAll(matches.get(1));
                        clusters.add(matches.get(0));
                        break;
                }
            }

            temp = scan.nextLine().toCharArray();
            boolean wasFound = false;
            for (HashSet<Character> cluster : clusters) {
                if (cluster.contains(temp[0]) && cluster.contains(temp[1])) {
                    wasFound = true;
                    break;
                }
            }

            if (wasFound)
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }
}