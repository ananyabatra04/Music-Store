

/**
 * Heap.java
 * @author Ananya Batra
 * @author Bence Danko
 * Final Project
 */

import java.util.Comparator;
import java.util.ArrayList;

public class Heap<T> {

	private int heapSize;
	private ArrayList<T> heap;

	/**
	 * Constructors/
	 * 
	 * /** Constructor for the Heap class
	 * 
	 * @param data an unordered ArrayList Calls buildHeap
	 */
	public Heap(ArrayList<T> data, Comparator<T> comparator) {
		heapSize = data.size() + 1;
		heap = data;
		heap.add(0, null);
		buildHeap(comparator);
	}

	/** Mutators */

	/**
	 * Converts an ArrayList into a valid max heap. Called by constructor Calls
	 * helper method heapify
	 */
	public void buildHeap(Comparator<T> comparator) {
		for (int i = heapSize / 2; i >= 1; i--) { // check

			heapify(i, comparator);
		}
	}

	/**
	 * helper method to buildHeap, remove, and sort bubbles an element down to its
	 * proper location within the heap
	 * 
	 * @param index an index in the heap
	 */
	private void heapify(int index, Comparator<T> comparator) throws IndexOutOfBoundsException {

		if (index < 1 || index > heapSize) {
			throw new IndexOutOfBoundsException("heapify: index out of bounds");
		}

		int indexOfMax = index;
		int l = 2 * index;
		int r = 2 * index + 1;

		if (l < heapSize && comparator.compare(heap.get(l), heap.get(index)) > 0) { // we need the comparator here?
			indexOfMax = l;
		}

		if (r < heapSize && comparator.compare(heap.get(r), heap.get(indexOfMax)) > 0) { // we need the comparator //
																							// here?
			indexOfMax = r;
		}

		if (index != indexOfMax) {
			T temp = heap.get(index);

			heap.set(index, heap.get(indexOfMax));
			heap.set(indexOfMax, temp);

			heapify(indexOfMax, comparator);
		}

	}

	/**
	 * Inserts the given data into heap Calls helper method heapIncreaseKey
	 * 
	 * @param key the data to insert
	 */
	public void insert(T key, Comparator<T> comparator) {
		heapSize++;
		heap.add(key);
		heapIncreaseKey(heapSize - 1, key, comparator);

	}

	/**
	 * Helper method for insert. Bubbles an element up to its proper location
	 * 
	 * @param index the current index of the key
	 * @param key   the data
	 * @precondition
	 */
	private void heapIncreaseKey(int index, T key, Comparator<T> comparator) throws IndexOutOfBoundsException {

		if (comparator.compare(key, heap.get(index)) > 0) {
			heap.set(index, key);
		}

		while (index > 1 && comparator.compare(heap.get(getParent(index)), heap.get(index)) < 0) {
			T temp = heap.get(index);

			heap.set(index, heap.get(getParent(index)));
			heap.set(getParent(index), temp);

			index = getParent(index);
		}

	}

	/**
	 * removes the element at the specified index Calls helper method heapify
	 * 
	 * @param index the index of the element to remove
	 */
	public void remove(int index, Comparator<T> comparator) throws IndexOutOfBoundsException {

		if (index < 1 || index > heapSize) {
			throw new IndexOutOfBoundsException("remove: index out of bounds");
		}

		heap.set(index, heap.get(heapSize - 1));
		heap.remove(heapSize - 1);
		heapify(index, comparator);
		heapSize--;
	}

	/** Accessors */

	/**
	 * returns the maximum element (highest priority)
	 * 
	 * @return the max value
	 */
	public T getMax() {
		if (heapSize <= 1) {
			return null;
		}

		return heap.get(1);
	}

	/**
	 * returns the whole object associated with the given data and comparator
	 * 
	 * @param data the data associated with the object
	 * @return the object associated with the data
	 */
	public T search(T data, Comparator<T> comparator) {

		for (int i = 1; i < heapSize; i++) {
			if (comparator.compare(data, heap.get(i)) == 0) {
				return heap.get(i);
			}
		}
		return null;

	}

	/**
	 * returns the location (index) of the parent of the element stored at index
	 * 
	 * @param index the current index
	 * @return the index of the parent
	 * @precondition 1 <= index <= heapSize
	 * @throws IndexOutOfBoundsException
	 */
	public int getParent(int index) throws IndexOutOfBoundsException {
		if (index < 1 || index > heapSize) {
			throw new IndexOutOfBoundsException("getParent(): Index is out of bounds");
		}
		return index / 2;

	}

	/**
	 * returns the location (index) of the left child of the element stored at index
	 * 
	 * @param index the current index
	 * @return the index of the left child
	 * @precondition 1 <= index <= heapSize
	 * @throws IndexOutOfBoundsException
	 */
	public int getLeft(int index) throws IndexOutOfBoundsException {
		int result = (2 * index);

		if (index < 1 || result > heapSize) {
			throw new IndexOutOfBoundsException("getLeft(): Index is out of bounds");
		}

		return result;
	}

	/**
	 * returns the location (index) of the right child of the element stored at
	 * index
	 * 
	 * @param index the current index
	 * @return the index of the right child
	 * @precondition 1 <= index <= heapSize
	 * @throws IndexOutOfBoundsException
	 */
	public int getRight(int index) throws IndexOutOfBoundsException {

		int result = (2 * index) + 1;

		if (index < 1 || result > heapSize) {
			throw new IndexOutOfBoundsException("getRight(): Index is out of bounds");
		}

		return result;
	}

	/**
	 * returns the heap size (current number of elements)
	 * 
	 * @return the size of the heap
	 */
	public int getHeapSize() {
		return heapSize - 1;
	}

	/**
	 * Gets the element at the specified index
	 * 
	 * @param index at which to access
	 * @return the element at index *
	 * @precondition 1 <= index <= heapSize
	 * @throws IndexOutOfBoundsException
	 */
	public T getElement(int index) throws IndexOutOfBoundsException {
		if (index < 1 || index > heapSize) {
			throw new IndexOutOfBoundsException("getElement(): Index is out of bounds");
		}
		return heap.get(index);
	}

	/** Additional Operations */

	/**
	 * Creates a String of all elements in the heap
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int i = 1; i < heapSize; i++) {
			result.append(heap.get(i) + " ");
		}
		return result.toString();
	}

	/**
	 * Uses the heap sort algorithm to sort the heap into ascending order Calls
	 * helper method heapify
	 * 
	 * @return an ArrayList of sorted elements
	 * @postcondition heap remains a valid heap
	 */
	public ArrayList<T> sort(Comparator<T> comparator) {

		ArrayList<T> modifiedHeap = new ArrayList<T>(heap);
		modifiedHeap.remove(0);
		Heap<T> result = new Heap<T>(new ArrayList<T>(modifiedHeap), comparator);

		for (int i = heapSize - 1; i >= 2; i--) {
			T temp = result.heap.get(1);

			result.heap.set(1, result.heap.get(i));
			result.heap.set(i, temp);

			result.heapSize--;

			result.heapify(1, comparator);
		}

		return result.heap;

	}

}