import java.util.Scanner;

public class Permutation {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        permute(str, 0, str.length() - 1);
    }

    public static void permute(String s, int start, int end) {
        if (start == end)
            System.out.println(s);

        else {
            for (int i = start; i <= end; i++) {
                s = swap(s, start, i);
                permute(s, start + 1, end);
                s = swap(s, start, i);
            }
        }
    }

    public static String swap(String s, int i, int j) {
        char[] c = s.toCharArray();
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
        return new String(c);
    }

}
