/**
* Do NOT mess with these imports as you will likely need to
* import something from Java's collections package. For those brave
* souls wishing to use their own list implementations; you need to attach
* the JAR file containing your compiled objects and then import the package
* within which you defined your list class .... 
*/
package student_classes;

import java.util.*;

/**
 * 
 */
/**
 * Read the instructions that are provided with this project which can be found
 * on the Submit Server for specifics regarding these methods.
 * 
 * Note: the "original" version of this file contained NO definitions
 * whatsoever. Subsequent to discussions with the Teaching Staff, people felt
 * that many students were struggling with method signatures and that expecting
 * students to figure these out and manage the additional complexity of generics
 * would be stressful.
 * 
 * To that end, we have provided the signatures for the "statics" and the three
 * constructors---with the intent of allowing you to focus on the use of
 * generics, inheritance, and how static and dynamic methods differ in this
 * setting.
 * 
 * Finally: the required methods were chosen to facilitate a recursive
 * implementation of the standard three set operators .... Doing this
 * iteratively is possible, but less direct and obvious.
 * 
 * Please bear in mind that you may NOT use the Iterator defined on any of these
 * classes to implement any of the basic three set operators, i.e, union,
 * intersection, or setDifference.
 * 
 * @author CMSC132
 *
 */
public class BasicSet<T> implements Iterable<T>
{
	protected List<T> elements = null;

	/**
	 * At least two constructors are required for this class:
	 * <ol>
	 * <li><code>BasicSet()</code>, which is the default ctor for this
	 * class.</li>
	 * <li><code>BasicSet( BasicSet other )</code>, which is the copy
	 * constructor for this class.</li>
	 * </ol>
	 */
	protected BasicSet() // initialize the list as array
	{
		elements = new ArrayList<T>();
	}

	protected BasicSet(BasicSet<T> other) // copy constructor
	{
		this.elements = other.elements;
	}

	public static <T> BasicSet<T> createSet() // create a new set and return it
	{
		BasicSet<T> basic = new BasicSet<T>();
		return basic;
	}

	// creates a set through a collection
	public static <T> BasicSet<T> createSet(Collection<T> collection)
	{
		BasicSet<T> set = new BasicSet<T>(); // create a new set
		for (T element : collection) // fill the new set with collection items
		{
			if (!set.elements.contains(element)) // do not add duplicates
			{
				set.elements.add(element);
			}
		}
		return set; // return the new set
	}

	protected int cardinality() // return the size of the set
	{
		return elements.size();
	}

	public static <T> int cardinality(BasicSet<T> set)
	{
		return set.cardinality(); // calls the protected method to return size
	}

	// return boolean of whether set is empty
	protected boolean isEmpty()
	{
		return (this.cardinality() == 0); // returns true if size is empty
	}

	public static <T> boolean isEmpty(BasicSet<T> set)
	{
		return set.isEmpty(); // returns the protected empty method
	}

	// check to see if the set contains a key given
	protected boolean contains(T key)
	{
		boolean found = false;
		for (T element : this)
		{
			if (key == element)
			{
				found = true;
			}
		}
		return found;
	}

	public static <T> boolean contains(T element, BasicSet<T> set)
	{
		return set.contains(element); // returns the protected method contains
	}

	//returns the first element
	protected T firstElement()
	{
		if (this.isEmpty())//throw exception if empty
		{
			throw new IllegalStateException();
		}
		else
		{
			return elements.get(0); //first element of arrayList
		}
	}

	//call protected firstElement()
	public static <T> T firstElement(BasicSet<T> set)
	{

		return set.firstElement();
	}

	//returns every element except the first
	protected BasicSet<T> remainingElements()
	{
		if (this.isEmpty()) //if Empty throw exception
		{
			throw new IllegalStateException();
		}
		BasicSet<T> remaining = new BasicSet<T>(); //create new set to contain remaining elements
		for (T element : this)
		{
			if (element != this.firstElement()) //add all but first
			{
				remaining.elements.add(element);
			}
		}
		return remaining; //return remaining
	}

	//call protected remainingElements
	public static <T> BasicSet<T> remainingElements(BasicSet<T> set)
	{
		return set.remainingElements();
	}

	//check to see if one set is a subset of another
	public static <T> boolean isSubset(BasicSet<T> thisSet, BasicSet<T> otherSet)
	{
		if (thisSet.isEmpty())
		{
			return true;
		}
		if (otherSet.contains(thisSet.firstElement()))
		{
			return isSubset(thisSet.remainingElements(), otherSet);
		}
		else
		{
			return false;
		}
	}

	//adds an element to the Set if it is not in the Set
	protected BasicSet<T> adjoin(T key)
	{
		BasicSet<T> adjoin = new BasicSet<T>();
		for (T element : this)
		{
			adjoin.elements.add(element);
		}

		if (!adjoin.contains(key))
		{
			adjoin.elements.add(key);
		}
		return adjoin;
	}

	//calls the protected method for adjoin
	public static <T> BasicSet<T> adjoin(T element, BasicSet<T> set)
	{
		return set.adjoin(element);
	}

	/* Object method overrides */
	public String toString()
	{ // don't change this unless you have a good reason.
		String str = "{ ";
		for (T element : this.elements)
		{
			str += element + ", ";
		}
		return str + " }";
	}

	//two sets are equal if one is a subset of the other
	@SuppressWarnings("unchecked")
	public boolean equals(Object other)
	{
		return (isSubset(this, (BasicSet<T>) other));
	}
	
	
	//check if two sets are equal
	public static <T> boolean equals(BasicSet<T> set1, BasicSet<T> set2)
	{
		return set1.equals(set2);
	}

	//returns a new set containing the elements that appear in eiher set
	public static <T> BasicSet<T> union(BasicSet<T> thisSet, BasicSet<T> otherSet)
	{
		if (thisSet.isEmpty())
		{
			return otherSet;
		}
		else if(otherSet.isEmpty())
		{
			return thisSet;
		}
		else
			return adjoin(thisSet.firstElement(), union(thisSet.remainingElements(), otherSet));
	}

	//returns a new set containing only the elements that they have in common
	public static <T> BasicSet<T> intersection(BasicSet<T> thisSet, BasicSet<T> otherSet)
	{
		
		if (otherSet.isEmpty()) {
			return otherSet;
		}
		if (thisSet.contains(otherSet.firstElement())){
		    return adjoin(otherSet.firstElement(), intersection(thisSet, otherSet.remainingElements()));
		}
		else {
			return intersection(thisSet, otherSet.remainingElements());
		}
	}

	//returns a new set containing only elements that are in set1 that are not in set2
	public static <T> BasicSet<T> setDifference(BasicSet<T> thisSet, BasicSet<T> otherSet)
	{
		if (thisSet.isEmpty()) {
			return thisSet;
		}
		if (!thisSet.contains(otherSet.firstElement())) {
			return adjoin(otherSet.firstElement(), intersection(thisSet, otherSet.remainingElements()));
		}
		else {
			return setDifference(thisSet, otherSet.remainingElements());
		}
	}

	/**
	 * Returns an object that implements the <code>Iterator</code> interface for
	 * this class.
	 */
	//iterate over Set object
	@Override
	public Iterator<T> iterator()
	{
		return new SetIterator();
	}

	public class SetIterator implements Iterator<T>
	{
		private int index = 0;

		public boolean hasNext() {
			return index < elements.size();
		}

		public T next() {
			T ele = elements.get(index);
			index++;
			return ele;
		}

	}

}
