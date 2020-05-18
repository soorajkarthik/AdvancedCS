import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class War {

    private static final HashMap<String, Integer> scores = new HashMap<String, Integer>() {{
        put("2", 2);
        put("3", 3);
        put("4", 4);
        put("5", 5);
        put("6", 6);
        put("7", 7);
        put("8", 8);
        put("9", 9);
        put("T", 10);
        put("J", 11);
        put("Q", 12);
        put("K", 13);
        put("A", 14);
    }};

    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File("war.dat"));
        while (file.hasNextLine()) {

            Queue<String> player1 = new LinkedList<>(Arrays.asList(file.nextLine().split(" ")));
            Queue<String> player2 = new LinkedList<>(Arrays.asList(file.nextLine().split(" ")));
            Queue<String> pile = new LinkedList<>();

            int plays = 0;

            while (!player1.isEmpty() && !player2.isEmpty() && plays < 100000) {

                String card1 = player1.poll();
                String card2 = player2.poll();

                int score1 = scores.get(card1.substring(0, 1));
                int score2 = scores.get(card2.substring(0, 1));

                if (score1 > score2) {
                    player1.offer(card1);
                    player1.offer(card2);
                } else if (score2 > score1) {
                    player2.offer(card1);
                    player2.offer(card2);
                } else {
                    war(player1, player2, pile);
                }

                plays++;

                if (plays == 100000) {
                    System.out.println("Tie game stopped at 100000 plays");
                }
            }
        }


    }

    public static void war(Queue<String> player1, Queue<String> player2, Queue<String> pile) {


        pile.offer(player2.remove());
        pile.offer(player1.remove());

        String card1 = player1.poll();
        String card2 = player2.poll();

        pile.offer(card1);
        pile.offer(card2);

        int score1 = scores.get(card1.substring(0, 1));
        int score2 = scores.get(card2.substring(0, 1));

        if (score1 > score2) {
            while (!pile.isEmpty()) {
                player1.offer(pile.poll());
            }
        } else if (score2 > score1) {
            while (!pile.isEmpty()) {
                player2.offer(pile.poll());
            }
        } else {
            war(player1, player2, pile);
        }


    }

}
