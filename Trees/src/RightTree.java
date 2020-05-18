import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RightTree {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(new File("righttree.dat"));
        int cases = scan.nextInt();
        scan.nextLine();

        for (int c = 0; c < cases; c++) {

            String[] arr = scan.nextLine().split("");
            boolean isRight = true;

            for (int i = 0; i < arr.length; i++) {

                int leftCount = size(2 * i + 1, arr);
                int rightCount = size(2 * i + 2, arr);

                if (leftCount > rightCount) {
                    isRight = false;
                    break;
                }
            }

            if (isRight)
                System.out.println("Tree " + (c + 1) + " is a right tree");
            else
                System.out.println("Tree " + (c + 1) + " is not a right tree");

        }

    }

    public static int size(int node, String[] arr) {
        if (node >= arr.length)
            return 0;

        return 1 + size(node * 2 + 1, arr) + size(node * 2 + 2, arr);
    }


}
