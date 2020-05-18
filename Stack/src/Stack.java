public class Stack<E> {

    private E[] data;
    private int t;

    public Stack() {
        data = (E[]) new Object[1];
        t = -1;
    }

    public void push(E e) {
        t++;
        if (size() > capacity()) {
            resize(capacity() * 2);
        }

        data[t] = e;
    }

    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        E ret = data[t];
        data[t] = null;
        t--;

        if (size() < (capacity() / 3)) {
            resize(capacity() / 2);
        }
        return ret;
    }

    public E top() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        return data[t];
    }

    public int size() {
        return t + 1;
    }

    public int capacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return t == -1;
    }

    public String toString() {

        String ret = "";

        for (int i = 0; i <= t; i++) {
            if (i == t) {
                ret = ret + data[i];
                break;
            }
            ret = ret + data[i] + ",";
        }

        return ret;
    }

    private void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < t; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }


}