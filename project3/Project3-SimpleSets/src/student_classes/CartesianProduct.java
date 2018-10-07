package student_classes;

import java.util.Iterator;


/**
 * <h1>Cartesian Products</h1>
 * <p>
 * A <b>Cartesian Product</b> is a set of "ordered pairs" that may be visualized
 * as:
 * 
 * <pre>
 * { ( key1, value1 ), ( key2, value2 ), ... }
 * </pre>
 * 
 * In Java, by the way, these "pairs" are referred to as "Maps"--which we will
 * encounter throughout the java.util.Collections classes and methods.
 * </p>
 * <p>
 * This implementation is pretty basic: the <code>CartesianProduct></code> class
 * <code>implements</code> the <code>Iterable</code> interface with the type
 * restriction of <code>OrderedPair</code>, for types, "K" (for Key) and "V"
 * (for value). This explains the signature for the class that appears below.
 * <p>
 * <h2>Implementation Notes</h2> You will likely need to provide two "inner
 * classes"
 * <ul>
 * <li>One that defines an object that implements the <code>OrderedPair</code>
 * interface over two generic types.</li>
 * <li>One that provides the <code>Iterator</code> interface for the
 * <code>CartesianProduct</code> class. Note, this interface must provide three
 * methods: <code>hasNext()</code>, <code>next()</code>, and
 * <code>remove()</code>.</li>
 * </ul>
 * 
 * @author cmsc132
 *
 * @param <K>
 * @param <V>
 */
public class CartesianProduct<K, V> implements Iterable<OrderedPair<K, V>>
{
	/*
	 * Choose a Java Collections class object here.
	 */
	protected BasicSet<OrderedPairs> product = null;

	// ---end Instance Variable(s)
	/*
	 * Create an inner-class to implement the OrderedPair< K, V > interface. You
	 * should implement, in addition to the required methods, a toString and an
	 * equals method within this class.
	 */
	public class OrderedPairs<K, V> implements OrderedPair<K, V>
	{
		private K key;
		private V value;

		protected OrderedPairs()
		{
			key = null;
			value = null;
		}

		protected OrderedPairs(K k, V v)
		{
			key = k;
			value = v;
		}

		public K getKey()
		{
			return key;
		}

		public V getValue()
		{
			return (V) value;
		}

		public void setKey(K newKey)
		{
			key = newKey;
		}

		public void setValue(V newValue)
		{
			value = newValue;
		}

	}

	/**
	 * Basic Constructor that requires two BasicSets and creates the ordinary
	 * Cartesian Product by creating a Pairing of each corresponding element in
	 * the first set with each element in the paired set.
	 * 
	 * @param keys
	 * @param values
	 */

	protected CartesianProduct(BasicSet<K> keys, BasicSet<V> values)
	{
		BasicSet<V> tempValues = values;
		BasicSet<K> tempKeys = keys;
		
		product = BasicSet.createSet();
		for (int i = 0; i < keys.cardinality() * values.cardinality(); i++)
		{
			if (tempValues.isEmpty()) //once you go through all the values, reset values and move to the next key
			{
				tempValues = values;
				tempKeys = tempKeys.remainingElements();
			}
			
			//adjoin new pairs into the ordered pair basic set
			product = BasicSet.adjoin(new OrderedPairs(tempKeys.firstElement(), tempValues.firstElement()), product);

			tempValues = tempValues.remainingElements();
		}
	}

	/**
	 * Copy Constructor: returns a new Cartesian Product whose pairs are shallow
	 * copies of the <code>product</code>'s pairs.
	 * 
	 * @param product
	 */
	protected CartesianProduct(CartesianProduct<K, V> product)
	{
		this.product = product.product; //create shallow copy
	}

	/**
	 * Default Ctor.
	 */
	protected CartesianProduct()
	{
		product = BasicSet.createSet();
	}

	/**
	 * Public convenience method: creates a new Empty Cartesian Product object
	 * with the types for Key and Value.
	 * 
	 * @return
	 */
	public static <K, V> CartesianProduct<K, V> createProduct()
	{
		CartesianProduct<K, V> create = new CartesianProduct<K, V>();
		return create;
	}

