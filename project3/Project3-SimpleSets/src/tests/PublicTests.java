package tests;

import static org.junit.Assert.*;
//import static student_classes.BasicSet.adjoin;
//import static student_classes.BasicSet.createSet;

import org.junit.Test;

import student_classes.BasicSet;

import java.util.HashMap;

import student_classes.BasicSet;

import static student_classes.BasicSet.*;

import java.util.Iterator;

// uncomment the following code when you've defined all of your
// classes and you wish to run the public tests ... these are
// included as "suggestions" as to how you might design your
// Student Tests  ...

public class PublicTests {

	@Test
	public void testCreateSet() {
		BasicSet<String> names = createSet();
		assertNotNull( names );
	}
	
	@Test
	public void testCardinality() {
		BasicSet<String> names = createSet();
		String[] strNames = { "Tom", "Sue", "Bill" };
		for( String name : strNames ) {
			names = adjoin( name, names ); //names.adjoin( name );
		}
		assert( cardinality(names) == 3 );
	}
	
	@Test
	public void testFirstElement() {
		BasicSet<String> names = createSet(); 
		String[] strNames = { "Tom", "Sue", "Bill" };
		for( String name : strNames ) {
			names = adjoin( name, names ); //names.adjoin( name );
		}
		System.out.println(names);
		assert( cardinality(names) == 3 );
		assert( firstElement(names).equals( "Tom"));
	}
	@Test
	public void testRemainingElements() {
		BasicSet<String> names = createSet(); 
		String[] strNames = { "Tom", "Sue", "Bill" };
		for( String name : strNames ) {
			names = adjoin( name, names ); 
		}
		assert( cardinality(names) == 3 );
		BasicSet<String> remainingElements = remainingElements(names);
		assert( cardinality(remainingElements) == 2 );
		
	}
	@Test
	public void testAdjoin() {
		BasicSet<String> names = createSet();
		String[] strNames = { "Tom", "Sue", "Bill" };
		for( String name : strNames ) {
			names = adjoin( name, names ); 
		}
		assert( cardinality(names) == 3 );
		for( String name : strNames ) {
			names = adjoin( name, names ); 
		}
		System.out.println(names);
		assert( cardinality(names) == 3 );
	}
	
	@Test
	public void testContains() {
		BasicSet<String> names = createSet();
		String[] strNames = { "Tom", "Sue", "Bill" };
		for( String name : strNames ) {
			names = adjoin( name, names ); 
		}
		assert( cardinality(names) == 3 );
		//System.out.println("Name is " + names );
		assertTrue( contains( "Tom", names ) );
		assertTrue( contains( "Bill", names ));
	}
	
	@Test
	public void testIsSubset() {
		BasicSet<String> names = createSet();
		String[] strNames = { "Tom", "Sue", "Bill" };
		for( String name : strNames ) {
			names = adjoin( name, names ); 
		}
		BasicSet<String> subNames = createSet();
		subNames = adjoin( "Sue", subNames ); 
		assertTrue( isSubset( subNames,names ));
		assertFalse( isSubset( names, subNames ));
	}
	
	@Test
	public void testEquals() {
		BasicSet<String> names = createSet();
		String[] strNames = { "Tom", "Sue", "Bill" };
		for( String name : strNames ) {
			names = adjoin( name, names ); 
		}
		BasicSet<String> names1 = createSet();
		String[] strNames1 = { "Bill", "Tom", "Sue" };
		for( String aName : strNames1 ) {
			names1 = adjoin( aName, names1);
		}
		assert(names.equals( names1 ) );
	}
	
	@Test
	public void testIterator() {
		BasicSet<String> names = createSet();
		String[] strNames = { "Tom", "Sue", "Bill" };
		for( String name : strNames ) {
			names = adjoin( name, names ); 
		}
		HashMap<String, Integer> table = new HashMap<>();
		for( String aKey : strNames )
			table.put(aKey, 0);
		
		Iterator<String> iter = names.iterator();
		int counter=0;
		
		while( iter.hasNext() ) {
			String name = iter.next();
			Integer currentCount = table.get( name );
			assertNotNull( "expecting to find table entry", currentCount );
			currentCount++;
			table.put( name, currentCount );
			counter++;
		}
		assert( counter == 3 );
		for( Integer eachCount : table.values() )
			assertTrue("checking uniqueness of table entries", eachCount == 1 );
	}

}
