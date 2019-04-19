public interface BST {

    //method that returns node with key "element" if it is exists, null otherwise
    BinarySearchTree.Node find(Integer element);

    //method that inserts an element with key "element"
    void insert(Integer element);

    //method that removes an element with key "element"
    void remove(Integer element);

    //method that returns the string of the inorder traversal of the tree
    String traverse();

    //method that returns the string of the tree
    String print();

    //method that returns the string of the mirrored thee
    String mirror();
}
