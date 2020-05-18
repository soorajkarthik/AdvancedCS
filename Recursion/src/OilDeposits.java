import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class OilDeposits {
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(new File("oil.txt"));

        while (scan.hasNextInt()) {

            int m = scan.nextInt();
            int n = scan.nextInt();

            scan.nextLine();

            if (m == 0)
                break;

            String[][] arr = new String[m][n];

            for (int i = 0; i < m; i++) {
                arr[i] = scan.nextLine().split("");
            }

            System.out.println(findClusters(arr));
        }
    }

    public static int findClusters(String[][] arr) {

        int count = 0;
        boolean[][] visited = new boolean[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {

                if (arr[i][j].equals("@") && !visited[i][j]) {
                    count++;
                    markCluster(arr, i, j, visited);
                }
            }
        }

        return count;
    }


    public static void markCluster(String[][] arr, int i, int j, boolean[][] visited) {

        if (!visited[i][j]) {
            visited[i][j] = true;

            for (int r = -1; r <= 1; r++) {
                for (int c = -1; c <= 1; c++) {
                    if (!(r == 0 && c == 0) && neighborExists(arr, i + r, j + c)) {
                        markCluster(arr, i + r, j + c, visited);
                    }
                }
            }
        }

    }

    public static boolean neighborExists(String[][] arr, int i, int j) {

        return (i >= 0) && (i < arr.length) && (j >= 0) && (j < arr[i].length) && (arr[i][j].equals("@"));

    }


}
