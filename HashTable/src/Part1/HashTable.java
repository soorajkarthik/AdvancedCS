package Part1;

public class HashTable {

    Node[] table;

    public HashTable() {
        table = new Node[101];
    }

    public HashTable(int initCap) {
        table = new Node[initCap];
    }

    public Object put(Object key, Object value) {

        int index = key.hashCode() % table.length;
        Node temp = table[index];
        table[index] = new Node(key, value);

        return temp == null ? null : temp.getValue();

    }

    public Object get(Object key) {

        int index = key.hashCode() % table.length;
        return table[index] == null ? null : table[index].getValue();

    }

    public Object remove(Object key) {
        int index = key.hashCode() % table.length;
        Node temp = table[index];
        table[index] = null;

        return temp == null ? null : temp.getValue();
    }

    @Override
    public String toString() {

        String res = "";
        for (Node n : table) {

            if (n != null)
                res += n.toString() + "\n";

        }

        return res;
    }

}
