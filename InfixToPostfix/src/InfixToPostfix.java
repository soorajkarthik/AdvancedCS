import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class InfixToPostfix {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File("infix.txt"));
        int cases = file.nextInt();
        file.nextLine();

        for (int c = 0; c < cases; c++) {

            Stack<String> stack = new Stack<>();
            String[] input = file.nextLine().split(" ");
            String ret = "";

            for (String s : input) {

                if (isOperand(s)) {
                    ret = ret + s + " ";
                } else if (isOperator(s)) {

                    while (!stack.isEmpty() && !isOpeningParenthesis(stack.peek()) && isHigherPrecedence(stack.peek(), s)) {
                        ret = ret + stack.pop() + " ";
                    }

                    stack.push(s);
                } else if (isOpeningParenthesis(s)) {
                    stack.push(s);
                } else if (isClosingParenthesis(s)) {
                    while (!stack.isEmpty() && !isOpeningParenthesis(stack.peek())) {
                        ret = ret + stack.pop() + " ";
                    }

                    stack.pop();
                }
            }

            while (!stack.isEmpty()) {
                ret = ret + stack.pop() + " ";
            }

            System.out.println(ret.trim());

        }
    }

    public static boolean isOperand(String s) {
        return s.matches("^\\d$");
    }

    public static boolean isOperator(String s) {
        return "+-*/^".contains(s);
    }

    public static boolean isOpeningParenthesis(String s) {
        return "([{".contains(s);
    }

    public static boolean isClosingParenthesis(String s) {
        return ")]}".contains(s);
    }

    public static boolean isHigherPrecedence(String s1, String s2) {

        HashMap<String, Integer> map = new HashMap<>();
        map.put("+", 1);
        map.put("-", 1);
        map.put("*", 2);
        map.put("/", 2);
        map.put("^", 3);

        return map.get(s1) >= map.get(s2);
    }

}
