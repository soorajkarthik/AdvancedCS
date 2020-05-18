import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordLadder {

    public static HashSet<String> dict;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(new File("dictionary.txt"));

        dict = new HashSet<>();

        while (scan.hasNextLine())
            dict.add(scan.nextLine());

        scan = new Scanner(new File("input.txt"));

        while (scan.hasNextLine()) {

            String start = scan.next();
            String end = scan.next();

            System.out.println(getResult(start, end));
        }


        System.out.println(buildLadder("sail", "ruin", 5).size());
    }

    public static String getResult(String start, String end) {

        HashSet<String> currDict = new HashSet<>(dict);

        HashSet<String> usedWords = new HashSet<>();
        Queue<Stack<String>> stackQueue = new LinkedList<>();

        if (start.length() != end.length() || !currDict.contains(start) || !currDict.contains(end))
            return "There is no word ladder between " + start + " and " + end + "!";

        if (start.equals(end))
            return ("[" + start + "]");

        for (String s : currDict) {

            if (isNeighbor(start, s)) {
                Stack<String> temp = new Stack<>();
                temp.push(start);
                temp.push(s);
                usedWords.add(s);
                stackQueue.add(temp);
            }

            if (s.length() != start.length())
                usedWords.add(s);
        }

        while (!stackQueue.isEmpty()) {

            currDict.removeAll(usedWords);
            Stack<String> curr = stackQueue.poll();

            if (curr.peek().equals(end)) {
                return curr.toString();
            }

            for (String s : currDict) {
                if (isNeighbor(curr.peek(), s) && !usedWords.contains(s)) {
                    Stack<String> temp = new Stack<>();
                    temp.addAll(curr);
                    temp.push(s);
                    stackQueue.offer(temp);
                    usedWords.add(s);
                }
            }
        }

        return "There is no word ladder between " + start + " and " + end + "!";
    }

    public static boolean isNeighbor(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        int differ = 0;

        for (int i = 0; i < s1.length(); i++) {

            if (s1.charAt(i) != s2.charAt(i))
                differ++;

            if (differ > 1)
                return false;
        }

        return true;
    }

    //EXTRA CREDIT
    public static ArrayList<Stack<String>> buildLadder(String start, String end, int length) {

        HashSet<String> currDict = new HashSet<>(dict);
        HashSet<String> removeWords = new HashSet<>();
        ArrayList<Stack<String>> res = new ArrayList<>();
        HashSet<HashSet<String>> duplicateChecker = new HashSet<>();
        Queue<Stack<String>> stackQueue = new LinkedList<>();

        for (String s : currDict) {

            if (isNeighbor(start, s)) {
                Stack<String> temp = new Stack<>();
                temp.push(start);
                temp.push(s);
                stackQueue.add(temp);
            }

            if (s.length() != start.length())
                removeWords.add(s);
        }

        currDict.removeAll(removeWords);


        while (!stackQueue.isEmpty()) {

            Stack<String> curr = stackQueue.poll();

            if (curr.peek().equals(end)) {

                HashSet<String> tempSet = new HashSet<>(curr);

                if (curr.size() == length && !duplicateChecker.contains(tempSet))
                    res.add(curr);

                else
                    duplicateChecker.add(tempSet);

                continue;
            }

            if (curr.size() > length)
                break;

            for (String s : currDict) {
                if (isNeighbor(curr.peek(), s) && !curr.contains(s)) {
                    Stack<String> temp = new Stack<>();
                    temp.addAll(curr);
                    temp.push(s);
                    stackQueue.offer(temp);
                }
            }
        }

        return res;
    }
}