	/**
	 * Public convenience method: creates a new Cartesian Product by
	 * constructing the product of the set of <code>keys</code> and the set of
	 * <code>values</code>.
	 * 
	 * @param keys
	 * @param values
	 * @return
	 */
	public static <K, V> CartesianProduct<K, V> createProduct(BasicSet<K> keys, BasicSet<V> values)
	{
		CartesianProduct<K, V> create = new CartesianProduct<K, V>(keys, values);
		return create;
	}

	/**
	 * Public (static) analogue for the Copy constructor for Cartesian Products.
	 * In other words: call this to get a new Cartesian Product that is the
	 * shallow copy of <code>otherProduct</code>
	 * 
	 * @param otherProduct
	 * @return
	 */
	public static <K, V> CartesianProduct<K, V> createProduct(CartesianProduct<K, V> otherProduct)
	{
		CartesianProduct<K, V> create = new CartesianProduct<K, V>(otherProduct);
		return create;

	}

	/*
	 * Object methods (overrides)
	 */
	/**
	 * (Not tested, but highly recommended). Pretty prints Cartesian Products as
	 * { < key1, value1 > .... }.
	 */
	public String toString()
	{
		String str = "{ ";
		for (OrderedPairs element : this.product)
		{
			str += element.getKey() + " " + element.getValue()+", ";
		}
		return str + " }";
	}

	/*
	 * Iterator implementation. Note that the remove() method is Optional here.
	 * It's not likely to be used, however.
	 */
	public class CartesianProductIterator implements Iterator<OrderedPair<K, V>>
	{
		private int index = 0;
		BasicSet<OrderedPairs> temp = product;
		
		public boolean hasNext()
		{
			return index < product.cardinality();
		}

		
		public OrderedPair<K, V> next()
		{
			OrderedPairs<K, V> ele = temp.firstElement();
			temp = temp.remainingElements();
			index++;
			return ele;
		}

	}

	@Override
	public Iterator<OrderedPair<K, V>> iterator()
	{
		return new CartesianProductIterator();
	}

	/*
	 * Public Interface
	 */
	/**
	 * Returns a non-negative integer indicating the number of pairings in this
	 * Cartesian Product.
	 * 
	 * @return
	 */
	public int size()
	{
		return product.cardinality();
	}

	/**
	 * Returns a Basic Set of the keys contained within this Cartesian Product.
	 * 
	 * @return
	 */
	public BasicSet<K> getKeys()
	{
		BasicSet<OrderedPairs> temp = new BasicSet(product);
		BasicSet<OrderedPairs> pairs = BasicSet.createSet();
		BasicSet<K> keys = BasicSet.createSet();
		while (!temp.isEmpty())
		{
			pairs = BasicSet.adjoin(temp.firstElement(), pairs);
			temp = temp.remainingElements();
		}
		while (!pairs.isEmpty())
		{
			keys = BasicSet.adjoin((K)pairs.firstElement().getKey(), keys);
			pairs = pairs.remainingElements();
		}
		return keys;
	}

	/**
	 * Returns a BasicSet of the values contained with this Cartesian Product.
	 * 
	 * @return
	 */
	public BasicSet<V> getValues()
	{
		BasicSet<OrderedPairs> temp = new BasicSet(product);
		BasicSet<OrderedPairs> pairs = BasicSet.createSet();
		BasicSet<V> values = BasicSet.createSet();
		while (!temp.isEmpty())
		{
			pairs = BasicSet.adjoin(temp.firstElement(), pairs);
			temp = temp.remainingElements();
		}
		while (!pairs.isEmpty())
		{
			values = BasicSet.adjoin((V)pairs.firstElement().getValue(), values);
			pairs = pairs.remainingElements();
		}
		return values;
	}

	/**
	 * Returns a BasicSet of the values associated with <code>forKey</code>.
	 * 
	 * @param forKey
	 * @return
	 */
	public BasicSet<V> getValues(K forKey)
	{
		return getValues();
	}

	/**
	 * Returns a BasicSet of the keys associated with <code>withValue</code>.
	 * 
	 * @param withValue
	 * @return
	 */
	public BasicSet<K> getKeys(V withValue)
	{
		return getKeys();
	}

}