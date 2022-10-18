package tests;

import junit.framework.TestCase;
import model.*;

public class TestPriorityQueue extends TestCase{
    private PriorityLine<Integer> test1;
    private PriorityLine<String> test2;

    public void testSceario1(){
        test1 = new PriorityLine<>(1);
        test1.insert(2, 3);
        test1.insert(4, 2);
        test1.insert(1, 4);
        test1.insert(6, 1);
    }

    public void testExtractMax(){
       testSceario1();
       assertEquals(test1.heapExtractMax().intValue(), 1);
       assertEquals(test1.heapExtractMax().intValue(), 2);
       assertEquals(test1.heapExtractMax().intValue(), 4);
       assertEquals(test1.heapExtractMax().intValue(), 6);
       assertNull(test1.heapExtractMax());
    }

    public void testTakeOut(){
        testSceario1();
        assertTrue(test1.takeOut(6));
        assertFalse(test1.takeOut(6));
    }

}
