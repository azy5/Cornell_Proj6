package project1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExercisesTest {
	

	@Test
	void test() {
		Exercises e = new Exercises();
		String s= "hannah";
		String s1 = "To day was good.";
		int a [] = new int[5];
		int a2 [] [] = new int[5][5];
		for(int i =0; i<5; i++) {
			a[i]= i;
		}for(int j =0; j<5; j++) {		
			for(int i =0; i<5; i++) {
			a2[i][j]= 1;
		}
		}
		assertEquals(e.isPalindrome(s),true);
		assertEquals(e.normalize(s1),"Todaywasgood");
		assertEquals(e.numZeros(a),1);
		assertEquals(e.mean(a),2);
		assertEquals(e.median(a),2);
		assertEquals(e.hasConstDiagonal(a2),true);
		
	}

}
