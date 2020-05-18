package Part8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ListTester {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File("actors.txt"));
        LinkedList actors = new LinkedList();

        while (file.hasNextLine()) {
            actors.add(new Actor(file.nextLine()));
        }

        file = new Scanner(new File("movies.txt"));
        LinkedList movies = new LinkedList();

        while (file.hasNextLine()) {
            String in = file.nextLine();
            int date = Integer.parseInt(in.substring(0, 4));
            String title = in.substring(5, 38).trim();
            LinkedList acts = new LinkedList();

            for (String s : in.substring(38, 84).trim().split(", ")) {
                acts.add(new Actor(s));
            }

            LinkedList directors = new LinkedList();
            for (String s : in.substring(89).trim().split(",")) {
                if (s != null)
                    directors.add(s);
            }

            movies.add(new Movie(date, title, acts, directors));
        }

        for (int a = 0; a < actors.size(); a++) {

            Actor currActor = (Actor) actors.get(a);
            System.out.println(currActor.getName());

            for (int m = 0; m < movies.size(); m++) {
                Movie currMovie = (Movie) movies.get(m);

                for (int i = 0; i < currMovie.getActors().size(); i++) {

                    String s = ((Actor) currMovie.getActors().get(i)).getName();


                    if (s.equals(currActor.getName())) {
                        System.out.println("Date: " + currMovie.getDate() + "\nTitle: " + currMovie.getTitle());
                        System.out.println();
                    }
                }
            }

            System.out.println();
            System.out.println();
        }

    }
}