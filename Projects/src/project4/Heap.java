package project4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap<E,P> implements PriorityQueue<E, P> {
private ArrayList<E> heap= new ArrayList<E>();
private int size;
private int left;
private int rigth;
private int parent;
private Comparator<P> conparator;
public Heap(Comparator<P> c) {
		 conparator= c;
}
	@Override
	public Comparator<? super P> comparator() {
		// TODO Auto-generated method stub
		return conparator;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public E poll() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Object e, Object p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePriority(Object e, Object p) throws NoSuchElementException {
		// TODO Auto-generated method stub
		
	}
	public E getleft() {
		return null;
	}
	public E getright() {
		return null;
	}
public E getparent() {
	return null;
}
}
