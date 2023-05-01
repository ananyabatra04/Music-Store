/**
 * HeapTest.java
 * @author Ananya Batra
 * @author Bence Danko
 * Final Project
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator; 

import org.junit.jupiter.api.Test;

class HeapTest {
	@Test
	void testHeap() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		//a.add(null);
		a.add(6);
		a.add(5);
		a.add(4);
		a.add(3);
		a.add(2);
		a.add(1);
		
		Heap<Integer> empty = new Heap<Integer>(new ArrayList<Integer>(), new IntComparator());
		Heap<Integer> ordered = new Heap<Integer>(a, new IntComparator());
		assertEquals(6, ordered.getHeapSize());
		assertEquals("6 5 4 3 2 1 ", ordered.toString());
		assertEquals(0, empty.getHeapSize());
		
	}

	@Test
	void testBuildHeap() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		//a.add(0, null);
		a.add(0, 1);
		a.add(1, 2);
		a.add(2, 3);
		a.add(3, 4);
		a.add(4, 5);
		a.add(5, 6);
		
		ArrayList<String> b = new ArrayList<String>();
		//b.add(null);
		b.add("1");
		b.add("2");
		b.add("3");
		b.add("4");
		b.add("5");
		b.add("6");
		Heap<Integer> ordered = new Heap<Integer>(a, new IntComparator());
		Heap<String> ordereds = new Heap<String>(b, new StringComparator());
		
		assertEquals("6 5 3 4 2 1 ", ordered.toString());
	
		assertEquals("6 5 3 4 2 1 ", ordereds.toString());
		
	}

	@Test
	void testInsert() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(6);
		a.add(5);
		a.add(4);
		a.add(3);
		a.add(2);
		a.add(1);
		
		IntComparator comparator = new IntComparator();
		
		Heap<Integer> empty = new Heap<Integer>(new ArrayList<Integer>(), comparator);
		Heap<Integer> ordered = new Heap<Integer>(a, comparator);
		

		ordered.insert(7, comparator);
		assertEquals("7 5 6 3 2 1 4 ", ordered.toString());
		assertEquals(7, ordered.getHeapSize());
		
		empty.insert(1, comparator);
		assertEquals("1 ", empty.toString());
		assertEquals(1, empty.getHeapSize());
		
	}

	@Test
	void testRemove() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		
		a.add(6);
		a.add(5);
		a.add(4);
		a.add(3);
		a.add(2);
		a.add(1);
		IntComparator comparator = new IntComparator();
		
		Heap<Integer> empty = new Heap<Integer>(new ArrayList<Integer>(), comparator);
		Heap<Integer> ordered = new Heap<Integer>(a, comparator);
		
		ordered.remove(2, comparator);
		assertEquals("6 3 4 1 2 ", ordered.toString());
		assertThrows(IndexOutOfBoundsException.class, ()->{empty.remove(6, comparator);});
		assertEquals(5, ordered.getHeapSize());
		
		assertThrows(IndexOutOfBoundsException.class, ()->{empty.remove(1, comparator);});
	}

	@Test
	void testGetMax() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(6);
		a.add(5);
		a.add(4);
		a.add(3);
		a.add(2);
		a.add(1); 
		IntComparator comparator = new IntComparator();
		
		Heap<Integer> empty = new Heap<Integer>(new ArrayList<Integer>(), comparator);
		Heap<Integer> ordered = new Heap<Integer>(a, comparator);
		
		assertEquals(ordered.getMax(), 6);
		ordered.remove(1, comparator);
		assertEquals(ordered.getMax(), 5);
		
		assertEquals(empty.getMax(), null);
	}

	@Test
	void testSearch() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(6);
		a.add(5);
		a.add(4);
		a.add(3);
		a.add(2);
		a.add(1);
		
		IntComparator comparator = new IntComparator();
		
		Heap<Integer> empty = new Heap<Integer>(new ArrayList<Integer>(), comparator);
		Heap<Integer> ordered = new Heap<Integer>(a, comparator);
		
		assertEquals(3, ordered.search(3, comparator));
		assertEquals(null, ordered.search(7, comparator));
		
		assertEquals(null, empty.search(1, comparator));
	}

	@Test
	void testGetParent() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(6);
		a.add(5);
		a.add(4);
		a.add(3);
		a.add(2);
		a.add(1);
		
		IntComparator comparator = new IntComparator();
		
		Heap<Integer> empty = new Heap<Integer>(new ArrayList<Integer>(), comparator);
		Heap<Integer> ordered = new Heap<Integer>(a, comparator);
		
		assertEquals(2, ordered.getParent(4));
		assertEquals(2, ordered.getParent(5));
		
		assertThrows(IndexOutOfBoundsException.class, ()->{ordered.getParent(8);});
	}

	@Test
	void testGetLeft() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(6);
		a.add(5);
		a.add(4);
		a.add(3);
		a.add(2);
		a.add(1);
		IntComparator comparator = new IntComparator();
		
		Heap<Integer> empty = new Heap<Integer>(new ArrayList<Integer>(), comparator);
		Heap<Integer> ordered = new Heap<Integer>(a, comparator);
		
		assertEquals(6, ordered.getLeft(3));
		assertEquals(2, ordered.getLeft(1));
		
		assertThrows(IndexOutOfBoundsException.class, ()->{ordered.getLeft(4);});
	}

	@Test
	void testGetRight() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(6);
		a.add(5);
		a.add(4);
		a.add(3);
		a.add(2);
		a.add(1);
		IntComparator comparator = new IntComparator();
		
		Heap<Integer> empty = new Heap<Integer>(new ArrayList<Integer>(), comparator);
		Heap<Integer> ordered = new Heap<Integer>(a, comparator);
		
		assertEquals(5, ordered.getRight(2));
		assertEquals(3, ordered.getRight(1));
		
		assertThrows(IndexOutOfBoundsException.class, ()->{ordered.getParent(8);});
	}

	@Test
	void testGetHeapSize() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(6);
		a.add(5);
		a.add(4);
		a.add(3);
		a.add(2);
		a.add(1);
		IntComparator comparator = new IntComparator();
		
		Heap<Integer> empty = new Heap<Integer>(new ArrayList<Integer>(), comparator);
		Heap<Integer> ordered = new Heap<Integer>(a, comparator);
		
		assertEquals(6, ordered.getHeapSize());
		ordered.remove(1, comparator);
		assertEquals(5, ordered.getHeapSize());
		
		assertThrows(IndexOutOfBoundsException.class, ()->{ordered.getParent(8);});
	}

	@Test
	void testGetElement() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(6);
		a.add(5);
		a.add(4);
		a.add(3);
		a.add(2);
		a.add(1);
		IntComparator comparator = new IntComparator();
		
		Heap<Integer> empty = new Heap<Integer>(new ArrayList<Integer>(), comparator);
		Heap<Integer> ordered = new Heap<Integer>(a, comparator);
		
		assertEquals(6, ordered.getElement(1));
		assertEquals(3, ordered.getElement(4));
		assertThrows(IndexOutOfBoundsException.class, ()->{ordered.getElement(9);});
	}

	@Test
	void testToString() {
		ArrayList<String> a = new ArrayList<String>();
		a.add("6");
		a.add("5");
		a.add("4");
		a.add("3");
		a.add("2");
		a.add("1");
		IntComparator comparator = new IntComparator();
		StringComparator comparator2 = new StringComparator();
		
		Heap<Integer> empty = new Heap<Integer>(new ArrayList<Integer>(), comparator);
		Heap<String> ordereds = new Heap<String>(a, comparator2);
		
		assertEquals("6 5 4 3 2 1 ", ordereds.toString());
		assertEquals("", empty.toString());
		
		
		
	}

	@Test
	void testSort() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(6);
		a.add(5);
		a.add(4);
		a.add(3);
		a.add(2);
		a.add(1);
		IntComparator comparator = new IntComparator();
		
		Heap<Integer> ordered = new Heap<Integer>(a, comparator);

		ArrayList sorted = ordered.sort(comparator);
		sorted.remove(0);
		
		assertEquals("6 5 4 3 2 1 ", ordered.toString());
		assertEquals("[1, 2, 3, 4, 5, 6]", sorted.toString());
		
		
	}

}
class IntComparator implements Comparator<Integer>{
	public int compare(Integer a, Integer b) {
		return Integer.compare(a, b);
    }
}

class StringComparator implements Comparator<String>{
	public int compare(String o1, String o2) {
	    if (o1 == null && o2 == null){return 0;}
	    if (o1 == null) { return -1;}
	    if (o2 == null) { return 1;}
	    return o1.compareTo(o2);
	}
}


