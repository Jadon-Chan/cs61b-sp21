package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int front; // inclusive
    private int back; // exclusive

    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        back = 0;
    }

    @SuppressWarnings("unchecked")
    private void checkExpand() {
        if (items.length == size) {
            T[] a = (T[]) new Object[2 * size];
            assert(front == back);
            System.arraycopy(items, front, a, 0, items.length - front);
            System.arraycopy(items, 0, a, items.length - front, front);
            front = 0;
            back = size;
            items = a;
        }
    }

    @SuppressWarnings("unchecked")
    private void checkShrink() {
        if (4 * (size - 1) < items.length && size - 1 > 4) {
            T[] a = (T[]) new Object[2 * size];
            if (front < back) {
                System.arraycopy(items, front, a, 0, size);
            }
            else {
                System.arraycopy(items, front, a, 0, items.length - front);
                System.arraycopy(items, 0, a, items.length - front, front);
            }
            front = 0;
            back = size;
            items = a;
        }
    }

    @Override
    public void addFirst(T item) {
        checkExpand();
        if (front > 0) {
            front--;
        }
        else {
            front = items.length - 1;
        }
        items[front] = item;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        checkExpand();
        items[back] = item;
        if (back < items.length - 1) {
            back++;
        }
        else {
            back = 0;
        }
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (size == 0) {
            System.out.println();
            return;
        }
        if (back > front) {
            for (int i = front; i < back; i++) {
                System.out.print(items[i] + " ");
            }
        }
        else {
            for (int i = front; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < back; i++) {
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        checkShrink();
        T ret = items[front];
        items[front] = null;
        if (front == items.length - 1) {
            front = 0;
        }
        else {
            front += 1;
        }
        size -= 1;
        return ret;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        checkShrink();
        if (back == 0) {
            back = items.length - 1;
        }
        else {
            back -= 1;
        }
        T ret = items[back];
        items[back] = null;
        size -= 1;
        return ret;
    }

    @Override
    public T get(int index) {
        if (front + index < items.length) {
            return items[front + index];
        }
        else {
            return items[index - items.length + front];
        }
    }

    private class ArrayIterator implements Iterator<T> {
        private int pos = front;
        @Override
        public boolean hasNext() {
            if (size == 0) {
                return false;
            }
            return pos < back;
        }
        @Override
        public T next() {
            pos += 1;
            return items[pos - 1];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> dp = (Deque<T>) o;
        if (dp.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(dp.get(i))) {
                return false;
            }
        }
        return true;
    }
}
