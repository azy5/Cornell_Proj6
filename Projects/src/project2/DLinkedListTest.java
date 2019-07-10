package project2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import project2.DLinkedList.Tests;

class DLinkedListTest {

	@Test
	void test() {
		Tests t= new Tests();
		t.testToString();
		t.testAppend();
		 List<Integer> dll = new DLinkedList<Integer>();
		 dll.add(4);
		 dll.add(2);
		 dll.add(7);
		 assertEquals(dll.get(2),2);
		 dll.set(1, 3);
		 assertEquals(dll.get(1),3);
		//fail("Not yet impleme.nted");
	}

}
