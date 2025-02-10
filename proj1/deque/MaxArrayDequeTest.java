package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;


public class MaxArrayDequeTest {

    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

    private static class DoubleComparator implements Comparator<Double> {
        @Override
        public int compare(Double a, Double b) {
            if (Math.abs(a - b) < Math.pow(10, -6)) {
                return 0;
            } else if (a - b > 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return a.compareToIgnoreCase(b);
        }
    }

    private static class DigitalSumComparator implements Comparator<Integer> {
        private int digitalSum(int x) {
            int ret = 0;
            while (x > 0) {
                ret += x % 10;
                x /= 10;
            }
            return ret;
        }
        @Override
        public int compare(Integer a, Integer b) {
            return digitalSum(a) - digitalSum(b);
        }
    }

    @Test
    public void digitalSumComparatorTest() {
        int[] test = {12, 29, 39, 19, 37, 59, 92, 63};
        DigitalSumComparator digitalSumComparator = new DigitalSumComparator();
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(digitalSumComparator);
        for (int i: test) {
            deque.addLast(i);
        }
        assertEquals(59, (int) deque.max());
    }

    @Test
    public void stringTest() {
        String[] test = {"Lived", "happily", "ever", "after"};
        StringComparator stringComparator = new StringComparator();
        MaxArrayDeque<String> deque = new MaxArrayDeque<>(stringComparator);
        for (String str: test) {
            deque.addLast(str);
        }
        assertEquals("Lived", deque.max());
    }

    @Test
    public void intTest() {
        Integer[] test = {1, 39, 23947, 47129, 20652, 50852, 11536};
        IntComparator stringComparator = new IntComparator();
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(stringComparator);
        for (Integer i: test) {
            deque.addLast(i);
        }
        assertEquals(50852, (int) deque.max());
    }

    @Test
    public void doubleTest() {
        Double[] test = {Math.sin(StdRandom.uniform()), Math.PI, Math.E};
        DoubleComparator stringComparator = new DoubleComparator();
        MaxArrayDeque<Double> deque = new MaxArrayDeque<>(stringComparator);
        for (Double str: test) {
            deque.addLast(str);
        }
        assertEquals(Math.PI, deque.max(), Math.pow(10, -6));
    }
}
