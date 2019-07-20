package discusstion5;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

/**
 * An instance is a doubly linked list. It provides much of the functionality
 * of Java class java.util.LinkedList.
 */
public class DLinkedList<E> extends java.util.AbstractList<E> {
    /** Number of nodes in the linked list. */
    private int size;

    /** first node of the linked list (null if the list is empty) */
    private Node head;

    /** last  node of the linked list (null if the list is empty) */
    private Node tail;
    
    /** Constructor: an empty linked list. */
    public DLinkedList() {
    	this.size = 0;
    	this.head = null;
    	this.tail = null;
    }

    /**
     * Return the number of elements in this list.
     * This operation must take constant time.
     */
    /** @see java.util.List.iterator() */
     @Override
     public Iterator<E> iterator() {
    	 DListIterator d = new DListIterator();
    	 return d;
     }
     /** An instance iterates over a DLinkedList */
      private class DListIterator implements Iterator<E> {
 Node n= head;
		@Override
		public boolean hasNext() {
			return n != null;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			if(hasNext()) {
			E data = n.data;
			n =n.succ;
			return data;
			}
			throw new NotImplementedError();
		}
     }

    public @Override int size() {
    	return this.size;
    }

    /**
     * Return "[s0, s1, .., sn]" where (s0, s1, .., sn) are strings representing
     * the objects contained in this, in order.  Uses toString to convert the
     * objects to strings.
     *
     * For example, if this contains 6 3 8 in that order, the result is "[6, 3, 8]".
     */
    public @Override String toString() {
        String res= "[";
        // invariant: res = "[s0, s1, .., sk" where sk is the object before node n
        for (Node n = head; n != null; n= n.succ) {
            if (n != head)
                res= res + ", ";
            res= res + n.data;
        }
        return res + "]";
    }

    /**
     * Return "[sn, .., s1, s0]" where (s0, s1, .., sn) are strings representing
     * the objects contained in this, in order.  Uses toString to convert the
     * objects to strings.
     *
     * For example, if this contains 6 3 8 in that order, the result is "[8, 3, 6]".
     */
    public String toStringRev() {
    	String result = "[";
    	for (Node n = tail; n != null; n = n.pred) {
    		// invariant: result = "[s0, s1, ..., sk,"
    		result += n.data;
    		if (n.pred != null)
    			result += ", ";
    	}
    	return result + "]";
    }
    
    /**
     * Place element in a new node at the end of the list and return the new node.
     * This operation must take constant time.
     */
    private Node append(E element) {
    	Node result = new Node(this.tail, element, null);
    	this.size += 1;
    	
    	if (this.tail == null) {
    		this.tail = result; this.head = result;
    	}
    	else {
    		this.tail.succ = result;
    		this.tail = result;
    	}
    	return result;
    }
    
    /** Append element to the end of this list and return true. */
    public @Override boolean add(E element) {
        // TODO item #5
    	append(element);
    	return true;
    }
    
    /**
     * Return the Node at the given index of this list.
     * Takes time proportional to min(index, size - index).
     *
     * @param index the index of the node, in [0..size).
     *              0 is the first element, 1 is the second, etc.
     * @throws IndexOutOfBoundsException if index is not in [0..size)
     */
    private Node getNode(int index) {
    	if (index < 0 || index >= size)
    		throw new IndexOutOfBoundsException();
    	
    	if (index < size/2) {
    		Node current = this.head;
    		for (int i = 0; i < index; i++)
    			current = current.succ;
    		return current;
    	}
    	else {
    		Node current = this.tail;
    		for (int i = 0; i < (size - 1) - index; i++)
    			current = current.pred;
    		return current;
    	}
    }
    
    /**
     * Return the Node at the given index of this list.
     * Takes time proportional to min(index, size - index).
     *
     * @param index the index of the node, in [0..size).
     *              0 is the first element, 1 is the second, etc.
     * @throws IndexOutOfBoundsException if index is not in [0..size)
     */
    public @Override E get(int index) {
    	return getNode(index).data;
    }
    
    /**
     * Replace the element at the given index of this list with e.
     * Takes time proportional to min(index, size - index).
     *
     * @param index the index of the node, in [0..size).
     *              0 is the first element, 1 is the second, etc.
     * @param e     the new value to place in the list
     * @return      the former value stored at the index
     * @throws IndexOutOfBoundsException if index is not in [0..size)
     */
    public @Override E set(int index, E element) {
    	Node n = getNode(index);
    	E result = n.data;
    	n.data = element;
    	return result;
    }

    /**
     * Insert element in a new node at the beginning of the list and return the
     * new node.
     * Runs in constant time.
     */
    private Node prepend(E element) {
    	Node result = new Node(null, element, this.head);
    	this.size++;
    	if (this.head == null) {
    		this.head = result;
    		this.tail = result;
    	}
    	else {
    		this.head.pred = result;
    		this.head = result;
    	}
    	return result;
    }
    
