package Part3;

public class Node {

    private Node next;
    private Object data;

    public Node() {
        next = null;
        data = null;
    }

    public Node(Object data) {
        next = null;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getNextPtr() {
        return next;
    }

    public void setNextPtr(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
