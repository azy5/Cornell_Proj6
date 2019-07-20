package discusstion5;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;



public class DListTestIterable {

    @Test
    public void testIterator() {
        // Make a list of the integers in 10..19
        DLinkedList<Integer> d= new DLinkedList<Integer>();
        for (int k= 0; k < 10; k= k+1) {
            d.add(k+10);
        }
        
        // Test that the list contains 10..19
        Iterator<Integer> dit= d.iterator();
        int k= 0;
        while (dit.hasNext()) {
            assertEquals((Integer)(k+10), (Integer) dit.next());
            k= k+1;
        }
        
        assertEquals(10, k);
    }
    
    @Test
    public void testIterable() {
        DLinkedList<Integer> d= new DLinkedList<Integer>();
        for (int k= 0; k < 10; k= k+1) {
            d.add(k);
        }
        
        int tt= 0;
        for (Object i : d) {
            assertEquals(tt, i);
            tt= tt + 1;
        }
    }
    


}
