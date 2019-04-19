import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class AVLTree extends BinarySearchTree {

    class Node{
        int height = 0;
        Integer element;
        Node left = null;
        Node right = null;
        Node parent = null;
    }

    private Node root = new Node();

    public void insert(Integer element) {
        if(root.element == null) {
            root.element = element;
        }
        else {
            insert(root, element);
        }
    }

    private void insert(Node current, Integer element) {
        if (element < current.element) {
            if (current.left == null) {
                current.left = new Node();
                current.left.element = element;
                current.left.parent = current;
                if (current.right == null) {
                    heightPlus(current);
                    check(current); //check for disbalance
                }
            }
            else {
                insert(current.left, element);
            }
        }
        else {
            if (current.right == null) {
                current.right = new Node();
                current.right.element = element;
                current.right.parent = current;
                if (current.left == null) {
                    heightPlus(current);
                    check(current); //check for disbalance
                }
            }
            else {
                insert(current.right, element);
            }
        }
    }

    private void heightPlus(Node current) { //Method that increase height of nodes after insertions
        current.height++;
        if (current != root && current.parent.height > current.height) {
            heightPlus(current.parent);
        }
    }

    private void check(Node current) {
        if (current != root) {
            if (current.parent.right == current) {
                if (current.parent.left != null && Math.abs(current.height - current.parent.left.height) == 2) {
                    if (current.left.height <= current.right.height) {
                        smallLeft(current);
                    }
                    else {
                        BigLeft(current);
                    }
                }
                else if (current.parent.left == null && current.height == 2) {
                    if (current.left.height <= current.right.height) {
                        smallLeft(current);
                    }
                    else {
                        BigLeft(current);
                    }
                }
                else {
                    check(current.parent);
                }
            }
            else {
                if (current.parent.right != null && Math.abs(current.height - current.parent.right.height) == 2) {
                    if (current.right.height <= current.left.height) {
                        smallRight(current);
                    }
                    else {
                        BigRight(current);
                    }
                }
                else if (current.parent.right == null && current.height == 2) {
                    if (current.right.height <= current.left.height) {
                        smallRight(current);
                    }
                    else {
                        BigRight(current);
                    }
                }
                else {
                    check(current.parent);
                }
            }
        }
    }

    private void smallLeft (Node current) { //Case 1
        if (current.left != null) {
            current.left.parent = current.parent;
            current.parent.right = current.left;
        }
        if (current.parent == current.parent.parent.right) {
            current.parent.parent.right = current;
        }
        else {
            current.parent.parent.left = current;
        }
        current.left = current.parent;
        current.parent = current.left.parent;
        current.left.parent = current;
    }

    private void smallRight(Node current) { //Case 2
        if (current.right != null) {
            current.right.parent = current.parent;
            current.parent.left = current.right;
        }
        if (current.parent == current.parent.parent.right) {
            current.parent.parent.right = current;
        }
        else {
            current.parent.parent.left = current;
        }
        current.right = current.parent;
        current.parent = current.right.parent;
        current.right.parent = current;
    }

    private void BigLeft(Node current) { //Case 3
        if (current.left != null) {
            current.left.parent = current.parent.parent;
            current.parent.parent.right = current.left;
        }
        if (current.right != null) {
            current.right.parent = current.parent;
            current.parent.left = current.right;
        }
        current.left = current.parent.parent;
        current.right = current.parent;
        if (current.left == current.left.parent.left) {
            current.left.parent.left = current;
        }
        else {
            current.left.parent.right = current;
        }
        current.parent = current.left.parent;
        current.left.parent = current;
        current.right.parent = current;
    }

    private void BigRight(Node current) { //Case 4
        if (current.left != null) {
            current.left.parent = current.parent;
            current.parent.right = current.left;
        }
        if (current.right != null) {
            current.right.parent = current.parent.parent;
            current.parent.parent.left = current.right;
        }
        current.right = current.parent.parent;
        current.left = current.parent;
        if (current.right == current.right.parent.left) {
            current.right.parent.left = current;
        }
        else {
            current.left.parent.right = current;
        }
        current.parent = current.right.parent;
        current.right.parent = current;
        current.left.parent = current;
    }

    public Node search(Integer element) {
        return find(root, element);
    }

    private Node find(Node current, Integer element) {
        if (current == null) {
            return null;
        }
        if (current.element.equals(element)) {
            return current;
        }
        if (element < current.element) {
            return find(current.left, element);
        }
        else {
            return find(current.right, element);
        }
    }

    public void remove (Integer element) {
        Node removable = search(element);
        if (removable != null) {
            if (removable.left == null && removable.right == null) {
                heightMinus(removable);
                if (removable.parent.right == removable) {
                    removable.parent.right = null;
                } else {
                    removable.parent.left = null;
                }
            } else if (removable.left == null) {
                heightMinus(removable);
                removable.right.parent = removable.parent;

                if (removable.parent.right == removable) {
                    removable.parent.right = removable.right;
                } else {
                    removable.parent.left = removable.right;
                }
            } else if (removable.right == null) {
                heightMinus(removable);
                removable.left.parent = removable.parent;

                if (removable.parent.right == removable) {
                    removable.parent.right = removable.left;
                } else {
                    removable.parent.left = removable.left;
                }
            } else {
                Node successor = successor(removable.right);
                removable.element = successor.element;
                if (!(successor.parent.left != null && successor.parent.right != null)) {
                    heightMinus(successor.parent);
                }
                if (successor.parent.right == successor) {
                    successor.parent.right = null;
                }
                else {
                    successor.parent.left = null;
                }
                check(successor);
                return;
            }
            check(removable);
        }
    }

    private void heightMinus(Node removable) { //Method that decreasing height of nodes after removing
        removable.parent.height--;
        if (removable.parent.parent.right == removable.parent) {
            if (removable.parent != root && removable.parent.parent.height - removable.parent.height == 2 && removable.parent.parent.height - removable.parent.parent.left.height != 1) {
                heightMinus(removable.parent);
            }
        }
        else {
            if (removable.parent != root && removable.parent.parent.height - removable.parent.height == 2 && removable.parent.parent.height - removable.parent.parent.right.height != 1) {
                heightMinus(removable.parent);
            }
        }
    }

    private Node successor(Node current) {
        if (current.left != null) {
            return successor(current.left);
        }
        else {
            return current;
        }
    }

    private Node predecessor(Node current) {
        if (current.right != null) {
            return successor(current.right);
        }
        else {
            return current;
        }
    }

    private String traverse = "";

    public String traverse() {
        traverse(root);
        return traverse;
    }

    private void traverse(Node current) {
        if (current.left != null) {
            traverse(current.left);
        }
        traverse += current.element + " ";
        if (current.right != null) {
            traverse(current.right);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        AVLTree tree = new AVLTree();

        Scanner input = new Scanner(new File("input.txt"));
        PrintWriter output  = new PrintWriter("output.txt", "UTF-8");

        ArrayList<Integer> sequence = new ArrayList<>();

        while (input.hasNextInt()) {
            sequence.add(input.nextInt());
        }

        int i = 0;

        for (i = 0; i < sequence.size(); i++) {
            tree.insert(sequence.get(i));
        }

        String inorder = tree.traverse();
        ArrayList<Integer> inorderSequence = new ArrayList<>();

        for (String temp: inorder.split(" ")) {
            inorderSequence.add(Integer.parseInt(temp));
            i++;
        }

        int sum = 0;

        for (i = 1; i < inorderSequence.size(); i++) {
            sum += i;
        }

        output.print(sum);
        output.close();

    }
}