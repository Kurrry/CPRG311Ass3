package tree;

import exceptions.TreeException;

public class BSTree<E extends Comparable<E>> implements BSTreeADT<E>{

    private BSTreeNode<E> root;

    public BSTree() {
        this.root = null;
    }

    public BSTree(E root) {
        this.root = new BSTreeNode<>(root);
    }

    @Override
    public BSTreeNode<E> getRoot() throws TreeException {
        if(this.root == null) throw new TreeException();
        return root;
    }

    @Override
    public int getHeight() {
        return root.getHeight(root); // this looks silly but its more flexible
    }

    @Override
    public int size() {
        return root.countNodes(root); // more silliness
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        root = null;
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
        if (newEntry == null) throw new NullPointerException();
        try {
            if (this.contains(newEntry)) return false;
        } catch (TreeException ex) {
            ex.printStackTrace();
        }
        if (root == null) {
            root = new BSTreeNode<>(newEntry);
            return true;
        }
        root = add_recur(root, newEntry);
        return true;
    }

    private BSTreeNode<E> add_recur(BSTreeNode<E> root, E newEntry) {
        if (root == null) return new BSTreeNode<>(newEntry);

        if (newEntry.compareTo(root.getElement()) < 0) root.setLeft(add_recur(root.getLeft(), newEntry));
        else if (newEntry.compareTo(root.getElement()) > 0) root.setRight(add_recur(root.getRight(), newEntry));
        return root;
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
