package Part2;

public class ListTester {
    public static void main(String[] args) {
        ActorLinkedList list = new ActorLinkedList();
        list.add(new Actor("Bob"));
        list.add(new Actor("Jim"));
        list.add(new Actor("John"));
        list.add(new Actor("George"));
        list.add(new Actor("Jesus"));

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            System.out.println();
        }

        System.out.println(list.get(0).getName());
    }
}
