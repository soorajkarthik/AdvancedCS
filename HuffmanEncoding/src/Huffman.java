import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {

    public static final char EOF = '~';

    public static void main(String[] args) throws IOException {
        String fileName = "War and Peace"; //file name without extension
        HashMap<Character, Integer> freqMap = getFreqMap(fileName);
        Node root = buildHuffmanTree(freqMap);
        HashMap<Character, String> bitStrMap = getBitStringMap(root);
        writeCompressedFile(fileName, bitStrMap);
        writeTreeFile(fileName, bitStrMap);
        Node newRoot = rebuildHuffmanTree(fileName);
        decompressFile(fileName, newRoot);

        System.out.println(freqMap);
        System.out.println(root.freq);
        System.out.println(bitStrMap);
    }

    public static HashMap<Character, Integer> getFreqMap(String fileName) throws IOException {
        Scanner scan = new Scanner(new File(fileName + ".txt")).useDelimiter("");
        HashMap<Character, Integer> map = new HashMap<>();
        while (scan.hasNext())
            map.merge(scan.next().charAt(0), 1, Integer::sum);
        map.put(EOF, 1);
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

    public static void writeCompressedFile(String fileName, HashMap<Character, String> bitStrMap) throws IOException {
        BitOutputStream outputStream = new BitOutputStream(fileName + ".short");
        Scanner scan = new Scanner(new File(fileName + ".txt")).useDelimiter("");
        while (scan.hasNext()) {
            for (char c : bitStrMap.get(scan.next().charAt(0)).toCharArray()) {
                outputStream.writeBit(c - 48);
            }
        }

        for (char c : bitStrMap.get(EOF).toCharArray()) {
            outputStream.writeBit(c - 48);
        }

        outputStream.close();
    }

    public static void writeTreeFile(String fileName, HashMap<Character, String> bitStrMap) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter(new File(fileName + ".code"));
        bitStrMap.forEach((key, value) -> writer.println((int) key + " " + value));
        writer.close();
    }

    public static Node rebuildHuffmanTree(String fileName) throws IOException {
        Node root = new Node();
        Scanner scan = new Scanner(new File(fileName + ".code"));
        while (scan.hasNextLine())
            rebuildTree((char) scan.nextInt(), scan.nextLine().trim(), root);
        return root;
    }

    private static void rebuildTree(char c, String bitString, Node node) {
        if (bitString.length() == 0)
            node.cVal = c;
        else if (bitString.charAt(0) == '0') {
            if (node.left == null)
                node.left = new Node();
            rebuildTree(c, bitString.substring(1), node.left);
        } else {
            if (node.right == null)
                node.right = new Node();
            rebuildTree(c, bitString.substring(1), node.right);
        }
    }

    public static void decompressFile(String fileName, Node root) throws IOException {
        BitInputStream inputStream = new BitInputStream(fileName + ".short");
        PrintWriter writer = new PrintWriter(new File(fileName + ".new"));
        Node curr = root;
        while (curr.cVal != EOF) {
            if (curr.cVal != 0) {
                writer.print(curr.cVal);
                curr = root;
            }
            curr = inputStream.readBit() == 0 ? curr.left : curr.right;
        }
        writer.close();
    }
}

class Node implements Comparable<Node> {

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

    public Node() {
        left = right = null;
        cVal = 0;
        freq = 0;
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