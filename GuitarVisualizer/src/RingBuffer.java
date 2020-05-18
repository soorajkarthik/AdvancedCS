public class RingBuffer {

    private final double[] data;
    private int first;
    private int last;
    private int size;

    public RingBuffer(int capacity) {

        data = new double[capacity];
        first = 0;
        last = 0;
        size = 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public void enqueue(double x) {
        if (isFull()) {
            throw new IllegalStateException();
        }

        data[last] = x;
        last = ++last % data.length;
        size++;
    }

    public double dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();

        }

        double res = data[first];
        first = ++first % data.length;
        size--;
        return res;
    }

    public double peek() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        return data[first];
    }
}
