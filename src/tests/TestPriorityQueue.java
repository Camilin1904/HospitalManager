package tests;

import junit.framework.TestCase;
import model.*;

public class TestPriorityQueue extends TestCase{
    private PriorityLine<Integer,Integer> test1;
    private PriorityLine<String, Integer> test2;

    
    public void testSceario1(){
        test1 = new PriorityLine<>(1);
        test1.insert(new Node<Integer,Integer>(2, 3), 3);
        test1.insert(new Node<Integer,Integer>(4, 2), 2);
        test1.insert(new Node<Integer,Integer>(1, 4),7);
        test1.insert(new Node<Integer,Integer>(3, 7),90);
    }

    public void testExtractMax(){
       testSceario1();
       /* 
       assertEquals(test1.heapExtractMax().intValue(), 1);
       assertEquals(test1.heapExtractMax().intValue(), 2);
       assertEquals(test1.heapExtractMax().intValue(), 4);
       assertEquals(test1.heapExtractMax().intValue(), 6);
       assertNull(test1.heapExtractMax());
       */
      test1.heapExtractMax();
      assertEquals(test1.getHeapSize(), 3);
    }

    public void testMaximum(){
        testSceario1();
        assertEquals(test1.heapMaximum().getPriority(), 90);
    }

    public void testIncrease(){
        testSceario1();
        test1.increaseKey(0, 100);
        assertEquals(test1.heapMaximum().getPriority(), 100);
    }

    

    /* 
    public void testTakeOut(){

        testSceario1();
        assertTrue(test1.takeOut(6));
        assertFalse(test1.takeOut(6));
    }
    */
    

}
