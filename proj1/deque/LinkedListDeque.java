package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private static class Node<T> {
        private T item;
        private Node<T> prev;
        private Node<T> next;

        Node() {
            this.item = null;
            prev = null;
            next = null;
        }

        public T getRecursive(int index) {
            if (index == 0) {
                return item;
            } else {
                return next.getRecursive(index - 1);
            }
        }
    }

    private final Node<T> sentinelF;
    private final Node<T> sentinelB;
    private int size;

    public LinkedListDeque() {
        sentinelF = new Node<>();
        sentinelB = new Node<>();
        sentinelF.next = sentinelB;
        sentinelF.prev = null;
        sentinelB.prev = sentinelF;
        sentinelB.next = null;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        // Create new node
        Node<T> node = new Node<>();
        node.item = item;
        // re-link
        sentinelF.next.prev = node;
        node.next = sentinelF.next;
        sentinelF.next = node;
        node.prev = sentinelF;
        // change size
        size += 1;
    }

    @Override
    public void addLast(T item) {
        // Create new node
        Node<T> node = new Node<>();
        node.item = item;
        // re-link
        sentinelB.prev.next = node;
        node.prev = sentinelB.prev;
        sentinelB.prev = node;
        node.next = sentinelB;
        // change size
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int count = size;
        Node<T> np = sentinelF.next;
        while (count > 0) {
            System.out.print(np.item + " ");
            count--;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        // re-link
        T ret = sentinelF.next.item;
        sentinelF.next = sentinelF.next.next;
        sentinelF.next.prev = sentinelF;
        // change size
        size -= 1;
        return ret;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        // re-link
        T ret = sentinelB.prev.item;
        sentinelB.prev = sentinelB.prev.prev;
        sentinelB.prev.next = sentinelB;
        // change size
        size -= 1;
        return ret;
    }

    @Override
    public T get(int index) {
        Node<T> np = sentinelF.next;
        while (index > 0) {
            np = np.next;
            index--;
        }
        return np.item;
    }

    public T getRecursive(int index) {
        return sentinelF.next.getRecursive(index);
    }

    private class ListIterator implements Iterator<T> {
        private Node<T> pos = sentinelF;

        @Override
        public boolean hasNext() {
            return pos.next != sentinelB;
        }

        @Override
        public T next() {
            pos = pos.next;
            return pos.item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Deque)) {
            return false;
        }
        Deque<T> dp = (Deque<T>) obj;
        if (dp.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!dp.get(i).equals(get(i))) {
                return false;
            }
        }
        return true;
    }
}