    /**
     * Insert element into a new node before Node node and return the new node.
     * Takes constant time.
     *
     * @param element the element to insert
     * @param node a non-null Node that must be in this list
     */
    private Node insertBefore(E element, Node node) {
    	Node n = new Node(node.pred, element, node);
    	this.size++;
    	
    	if (n.pred == null)
    		this.head = n;
    	else
    		n.pred.succ = n;
    	
    	n.succ.pred = n;
    	return n;
    }
    
    /**
     * Insert e into this list at position i.
     * The element currently at index i, as well as all later elements, are
     * shifted down to make room for element.
     * Takes time proportional to min (index, size - index).
     *
     * @param e the element to insert
     * @param i the place to put e, in [0..size] (note: i == size is allowed!)
     * @throws IndexOutOfBoundsException if i is not in [0..size]
     */
    public @Override void add(int index, E element) {
    	if (index == this.size)
    		append(element);
    	else
    		insertBefore(element, getNode(index));
    }
    
    /**
     * Remove n from this list and return its data.
     *
     * @param  n the node to remove.  It must be in this list and non-null.
     * @return the data inside of n
     */
    private E removeNode(Node n) {
    	assert n != null;
    	this.size--;
    	
    	if (n.pred == null)
    		this.head = n.succ;
    	else
    		n.pred.succ = n.succ;
    	
    	if (n.succ == null)
    		this.tail = n.pred;
    	else
    		n.succ.pred = n.pred;
    	
    	return n.data;
    }
    
    /**
     * Remove and return the element at index i.
     * Takes time proportional to min(i, size - i).
     *
     * @param i the index of the element to remove, in [0..size).
     *          0 is the first element, 1 is the second, etc.
     * @return the removed element
     * @throws IndexOutOfBoundsException if i is not in [0..size)
     */
    public @Override E remove(int i) {
    	return removeNode(getNode(i));
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    /** An instance is a node of this list. */
    private class Node {
        /** Predecessor of this node on list (null if this is the first node). */
        private Node pred;
        
        /** The data in this element. */
        private E data;
        
        /** Successor of this node on list. (null if this is the last node). */
        private Node succ;
        
        /** Constructor: an instance with predecessor node p (can be null),
          * successor node s (can be null), and data e. */
        private Node(Node p, E e, Node s) {
            this.pred = p;
            this.succ = s;
            this.data = e;
        }
    }

    
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Glass-box tests for DLinkedList.  Since this is an inner
     * class, it has access to DLinkedList's private types, fields, and methods.
     * 
     * This inherits the black box tests from ListTest.
     */
    public static class Tests extends ListTest<DLinkedList<Integer>> {

        /**
         * Asserts that this satisfies its invariants.  It is useful to call
         * this after any tests that modify a linked list to ensure that the
         * list remains well-formed.
         *
         * @throws AssertionFailedError if the list is not well-formed
         */
    	@Override
        public void assertInvariant(DLinkedList<Integer> list) {
        	int size = 0;
        	for (DLinkedList<?>.Node n = list.head; n != null; n = n.succ) {
        		size++;
        		if (n == list.head)
        			assertEquals(null, n.pred);
        		else {
        			assertNotEquals(null, n.pred);
        			assertEquals(n, n.pred.succ);
        		}
        		
        		if (n == list.tail)
        			assertEquals(null, n.succ);
        		else {
        			assertNotEquals(null, n.succ);
        			assertEquals(n, n.succ.pred);
        		}
        	}
        	assertEquals(size, list.size);
        }
        
        @Test
        public void testAppend() {
            DLinkedList<Integer> ll     = new DLinkedList<Integer>();
            DLinkedList<Integer>.Node n = ll.append(100);
            assertEquals("[100]", ll.toString());
            assertEquals("[100]", ll.toStringRev());
            assertEquals(1, ll.size());
            assertEquals(ll.tail, n);
            assertInvariant(ll);
        }

        @Test
        public void testPrepend() {
        	DLinkedList<Integer> ll = testCase();
        	DLinkedList<?>.Node  n  = ll.prepend(-1);
        	assertInvariant(ll);
        	assertSame(ll.head, n);
        }
        
        @Test
        public void testPrependEmpty() {
        	DLinkedList<Integer>      ll = new DLinkedList<Integer>();
        	DLinkedList<Integer>.Node n  = ll.prepend(0);
        	assertSame(ll.head, n);
        	assertEquals(1,ll.size());
        	assertEquals("[0]", ll.toString());
        	assertEquals("[0]", ll.toString());
        	assertInvariant(ll);
        }

		@Override
		DLinkedList<Integer> createEmptyLT() {
			return new DLinkedList<Integer>();
		}

		@Override
		String toStringRev(DLinkedList<Integer> lt) {
			return lt.toStringRev();
		}
    }
}
