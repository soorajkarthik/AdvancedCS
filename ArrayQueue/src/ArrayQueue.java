public class ArrayQueue<E> {

    private E[] data;
    private int f;
    private int size;

    public ArrayQueue() {

        data = (E[]) new Object[1];
        f = 0;
        size = 0;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> aq = new ArrayQueue<>();
        aq.enqueue(5);
        aq.enqueue(3);
        System.out.println(aq);
        aq.size();
        aq.dequeue();
        System.out.println(aq);

    }

    public void enqueue(E e) {

        if (size == data.length) {
            resize(size * 2);
        }

        data[(f + size) % data.length] = e;
        size++;

    }

    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        E res = data[f];
        data[f] = null;
        f = ++f % data.length;
        size--;

        if (size < data.length / 3 && data.length / 2 > 4) {
            resize(data.length / 2);
        }

        return res;
    }

    public E first() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        return data[f];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {

        String res = "";

        if (!isEmpty()) {
            res = res + data[f].toString();

            for (int i = (f + 1) % data.length; i < f + size; i++) {
                E temp = data[i % data.length];

                if (temp == null) {
                    break;
                }

                res = res + ", " + temp.toString();
            }
        }

        return res;
    }

    private void resize(int capacity) {

        E[] newData = (E[]) new Object[capacity];

        for (int i = f, iNew = 0; i < f + size && iNew < newData.length; i++, iNew++) {
            newData[iNew] = data[i % data.length];
        }

        f = 0;
        data = newData;
    }
}
