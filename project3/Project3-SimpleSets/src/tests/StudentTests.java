package tests;

import static org.junit.Assert.*;

/* remove the "//" comments as you define each of the 
 * classes and the methods referenced by each of these
 * static imports ...
 */

//import static student_classes.BasicSet.createSet;
//import static student_classes.BasicSet.adjoin;
//import static student_classes.BasicSet.cardinality;

//import static student_classes.OrderedSet.*;

//import static student_classes.CartesianProduct.createProduct;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import student_classes.OrderedSet;
import student_classes.OrderedSet.*;
import student_classes.BasicSet;
import static student_classes.BasicSet.*;
import student_classes.CartesianProduct;
import student_classes.OrderedPair;


public class StudentTests {

	@Test
	public void testRemaining() { 
		BasicSet<String> names = createSet();
		
		String[] strNames = { "Tom", "Susan", "Sue", "Bill" };
		
		for( String name : strNames ) {
			names = adjoin( name, names ); 
		}
		
		BasicSet<String> names2 = createSet();
	
		String[] strNames2 = { "Tom", "Sue", "Bill", "Will"};
		for( String name : strNames2 ) {
			names2 = adjoin( name, names2 ); 
		}
		
		BasicSet<String> result = createSet();
		String[] strResult = { "Sue", "Bill", "Will" };
		for( String name : strResult ) {
			result = adjoin( name, result ); 
		}
		
		BasicSet<String> remaining = remainingElements(names2);
		assertTrue(remaining.equals(result));
	}
	
	@Test
	public void testAdjoin()
	{
		BasicSet<String> names = createSet();
		
		String[] strNames = { "Tom", "Susan", "Sue", "Bill" };
		
		for( String name : strNames ) {
			names = adjoin( name, names ); 
		}
		
		BasicSet<String> result = createSet();
		
		String[] strResult = { "Tom", "Susan", "Sue" , "Bill", "Tim"};
		for( String name : strResult ) {
			result = adjoin( name, result ); 
		}
		
		BasicSet<String> adjoin = adjoin("Tim", names);
		assertTrue(adjoin.equals(result));
	}
	
	@Test
	public void testIntersect()
	{
		BasicSet<String> names = createSet();
		
		String[] strNames = { "Tom", "Susan", "Sue", "Bill" };
		
		for( String name : strNames ) {
			names = adjoin( name, names );
		}
		
		BasicSet<String> names2 = createSet();
	
		String[] strNames2 = { "Tom", "Sue", "Bill", "Will"};
		for( String name : strNames2 ) {
			names2 = adjoin( name, names2 ); 
		}
		
		BasicSet<String> result = createSet();
		String[] strResult = { "Tom", "Sue", "Bill" };
		for( String name : strResult ) {
			result = adjoin( name, result ); 
		}
		
		BasicSet<String> intersect= intersection(names, names2);
		assertTrue(intersect.equals(result));
	}
	
	@Test
	public void testDifference()
	{
		BasicSet<String> names = createSet();
		
		String[] strNames = { "Tom", "Susan", "Sue", "Bill" };
		
		for( String name : strNames ) {
			names = adjoin( name, names ); 
		}
		
		BasicSet<String> names2 = createSet();
	
		String[] strNames2 = { "Tom", "Sue", "Bill", "Will"};
		for( String name : strNames2 ) {
			names2 = adjoin( name, names2 ); 
		}
		
		BasicSet<String> result = createSet();
		String[] strResult = { "Susan", "Will" };
		for( String name : strResult ) {
			result = adjoin( name, result ); 
		}
		
		
		BasicSet<String> difference = setDifference(names, names2);
		assertTrue(difference.equals(result));
	}
	
	@Test
	public void testUnion()
	{
		BasicSet<String> names = createSet();
		
		String[] strNames = { "Tom", "Susan", "Sue", "Bill" };
		
		for( String name : strNames ) {
			names = adjoin( name, names ); 
		}
		
		BasicSet<String> names2 = createSet();
	
		String[] strNames2 = { "Tom", "Sue", "Bill", "Will"};
		for( String name : strNames2 ) {
			names2 = adjoin( name, names2 ); 
		}
		
		BasicSet<String> result = createSet();
		String[] strResult = { "Tom", "Susan", "Sue", "Bill", "Will" };
		for( String name : strResult ) {
			result = adjoin( name, result ); 
		}
		
		BasicSet<String> union = union(names, names2);
		assertTrue(union.equals(result));
	}
	
	@Test
	public void OrderedSet()
	{
		OrderedSet<String> names = OrderedSet.createOrderedSet();
		
		String[] strNames = { "Tom", "Sue", "Bill" , "Max", "Susan"};
		
		for( String name : strNames ) {
			names = OrderedSet.adjoin( name, names ); 
		}
		System.out.println(names);
	}
	
	@Test
	public void testCartesianProduct()
	{
		BasicSet<Integer> number = createSet();
		int[] nums = { 1, 2, 3, 4, 5 ,6 };
		
		for( int ele : nums ) {
			number = adjoin( ele , number ); 
		}
		
		BasicSet<String> names = createSet();
		
		String[] strNames = { "Tom", "Susan", "Sue", "Bill" , "Will"};
		
		for( String name : strNames ) {
			names = adjoin( name, names ); 
		}
		
		CartesianProduct<Integer, String> test = CartesianProduct.createProduct(number, names);
		
		
		BasicSet<Integer> keys = test.getKeys();
	
		
	}
	
}
