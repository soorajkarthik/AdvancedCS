import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class RakeMeUp {

    public static int currIndex = 0;
    public static TreeMap<Integer, Integer> map = new TreeMap<>();

    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(new File("rake.dat"));
        String s = in.nextLine();

        while (!s.equals("-1")) {

            currIndex = 0;
            map = new TreeMap<>();

            String[] temp = s.split(" ");
            int[] pre = new int[temp.length];

            for (int i = 0; i < temp.length; i++) {
                pre[i] = Integer.parseInt(temp[i]);
            }

            Node tree = constructTree(pre);
            calculate(tree, 0);

            for (int i : map.values()) {
                System.out.print(i + " ");
            }

            System.out.println();
            s = in.nextLine();
        }
    }

    public static Node constructTree(int[] pre) {
        if (currIndex >= pre.length || pre[currIndex] == -1) {
            return null;
        } else {
            Node n = new Node(pre[currIndex]);
            currIndex++;
            n.left = constructTree(pre);
            currIndex++;
            n.right = constructTree(pre);

            return n;
        }
    }

    public static void calculate(Node n, int pos) {
        if (n != null) {
            calculate(n.left, pos - 1);
            calculate(n.right, pos + 1);

            if (map.get(pos) != null) {
                map.put(pos, map.get(pos) + n.val);
            } else {
                map.put(pos, n.val);
            }
        }
    }
}
