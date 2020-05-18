package Part5;

import java.util.LinkedList;

public class HashTable {

    LinkedList[] table;
    int size;

    private int insertionCount = 0;
    private int searchCount = 0;

    public HashTable() {
        table = new LinkedList[101];
        for (int i = 0; i < table.length; i++)
            table[i] = new LinkedList<Node>();
        size = 0;
    }

    public HashTable(int initCap) {
        table = new LinkedList[initCap];
        for (int i = 0; i < table.length; i++)
            table[i] = new LinkedList<Node>();
        size = 0;
    }

    public Object put(Object key, Object value) {

        int index = Math.abs(key.hashCode() % table.length);
        LinkedList<Node> bucket = table[index];
        Node temp = null;
        insertionCount++;
        for (Node n : bucket) {

            insertionCount++;

            if (n.key.equals(key)) {
                temp = n;
                break;
            }
        }

        if (temp != null) {
            bucket.remove(temp);
            size--;
        }

        bucket.add(new Node(key, value));
        size++;

        table[index] = bucket;


        return temp == null ? null : temp.value;

    }

    public Object get(Object key) {

        int index = Math.abs(key.hashCode() % table.length);
        LinkedList<Node> bucket = table[index];

        for (Node n : bucket) {

            searchCount++;

            if (n.key.equals(key))
                return n.value;
        }

        return null;

    }

    public Object remove(Object key) {

        int index = Math.abs(key.hashCode() % table.length);
        LinkedList<Node> bucket = table[index];
        Node temp = null;

        for (Node n : bucket) {
            if (n.key.equals(key)) {
                temp = n;
                break;
            }
        }

        if (temp != null) {
            bucket.remove(temp);
            size--;
        }

        return temp == null ? null : temp.value;


    }

    @Override
    public String toString() {

        String res = "";

        for (LinkedList<Node> l : table) {

            for (Node n : l) {

                res += n.toString() + "\n";
            }

        }

        return res;
    }

    public int size() {
        return size;
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

        public Node() {
            key = null;
            value = null;
        }

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Key: " + key + "\tValue: " + value;
        }

    }

}



