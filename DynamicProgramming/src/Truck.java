import java.util.Scanner;

public class Truck {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int cases = scan.nextInt();

        for (int c = 0; c < cases; c++) {

            int cap = scan.nextInt();
            int num = scan.nextInt();
            int[] weight = new int[num];
            int[] val = new int[num];

            for (int i = 0; i < num; i++) {
                weight[i] = scan.nextInt();
                val[i] = scan.nextInt();
            }

            int[][] table = new int[num + 1][cap + 1];

            for (int i = 1; i < num; i++) {
                for (int w = 0; w <= cap; w++) {

                    if (weight[i - 1] > w) {
                        table[i][w] = table[i - 1][w];
                    } else {
                        table[i][w] = Math.max(table[i - 1][w], val[i - 1] + table[i][w - weight[i - 1]]);
                    }
                }
            }

            System.out.println(table[num - 1][cap]);

        }
    }
}
