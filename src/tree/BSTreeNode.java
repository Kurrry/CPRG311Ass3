package tree;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Group 9 <br>
 *
 * Represents a node in a Binary Search Tree (BST). Each node contains an element,
 * pointers to its left and right children, as well as additional information such as
 * a count of occurrences.
 *
 * @param <E> the type of elements stored in the node
 */
public class BSTreeNode<E> implements Serializable {
    private E element;
    private BSTreeNode<E> left, right;
    @Serial
    private static final long serialVersionUID = 123456L;
    private int count = 1;

    /**
     * Creates a new BSTreeNode with the specified element and no children.
     *
     * @param element the element to be stored in the node
     */
    public BSTreeNode(E element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }
    /**
     * Creates a new BSTreeNode with the specified element, left child, and right child.
     *
     * @param element the element to be stored in the node
     * @param left the left child of the node
     * @param right the right child of the node
     */
    public BSTreeNode(E element, BSTreeNode<E> left, BSTreeNode<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    /**
     * Returns the count of occurrences of the element in the node.
     *
     * @return the count of occurrences
     */
    public int getCount() {
        return count;
    }

    /**
     * Increments the count of occurrences for the element in the node.
     */
    public void incrementCount() {
        this.count++;
    }

    /**
     * Returns the element stored in the node.
     *
     * @return the element in the node
     */
    public E getElement() {
        return element;
    }
    /**
     * Sets the element stored in the node.
     *
     * @param element the new element to be stored in the node
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Returns the left child of the node.
     *
     * @return the left child of the node
     */
    public BSTreeNode<E> getLeft() {
        return left;
    }

    /**
     * Sets the left child of the node.
     *
     * @param left the new left child for the node
     */
    public void setLeft(BSTreeNode<E> left) {
        this.left = left;
    }

    /**
     * Returns the right child of the node.
     *
     * @return the right child of the node
     */
    public BSTreeNode<E> getRight() {
        return right;
    }

    /**
     * Sets the right child of the node.
     *
     * @param right the new right child for the node
     */
    public void setRight(BSTreeNode<E> right) {
        this.right = right;
    }

    /**
     * Checks if the node has a left child.
     *
     * @return true if the node has a left child, false otherwise
     */
    public boolean hasLeftChild() {
        return this.left != null;
    }

    /**
     * Checks if the node has a right child.
     *
     * @return true if the node has a right child, false otherwise
     */
    public boolean hasRightChild() {
        return this.right != null;
    }

    /**
     * Checks if the node is a leaf node (i.e., has no children).
     *
     * @return true if the node is a leaf node, false otherwise
     */
    public boolean isLeaf() {
        return !(this.hasLeftChild() && this.hasRightChild());
    }

    /**
     * Recursively counts the number of nodes in the subtree rooted at the given node.
     *
     * @param node the root node of the subtree
     * @return the count of nodes in the subtree
     */
    public int countNodes(BSTreeNode<E> node) {
        if (node == null) return 0;

        int left = countNodes(node.left);
        int right = countNodes(node.right);

        return 1 + left + right;

    }

    /**
     * Recursively calculates the height (maximum depth) of the subtree rooted at the given node.
     *
     * @param node the root node of the subtree
     * @return the height of the subtree
     */
    public int getHeight(BSTreeNode<E> node) {
        if (node == null) return 0;

        int leftDepth = getHeight(node.left);
        int rightDepth = getHeight(node.right);

        if (leftDepth > rightDepth) return leftDepth + 1;
        return rightDepth + 1;
    }
}
