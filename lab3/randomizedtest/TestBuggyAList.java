package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> no_resize = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();
        no_resize.addLast(4);
        no_resize.addLast(5);
        no_resize.addLast(6);
        buggy.addLast(4);
        buggy.addLast(5);
        buggy.addLast(6);
        int from_no_resize, from_buggy;
        for (int i = 0; i < 3; i++) {
            from_no_resize = no_resize.removeLast();
            from_buggy = buggy.removeLast();
            assertEquals(from_no_resize, from_buggy);
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> M = new BuggyAList<>();

        int N = 500;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                M.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            }
            else if (operationNumber == 1) {
                int sizeL = L.size();
                int sizeM = M.size();
                assertEquals(sizeL, sizeM);
                System.out.println("sizeL: " + sizeL + " sizeM: " + sizeM);
            }
            else if (operationNumber == 2) {
                int sizeL = L.size();
                int sizeM = M.size();
                assertEquals(sizeL, sizeM);
                if (sizeL == 0)
                    continue;
                int lastL = L.removeLast();
                int lastM = M.removeLast();
                assertEquals(lastL, lastM);
                System.out.println("removeLastL: " + lastL + " removeLastM: " + lastM);
            }
        }
    }
}
