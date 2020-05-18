package Part2;

public class ActorLinkedList {

    private Actor head;
    private int count;

    public ActorLinkedList() {
        head = null;
        count = 0;
    }

    public void add(Actor actor) {
        actor.setNextPtr(head);
        head = actor;
        count++;
    }

    public Actor get(int index) {
        if (index == 0)
            return head;

        int i = 1;
        for (Actor current = head.getNextPtr(); current != null; current = current.getNextPtr(), i++) {
            if (i == index)
                return current;
        }

        return null;
    }

    public int size() {
        return count;
    }

}