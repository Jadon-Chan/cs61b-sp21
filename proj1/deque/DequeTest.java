package deque;

import edu.princeton.cs.algs4.StdRandom;
import jh61b.junit.In;
import org.junit.Test;
import static org.junit.Assert.*;


public class DequeTest {

    @Test
    public void addIsEmptySizeTest() {
        Deque<String> deque1 = new LinkedListDeque<>();
        Deque<String> deque2 = new ArrayDeque<>();
        addIsEmptySizeTest(deque1);
        addIsEmptySizeTest(deque2);
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    private static void addIsEmptySizeTest(Deque<String> deque) {
        assertTrue("A newly initialized Deque should be empty", deque.isEmpty());
        deque.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, deque.size());
        assertFalse("deque should now contain 1 item", deque.isEmpty());

        deque.addLast("middle");
        assertEquals(2, deque.size());

        deque.addLast("back");
        assertEquals(3, deque.size());

        System.out.println("Printing out deque: ");
        deque.printDeque();
    }

    @Test
    public void addRemoveTest() {
        Deque<Integer> deque1 = new LinkedListDeque<>();
        Deque<Integer> deque2 = new ArrayDeque<>();
        addRemoveTest(deque1);
        addRemoveTest(deque2);
    }

    /** Adds an item, then removes an item, and ensures that deque is empty afterwards. */
    private static void addRemoveTest(Deque<Integer> deque) {
        // should be empty
        assertTrue("deque should be empty upon initialization", deque.isEmpty());

        deque.addFirst(10);
        // should not be empty
        assertFalse("deque should contain 1 item", deque.isEmpty());

        deque.removeFirst();
        // should be empty
        assertTrue("deque should be empty after removal", deque.isEmpty());
    }

    @Test
    public void removeEmptyTest() {
        Deque<Integer> deque1 = new LinkedListDeque<>();
        Deque<Integer> deque2 = new ArrayDeque<>();
        removeEmptyTest(deque1);
        removeEmptyTest(deque2);
    }
    /* Tests removing from an empty deque */
    private static void removeEmptyTest(Deque<Integer> deque) {
        deque.addFirst(3);

        deque.removeLast();
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();

        int size = deque.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    public void emptyNullReturnTest() {
        Deque<Integer> deque1 = new LinkedListDeque<>();
        Deque<Integer> deque2 = new ArrayDeque<>();
        emptyNullReturnTest(deque1);
        emptyNullReturnTest(deque2);
    }
    /* check if null is return when removing from an empty LinkedListDeque. */
    private static void emptyNullReturnTest(Deque<Integer> deque) {
        assertNull("Should return null when removeFirst is called on an empty Deque,", deque.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque,", deque.removeLast());
    }

    @Test
    public void bigLLDequeTest() {
//        Deque<Integer> deque1 = new LinkedListDeque<>();
        Deque<Integer> deque2 = new ArrayDeque<>();
//        bigLLDequeTest(deque1);
        bigLLDequeTest(deque2);
    }
    /* Add large number of elements to deque; check if order is correct. */
    private static void bigLLDequeTest(Deque<Integer> deque) {
        for (int i = 0; i < 1000000; i++) {
            deque.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) deque.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) deque.removeLast(), 0.0);
        }
    }

    @Test
    public void shrinkTest() {
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < 1000; i++) {
            deque.addLast(i);
        }

        for (double i = 0; i < 500; i++) {
            assertEquals("Should have the same value", i, (double) deque.removeFirst(), 0.0);
        }

        for (double i = 999; i > 500; i--) {
            assertEquals("Should have the same value", i, (double) deque.removeLast(), 0.0);
        }
    }
}
