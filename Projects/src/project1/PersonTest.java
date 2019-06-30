package project1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PersonTest {

	@Test
	void test() {
		Person p1= new Person("John",1995,6,17);
		Person p2= new Person ("Bob",1999,7,6);
		Person p3= new Person ("Hannah",1995,6,16);
		Person p4 =new Person ("Jessica", 2000,2,27);
		assertEquals(p1.name(),"John");
		assertEquals(p2.name(),"Bob");
		assertEquals(p3.name(),"Hannah");
		assertEquals(p4.name(),"Jessica");
		assertEquals(p1.brithyear(),1995);
		assertEquals(p2.brithyear(),1999);
		assertEquals(p3.brithyear(),1995);
		assertEquals(p4.brithyear(),2000);
		assertEquals(p1.brithmonth(),6);
		assertEquals(p2.brithmonth(),7);
		assertEquals(p3.brithmonth(),6);
		assertEquals(p4.brithmonth(),2);
		assertEquals(p1.brithday(),17);
		assertEquals(p2.brithday(),6);
		assertEquals(p3.brithday(),16);
		assertEquals(p4.brithday(),27);
		assertEquals(p1.mother(), null);
		assertEquals(p2.mother(), null);
		assertEquals(p3.mother(), null);
		assertEquals(p4.mother(), null);
		assertEquals(p1.father(), null);
		assertEquals(p2.father(), null);
		assertEquals(p3.father(), null);
		assertEquals(p4.father(), null);
		assertEquals(p2.isOlderThan(p1),false);
		p1.setfather(p4);
		p1.setmother(p2);
		p3.setfather(p1);
		p3.setmother(p2);
		assertEquals(p1.isHalfSibling(p3), true);
		assertEquals(p1.isSibling(p3), false);
		
		//fail("Not yet implemented");
	}

}
