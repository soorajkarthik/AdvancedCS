import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanPart1 {

    public static void main(String[] args) throws IOException {
        String fileName = "test"; //file name without extension
        HashMap<Character, Integer> freqMap = getFreqMap(fileName);
        Node root = buildHuffmanTree(freqMap);
        HashMap<Character, String> bitStrMap = getBitStringMap(root);
        //writeCompressedFile(fileName, bitStrMap);
        //writeTreeFile(fileName, bitStrMap);
        //Node newRoot = rebuildHuffmanTree(fileName);
        //decompressFile(fileName, newRoot);

        System.out.println(freqMap);
        System.out.println(root.freq);
        System.out.println(bitStrMap);
    }

    public static HashMap<Character, Integer> getFreqMap(String fileName) throws IOException {
        Scanner scan = new Scanner(new File(fileName + ".txt")).useDelimiter("");
        HashMap<Character, Integer> map = new HashMap<>();
        while (scan.hasNext())
            map.merge(scan.next().charAt(0), 1, Integer::sum);
        map.put('#', 1);
        return map;
    }

    public static Node buildHuffmanTree(HashMap<Character, Integer> freqMap) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        freqMap.forEach((cVal, freq) -> queue.add(new Node(cVal, freq)));

        while (queue.size() > 1) {
            Node n1 = queue.poll();
            Node n2 = queue.poll();
            Node res = new Node(n1.freq + n2.freq);
            res.left = n1;
            res.right = n2;
            queue.add(res);
        }
        return queue.poll();
    }

    public static HashMap<Character, String> getBitStringMap(Node root) {
        HashMap<Character, String> codeMap = new HashMap<>();
        getBitStrings("", root, codeMap);
        return codeMap;
    }

    private static void getBitStrings(String bitString, Node node, HashMap<Character, String> codeMap) {
        if (node == null)
            return;
        if (node.cVal != 0)
            codeMap.put(node.cVal, bitString);
        else {
            getBitStrings(bitString + "0", node.left, codeMap);
            getBitStrings(bitString + "1", node.right, codeMap);
        }
    }

    public static void writeCompressedFile(String fileName, HashMap<Character, String> bitStrMap) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public static void writeTreeFile(String fileName, HashMap<Character, String> bitStrMap) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public static Node rebuildHuffmanTree(String fileName) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public static void decompressFile(String fileName, Node root) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    static class Node implements Comparable<Node> {

        public Node left, right;
        public char cVal;
        public int freq;

        public Node(char c, int i) {
            left = right = null;
            cVal = c;
            freq = i;
        }

        public Node(int i) {
            left = right = null;
            cVal = 0;
            freq = i;
        }

        @Override
        public int compareTo(Node n) {
            return freq - n.freq;
        }

        @Override
        public String toString() {
            return " cVal=" + cVal +
                    ", freq=" + freq;
        }
    }

}

