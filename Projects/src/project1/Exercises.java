package project1;

import java.util.Arrays;

public class Exercises {

	public static boolean isPalindrome(String s) {
		for(int i=0; i<s.length(); i++) {
			if (s.charAt(i)!=s.charAt(s.length()-1-i)) {
				return false;
			}
		}
		return true;
	}
	public static String normalize(String s) {
		for(int i=0; i< s.length(); i++) {
			if(s.charAt(i)==' ' || s.charAt(i)=='.') {
				String a= s.substring(0, i-1);
				String b=s.substring(i+1);
				s=a+""+b;
			}
		}
		return s;
	}
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
	public static int numZeros(int[ ] values) {
		int x = 0;
		for (int i= 0; i<values.length; i++) {
			if (values[i]==0) {
				x= x+1;
			}
		}
		return x;
	}
	public static double mean(int[ ] values) {
		double x=0;
		for (int i=0; i<values.length; i++) {
			x= x+ values[i];
		}
		x= x/values.length;
		return x;
	}
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
