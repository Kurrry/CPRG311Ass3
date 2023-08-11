package tree;

import exceptions.TreeException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Group 9 <br>
 *
 * Implementation of the Binary Search Tree ADT
 */
public class BSTree<E extends Comparable<E>> implements BSTreeADT<E>, Serializable {

    private BSTreeNode<E> root;
    @Serial
    private static final long serialVersionUID = 12345L;

    /**
     * Constructs an empty BST.
     * The root of the tree is set to null.
     */
    public BSTree() {
        this.root = null;
    }

    /**
     * Constructs a BST with a specified root element.
     *
     * @param root the root element of the BST
     */
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
        return this.search(entry) != null;
    }

    @Override
    public BSTreeNode<E> search(E entry) throws TreeException {
        if (root == null) throw new TreeException();
        if (root.getElement().equals(entry)) return root;

        return searchRecur(root, entry);

    }

    /**
     * Private utility method for recursively searching for an element in a Binary Search Tree (BST).
     * This method compares elements in the BST nodes and returns the node containing the matching element
     * or the node where the element should be located in the tree.
     *
     * @param node the current node being examined
     * @param entry the element being searched for
     * @return the node containing the matching element, or the appropriate insertion point if the element is not found
     */
    private BSTreeNode<E> searchRecur(BSTreeNode<E> node, E entry) {
        if(node == null || node.getElement().compareTo(entry) == 0) return node;
        if(node.getElement().compareTo(entry) < 0) return searchRecur(node.getRight(), entry);
        return searchRecur(node.getLeft(), entry);
    }

    @Override
    public boolean add(E newEntry) throws NullPointerException {
        if (newEntry == null) throw new NullPointerException();

        if (root == null) {
            root = new BSTreeNode<>(newEntry);
            return true;
        }
        add_recur(root, newEntry);
        return true;
    }

    /**
     * Private utility method for recursively adding a new element to a Binary Search Tree (BST).
     * This method compares the new element with elements in the BST nodes to determine the appropriate
     * insertion point in the tree, or increments the count if the element already exists in the tree.
     *
     * @param root the root of the current subtree being examined
     * @param newEntry the new element to be added to the BST
     * @return the modified root node of the subtree after adding the new element
     */
    private BSTreeNode<E> add_recur(BSTreeNode<E> root, E newEntry) {
        if (root == null) return new BSTreeNode<>(newEntry);
        if (root.getElement().compareTo(newEntry) == 0) {
            root.incrementCount();
            return root;
        }

        if (newEntry.compareTo(root.getElement()) < 0) root.setLeft(add_recur(root.getLeft(), newEntry));
        else if (newEntry.compareTo(root.getElement()) > 0) root.setRight(add_recur(root.getRight(), newEntry));
        return root;
    }

    @Override
    public Iterator<E> inorderIterator() {
        return new InorderIterator();
    }

    @Override
    public Iterator<E> preorderIterator() {
        return new PreorderIterator();
    }

    @Override
    public Iterator<E> postorderIterator() {
        return new PostorderIterator();
    }

    /**
     * Represents a preorder iterator for a Binary Search Tree (BST).
     */
    private class PreorderIterator implements Iterator<E> {

        BSTreeNode<E> root = BSTree.this.root;
        private final Deque<BSTreeNode<E>> stack;

        /**
         * Constructs a preorder iterator for the BST.
         * Initializes the iterator's internal stack with the root node, if the root is not null.
         */
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

    /**
     * Represents a postorder iterator for a Binary Search Tree (BST).
     */
    private class PostorderIterator implements Iterator<E> {

        BSTreeNode<E> root = BSTree.this.root;
        BSTreeNode<E> returnNode;
        private final Deque<BSTreeNode<E>> stack;

        /**
         * Constructs a postorder iterator for the BST.
         * Initializes the iterator's internal stack with the root node and performs the initial traversal.
         */
        public PostorderIterator() {
            stack = new ArrayDeque<>();
            if (root != null) pushAll(root);
        }

        /**
         * Private utility method that pushes nodes from the given subtree onto a stack in a specific order.
         * The order is: root, left subtree, and right subtree.
         *
         * @param root the root node of the subtree being pushed
         */
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

    /**
     * Represents a inorder iterator for a Binary Search Tree (BST).
     */
    private class InorderIterator implements Iterator<E> {

        BSTreeNode<E> root = BSTree.this.root;
        private final Deque<BSTreeNode<E>> stack;

        /**
         * Constructs an inorder iterator for the BST.
         * Initializes the iterator's internal stack by traversing left from the root node, if the root is not null.
         */
        public InorderIterator() {
            stack = new ArrayDeque<>();
            if (root != null) traverseLeft(root);
        }

        /**
         * Private utility method that traverses and pushes nodes from the left subtree onto a stack.
         * The method starts with the given node and proceeds by repeatedly moving to the left child.
         *
         * @param node the starting node for the left subtree traversal
         */
        private void traverseLeft(BSTreeNode<E> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() throws NoSuchElementException {
            BSTreeNode<E> node = stack.pop();

            if (node.hasRightChild()) {
                traverseLeft(node.getRight());
            }

            return node.getElement();
        }
    }
}
