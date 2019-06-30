package discusstion2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RectangleTest {

	@Test
	void test() {
		Rectangle r = new Rectangle(5); 
		Rectangle r2 = new Rectangle (6.0,7.0);
		assertEquals(r2.getLength(), 6.0);
		assertEquals(r2.getWidth(),7.0);
		r2.setLength(4.0);
		r2.setWidth(3.0);
		assertEquals(r2.getLength(),4.0);
		assertEquals(r2.getWidth(),3.0);
		assertEquals(r2.getArea(), 12.0);
		assertEquals(r2.getPerimeter(),14);
		assertEquals(r.getLength(), 5);
		assertEquals(r.getWidth(),5);
		r.setLength(3);
		assertEquals(r.getLength(), 3);
		assertEquals(r.getWidth(),3);
		assertEquals(r.getArea(), 9);
		assertEquals(r.getPerimeter(), 12);
		r.setWidth(4);
		
		assertEquals(r.getLength(), 4);
		assertEquals(r.getWidth(),4);
		assertEquals(r.getArea(), 16);
		assertEquals(r.getPerimeter(), 16);
		System.out.println(r.getArea());
		System.out.println(r.getPerimeter());
		System.out.println(r2.getArea());
		System.out.println(r2.getPerimeter());
		//fail("Not yet implemented");
	}

}
