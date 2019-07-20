package discusstion5;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * An instance is a circular linked list. It provides much of the functionality
 * of Java class java.util.LinkedList.
 */
public class CLinkedList<E> extends java.util.AbstractList<E> {
    /** Number of nodes in the linked list. */
    private int size;

    /** A special non-null node pointing to the head and tail, with null data */
    private Node sentinel;
    
    /** Constructor: an empty linked list. */
    public CLinkedList() {
    	this.sentinel = new Node();
    	this.sentinel.succ = sentinel;
    	this.sentinel.pred = sentinel;
    	this.size = 0;
    }

    /**
     * Return the number of elements in this list.
     * This operation must take constant time.
     */
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
        for (Node n = sentinel.succ; n != sentinel; n= n.succ) {
            if (n != sentinel.succ)
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
        String res= "[";
        // invariant: res = "[s0, s1, .., sk" where sk is the object after node n
        for (Node n = sentinel.pred; n != sentinel; n= n.pred) {
            if (n != sentinel.pred)
                res= res + ", ";
            res= res + n.data;
        }
        return res + "]";
    }
     
    /**
     * Place element in a new node at the end of the list and return the new node.
     * This operation must take constant time.
     */
    private Node append(E element) {
    	return new Node(sentinel.pred, element);
    }
    
    /** Append element to the end of this list and return true. */
    public @Override boolean add(E element) {
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
    	Node result = sentinel;
    	// invariant for both loops: result = this[i]
    	if (index < size / 2)
    		for (int i = -1; i < index; i++)
    			result = result.succ;
    	else
    		for (int i = this.size; i > index; i--)
    			result = result.pred;
    	return result;
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
    	return new Node(sentinel, element);
    }
    
    /**
     * Insert element into a new node before Node node and return the new node.
     * Takes constant time.
     *
     * @param element the element to insert
     * @param node a non-null Node that must be in this list
     */
    private Node insertBefore(E element, Node node) {
    	return new Node(node.pred, element);
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
    	Node n = index == size ? sentinel : getNode(index);
    	new Node(n.pred, element);
    }
    
    /**
     * Remove n from this list and return its data.
     *
     * @param  n the node to remove.  It must be in this list and non-null.
     * @return the data inside of n
     */
    private E removeNode(Node n) {
    	assert (n != sentinel);
    	n.succ.pred = n.pred;
    	n.pred.succ = n.succ;
    	size--;
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
        
        /**
         * Initialize this to be a sentinel for an empty list.
         */
        private Node() {
        	this.data = null;
        	this.pred = this;
        	this.succ = this;
        }
        
        /**
         * Initialize this to a new node, placing it in the last right after p.
         * reestablish the list invariants.
         */
        private Node(Node p, E e) {
        	CLinkedList.this.size++;
        	this.data = e;
        	this.pred = p;
        	this.succ = p.succ;
        	this.pred.succ = this;
        	this.succ.pred = this;
        }
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Glass-box tests for CLinkedList.  Since this is an inner
     * class, it has access to CLinkedList's private types, fields, and methods.
     */
    public static class Tests extends ListTest<CLinkedList<Integer>> {

        /**
         * Asserts that list satisfies its invariants.  It is useful to call
         * this after any tests that modify a linked list to ensure that the
         * list remains well-formed.
         *
         * @throws AssertionFailedError if the list is not well-formed
         */
    	@Override
        public void assertInvariant(CLinkedList<Integer> list) {
    		int size = 0;
    		assert list.sentinel != null;
    		for (CLinkedList<Integer>.Node n = list.sentinel.succ; n != list.sentinel; n = n.succ) {
    			assertNotNull(n);
    			assertSame(n, n.succ.pred);
    			assertSame(n, n.pred.succ);
    			size++;
    		}
    		assertEquals(size, list.size);
    	}

        @Test
        public void testAppend() {
            CLinkedList<String> ll     = new CLinkedList<String>();
            CLinkedList<String>.Node n = ll.append("Mike");
            assertEquals("[Mike]", ll.toString());
            assertEquals("[Mike]", ll.toStringRev());
            assertEquals(1, ll.size());
            assertEquals(ll.sentinel.succ, n);   
        }

		@Override
		CLinkedList<Integer> createEmptyLT() {
			return new CLinkedList<Integer>();
		}

		@Override
		String toStringRev(CLinkedList<Integer> lt) {
			return lt.toStringRev();
		}
    }
}
