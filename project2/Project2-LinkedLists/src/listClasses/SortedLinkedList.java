package listClasses;

import listClasses.BasicLinkedList;

//author: Esther Malone
public class SortedLinkedList<T> extends BasicLinkedList<T>
{
	// Link head;
	// Link tail;
	private java.util.Comparator<T> c;

	/*
	 * public class Link { T data; Link next;
	 * 
	 * public Link(T data) { this.data = data; next = null; } }
	 */
	public SortedLinkedList(java.util.Comparator<T> comparator)
	{
		super();
		c = comparator;
	}

	// adds an element into the sorted list into the appropriate spot based on
	// ordering of least to greatest
	public SortedLinkedList<T> add(T element)
	{
		Link node = new Link(element); // new node with element
		size++;
		if (head == null)// if the list is empty
		{
			head = node; // set both head and tail to the new node
			tail = node;

			return this;
		}

		else if (c.compare(element, head.data) < 0)// if link becomes head
		{
			node.next = head;
			head = node; // reinitialize head
		}

		else if (c.compare(element, tail.data) > 0) // if link becomes tail
		{
			tail.next = node; // reinitialize tail
			tail = node;
		}
		else
		{
			Link curr = head;
			Link prev = head;

			while (curr != null)
			{
				if (c.compare(element, curr.data) < 0 || c.compare(element, curr.data) == 0)
				{
					// if the elment is less than the current node
					tail = curr; // set new node before the current node
					node.next = prev.next;
					prev.next = node;

					Link current = head;

					while (current.next != null) // reinitialize tail...probably
													// a better way to do this
					{
						current = current.next;
					}
					tail = current;
					break;
				}

				prev = curr;
				curr = curr.next;
			}
		}

		return this;
	}

	// uses BasicLinkedList remove method, call super
	public BasicLinkedList<T> remove(T targetData)
	{
		return super.remove(targetData, c);
	}

	@Override // SortedLinkedList may not use this method
	public BasicLinkedList<T> addToEnd(T data)
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}

	@Override // SortedLinkList may not use this method
	public BasicLinkedList<T> addToFront(T data)
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}

}