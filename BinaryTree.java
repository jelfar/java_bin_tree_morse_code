/**
 * BinaryTree.java
 *
 * Definition for a Binary Tree.
 *
 * @author Jonathon Elfar
 * @author Noah Dietz
 * @version 11/1/2013
 */
public class BinaryTree<Element> {
    private BinaryNode<Element> current; //current location in the binary tree
    private BinaryNode<Element> root; //root of binary tree

    /*
     * Recursively traverse the tree to find the parent of current. 
     * Current becomes the parent, unless current is not in the tree 
     * below finder. To use this method, pass the root as the 
     * argument. 
     *
     */
    private void findParent(BinaryNode<Element> finder) {
        if((finder.getLeft() != null && finder.getLeft().equals(current)) ||
            finder.getRight() != null && finder.getRight().equals(current)) {
            this.current = finder;
            return;
        }

        if(finder.getLeft() != null)
            findParent(finder.getLeft());
        if(finder.getRight() != null)
            findParent(finder.getRight()); 
    }

    /**
      * Inserts a new node into the tree (relative to the current node)
      * and make it the current node 
      */
    public void insert(Element item, Relative where) {
        if(isEmpty()) {
            root = new BinaryNode<Element>(item);
            current = root;
        }

        BinaryNode<Element> newNode = new BinaryNode<Element>(item);
        switch(where) {
            case LEFT_CHILD:
                current.setLeft(newNode);
                current = current.getLeft();
                break;
            case RIGHT_CHILD:
                current.setRight(newNode);
                current = current.getRight();
                break;
            default:
                break;
        }
    
    }

    /**
      * Returns state of the tree
      */
    public boolean isEmpty() {
        return root == null;
    }

    /**
      * Empty the tree
      */
    public void makeEmpty() {
        root = null;
    }

    /**
      * Move current to a new position.
      * Returns whether or not the move was successful.
      */
    public boolean move(Relative where) {
        if(isEmpty()) {
            return false;
        }

        switch(where) {
            case LEFT_CHILD:
                current = current.getLeft();
                return true;
            case RIGHT_CHILD:
                current = current.getRight();
                return true;
            case ROOT:
                current = root;
                return true;
            case PARENT:
                findParent(root);
                return true;
            default:
                return false;
        }
    }

    /**
      * Return the value of the current node
      */
    public Element retrieve() {
        return current.getData();
    }

    /**
      * Return a string representation of a pre-order traversal
      * Returns empty String if tree is empty
      */
    public String traverse() {
        if(isEmpty())
            return "";
        return traverseTree(root);
    }

    /**
      * Helper method. 
      * Return a string representation of a recursive pre-order
      * pre-order traversal of the tree. 
      */
    private String traverseTree(BinaryNode<Element> target) {
        if(target != null) {
            return target.getData() + traverseTree(target.getLeft()) + traverseTree(target.getRight()) + ""; 
        }
        return "";
    }
}
