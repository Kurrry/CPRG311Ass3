import exceptions.TreeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tree.BSTree;
import tree.Iterator;

import static org.junit.jupiter.api.Assertions.*;



public class BSTreeTests {
    BSTree<Integer> bstOne, bstTwo, bstThree;

    String element;

    Iterator<Integer> iterator;

    @BeforeEach
    void setUp() throws Exception {
        bstOne = new BSTree<>();
        bstTwo = new BSTree<>();

        bstOne.add(3);
        bstOne.add(1);
        bstOne.add(2);
        bstOne.add(4);

        /*
        *       3
        *     /  \
        *    1    4
        *     \
        *      2
        * */

        bstTwo.add(30);
        bstTwo.add(10);
        bstTwo.add(20);
        bstTwo.add(40);
    }

    @AfterEach
    void tearDown() throws Exception {
        bstOne.clear();
        bstTwo.clear();
        if (bstThree != null) bstThree.clear();

        element = null;
        iterator = null;
    }


    @Test
    void testBSTTree() {
        bstThree = new BSTree<>();
        assertEquals(0, bstThree.size());
    }

    @Test
    void testBSTTreeWithRoot() throws TreeException {
        bstThree = new BSTree<>(50);
        assertEquals(50, bstThree.getRoot().getElement());
    }

    @Test
    void testHasNextPreorder() {
        assertTrue(bstOne.preorderIterator().hasNext());
    }

    @Test
    void testHasNextPostorder() {
        assertTrue(bstOne.postorderIterator().hasNext());
    }

    @Test
    void testHasNextInorder() {
        assertTrue(bstOne.inorderIterator().hasNext());
    }

    @Test
    void testNextPreorder() {
        iterator = bstOne.preorderIterator();
        assertEquals(3, iterator.next());
        assertEquals(1, iterator.next());
    }

    @Test
    void testNextPostorder() {
        iterator = bstOne.postorderIterator();
        assertEquals(2, iterator.next());
        assertEquals(1, iterator.next());
    }

    @Test
    void testNextInorder() {
        iterator = bstOne.inorderIterator();
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
    }

    @Test
    void testGetHeight() {
        assertEquals(3, bstOne.getHeight());
    }

    @Test
    void testEmpty() {
        bstThree = new BSTree<>();
        assertTrue(bstThree.isEmpty());
    }

    @Test
    void testContains() throws TreeException {
        assertTrue(bstOne.contains(1));
    }

    @Test
    void testSearch() throws TreeException {
        assertEquals(4, bstOne.search(4).getElement());
    }


}
