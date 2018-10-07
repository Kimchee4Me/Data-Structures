package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import listClasses.BasicLinkedList;
import listClasses.SortedLinkedList;

public class StudentTests {

	@Test
	public void test() {
		BasicLinkedList<Integer> ints = new BasicLinkedList<Integer>();
		ints.addToEnd(5);
		ints.addToEnd(6);
		ints.addToEnd(2);
		ints.addToEnd(6);
		ints.addToEnd(7);
		ints.retrieveFirstElement();
		assertTrue(ints.getSize()==4);
		assertTrue(ints.getFirst()==6);
		
	}
	@Test
	public void test2() {
		BasicLinkedList<Integer> ints = new BasicLinkedList<Integer>();
		ints.addToEnd(5);
		ints.addToEnd(6);
		ints.addToEnd(2);
		ints.addToEnd(6);
		ints.addToEnd(7);
		ints.retrieveLastElement();
		assertTrue(ints.getLast()==6);
		assertTrue(ints.getSize()==4);
		
	}
	@Test
	public void test3() 
	{
		BasicLinkedList<String> basicList = new BasicLinkedList<String>();
		basicList.addToEnd("Red").addToFront("Yellow").addToFront("Blue").addToEnd("Red").addToEnd("Red").addToEnd("Green").addToEnd("Red");
		basicList.remove("Red", String.CASE_INSENSITIVE_ORDER);
		assertTrue(basicList.getFirst()=="Blue");
		assertTrue(basicList.getLast()=="Green");
		basicList.retrieveFirstElement();
		assertTrue(basicList.getFirst()=="Yellow");
		basicList.retrieveLastElement();
		assertTrue(basicList.getLast()=="Yellow");
	}
	@Test
	public void test4() 
	{
		BasicLinkedList<String> basicList = new BasicLinkedList<String>();
		basicList.addToEnd("Red").addToFront("Yellow").addToFront("Blue").addToEnd("Red").addToEnd("Red").addToEnd("Green").addToEnd("Red");
		basicList.remove("Red", String.CASE_INSENSITIVE_ORDER);
		assertTrue(basicList.getLast()=="Green");
		assertTrue(basicList.getFirst()=="Blue");
		
	}
	@Test
	public void test5() 
	{
		SortedLinkedList<String> sortedList = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		SortedLinkedList<String> sortedList2 =new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		sortedList.add("Yellow").add("Red").add("Zang").add("Blue").add("Red").add("Blue");
		sortedList2.add("Blue").add("Blue");
		assertTrue(sortedList.getFirst()=="Blue");
		assertTrue(sortedList.getLast()=="Zang");
		sortedList.remove("Blue");
		assertTrue(sortedList.getSize()==4);
		assertTrue(sortedList.getFirst()=="Red");
		assertTrue(sortedList2.getSize()==2);
		sortedList2.add("Apple");
		assertTrue(sortedList2.getFirst().equals("Apple"));
		sortedList2.retrieveFirstElement();
		sortedList2.retrieveFirstElement();
		sortedList2.remove("Blue");
		assertTrue(sortedList2.getSize()==0);
	}


}
