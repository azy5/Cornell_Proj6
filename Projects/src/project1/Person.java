package project1;

public class Person {
	private String name;
	private int birthyear;
	private int birthmonth;
	private int birthday;
	private int children;
	private Person mother;
	private Person father;
	/**Constructor: Initialize a person with the given name and birthdate,
	and unknown parents. The n must be non-null and non-empty.
	The bm must be in the range 1..12 and the bd must
	be in the range 1..31.*/
	public Person(String n, int by, int bm, int bd) {
		name =n;
		birthyear = by;
		birthmonth = bm;
		birthday=bd;
		children=0;
	}
	/** return the name of the person*/
public String name() {
	return name;
}
/**returns the birth year of the person*/
public int brithyear() {
	return birthyear;
}
/**returns the birth month of the person*/
public int brithmonth() {
	return birthmonth;
}
/**returns the birth day of the person*/
public int brithday() {
	return birthday;
}
/**returns the mother of the person or null if unknown*/
public Person mother() {
	return mother;
}
/**returns the father of the person or null if unknown*/
public Person father() {
	return father;
}
/**Return the number of children a person has*/
public int numChildren() {
	return children;
}
/**Change this person’s name to n, which must be non-null and nonempty.*/
public void setname(String n) {
	name= n;
}
/** change this person's birth
 *  year to by.*/
public void setbrithyear(int by) {
	birthyear= by;
}
/**Change this person's birth month to bm. bm must be in the range 1..12.*/
public void setbrithmonth(int bm) {
	birthmonth= bm;
}
/**Change this person's birth day to bd. bd must be in the range 1..31*/
public void setbrithday(int bd) {
	birthday = bd;
}
/**Change this person's mother to m. If m is null then the mother becomes unknown*/
public void setmother(Person m) {
	if (this.mother() != null) {
		Person a= this.mother();
		
		a.setnumChildren(a.numChildren()-1);
	}
	mother= m;
	m.setnumChildren(m.numChildren()+1);
}
/**Change this person's father to f. If f is null then the father becomes unknown*/
public void setfather(Person f) {
	if (this.father() != null) {
		Person a= this.father();
		a.setnumChildren(a.numChildren()-1);
	}
	father= f;
	f.setnumChildren(f.numChildren()+1);
}
/** Change the number of children to nc*/
public void setnumChildren(int nc) {
	children= nc;
}
/**Return true if this and other share a known parent but not the other. Requires that
other is non-null*/
public boolean isHalfSibling(Person other) {
	if (this.mother()== other.mother() && this.father()== other.father()) {
		return false;
	}
	else if (this.mother()!= other.mother() && this.father() != other.father()) {
		return false;
	}
	return true;
}
/** Returns true if this and other share the same parents. Requires that other is non-null*/
public boolean isSibling(Person other) {
	if(this.mother()== other.mother() && this.father() ==other.father()) {
		return true;
	}
	return false;
}
/**Return true if this person’s birth date is before other’s. Requires
that other is non-null.*/
public boolean isOlderThan(Person other) {
	if(this.brithyear()> other.brithyear()) {
		return false;
	}
	else if (this.brithyear()==other.brithyear()) {
		if(this.brithmonth() > other.brithmonth()) {
			return false;
		}
	}
	else if(this.brithmonth()== other.brithmonth()) {
		if(this.brithday() > other.brithday()) {
			return false;
		}
	}
	return true;
}
}
