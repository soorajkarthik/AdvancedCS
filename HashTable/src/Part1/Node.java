package Part1;

public class Node {

    private final Object key;
    private final Object value;

    public Node() {
        key = null;
        value = null;
    }

    public Node(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Key: " + key + "\tValue: " + value;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
