package discusstion4;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Comparators {
	// Number interfaces ///////////////////////////////////////////////////////
	
	public static interface Num {
		public double toDouble();
	}
	
	public static class Int implements Num {
		public int i;
		public Int(int i)    { this.i = i; }
		public double toDouble() { return this.i; }
		public String toString() { return String.valueOf(this.i); }
		public boolean equals(Object other) { return other instanceof Int && this.i == ((Int) other).i; }
	}
	
	public static class Doub implements Num {
		double d;
		public Doub(double d)  { this.d = d; }
		public double toDouble() { return this.d; }
		public String toString() { return String.valueOf(this.d); }
		public boolean equals(Object other) { return other instanceof Doub && this.d == ((Doub) other).d; }
	}

	/** Return the given values as Integer objects; for testing. */
	public static Int[] makeIntTest(int[] values) {
		Int[] ints = new Int[values.length];
		for (int i = 0; i < values.length; i++) {
			ints[i] = new Int(values[i]);
		}
		return ints;
	}
	
	/** Return the given values as Double objects, for testing. */
	public static Doub[] makeDoubleTest(double[] values) {
		Doub[] result = new Doub[values.length];
		for (int i = 0; i < values.length; i++) {
			result[i] = new Doub(values[i]);
		}
		return result;
	}
	
	// Comparator examples /////////////////////////////////////////////////////
	
	public static class NumberComparator implements Comparator<Num> {
		/**
		 * Compare n1 and n2 using toDouble.
		 * @see java.util.Comparator#compare
		 */
		@Override
		public int compare(Num n1, Num n2) {
			// TODO
			if(n1.toDouble()<n2.toDouble()) {
				return -1;
			}
			else if(n1.toDouble()>n2.toDouble()) {
				return 1;
			}
			else if(n1.toDouble()==n2.toDouble()) {
				return 0;
			}
			return 1;
			//throw new NotImplementedError();
		}
	}
	
	@Test
	public void testNumberComparator() {
		Int[] ia     = makeIntTest(new int[] {4,1,2,0,3});
		Int[] sorted = makeIntTest(new int[] {0,1,2,3,4});
		Arrays.sort(ia, new NumberComparator());
		assertArrayEquals(ia, sorted);
	}
	
	public static class IntegerComparator implements Comparator<Int> {
		/**
		 * Compare i1 and i2 according to their i fields.
		 * @see java.util.Comparator#compare
		 */
		@Override
		public int compare(Int n1, Int n2) {
			if(n1.i<n2.i) {
				return -1;
			}
		else if(n1.i>n2.i) {
			return 1;
		}
		else if(n1.i==n2.i) {
			return 0;
		}
		return 1;
		//throw new NotImplementedError();
	
			// TODO
			//throw new NotImplementedError();
		}
	}
	
	@Test
	void testIntegerComparator() {
		Int[] ia     = makeIntTest(new int[] {4,1,2,0,3});
		Int[] sorted = makeIntTest(new int[] {0,1,2,3,4});
		Arrays.sort(ia, new IntegerComparator());
		assertArrayEquals(ia, sorted);	
	}

	// Generic comparators /////////////////////////////////////////////////////
	
	/**
	 * A ReverseComparator stores a reference to another comparator, and
	 * sorts values in the opposite order.
	 */
	public static class ReverseComparator<T> implements Comparator<T>  {
		private Comparator<T> forwardComparator;
		
		public ReverseComparator(Comparator<T> forwardComparator) {
			this.forwardComparator = forwardComparator;
		}
		
		@Override
		public int compare(T arg0, T arg1) {
			return forwardComparator.compare(arg1,arg0);
			// TODO
			//throw new NotImplementedError();
			
		}
		
	}
	
	@Test
	public void testReverseComparator() {
		Int[] ia     = makeIntTest(new int[] {4,1,2,0,3});
		Int[] sorted = makeIntTest(new int[] {4,3,2,1,0});
		Arrays.sort(ia, new ReverseComparator<Num>(new NumberComparator()));
		assertArrayEquals(ia, sorted);
	}
	
	// Merge ///////////////////////////////////////////////////////////////////
	
	/**
	 * Add the sorted outputs produced by in1 and in2 into result, in increasing
	 * order (according to c).
	 * @param <E>
	 * 
	 * @param in1 outputs items in increasing order (according to c)
	 * @param in2 outputs items in increasing order (according to c)
	 */
	// NOTE: the <T> here is a "type parameter", it can be automatically filled
	// in with any type.
	@SuppressWarnings("unchecked")
	public static < E>  void merge(List<? super E> result, Iterator<? extends E> in1, Iterator<? extends E> in2, Comparator<? super E> c) {
	int a;
	E int1 =null;
	E int2=null;
	int h= -1;
		while(in1.hasNext()|| in2.hasNext()) {
			if(in1.hasNext()) {
			int1= in1.next();
			
			}
			else if(in1.hasNext()==false) {
				int1= null;
			}
			if(in2.hasNext()) {
			int2 = in2.next();
			
			}
else if(in2.hasNext()==false) {
				int2 =null;
			}
		if(in1.hasNext()&&in2.hasNext()) {
			
		 a=c.compare(int1, int2);
		}
		else {
			if(c.compare(int1, (E) result.get(h))<0) {
				result.add(h, int1);
				a=10;
			}
			else {
			a=0;
			}
		}
		if(a==0) {
			if(int1!=null) {
				result.add(int1);
				h++;
			}
			
if(int2!=null) {
	result.add(int2);
	h++;
			}
			
			
		}
		else if (a==-1) {
if(int1!=null) {
	result.add(int1);
	h++;
			}
				if(int2!=null) {
					result.add(int2);
					h++;
				}
			
				
		}
		else if(a==1) {
if(int2!=null) {
	result.add(int2);
	h++;
			}
				
				if(int1!=null) {
					result.add(int1);
					h++;
				}
			
				
				
		}
		System.out.println(result);
	}
		
		// TODO
		//throw new NotImplementedError();
	}
	
	@Test
	public void testMerge() {
		List<Int> in1 = Arrays.asList(makeIntTest(new int[] {1, 2, 4, 5, 7}));
		List<Int> in2 = Arrays.asList(makeIntTest(new int[] {0, 3, 6}));
		
		List<Int> result = new ArrayList<Int>();
		List<Int> expect = Arrays.asList(makeIntTest(new int[] {0,1,2,3,4,5,6,7}));
		merge(result, in1.iterator(), in2.iterator(), new IntegerComparator());
		System.out.println(result);
		assertEquals(expect, result);
	}
}
