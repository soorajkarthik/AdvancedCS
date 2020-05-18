package Part7;

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

        file = new Scanner(new File("movies.txt"));
        LinkedList movies = new LinkedList();

        while (file.hasNextLine()) {
            String in = file.nextLine();
            int date = Integer.parseInt(in.substring(0, 4));
            String title = in.substring(5, 38).trim();
            LinkedList actors = new LinkedList();

            for (String s : in.substring(38, 84).trim().split(",")) {
                actors.add(new Actor(s));
            }

            LinkedList directors = new LinkedList();
            for (String s : in.substring(89).trim().split(",")) {
                if (s != null)
                    directors.add(s);
            }

            movies.add(new Movie(date, title, actors, directors));
        }

        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i));
            System.out.println();
        }

        System.out.println(((Movie) movies.get(0)).getActors().get(0));
    }
}