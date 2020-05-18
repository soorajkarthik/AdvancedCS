package Part5;

public class LinkedList {

    private Node head;
    private int count;

    public LinkedList() {
        head = null;
        count = 0;
    }

    public void add(Object data) {
        Node temp = new Node(data);
        temp.setNextPtr(head);
        head = temp;
        count++;
    }

    public Object get(int index) {
        if (index == 0)
            return head.getData();

        int i = 1;
        for (Node current = head.getNextPtr(); current != null; current = current.getNextPtr(), i++) {
            if (i == index)
                return current.getData();
        }

        return null;
    }

    public int size() {
        return count;
    }

}
