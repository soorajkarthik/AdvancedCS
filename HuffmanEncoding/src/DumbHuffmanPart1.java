import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class DumbHuffmanPart1 {

    public static void main(String[] args) throws IOException {
        String fileName = "War and Peace"; //file name without extension
        HashMap<Character, Integer> freqMap = getFreqMap(fileName);
        Node root = buildHuffmanTree(freqMap);
        HashMap<Character, String> bitStrMap = getBitStringMap(root);
        writeCompressedFile(fileName, bitStrMap);
        writeTreeFile(fileName, bitStrMap);
        Node newRoot = rebuildHuffmanTree(fileName);
        decompressFile(fileName, newRoot);
    }

    public static HashMap<Character, Integer> getFreqMap(String fileName) throws IOException {
        Scanner scan = new Scanner(new File(fileName + ".txt")).useDelimiter("");
        HashMap<Character, Integer> map = new HashMap<>();
        while (scan.hasNext()) {
            char c = scan.next().charAt(0);
            if (map.get(c) == null)
                map.put(c, 1);
            else
                map.put(c, map.get(c) + 1);
        }
        map.put('|', 1);
        return map;
    }

    public static Node buildHuffmanTree(HashMap<Character, Integer> freqMap) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Set<Character> chars = freqMap.keySet();
        for (char c : chars) {
            Node temp = new Node(c, freqMap.get(c));
            queue.add(temp);
        }

        while (queue.size() > 1) {
            Node n1 = queue.poll();
            Node n2 = queue.poll();

            int totalFrequency = n1.freq + n2.freq;

            Node res = new Node('~', totalFrequency);
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
        if (node != null) {
            if (node.val != '~')
                codeMap.put(node.val, bitString);
            else {
                getBitStrings(bitString + "0", node.left, codeMap);
                getBitStrings(bitString + "1", node.right, codeMap);
            }
        }
    }

    public static void writeCompressedFile(String fileName, HashMap<Character, String> bitStrMap) throws FileNotFoundException {
        BitOutputStream outputStream = new BitOutputStream(fileName + ".short");
        Scanner scan = new Scanner(new File(fileName + ".txt"));
        scan.useDelimiter("");

        while (scan.hasNext()) {
            char c = scan.next().charAt(0);
            String[] bits = bitStrMap.get(c).split("");

            for (String s : bits) {
                outputStream.writeBit(Integer.parseInt(s));
            }
        }

        for (String s : bitStrMap.get('|').split("")) {
            outputStream.writeBit(Integer.parseInt(s));
        }

        outputStream.close();
    }

    public static void writeTreeFile(String fileName, HashMap<Character, String> bitStrMap) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter(new File(fileName + ".code"));
        for (char key : bitStrMap.keySet()) {
            String value = bitStrMap.get(key);
            writer.println((int) key);
            writer.println(value);
        }
        writer.close();
    }

    public static Node rebuildHuffmanTree(String fileName) throws IOException {
        Node root = new Node('~', 0);
        Scanner scan = new Scanner(new File(fileName + ".code"));
        while (scan.hasNextLine()) {
            int i = scan.nextInt();
            scan.nextLine();
            String s = scan.nextLine();
            rebuildTree(i, s, root);
        }
        return root;
    }

    private static void rebuildTree(int i, String bitString, Node node) {
        char c = ((char) i);

        if (bitString.length() == 0) {
            node.val = c;
            return;
        }

        char check = bitString.charAt(0);
        String s = bitString.substring(1);

        if (check == '0') {
            if (node.left == null)
                node.left = new Node('~', 0);
            rebuildTree(c, s, node.left);
        } else {
            if (node.right == null)
                node.right = new Node('~', 0);
            rebuildTree(c, s, node.right);
        }
    }

    public static void decompressFile(String fileName, Node root) throws IOException {
        BitInputStream inputStream = new BitInputStream(fileName + ".short");
        PrintWriter writer = new PrintWriter(new File(fileName + ".new"));
        Node curr = root;
        while (curr.val != '|') {
            if (curr.val == '~') {
                if (inputStream.readBit() == 0)
                    curr = curr.left;
                else
                    curr = curr.right;

            } else {
                writer.print(curr.val);
                curr = root;
            }
        }
        writer.close();
    }

    static class Node implements Comparable<Node> {

        public Node left, right;
        public char val;
        public int freq;

        public Node(char c, int i) {
            left = right = null;
            val = c;
            freq = i;
        }

        @Override
        public int compareTo(Node n) {
            if (freq < n.freq)
                return -1;
            else if (freq > n.freq)
                return 1;
            else
                return 0;
        }

        @Override
        public String toString() {
            return "char: " + val + "     frequency: " + freq;
        }
    }
}

