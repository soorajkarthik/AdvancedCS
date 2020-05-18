import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class OpenSource {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(new File("open.dat"));
        String nextLine = scan.nextLine();

        HashMap<String, HashSet<String>> map = new HashMap<>();
        String lastKey = nextLine;

        while (!nextLine.equals("0")) {
            while (!nextLine.equals("1")) {

                if (Character.isUpperCase(nextLine.charAt(0))) {
                    lastKey = nextLine;
                    map.put(nextLine, new HashSet<>());
                    nextLine = scan.nextLine();
                    continue;
                }

                boolean wasRemoved = false;

                for (String s : map.keySet()) {
                    if (map.get(s).contains(nextLine) && !s.equals(lastKey)) {
                        map.get(s).remove(nextLine);
                        nextLine = scan.nextLine();
                        wasRemoved = true;
                    }
                }

                if (wasRemoved)
                    continue;

                HashSet<String> set = map.get(lastKey);
                set.add(nextLine);
                map.put(lastKey, set);
                nextLine = scan.nextLine();
            }

            List<Map.Entry<String, HashSet<String>>> entryList = new ArrayList<>(map.entrySet());

            entryList.sort((o1, o2) -> {
                if (o1.getValue().size() == o2.getValue().size()) {
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return o2.getValue().size() - o1.getValue().size();
                }
            });

            entryList.forEach(e -> System.out.println(e.getKey() + " " + e.getValue().size()));
            nextLine = scan.nextLine();


            for (int i = 100; i >= 0; i--) {
                for (Map.Entry<String, HashSet<String>> e : entryList) {
                    if (e.getValue().size() == i) {
                        System.out.println(e.getKey() + " " + e.getValue().size());
                    }
                }
            }

        }


    }
}

