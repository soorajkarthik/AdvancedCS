import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PlantATree {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(new File("plant.dat"));
        int cases = scan.nextInt();
        scan.nextLine();
        scan.nextLine();

        for (int i = 0; i < cases; i++) {

            HashMap<String, Integer> map = new HashMap<>();
            List<String> keys = new ArrayList<>();
            String nextLine = scan.nextLine();
            int totalNum = 0;

            while (!nextLine.isEmpty()) {

                if (map.get(nextLine) == null) {
                    map.put(nextLine, 1);
                    keys.add(nextLine);
                } else {
                    map.put(nextLine, map.get(nextLine) + 1);
                }

                nextLine = scan.nextLine();
                totalNum++;

            }

            Collections.sort(keys);

            for (String s : keys) {
                System.out.printf("%s %.4f\n", s, ((double) map.get(s) / totalNum) * 100);
            }

            System.out.println();
        }

    }
}
