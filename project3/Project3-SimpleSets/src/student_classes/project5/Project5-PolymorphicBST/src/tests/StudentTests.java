package tests;



import java.util.ArrayList;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import junit.framework.TestCase;
import tree.PlaceKeysValuesInArrayLists;
import tree.PolymorphicBST;

public class StudentTests extends TestCase {
	
	@Test
	public void testRemove() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		assertEquals(0, ptree.size());
		
		ptree.put(2, "Two");
		ptree.put(1, "One");
		ptree.put(3, "Three");
		ptree.put(1, "OneSecondTime");
		ptree.put(5, "five");
		ptree.put(6, "six");
		
		ptree.remove(2);
		
		ptree.put(4, "four");
		ptree.put(0,"zero");
		
		assertEquals(6, ptree.size());
		assertEquals("five", ptree.get(5));
		assertEquals("Three", ptree.get(3));
	
		ptree.remove(1);
		assertEquals(5, ptree.size());
	
		PolymorphicBST<Integer,String> ptree2 = new PolymorphicBST<Integer,String>();
		ptree2.put(10, "ten");
		ptree2.remove(10);
		assertEquals(0, ptree2.size());
		
		

		PolymorphicBST<Integer,String> ptree3 = new PolymorphicBST<Integer,String>();
		
		ptree3.put(2, "Two");
		ptree3.put(1, "One");
		ptree3.put(3, "Three");
		ptree3.remove(2);
		ptree3.remove(1);
		assertEquals(ptree3.size(),1);
		ptree3.remove(3);
		
		assertEquals(ptree3.size(), 0);
		ptree3.remove(8);
		assertEquals(ptree3.size(),0);
		
		
	}
	
	@Test
	public void TestMin()
	{
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(2, "Two");
		ptree.put(1, "One");
		ptree.put(3, "Three");
		ptree.put(1, "OneSecondTime");
		ptree.put(5, "five");
		ptree.put(6, "six");
		
		int min = ptree.getMin();
		assertEquals(min, 1);
		
		ptree.remove(1);
		
		min = ptree.getMin();
		assertEquals(min, 1);
		
		ptree.remove(1);
		assertEquals(min, 2);
	}
	
	@Test
	public void testMax()
	{
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(2, "Two");
		ptree.put(1, "One");
		ptree.put(3, "Three");
		ptree.put(1, "OneSecondTime");
		ptree.put(5, "five");
		ptree.put(6, "six");
		ptree.put(4, "four");
		
		int max = ptree.getMax();
		assertEquals(max, 6);
		
		ptree.remove(6);
		
		max = ptree.getMax();
		assertEquals(max, 5);
	}
	
	@Test
	public void testSubMap()
	{
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(2, "Two");
		ptree.put(1, "One");
		ptree.put(3, "Three");
		ptree.put(1, "OneSecondTime");
		ptree.put(5, "five");
		ptree.put(7, "seven");
		ptree.put(4, "four");
		
		PolymorphicBST<Integer,String> subTree =ptree.subMap(3, 5);
		assertEquals(subTree.size(), 3);
		
		ptree.put(4, "four2");
		PolymorphicBST<Integer,String> subTree2 =ptree.subMap(3, 6);
		assertEquals(subTree2.size(), 3);
	
	
	}
	
	@Test
	public void testTraversal()
	{
		
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		ptree.put(2, "Two");
		ptree.put(1, "One");
		ptree.put(3, "Three");
		ptree.put(5, "five");
		ptree.put(6, "six");
		ptree.put(4, "four");
		
		PlaceKeysValuesInArrayLists<Integer, String> p = new PlaceKeysValuesInArrayLists<Integer, String>();
		
		ptree.inorderTraversal(p);
		ArrayList<Integer> keys = p.getKeys();
		
		for(int i = 1; i<ptree.size();i++)
		{
			int check = keys.get(i)-1;
			assertEquals(check, i);
		}
		
		PlaceKeysValuesInArrayLists<Integer, String> p2 = new PlaceKeysValuesInArrayLists<Integer, String>();
		
		ptree.rightRootLeftTraversal(p2);
		ArrayList<Integer> keys2 = p2.getKeys();
		
		for(int i =ptree.size(); i==0;i--)
		{
			int check = keys2.get(i)-1;
			assertEquals(check, i);
		}
	}
	@Test
	public void testSet()
	{

		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		ptree.put(2, "Two");
		ptree.put(1, "One");
		ptree.put(3, "Three");
		ptree.put(4, "four");
		ptree.put(2, "Two2");
		
		Set<Integer> test =ptree.keySet();
		Set<Integer> test2 = new HashSet<Integer>();
		
		test2.add(1);
		test2.add(2);
		test2.add(3);
		test2.add(4);
		
		assertEquals(test, test2);
	}
}