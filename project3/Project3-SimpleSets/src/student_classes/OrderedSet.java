package student_classes;

import java.util.List;

/**
 * See the notes that introduce the BasicSet here.
 * 
 * @author CMSC132
 *
 * @param <T>
 */
public class OrderedSet<T extends Comparable<T>> extends BasicSet<T>
{
	/*
	 * Properties go here: You will need to define a "protected" instance
	 * variable to contain the "elements" that comprise this Basic Set. I use
	 * LinkedList<T>, but you may use an ArrayList if that's more comfortable to
	 * you.
	 */
	/*
	 * Note: many methods are not included in this file because we do not know
	 * exactly how you wish to partition your code.
	 */
	/*
	 * At least two constructors are needed here: the default ctor and the
	 * copy-ctor.
	 */
	/**
	 * Creates an empty Ordered Set of type <code>T extends Comparable</code>.
	 */
	protected OrderedSet()
	{
		super(); //call BasicSets constructor
	}

	/**
	 * Copy-ctor: returns a new Ordered Set of type <code>T</code> whose
	 * elements are a shallow-copy of <code>other</code>.
	 * 
	 * @param other
	 */
	protected OrderedSet(OrderedSet<T> other)
	{
		this.elements = other.elements; //create a shallow copy
	}

	/**
	 * This is how the public creates an empty Ordered Set of type
	 * <code>T</code>.
	 * 
	 * @return
	 */
	public static <T extends Comparable<T>> OrderedSet<T> createOrderedSet()
	{
		OrderedSet<T> ordered = new OrderedSet<T>();//create a new set
		return ordered;
	}

	/*
	 * This method is inherited and must be overridden to insert the element (if
	 * not already present), in order!
	 */
	@Override
	protected OrderedSet<T> adjoin(T element)
	{
		OrderedSet<T> adjoin = new OrderedSet<T>();
		
		for (T ele : this)
		{
			adjoin.elements.add(ele);
		}
		
		if (!adjoin.contains(element))
		{
			adjoin.elements.add(element);
		}

		return adjoin;
	}

	/**
	 * Depending upon your implementation: this method delegates to the
	 * protected (dynamic) method described above.
	 * 
	 * @param element
	 * @param set
	 * @return
	 */
	public static <T extends Comparable<T>> OrderedSet<T> adjoin(T element, OrderedSet<T> set)
	{
		return set.adjoin(element);
	}

	/* Object overrides ... */
	@SuppressWarnings("unchecked")
	public boolean equals(Object other)
	{
		if (other == null)
		{
			return false;
		}
		OrderedSet<T> otherSet = (OrderedSet<T>) other;
		OrderedSet<T> thisSet = new OrderedSet<T>(this);

		if (this.cardinality() != otherSet.cardinality())
		{
			return false;
		}
		else
		{
			for (T order : thisSet)
			{
				if (thisSet.firstElement() != otherSet.firstElement())
				{
					return false;
				}
				thisSet = (OrderedSet<T>) thisSet.remainingElements();
			}
		}
		return true;
	}

}
