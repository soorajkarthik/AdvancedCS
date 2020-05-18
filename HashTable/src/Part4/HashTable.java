package Part4;

import java.util.ArrayList;
import java.util.Collections;

public class HashTable {

    Node[] table;
    int size;
    int[] perm;

    private int insertionCount = 0;
    private int searchCount = 0;

    public HashTable() {
        table = new Node[101];
        perm = new int[101];
        shuffle();
        size = 0;
    }

    public HashTable(int initCap) {
        table = new Node[initCap];
        perm = new int[initCap];
        shuffle();
        size = 0;
    }

    public void shuffle() {

        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 1; i < perm.length; i++)
            temp.add(i);

        Collections.shuffle(temp);

        perm[0] = 0;

        for (int i = 1; i < temp.size(); i++)
            perm[i] = temp.get(i - 1);


    }

    public Object put(Object key, Object value) {

        if (size == table.length)
            return null;

        int index = Math.abs(key.hashCode() % table.length);

        for (int i = 0; i < table.length; i++) {

            insertionCount++;

            Node temp = table[(i + index + perm[i]) % table.length];

            if (temp == null || temp.isRemoved) {
                table[(i + index + perm[i]) % table.length] = new Node(key, value);
                size++;
                return null;
            } else if (temp.key.equals(key)) {
                table[(i + index + perm[i]) % table.length] = new Node(key, value);
                return temp.value;
            }

        }

        return null;

    }

    public Object get(Object key) {

        if (size == 0)
            return null;

        int index = Math.abs(key.hashCode() % table.length);

        for (int i = 0; i < table.length; i++) {

            searchCount++;

            Node temp = table[(i + index + perm[i]) % table.length];

            if (temp == null)
                break;

            if (temp.key.equals(key) && !temp.isRemoved)
                return temp.value;

            else if (temp.key.equals(key))
                break;

        }

        return null;

    }

    public Object remove(Object key) {

        if (size == 0)
            return null;

        int index = Math.abs(key.hashCode() % table.length);

        for (int i = 0; i < table.length; i++) {

            Node temp = table[(i + index + perm[i]) % table.length];

            if (temp == null)
                break;

            if (temp.key.equals(key) && !temp.isRemoved) {
                temp.isRemoved = true;
                size--;
                return temp.value;
            }

        }

        return null;

    }

    @Override
    public String toString() {

        String res = "";
        for (Node n : table) {
            if (n != null && n.isRemoved)
                res += "dummy\n";

            else if (n != null)
                res += n.toString() + "\n";
        }


        return res;
    }

    public int getInsertionCount() {
        return insertionCount;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }

    class Node {

        Object key;
        Object value;
        boolean isRemoved;

        public Node() {
            key = null;
            value = null;
            isRemoved = false;
        }

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
            isRemoved = false;
        }

        @Override
        public String toString() {
            return "Key: " + key + "\tValue: " + value;
        }

    }

}



