package listClasses;

import java.util.*;

//Esther Malone
public class BasicLinkedList<T> extends java.lang.Object implements Iterable<T>
{
	Link head; //first node
	Link tail; //last node
	int size = 0; //keep track of size

	public class Link
	{
		T data;
		Link next;

		public Link(T data)
		{
			this.data = data;
			next = null;
		}
	}

	// create an empty list
	public BasicLinkedList()
	{
		head = null; // first element of list
		tail = null;// last element of list
	}

	// return the size of the list
	public int getSize()
	{
		return size;
	}

	// adds an element to the end of the list
	public BasicLinkedList<T> addToEnd(T data)
	{
		Link n = new Link(data); // create a new link

		if (head == null)// if the list is empty, set the new link to the head
							// and the tail
		{
			head = n;
			tail = n;
			size++;
			return this;
		}

		Link curr = head;// current link to carry down to the end

		while (curr.next != null)// if the next link is not null, continue to
									// the next link
		{
			curr = curr.next;
		}
		// once the next link is null, set that link to n
		curr.next = n;
		tail = n;// since its the last link set it to tail
		size++;// increment size

		return this;

	}

	// adds an element to the from of the list
	public BasicLinkedList<T> addToFront(T data)
	{
		Link n = new Link(data); // create link to add to front

		if (head == null) // if the list empty
		{
			head = n; // new link becomes both head and tail
			tail = n;
			size++;
			return this;
		}
		else
		{ // add to head, reinitialize head
			n.next = head;
			head = n;
		}
		size++; // increment the size of the link
		return this;
	}

	// return first elements data
	public T getFirst()
	{
		return head.data;// return first elements data

	}

	// return the last elements data
	public T getLast()
	{
		return tail.data; // return the last elements data

	}

	// remove the first element and returns its data
	public T retrieveFirstElement()
	{

		Link n = new Link(head.data);
		
		head = head.next;
		T temp = n.data;// return the old heads data
		
		size--; //decrement size
		
		return temp;
	}

	// remove the last element and return its data
	public T retrieveLastElement()
	{

		Link n = new Link(tail.data); //new link holding tails data
		Link curr = head;
		Link prev = head;
		
		while (curr.next != null)
		{
			prev = curr;
			curr = curr.next;
		}
		
		prev.next = curr.next; //remove the last element
		curr = prev;
		tail = prev; //reinitialize tail
		
		T temp = n.data; // retrieve the data of the old tail to return
		
		size--;//decrement size
		
		return temp;
	}

	// remove all instances of the targetData
	public BasicLinkedList<T> remove(T targetData, java.util.Comparator<T> comparator)
	{
		if (targetData == null)
		{
			return this;
		}

		Link curr = head; // current node to travel down
		Link prev = head; // node that follows behind curr

		while (curr != null)
		{ // traverse through to the end of the list
			if (comparator.compare(curr.data, targetData) == 0) //check to see if target and current node contain the same element
			{
				size--; //decrement size
				
				if (curr == head) //if it is at head, move head up one
				{
					head = head.next;
				}

				if (curr == tail) 
				{
					tail = prev;
				}
				prev.next = curr.next; //move it between the current node and the previous node
				curr = prev;
			}

			prev = curr;
			curr = curr.next;
		}

		return this;
	}

	// print the list
	public String toString()
	{
		String s = "";
		Link curr = head;

		while (curr != null)
		{
			s += curr.data + "->";
			curr = curr.next;
		}

		return s;
	}

	//iterate through basiclinkedList<T>
	public java.util.Iterator<T> iterator()
	{
		return new BasicLinkedListIterator();
	}

	public class BasicLinkedListIterator implements Iterator<T>
	{
		private Link curr;

		public BasicLinkedListIterator()
		{
			curr = head;
		}

		public boolean hasNext()
		{
			if (curr != null)
			{
				return true;
			}
			else
			{
				return false;
			}

		}

		public T next()
		{
			T next = curr.data;
			curr = curr.next;
			return next;
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}

		public void add()
		{
			throw new UnsupportedOperationException();
		}

	}

}
