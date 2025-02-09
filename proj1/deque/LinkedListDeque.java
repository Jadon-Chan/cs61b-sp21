package deque;

public class LinkedListDeque<T> implements Deque<T>{
    private static class Node<T> {
        public T item;
        public Node<T> prev;
        public Node<T> next;

        public Node() {
            this.item = null;
            prev = null;
            next = null;
        }

        public T getRecursion(int index) {
            if (index == 0)
                return item;
            else
                return next.getRecursion(index - 1);
        }
    }

    private final Node<T> sentinelF;
    private final Node<T> sentinelB;
    private int size;

    public LinkedListDeque() {
        sentinelF = new Node<T>();
        sentinelB = new Node<T>();
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
        if (size == 0)
            return null;
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
        if (size == 0)
            return null;
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

    public T getRecursion(int index) {
        return sentinelF.next.getRecursion(index);
    }
}
