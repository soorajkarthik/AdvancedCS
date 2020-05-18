public class Main {

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((i1, i2) -> i2 - i1);
        System.out.println(queue.toString());
        queue.offer(2);
        queue.offer(7);
        queue.offer(1);
        queue.offer(3);
        queue.offer(-18);
        System.out.println(queue.toString());
        queue.poll();
        queue.poll();
        System.out.println(queue.toString());
        queue.offer(12312);
        queue.removes(2);
        System.out.println(queue.size());
        System.out.println(queue.isEmpty());

        while (!queue.isEmpty())
            System.out.println(queue.poll());

        System.out.println(queue.size());
        System.out.println(queue.isEmpty());
    }
}