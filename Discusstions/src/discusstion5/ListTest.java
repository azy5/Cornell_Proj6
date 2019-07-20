package discusstion5;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Black-box tests for lists.  I made this a separate class so that I could
 * share these tests with the CLinkedList class as well. 
 *
 * @param <LT> the type of list to test.
 */
public abstract class ListTest<LT extends List<Integer>> {

	abstract void assertInvariant(LT list);

	abstract LT createEmptyLT();
	
	abstract String toStringRev(LT lt);

	@Test
	public void testConstructor() {
		LT x = createEmptyLT();
		assertEquals(0, x.size());
		assertEquals("[]", x.toString());
		assertEquals("[]", toStringRev(x));
		assertInvariant(x);
	}


	public LT testCase() {
		LT ll = createEmptyLT();
		ll.add(0);
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(4);
		assertInvariant(ll);
		return ll;
	}


	/** Compare DLinkedList to standard library list */
	@Test
	public void testToString() {
		List<Integer>  ll = new java.util.LinkedList<Integer>();
		List<Integer> dll = createEmptyLT();

		assertEquals(dll.toString(), ll.toString());

		dll.add(5); ll.add(5);
		assertEquals(dll.toString(), ll.toString());

		dll.add(4); ll.add(4);
		assertEquals(dll.toString(), ll.toString());
	}

	@Test
	public void testGet() {
		LT ll = testCase();
		
		for(int i = 0; i < 5; i++)
			assertEquals(i, ll.get(i));
		
		assertThrows(IndexOutOfBoundsException.class, () -> { ll.get(-1); });
		assertThrows(IndexOutOfBoundsException.class, () -> { ll.get( 5); });
		assertThrows(IndexOutOfBoundsException.class, () -> { ll.get( 7); });
	}

	@Test
	public void testSet() {
		LT ll = testCase();
		
		assertThrows(IndexOutOfBoundsException.class, () -> { ll.set(-1, 17); });
		assertThrows(IndexOutOfBoundsException.class, () -> { ll.set( 5, 17); });
		assertThrows(IndexOutOfBoundsException.class, () -> { ll.set( 7, 17); });
		
		assertEquals(2, ll.set(2, 17));
		assertEquals("[0, 1, 17, 3, 4]", ll.toString());
		assertEquals("[4, 3, 17, 1, 0]", toStringRev(ll));
		assertEquals(5, ll.size());
		assertInvariant(ll);
	}
	
	@Test
	public void testInsert() {
		LT ll = testCase();
		
		ll.add(2, 17);
		assertEquals("[0, 1, 17, 2, 3, 4]", ll.toString());
		
		ll.add(0, 42);
		assertEquals("[42, 0, 1, 17, 2, 3, 4]", ll.toString());
		
		ll.add(7, 100);
		assertEquals("[42, 0, 1, 17, 2, 3, 4, 100]", ll.toString());
		
		assertEquals(8, ll.size());
		assertInvariant(ll);
	}
	
	@Test
	public void testInsertError() {
		LT ll = testCase();
		
		assertThrows(IndexOutOfBoundsException.class, () -> { ll.add(-1, 17); });
		assertThrows(IndexOutOfBoundsException.class, () -> { ll.add( 6, 17); });
		assertThrows(IndexOutOfBoundsException.class, () -> { ll.add( 7, 17); });
		
		assertInvariant(ll);
	}
	
	@Test
	public void testInsertEmpty() {
		LT ll = createEmptyLT();
		ll.add(0, 7);
		assertEquals("[7]", ll.toString());
	}
	
	@Test
	public void testRemove() {
		LT ll = testCase();
		
		assertEquals(2, ll.remove(2));
		assertInvariant(ll);
		assertEquals("[0, 1, 3, 4]", ll.toString());
		
		assertEquals(3, ll.remove(2));
		assertInvariant(ll);
		assertEquals("[0, 1, 4]", ll.toString());
		
		assertEquals(4, ll.remove(2));
		assertInvariant(ll);
		assertEquals("[0, 1]", ll.toString());
		
		assertEquals(0, ll.remove(0));
		assertInvariant(ll);
		assertEquals("[1]", ll.toString());
		
		assertEquals(1, ll.remove(0));
		assertInvariant(ll);
		assertEquals("[]", ll.toString());
	}
	
	@Test
	public void testRemoveErrors() {
		LT ll = testCase();
		
		assertThrows(IndexOutOfBoundsException.class, () -> { ll.remove(-1); });
		assertThrows(IndexOutOfBoundsException.class, () -> { ll.remove( 5); });
		assertThrows(IndexOutOfBoundsException.class, () -> { ll.remove( 7); });
		
		assertInvariant(ll);
		
		LT empty = createEmptyLT();
		assertThrows(IndexOutOfBoundsException.class, () -> { empty.remove( 0); });
	}
}

