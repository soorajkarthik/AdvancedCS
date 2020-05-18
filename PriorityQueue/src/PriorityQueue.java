import java.util.Arrays;
import java.util.Comparator;

/**
 * Sooraj Karthik 1B
 * Online Learning Week #2
 * PriorityQueue
 */
public class PriorityQueue<E extends Comparable<E>> {

    private E[] data;
    private int size;
    private int capacity;
    private final Comparator<? super E> comparator;

    /**
     * I didn't see the doc with instructions until after I made my PriorityQueue resizable
     * Oops..
     * I just left the resizing code in there, so the isFull method is pretty much useless
     */
    public PriorityQueue() {
        data = (E[]) new Comparable[1000];
        capacity = data.length;
        size = 0;
        comparator = Comparator.naturalOrder(); //If no comparator is passed in, uses natural order of E
    }

    public PriorityQueue(Comparator<? super E> comparator) {
        data = (E[]) new Comparable[1000];
        capacity = data.length;
        size = 0;
        this.comparator = comparator;
    }

    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return rightChildIndex(index) < size;
    }

    private boolean hasParent(int index) {
        return parentIndex(index) >= 0;
    }

    private void resize() {
        //If array is full, double its size
        if (size == capacity) {
            capacity *= 2;
            data = Arrays.copyOf(data, capacity);
        }

        //If only one-third of the array is being used, cut its size in half
        else if (size <= capacity / 3) {
            capacity /= 2;
            data = Arrays.copyOf(data, capacity);
        }
    }

    private void swap(int i1, int i2) {
        E temp = data[i1];
        data[i1] = data[i2];
        data[i2] = temp;
    }

    public void offer(E val) {
        //Ensures there's always enough room in array so we never need to throw exception
        if (isFull())
            resize();
        data[size] = val;
        size++;
        upHeap(size - 1);
    }

    public E peek() {
        if (size == 0)
            throw new IllegalStateException("PriorityQueue is empty!");

        return data[0];
    }

    public E poll() {
        if (size == 0)
            throw new IllegalStateException("PriorityQueue is empty!");

        E res = data[0];
        data[0] = data[size - 1];
        size--;
        downHeap(0);
        resize();
        return res;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o))
                return true;
        }
        return false;
    }

    public boolean removes(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) {
                data[i] = data[size - 1];
                size--;
                downHeap(i);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public String toString() {
        String res = "[";
        for (int i = 0; i < size; i++) {
            res += data[i];
            if (i == size - 1)
                return res + "]";
            res += ", ";
        }

        return "[]";
    }

    private void upHeap(int index) {
        if (hasParent(index) && comparator.compare(data[parentIndex(index)], data[index]) > 0) {
            swap(index, parentIndex(index));
            upHeap(parentIndex(index));
        }
    }

    private void downHeap(int index) {
        if (hasLeftChild(index)) {
            int swapIndex = leftChildIndex(index);
            if (hasRightChild(index) && comparator.compare(data[swapIndex], data[rightChildIndex(index)]) > 0) {
                swapIndex = rightChildIndex(index);
            }

            if (comparator.compare(data[swapIndex], data[index]) < 0) {
                swap(index, swapIndex);
                downHeap(swapIndex);
            }
        }
    }
}