package tree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 * 
 */
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V>
{

	/* Provide whatever instance variables you need */

	/**
	 * Only constructor we need.
	 * 
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */

	private K key;
	private V value;
	private Tree<K, V> left, right;

	public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right)
	{
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	// Searches for value associated with the key
	public V search(K key)
	{

		if (key.compareTo(this.key) == 0)// if they are equal return the value
		{
			return value;
		}
		else if (key.compareTo(this.key) > 0) // if the key is greater than
												// current, move right
		{
			return right.search(key);
		}
		else // else move left
		{
			return left.search(key);
		}
	}

	public NonEmptyTree<K, V> insert(K key, V value)
	{
		if (key.compareTo(this.key) == 0) // if they are equal, change the value
		{
			this.value = value;
			return this;
		}

		if (key.compareTo(this.key) > 0) // add/move to right side
		{
			right = right.insert(key, value);
		}
		else if (key.compareTo(this.key) < 0) // add to left side
		{
			left = left.insert(key, value);
		}
		return this;
	}

	public Tree<K, V> delete(K key)
	{
		if (key.compareTo(this.key) == 0) // if the keys are the same
		{
			K Key;
			try
			{
				Key = right.min(); // replace with the min of the max
			}
			catch (TreeIsEmptyException e) // if the right has no min, then
											// check the left
			{
				return left.delete(key);
			}
			V Val = right.search(Key);
			this.key = Key;
			this.value = Val;
			right = right.delete(Key);
			return this;

		}
		else if (key.compareTo(this.key) > 0) // if the key is greater, move
												// down to right
		{
			right = right.delete(key);
		}
		else// move down to left
		{
			left = left.delete(key);
		}
		return this;

	}

	//return max Key
	public K max()
	{
		try //check the right
		{
			return right.max();
		}
		catch (TreeIsEmptyException e) //if the right is empty
		{
			return this.key; //you are the max
		}

	}

	public K min()
	{
		try //check the left
		{
			return left.min();//if the left is empty
		}
		catch (TreeIsEmptyException e) //you are the min
		{
			return this.key;
		}
	}

	//return size of tree
	public int size()
	{
		return 1 + (right.size() + left.size());
	}

	//add all keys to colelction
	public void addKeysToCollection(Collection<K> c)
	{
		left.addKeysToCollection(c);
		c.add(key);
		right.addKeysToCollection(c);
	}

	public Tree<K, V> subTree(K fromKey, K toKey)
	{
		if (this.key.compareTo(fromKey) < 0) // if this key is less than from
												// key, go to right
		{
			return this.right.subTree(fromKey, toKey);
		}
		else if (toKey.compareTo(this.key) < 0) // go to left
		{
			return this.left.subTree(fromKey, toKey);
		}
		else //create the new subTree
			return new NonEmptyTree<K, V>(this.key, this.value, this.left.subTree(fromKey, toKey),
					this.right.subTree(fromKey, toKey));
	}

	//return the height of the tree
	public int height()
	{
		return 1 + Math.max(left.height(), right.height());
	}

	//inorder traversal of a task: left, root, right
	public void inorderTraversal(TraversalTask<K, V> p)
	{
		left.inorderTraversal(p); 
		p.performTask(key, value);
		right.inorderTraversal(p);
	}

	//right, root, left, traversal of a task
	public void rightRootLeftTraversal(TraversalTask<K, V> p)
	{
		right.rightRootLeftTraversal(p);
		p.performTask(key, value);
		left.rightRootLeftTraversal(p);
	}
}