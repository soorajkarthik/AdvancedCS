package Part3;

public class NodeTester {
    public static void main(String[] args) {
        Node temp = new Node(new Actor("Bob"));
        Node temp2 = new Node(new Actor("Jim"));
        temp2.setNextPtr(temp);

        temp = new Node(new Actor("John"));
        temp.setNextPtr(temp2);

        temp2 = new Node(new Actor("George"));
        temp2.setNextPtr(temp);

        Node head = new Node(new Actor("Bill"));
        head.setNextPtr(temp2);

        Node current = head;

        while (current != null) {
            System.out.println(current);
            current = current.getNextPtr();
        }

        System.out.println(((Actor) head.getData()).getName());
    }
}
