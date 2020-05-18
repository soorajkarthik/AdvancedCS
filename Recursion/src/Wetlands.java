import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Wetlands {

    public static int size = 0;

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(new File("wetlands.txt"));

        int cases = scan.nextInt();
        scan.nextLine();
        scan.nextLine();

        ArrayList<String> temp = new ArrayList<>();
        String s = scan.nextLine();

        while (!Character.isDigit(s.charAt(0))) {
            temp.add(s);
            s = scan.nextLine();
        }

        String[][] arr = new String[temp.size()][temp.get(0).length()];

        for (int i = 0; i < temp.size(); i++) {
            arr[i] = temp.get(i).split("");
        }

        findClustersSize(arr, Integer.parseInt(s.substring(0, 1)), Integer.parseInt(s.substring(2)));
        System.out.println(size);
        size = 0;

        while (scan.hasNextInt()) {
            int r = scan.nextInt() - 1;
            int c = scan.nextInt() - 1;

            findClustersSize(arr, r, c);
            System.out.println(size);
            size = 0;

        }


    }

    public static int findClustersSize(String[][] arr, int r, int c) {

        boolean[][] visited = new boolean[arr.length][arr[0].length];
        size++;
        markCluster(arr, r, c, visited);

        return size;
    }


    public static void markCluster(String[][] arr, int i, int j, boolean[][] visited) {

        if (!visited[i][j]) {
            visited[i][j] = true;

            for (int r = -1; r <= 1; r++) {
                for (int c = -1; c <= 1; c++) {
                    if (!(r == 0 && c == 0) && neighborExists(arr, i + r, j + c) && !visited[i + r][j + c]) {
                        size++;
                        markCluster(arr, i + r, j + c, visited);
                    }
                }
            }
        }

    }

    public static boolean neighborExists(String[][] arr, int i, int j) {

        return (i >= 0) && (i < arr.length) && (j >= 0) && (j < arr[i].length) && (arr[i][j].equals("W"));

    }


}
