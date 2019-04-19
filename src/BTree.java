import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BTree {
    class Node {
        Integer[] elements = new Integer[3];
        int size = 0;
        int childs = 0;
        Node parent = null;
        Node[] child = new Node[4];
        boolean isLeaf = true;
    }

    private Node root = new Node();

    public Node find(Integer element) {
        return find(root, element);
    }

    private Node find(Node current, Integer element) {
        for (int i = 0; i < current.size; i++) {
            if (element.equals(current.elements[i])) {
                return current;
            }
        }
        if (current.size == 2) {
            if (element < current.elements[0]) {
                if (current.child[0] != null) {
                    return find(current.child[0], element);
                }
                else {
                    return null;
                }
            } else if (element < current.elements[1]) {
                if (current.child[1] != null) {
                    return find(current.child[1], element);
                }
                else {
                    return null;
                }
            } else {
                if (current.child[2] != null) {
                    return find(current.child[2], element);
                }
                else {
                    return null;
                }
            }
        }
        else if (current.size == 1) {
            if (element < current.elements[0]) {
                if (current.child[0] != null) {
                    return find(current.child[0], element);
                }
                else {
                    return null;
                }
            }
            else {
                if (current.child[1] != null) {
                    return find(current.child[1], element);
                }
            }
        }
        return null;
    }

    public void insert(Integer element) {
        insert(root, element);

    }

    public void insertion(Integer element) {

    }

    private void insert(Node current, Integer element) {
        if (current.isLeaf) {
            current.elements[current.size] = element; //add element
            current.size++;
            Selection.SelectionSorting(current.elements);
            if (current.size == 3) { //If it is full
                split(current);
            }
        }
        else {
            int i = 0;
            while (i < current.size && element > current.elements[i]) {  //finding necessary child
                i++;
            }
            insert(current.child[i], element);
        }
    }

    private void split(Node current) {
        if (current != root) {
            current.parent.elements[current.parent.size] = current.elements[1]; //add element to parent
            current.parent.size++;
            Selection.SelectionSorting(current.parent.elements);

            if (current.parent.child[0] == current) {
                if (current.parent.childs == 3) {
                    current.parent.child[3] = current.parent.child[2];

                    current.parent.child[2] = current.parent.child[1];

                    current.parent.child[1] = new Node();
                    current.parent.child[1].parent = current.parent;
                    current.parent.child[1].elements[0] = current.elements[2];
                    current.parent.child[1].size++;

                    current.parent.child[0] = new Node();
                    current.parent.child[0].parent = current.parent;
                    current.parent.child[0].elements[0] = current.elements[0];
                    current.parent.child[0].size++;

                    if (current.parent == root) {
                        splitRoot(current);
                        return;
                    } else {
                        split(current.parent);
                    }
                } else if (current.parent.childs == 2) {
                    current.parent.child[2] = current.parent.child[1];

                    current.parent.child[1] = new Node();
                    current.parent.child[1].parent = current.parent;
                    current.parent.child[1].elements[0] = current.elements[2];
                    current.parent.child[1].size++;

                    current.parent.child[0] = new Node();
                    current.parent.child[0].parent = current.parent;
                    current.parent.child[0].elements[0] = current.elements[0];
                    current.parent.child[0].size++;

                    current.parent.childs++;
                } else {
                    current.parent.child[1] = new Node();
                    current.parent.child[1].parent = current.parent;
                    current.parent.child[1].elements[0] = current.elements[2];
                    current.parent.child[1].size++;

                    current.parent.child[0] = new Node();
                    current.parent.child[0].parent = current.parent;
                    current.parent.child[0].elements[0] = current.elements[0];
                    current.parent.child[0].size++;

                    current.parent.childs++;
                }
            } else if (current.parent.child[1] == current) {
                if (current.parent.childs == 3) {
                    current.parent.child[3] = new Node();
                    current.parent.child[3].parent = current.parent;
                    current.parent.child[3] = current.parent.child[2];

                    current.parent.child[2] = new Node();
                    current.parent.child[2].parent = current.parent;
                    current.parent.child[2].elements[0] = current.elements[2];
                    current.parent.child[2].size++;

                    current.parent.child[1] = new Node();
                    current.parent.child[1].parent = current.parent;
                    current.parent.child[1].elements[0] = current.elements[0];
                    current.parent.child[1].size++;

                    if (current.parent == root) {
                        splitRoot(current);
                    } else {
                        split(current.parent);
                    }
                } else if (current.parent.childs == 2) {
                    current.parent.child[2] = new Node();
                    current.parent.child[2].parent = current.parent;
                    current.parent.child[2].elements[0] = current.elements[2];
                    current.parent.child[2].size++;

                    current.parent.child[1] = new Node();
                    current.parent.child[1].parent = current.parent;
                    current.parent.child[1].elements[0] = current.elements[0];
                    current.parent.child[1].size++;

                    current.parent.childs++;
                }
            } else if (current.parent.child[2] == current) {
                current.parent.child[3] = new Node();
                current.parent.child[3].parent = current.parent;
                current.parent.child[3].elements[0] = current.elements[2];
                current.parent.child[3].size++;

                current.parent.child[2] = new Node();
                current.parent.child[2].parent = current.parent;
                current.parent.child[2].elements[0] = current.elements[0];
                current.parent.child[2].size++;

                if (current.parent == root) {
                    splitRoot(current);
                    return;
                } else {
                    split(current.parent);
                }
            }
            if (current.parent.size == 3) {
                split(current.parent);
            }
        }
        else {
            root.isLeaf = false;
            root.child[0] = new Node();
            root.child[0].parent = root;
            root.child[0].elements[0] = root.elements[0];
            root.child[0].size++;

            root.child[1] = new Node();
            root.child[1].parent = root;
            root.child[1].elements[0] = root.elements[2];
            root.child[1].size++;

            root.size -= 2;
            root.elements[0] = root.elements[1];
            root.elements[1] = null;
            root.elements[2] = null;
            root.childs += 2;
        }
    }

    private void splitRoot(Node current) {
        Node newRoot = new Node();
        newRoot.isLeaf = false;
        newRoot.childs = 2;
        newRoot.size = 1;
        newRoot.elements[0] = root.elements[1];

        newRoot.child[0] = new Node();
        newRoot.child[0].parent = newRoot;
        newRoot.child[0].elements[0] = root.elements[0];
        newRoot.child[0].size++;

        newRoot.child[1] = new Node();
        newRoot.child[1].parent = newRoot;
        newRoot.child[1].elements[0] = root.elements[2];
        newRoot.child[1].size++;

        newRoot.child[0].isLeaf = false;
        current.parent.child[0].parent = newRoot.child[0];
        newRoot.child[0].child[0] = current.parent.child[0];
        current.parent.child[1].parent = newRoot.child[0];
        newRoot.child[0].child[1] = current.parent.child[1];

        newRoot.child[1].isLeaf = false;
        current.parent.child[2].parent = newRoot.child[1];
        newRoot.child[1].child[0] = current.parent.child[2];
        current.parent.child[3].parent = newRoot.child[1];
        newRoot.child[1].child[1] = current.parent.child[3];

        newRoot.child[0].childs += 2;
        newRoot.child[1].childs += 2;

        root = newRoot;

    }

    private ArrayList<Integer> traverse = new ArrayList<>();

    public ArrayList<Integer> traverse() {
        traverse(root);
        return traverse;
    }

    private void traverse(Node current) {
        /*
        if (current.child[0] != null) {
            traverse(current.child[0]);
        }
        if (current.elements[0] != null) {
            traverse.add(current.elements[0]);
        }
        if (current.child[1] != null) {
            traverse(current.child[1]);
        }
        if (current.elements[1] != null) {
            traverse.add(current.elements[1]);
        }
        if (current.child[2] != null) {
            traverse(current.child[2]);
        }
        if (current.elements[2] != null) {
            traverse.add(current.elements[2]);
        }*/
        if(current == null)
            return;
        for(int i=0;i<4;i++){
            traverse(current.child[i]);
            if(i < current.size && current.elements[i] != null)
                traverse.add(current.elements[i]);
        }
    }

    public static void main (String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        BTree tree = new BTree();

        Scanner input = new Scanner(new File("input.txt"));
        PrintWriter output = new PrintWriter("output.txt", "UTF-8");

        ArrayList<Integer> sequence = new ArrayList<>();

        boolean isChars;

        if (input.hasNextInt()) {
            isChars = false;
            while (input.hasNextInt()) {
                sequence.add(input.nextInt());
            }
        } else {
            isChars = true;
            String inputSequence = input.nextLine();

            for (String temp : inputSequence.split(" ")) {
                sequence.add((int) temp.charAt(0));
            }
        }

        Collections.sort(sequence);

        for(Integer asd: sequence){
            if(isChars)
                output.print((char)asd.intValue() + " ");
            else
            output.print(asd.toString() + " ");
        }

        for (Integer aSequence : sequence) {
            tree.insert(aSequence);
        }

        ArrayList<Integer> trav = tree.traverse();

        if (isChars) {
            for (int i = 0; i < trav.size(); i++) {
                //output.print((char)trav.get(i).intValue() + " ");
            }
        }
        else {
            for (int i = 0; i < trav.size(); i++) {
                //output.print(trav.get(i) + " ");
            }
        }
        output.close();
    }
}
