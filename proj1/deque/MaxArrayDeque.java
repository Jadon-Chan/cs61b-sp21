package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comp;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comp = c;
    }
    public T max() {
        return max(comp);
    }
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T ret = get(0);
        for (int i = 1; i < size(); i++) {
            T obj = get(i);
            if (c.compare(ret, obj) < 0) {
                ret = obj;
            }
        }
        return ret;
    }
}
