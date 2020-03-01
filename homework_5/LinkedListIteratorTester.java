import java.util.*;
import org.junit.Before;
import org.junit.*;
import java.util.ArrayList;
import static org.junit.Assert.*; 
/**
* Test for class <code>Iterator</code> and <code>LinkedList</code>.
*
* @author Jack Collins
* @version April 12 2018
*/


public class LinkedListIteratorTester {
	
	/**
	* instance variables for the class the be used for the test.
	*/
	private Iterator<Integer> iter1; 
	private LinkedList<Integer> linkTest;
	private LinkedList<String> linkTest2;

	private String a;
	private String b;
	private String c;
	private String d;
	private String e;

	@Before
	public void setup(){
		linkTest = new LinkedList<Integer>();
		linkTest2 = new LinkedList<String>();
		a = "a";
		b = "b";
		c = "c";
		d = "d";
		e = "e";

	} // end before method.

	/**
	* Tests the <code>add</code>
	*/
	@Test
	public void testAdd(){
 		for (int i=0; i<10; i++) {
			linkTest.add(i); 
		}
		// Objects that are added into the linkTest2 linked list.
		linkTest2.add(a);
		linkTest2.add(b);
		linkTest2.add(c);
		linkTest2.add(d);
		linkTest2.add(e);

		assertTrue(linkTest.size() == 10);
		assertTrue(linkTest2.size() == 5);
		assertTrue(linkTest.get(1) == 1);
		assertTrue(linkTest2.get(2).equals("c"));
	}
	/**
	* Tests the <code>contains</code> to see if an element is in the list.
	*/
	@Test 
	public void testContains(){
		for(int i = 0; i < 10; i++){
			linkTest.add(i);
		}
	assertTrue(linkTest.contains(9) == true);
	assertTrue(linkTest.contains(11) == false);
	assertFalse(linkTest.contains(14)== true);
	
	} 
	/**
	* Tests the <code>indexOf</code> 
	*/
	@Test
	public void testIndexOf(){
		assertTrue(linkTest.indexOf(0)== -1);

		for(int i = 0; i <10; i++){
			linkTest.add(i);
		}
		assertTrue(linkTest.indexOf(5)==5);
		assertTrue(linkTest.indexOf(11)==-1);

	}
	/**
	* Tests the <code>iterator</code> to iterate through the linkTest linked list
	*/
	@Test
	public void testIterator(){
		for(int i = 0; i<10; i++){
			linkTest.add(i);
		}
		assertTrue(linkTest.iteratorAt(5)== iter1);
		assertTrue(linkTest.iteratorAt(11)== null);
	} 
} 