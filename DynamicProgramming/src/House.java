import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class House {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(new File("house.dat"));
        int cases = scan.nextInt();

        for (int iterations = 0; iterations < cases; iterations++) {
            int size = scan.nextInt();
            int[][] data = new int[size][];

            for (int i = 0; i < size; i++) {
                int[] temp = new int[i + 1];
                for (int j = 0; j <= i; j++) {
                    temp[j] = scan.nextInt();
                }
                data[i] = temp;
            }

            int[][] sum = new int[size][];
            for (int i = 0; i < size; i++) {
                sum[i] = new int[i + 1];
            }

            sum[size - 1] = data[size - 1];

            for (int r = size - 2; r >= 0; r--) {
                for (int c = 0; c < sum[r].length; c++) {
                    sum[r][c] = data[r][c] + Math.max(sum[r + 1][c], sum[r + 1][c + 1]);
                }
            }

            System.out.print(sum[0][0] + ": ");

            int currIndex = 0;
            for (int r = 0; r < sum.length - 1; r++) {
                System.out.print(data[r][currIndex] + " > ");
                if (sum[r + 1][currIndex + 1] > sum[r + 1][currIndex])
                    currIndex++;
            }

            System.out.println(sum[size - 1][currIndex]);

        }
    }
}
