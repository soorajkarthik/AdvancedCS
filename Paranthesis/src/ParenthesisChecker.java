import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class ParenthesisChecker {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File("paran.dat"));
        int cases = file.nextInt();
        file.nextLine();
        HashMap<String, String> map = new HashMap<>() {{
            put("(", ")");
            put(")", "(");
            put("[", "]");
            put("]", "[");
        }};

        for (int iterations = 0; iterations < cases; iterations++) {

            Stack<String> stack = new Stack<>();
            String[] input = file.nextLine().split("");

            if (input[0].equals("")) {
                System.out.println("Yes");
                continue;
            }

            stack.push(input[0]);

            for (int i = 1; i < input.length; i++) {
                if (stack.isEmpty()) {
                    stack.push(input[i]);
                } else if (map.get(input[i]).equals(stack.peek())) {
                    stack.pop();
                } else {
                    stack.push(input[i]);
                }
            }

            if (stack.isEmpty()) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

    }
}
