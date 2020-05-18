package Part8;

public class Movie {

    private int date;
    private String title;
    private LinkedList actors;
    private LinkedList directors;

    public Movie() {
        date = 0;
        title = null;
        actors = null;
        directors = null;
    }

    public Movie(int d, String t, LinkedList act, LinkedList dir) {
        date = d;
        title = t;
        actors = act;
        directors = dir;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LinkedList getActors() {
        return actors;
    }

    public void setActors(LinkedList actors) {
        this.actors = actors;
    }

    public LinkedList getDirectors() {
        return directors;
    }

    public void setDirectors(LinkedList directors) {
        this.directors = directors;
    }

    @Override
    public String toString() {
        String res = "";
        res += "Date: " + date + "\n";
        res += "Title: " + title + "\n";
        res += "Actors: " + ((Actor) actors.get(0)).getName();

        for (int i = 1; i < actors.size(); i++) {
            res += ", " + ((Actor) actors.get(i)).getName();
        }

        res += "\n";
        res += "Directors: " + directors.get(0);

        for (int i = 1; i < directors.size(); i++) {
            res += ", " + directors.get(i);
        }

        return res;
    }
}
