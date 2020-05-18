package Part3;

public class Actor {

    private String name;

    public Actor() {
        name = null;
    }

    public Actor(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }
}
