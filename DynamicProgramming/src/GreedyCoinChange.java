import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GreedyCoinChange {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(new File("change.dat"));
        int cases = scan.nextInt();
        for (int c = 0; c < cases; c++) {
            long start = System.currentTimeMillis();
            int denominations = scan.nextInt();
            int[] coins = new int[denominations];

            for (int i = 0; i < denominations; i++) {
                coins[i] = scan.nextInt();
            }

            int[] arr = new int[denominations];
            int index = denominations - 1;
            int count = 0;
            int val = scan.nextInt();

            while (val > 0 && index >= 0) {
                if (val - coins[index] >= 0) {
                    val -= coins[index];
                    count++;
                    arr[index]++;
                } else {
                    index--;
                }
            }

            System.out.print(count + " ");
            System.out.println(System.currentTimeMillis() - start);
            for (int i : arr) {
                System.out.print(i + " ");
            }
        }
    }
}
