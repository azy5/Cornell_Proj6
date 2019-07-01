package project1;

import java.util.Arrays;

public class Exercises {
	
	/**
	 * Return true if s is the same backwards and forwards.
	 * @param s inputed String
	 * @return whether the string is the same backwards and forwards.
	 */
	public static boolean isPalindrome(String s) {
		for(int i=0; i<s.length(); i++) {
			if (s.charAt(i)!=s.charAt(s.length()-1-i)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Return a copy of s with all whitespace and punctuation removed.
	 * @param s inputed string
	 * @return a string with just characters and without whitespace and punctuations
	 */
	public static String normalize(String s) {
		
		s = s.replaceAll("\\s", "");
		s = s.replaceAll("\\W", "");
		
		return s;
	}
	
	/**
	 * find the integer median of a integer array
	 * @param values the inputed array
	 * @return the integer median of this inputed array
	 */
	public static int median(int[ ] values) {
		Arrays.sort(values);
	
		int result = 0;
		
		
		if(values.length%2 == 1) {
			
			result = values[(values.length-1)/2];
			
		} else if(values.length%2 == 0) {
			result = (values[values.length/2] + values[(values.length/2)-1])/2;
		}
		
		
		return result;
	}
	
	/**
	 * counting the zeros in an array
	 * @param values the inputed integer array
	 * @return the number of zeros it has in that array
	 */
	public static int numZeros(int[ ] values) {
		int x = 0;
		for (int i= 0; i<values.length; i++) {
			if (values[i]==0) {
				x= x+1;
			}
		}
		return x;
	}
	
	/**
	 * Find the mean in an integer array
	 * @param values the inputed integer array
	 * @return the the value of the mean of the inputed integer array
	 */
	public static double mean(int[ ] values) {
		double x=0;
		for (int i=0; i<values.length; i++) {
			x= x+ values[i];
		}
		x= x/values.length;
		return x;
	}
	
	/**
	 * Find the whether the diagonal value of a 2D array is the same
	 * @param values the inputed integer 2D array
	 * @return true if all of the values on the diagonal are the same. 
	 * Requires values to be a square matrix (that is, for all i, 
	 * values[i].length== values.length).
	 */
	public static boolean hasConstDiagonal(int[ ][ ] values) {
		int j =0;
		int x =0;
		for(int i = 0; i<values.length; i++) {
			x= x+ values[i][j];
		}
		if (x==values[0][0]*values.length) {
			return true;
		}
		return false;
	}
	
	

}
