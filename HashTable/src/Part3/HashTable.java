package Part3;

public class HashTable {

    Node[] table;
    int size;

    private int insertionCount = 0;
    private int searchCount = 0;

    public HashTable() {
        table = new Node[101];
        size = 0;
    }

    public HashTable(int initCap) {
        table = new Node[initCap];
        size = 0;
    }

    public Object put(Object key, Object value) {


        if (size == table.length)
            return null;

        int index = Math.abs(key.hashCode() % table.length);


        for (int i = index; i < index + table.length; i++) {

            insertionCount++;
            Node temp = table[Math.abs(i * i % table.length)];

            if (temp == null || temp.isRemoved) {
                table[Math.abs(i * i % table.length)] = new Node(key, value);
                size++;
                return null;
            } else if (temp.key.equals(key)) {
                table[Math.abs(i * i % table.length)] = new Node(key, value);
                return temp.value;
            }

        }

        return null;

    }

    public Object get(Object key) {


        if (size == 0)
            return null;

        int index = Math.abs(key.hashCode() % table.length);

        for (int i = index; i < index + table.length; i++) {

            searchCount++;

            Node temp = table[Math.abs(i * i % table.length)];

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

        for (int i = index; i < index + table.length; i++) {

            Node temp = table[Math.abs(i * i % table.length)];

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



