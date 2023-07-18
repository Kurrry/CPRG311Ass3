package tree;

public class BSTreeNode<E> {
    private E element;
    private BSTreeNode<E> left, right;

    public BSTreeNode(E element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }
    public BSTreeNode(E element, BSTreeNode<E> left, BSTreeNode<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public BSTreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(BSTreeNode<E> left) {
        this.left = left;
    }

    public BSTreeNode<E> getRight() {
        return right;
    }

    public void setRight(BSTreeNode<E> right) {
        this.right = right;
    }

    public boolean hasLeftChild() {
        return this.left != null;
    }

    public boolean hasRightChild() {
        return this.right != null;
    }

    public boolean isLeaf() {
        return !(this.hasLeftChild() && this.hasRightChild());
    }

    public int countNodes(BSTreeNode<E> node) {
        if (node == null) return 0;

        int left = countNodes(node.left);
        int right = countNodes(node.right);

        return 1 + left + right;

    }

    public int getHeight(BSTreeNode<E> node) {
        if (node == null) return 0;

        int leftDepth = getHeight(node.left);
        int rightDepth = getHeight(node.right);

        if (leftDepth > rightDepth) return leftDepth + 1;
        return rightDepth + 1;
    }
}
