import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bottles {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(new File("bottles.dat"));
        int cases = scan.nextInt();

        for (int c = 0; c < cases; c++) {

            int num = scan.nextInt(), including = 0, excluding = 0, currentMax = 0;

            for (int i = 0; i < num; i++) {
                currentMax = Math.max(including, excluding);
                including = scan.nextInt() + excluding;
                excluding = currentMax;
            }

            System.out.println(Math.max(including, excluding));
        }
    }
}