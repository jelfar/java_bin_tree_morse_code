/**
 * BinaryNode.java
 *
 * Definition for a node in BinaryTree.
 *
 * @author Jonathon Elfar
 * @author Noah Dietz
 * @version 11/1/2013
 */
public class BinaryNode<T>
{
    private BinaryNode<T> left;
    private BinaryNode<T> right;
    private T nodeData;

    public BinaryNode(T n)
    {
        nodeData = n;
        left = null;
        right = null;
    }

    public BinaryNode(T n, BinaryNode<T> l, BinaryNode<T> r)
    {
        nodeData = n;
        left = l;
        right = r;
    }

    //Accessors
    public T getData()
    {
        return this.nodeData;
    }

    public BinaryNode<T> getLeft()
    {
        return this.left;
    }

    public BinaryNode<T> getRight()
    {
        return this.right;
    }

    //Mutators
    public void setLeft(BinaryNode<T> l) {
        this.left = l;
    }

    public void setRight(BinaryNode<T> r) {
        this.right = r;
    }

    public void setValue(T val) {
        this.nodeData = val;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o instanceof BinaryNode<?>) {
           if(((BinaryNode<?>)o).nodeData.equals(this.nodeData))
               return true;
        }
        return false;
    }
}
