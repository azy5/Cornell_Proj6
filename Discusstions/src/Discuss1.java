
public class Discuss1 {
	/** This method checks a string to see if it has a vowel in it*/
public static boolean containsVowel(String s) {
	int a = s.indexOf('a');
	int e = s.indexOf('e');
	int i = s.indexOf('i');
	int o = s.indexOf('o');
	int u = s.indexOf('u');
	if ( a>=0) {
		return true;
	}
	else if ( e>=0) {
		return true;
	}
	else if ( i>=0) {
		return true;
	}
	else if ( o>=0) {
		return true;
	}
	else if ( u>=0) {
		return true;
	}
	
	return false;
	
}
/** This method takes 3 ints that make a date and turnit in to a string*/
public static String datetoString(int x,int y, int z) {
	if (y ==1) {

		return ""+Integer.toString(x)+" January "+Integer.toString(z); 
	}
	if (y ==2) {

		return ""+Integer.toString(x)+" February "+Integer.toString(z); 
	}
	if (y ==3) {

		return ""+Integer.toString(x)+" March "+Integer.toString(z); 
	}
	if (y ==4) {

		return ""+Integer.toString(x)+" April "+Integer.toString(z); 
	}
	if (y ==5) {

		return ""+Integer.toString(x)+" May "+Integer.toString(z); 
	}
	if (y ==6) {

		return ""+Integer.toString(x)+" June "+Integer.toString(z); 
	}
	if (y ==7) {

		return ""+Integer.toString(x)+" July "+Integer.toString(z); 
	}
	if (y ==8) {

		return ""+Integer.toString(x)+" August "+Integer.toString(z); 
	}
	if (y ==9) {

		return ""+Integer.toString(x)+" September "+Integer.toString(z); 
	}
	if (y ==10) {

		return ""+Integer.toString(x)+" October "+Integer.toString(z); 
	}
	if (y ==11) {

		return ""+Integer.toString(x)+" November "+Integer.toString(z); 
	}
	if (y ==12) {

		return ""+Integer.toString(x)+" December "+Integer.toString(z); 
	}
		return " the";
}
/** This Method counts the nuber of Es in a string*/
public static int countEs(String s) {
	int x= 0;
	for( int i =0; i< s.length(); i++) {
		char a = s.charAt(i);
		if (a=='e' ) {
			x++;
		}
	}
	return x;
}
public static void main(String[] args) {
	String s= "Hello World";
	boolean check = containsVowel(s);
	System.out.println(check);
	System.out.println( datetoString(15,5, 2019));
	System.out.println(countEs("eeeee"));
}
}
