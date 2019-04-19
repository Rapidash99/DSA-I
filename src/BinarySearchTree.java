import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class BinarySearchTree implements BST{
    class Node {
        Integer element;
        Node left = null;
        Node right = null;
        Node parent = null;
    }

    private Node root = new Node();

    public Node find(Integer element) {
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

    public void insert(Integer element) {
        if (root.element == null) {
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
            } else {
                insert(current.left, element);
            }
        }
        else if (element > current.element) {
            if (current.right == null) {
                current.right = new Node();
                current.right.element = element;
                current.right.parent = current;
            }
            else {
                insert(current.right, element);
            }
        }
    }

    public void remove(Integer element) {
        Node removable = find(element);
        if (removable != null) {
            if (removable.left == null && removable.right == null) {
                if (removable.parent.right == removable) {
                    removable.parent.right = null;
                } else {
                    removable.parent.left = null;
                }
                removable = null;
            } else if (removable.left == null) {
                removable.right.parent = removable.parent;

                if (removable.parent.right == removable) {
                    removable.parent.right = removable.right;
                } else {
                    removable.parent.left = removable.right;
                }
                removable = null;
            } else if (removable.right == null) {
                removable.left.parent = removable.parent;

                if (removable.parent.right == removable) {
                    removable.parent.right = removable.left;
                } else {
                    removable.parent.left = removable.left;
                }
                removable = null;
            } else {
                Node successor = successor(removable.right);
                removable.element = successor.element;
                if (successor.parent.right == successor) {
                    successor.parent.right = null;
                } else {
                    successor.parent.left = null;
                }
                successor = null;
            }
        }
    }

    private Node successor(Node current) { //Method that find successor from the inorder traversal
        if (current.left != null) {
            return successor(current.left);
        }
        else {
            return current;
        }
    }

    private Node predecessor(Node current) { //Method that find predecessor from the inorder traversal
        if (current.right != null) {         //I need to use only successor or predecessor, but i just write these two
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

    private String output = "BST:";

    public String print() {
        if (root != null) {
            output(root);
            print(root);
        }
        return output;
    }

    private void print(Node current) {
        if (current.left != null && (current.left.left != null || current.left.right != null)) {
            output(current.left);
        }
        if (current.right != null && (current.right.left != null || current.right.right != null)) {
            output(current.right);
        }
        if (current.left != null && (current.left.left != null || current.left.right != null)) {
            print(current.left);
        }
        if (current.right != null && (current.right.left != null || current.right.right != null)) {
            print(current.right);
        }
    }

    private void output(Node current) {
        output += "\n";
        output += current.element;
        if (current.left != null) {
            output += " " + current.left.element;
        }
        if (current.right != null) {
            output += " " + current.right.element;
        }
    }

    private String moutput = "BSMT:";

    public String mirror() {
        if (root != null) {
            moutput(root);
            mirror(root);
        }
        return moutput;
    }

    private void mirror(Node current) {
        if (current.right != null && (current.right.right != null || current.right.left != null)) {
            moutput(current.right);
        }
        if (current.left != null && (current.left.right != null || current.left.left != null)) {
            moutput(current.left);
        }
        if (current.right != null && (current.right.right != null || current.right.left != null)) {
            mirror(current.right);
        }
        if (current.left != null && (current.left.right != null || current.left.left != null)) {
            mirror(current.left);
        }
    }

    private void moutput(Node current) {  //moutput -- mirrored output
        moutput += "\n";
        moutput += current.element;
        if (current.right != null) {
            moutput += " " + current.right.element;
        }
        if (current.left != null) {
            moutput += " " + current.left.element;
        }
    }

    public static void main (String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        BinarySearchTree tree = new BinarySearchTree();

        Scanner input = new Scanner(new File("input.txt"));
        PrintWriter output  = new PrintWriter("output.txt", "UTF-8");

        String inputSequence = input.nextLine();
        ArrayList<Integer> sequence = new ArrayList<>();
        int i = 0;

        for (String temp: inputSequence.split(" ")) {
            sequence.add(Integer.parseInt(temp));
            i++;
        }

        for (i = 0; i < sequence.size(); i++) {
            tree.insert(sequence.get(i));
        }

        Node found = tree.find(input.nextInt());
        if (found != null) {
            output.println(found.element);
        }

        tree.remove(input.nextInt());

        tree.insert(input.nextInt());

        output.println(tree.traverse());

        output.println(tree.print());
        output.print(tree.mirror());
        output.close();
    }
}