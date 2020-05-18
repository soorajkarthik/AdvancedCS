import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class RPNCalculator {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(new File("data.txt"));
        int cases = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < cases; i++) {
            Stack<Integer> nums = new Stack<>();
            String[] input = scan.nextLine().split(" ");

            for (String s : input) {
                if (isInteger(s)) {
                    nums.push(Integer.parseInt(s));
                } else {
                    int second = nums.pop();
                    int first = nums.pop();

                    switch (s) {
                        case "+":
                            nums.push(first + second);
                            break;
                        case "-":
                            nums.push(first - second);
                            break;
                        case "*":
                            nums.push(first * second);
                            break;
                        case "/":
                            nums.push(first / second);
                            break;
                        case "^":
                            nums.push((int) Math.pow(first, second));
                            break;

                    }
                }
            }
            System.out.println(nums.pop());
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
