import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(5);
        tree.insert(2);
        tree.insert(6);
        tree.insert(1);
        tree.insert(3);
        tree.insert(9);
        tree.insert(4);

        tree.printSideTree();


    }

    public void insert(int data) {
        if (root == null) {
            root = new Node(data);
        } else {
            insert(data, root);
        }
    }

    private void insert(int data, Node node) {
        if (data <= node.val) {
            if (node.left == null) {
                node.left = new Node(data);
            } else {
                insert(data, node.left);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(data);
            } else {
                insert(data, node.right);
            }
        }
    }

    public void remove(int key) {

        root = remove(root, key);
    }

    private Node remove(Node node, int key) {

        if (node == null) {
            return null;
        }

        if (key < node.val) {
            node.left = remove(node.left, key);
        } else if (key > node.val) {
            node.right = remove(node.right, key);
        } else {

            if (node.left == null && node.right == null) {
                return null;
            }

            if (node.left != null && node.right != null) {
                Node minVal = findMin(node.right);
                node.val = minVal.val;
                node.right = remove(node.right, minVal.val);
            } else {

                if (node.left != null) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public boolean contains(int data) {
        return contains(data, root);
    }

    private boolean contains(int data, Node node) {
        if (node == null) {
            return false;
        }

        if (node.val == data) {
            return true;
        }

        return data <= node.val ? contains(data, node.left) : contains(data, node.right);
    }

    public void printPreOrder() {
        printPreOrder(root);
    }

    private void printPreOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.print(node.val + " ");
        printInOrder(node.right);
    }

    public void printPostOrder() {
        printPostOrder(root);
    }

    private void printPostOrder(Node node) {
        if (node == null) {
            return;
        }
        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.print(node.val + " ");
    }

    public void printLevelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {

            Node tempNode = queue.poll();
            System.out.print(tempNode.val + " ");

            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }

    public void printSideTree() {
        printSideTree(root, 0);
    }

    private void printSideTree(Node node, int level) {
        if (node == null) {
            return;
        }
        printSideTree(node.right, level + 1);
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(node.val);
        printSideTree(node.left, level + 1);
    }
}