package Part1;

public class NodeTester {
    public static void main(String[] args) {
        Actor temp = new Actor("Bob");
        Actor temp2 = new Actor("Jim");
        temp2.setNextPtr(temp);

        temp = new Actor("John");
        temp.setNextPtr(temp2);

        temp2 = new Actor("George");
        temp2.setNextPtr(temp);

        Actor head = new Actor("Bill");
        head.setNextPtr(temp2);

        Actor current = head;

        while (current != null) {
            System.out.println(current.getName());
            current = current.getNextPtr();
        }

        System.out.println(head);
    }
}
