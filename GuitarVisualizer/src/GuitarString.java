public class GuitarString {
    private final int SAMPLING_RATE = 44100;
    private final double DECAY_FACTOR = 0.994;
    private final RingBuffer buffer;
    private int tics;

    public GuitarString(double frequency) {
        buffer = new RingBuffer((int) (SAMPLING_RATE / frequency + 0.5));
        while (!buffer.isFull()) {
            buffer.enqueue(0);
        }
        tics = 0;
    }

    public GuitarString(double[] init) {
        buffer = new RingBuffer(init.length);
        for (double d : init) {
            buffer.enqueue(d);
        }
        tics = 0;
    }

    public void pluck() {
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.dequeue();
            buffer.enqueue(Math.random() - 0.5);
        }
    }

    public void tic() {
        buffer.enqueue(DECAY_FACTOR * (buffer.dequeue() + buffer.peek()) / 2);
        tics++;
    }

    public double sample() {
        return buffer.peek();
    }

    public int time() {
        return tics;
    }
}
