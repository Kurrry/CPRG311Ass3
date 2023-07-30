package tree;

import exceptions.TreeException;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Objects;

public class BSTree<E extends Comparable<E>> implements BSTreeADT<E>, Serializable {

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
        if (root == null) return 0;
        return root.countNodes(root); // more silliness
    }

    @Override
    public boolean isEmpty() {
        return root == null;
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
        add_recur(root, newEntry);
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
        return new PreorderIterator();
    }

    @Override
    public Iterator<E> postorderIterator() {
        return new PostorderIterator();
    }

    private class PreorderIterator implements Iterator<E> {

        BSTreeNode<E> root = BSTree.this.root;
        private final Deque<BSTreeNode<E>> stack;

        public PreorderIterator() {
            stack = new ArrayDeque<>();
            if (root != null) stack.push(root);
        }
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() throws NoSuchElementException {
            BSTreeNode<E> node = stack.pop();
            if (node.hasRightChild()) stack.push(node.getRight());
            if (node.hasLeftChild()) stack.push(node.getLeft());
            return node.getElement();
        }
    }

    private class PostorderIterator implements Iterator<E> {

        BSTreeNode<E> root = BSTree.this.root;
        BSTreeNode<E> returnNode;
        private final Deque<BSTreeNode<E>> stack;

        public PostorderIterator() {
            stack = new ArrayDeque<>();
            if (root != null) pushAll(root);
        }

        private void pushAll(BSTreeNode<E> root) {
            while (root != null) {
                stack.addFirst(root);
                if (root.getLeft() != null) root = root.getLeft();
                else if (root.getRight() != null) root = root.getRight();
                else root = null;
            }
        }
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() throws NoSuchElementException {
            BSTreeNode<E> node = stack.peekFirst();
            assert node != null;
            if (returnNode == node.getRight()) {
                returnNode = node;
                return Objects.requireNonNull(stack.pollFirst()).getElement();
            } else {
                pushAll(node.getRight());
                returnNode = stack.pollFirst();
                assert returnNode != null;
                return returnNode.getElement();
            }
        }
    }
}
