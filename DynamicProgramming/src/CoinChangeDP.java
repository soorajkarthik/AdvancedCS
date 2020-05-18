import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class CoinChangeDP {
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(new File("change.dat"));
        int cases = scan.nextInt();
        for (int c = 0; c < cases; c++) {
            int[] coins = new int[scan.nextInt()];
            for (int i = 0; i < coins.length; i++)
                coins[i] = scan.nextInt();

            int[] arr = new int[scan.nextInt() + 1];
            Arrays.fill(arr, 1, arr.length, Integer.MAX_VALUE);

            for (int a = 1; a < arr.length; a++) {
                for (int coin : coins) {
                    if (a - coin >= 0) {
                        arr[a] = Math.min(arr[a], arr[a - coin] + 1);
                    } else {
                        break;
                    }
                }
            }

            System.out.println(arr[arr.length - 1]);
        }
    }
}