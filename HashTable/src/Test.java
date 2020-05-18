import Part5.HashTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {


        ArrayList<String> largeSet = new ArrayList<>();
        ArrayList<String> successful = new ArrayList<>();
        ArrayList<String> unsuccessful = new ArrayList<>();

        Scanner scan = new Scanner(new File("Large Data Set.txt"));

        while (scan.hasNextLine())
            largeSet.add(scan.nextLine());

        scan = new Scanner(new File("Successful Search Records.txt"));

        while (scan.hasNextLine())
            successful.add(scan.nextLine());

        scan = new Scanner(new File("Unsuccessful Search Records.txt"));

        while (scan.hasNextLine())
            unsuccessful.add(scan.nextLine());


        double loadFactor = 0.99;
        int capacity = (int) (50000 / loadFactor);

        System.out.println("Load Factor: " + loadFactor);

        long startTime = System.currentTimeMillis();

        HashTable table = new HashTable(capacity);

        for (String s : largeSet) {
            table.put(s.split("\t")[0], s);
        }

        System.out.println("Build Time: " + (System.currentTimeMillis() - startTime));
        System.out.println("Visited Objects(Build): " + table.getInsertionCount());
        System.out.println();


        startTime = System.currentTimeMillis();
        for (String s : successful) {
            table.get(s.split("\t")[0]);
        }

        System.out.println("Successful Time: " + (System.currentTimeMillis() - startTime));
        System.out.println("Visited Objects(Successful): " + table.getSearchCount());
        System.out.println();
        table.setSearchCount(0);

        startTime = System.currentTimeMillis();
        for (String s : unsuccessful) {
            table.get(s.split("\t")[0]);
        }


        System.out.println("Unsuccessful Time: " + (System.currentTimeMillis() - startTime));
        System.out.println("Visited Objects(Unsuccessful): " + table.getSearchCount());
        System.out.println();

    }
}

