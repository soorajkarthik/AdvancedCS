package Part5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ListTester {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File("actors.txt"));
        LinkedList list = new LinkedList();

        while (file.hasNextLine()) {
            list.add(new Actor(file.nextLine()));
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            System.out.println();
        }

        System.out.println(((Actor) list.get(0)).getName());
    }
}
