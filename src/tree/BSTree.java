package tree;

import exceptions.TreeException;

public class BSTree<E extends Comparable<E>> implements BSTreeADT<E>{

    @Override
    public BSTreeNode<E> getRoot() throws TreeException {
        return null;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(E entry) throws TreeException {
        return false;
    }

    @Override
    public BSTreeNode<E> search(E entry) throws TreeException {
        return null;
    }

    @Override
    public boolean add(E newEntry) throws NullPointerException {
        return false;
    }

    @Override
    public Iterator<E> inorderIterator() {
        return null;
    }

    @Override
    public Iterator<E> preorderIterator() {
        return null;
    }

    @Override
    public Iterator<E> postorderIterator() {
        return null;
    }
}
