/**
* @author Tian Hong Zhu Zhou
* @author Ran Bar-Niv
* 
*/
import java.util.NoSuchElementException;

public class LinkedList<T> {
    private class Node {
        private T data;
        private Node next;
        private Node prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private int length;
    private Node first;
    private Node last;
    private Node iterator;

    /**** CONSTRUCTORS ****/

    /**
     * Instantiates a new LinkedList with default values
     * @postcondition  When we instantiate the ADT linked list without parameters, all the data fields will be null.
     */
    public LinkedList() {
    	first = null;
    	last = null;
    	iterator = null;
    	length = 0;
    }

    /**
     * Converts the given array into a LinkedList
     * @param array the array of values to insert into this LinkedList
     * @postcondition <fill in here>
     */
    public LinkedList(T[] array) {
    	if(array == null) {
    		return;
    	}
    	else {
    		for(int i = 0; i < array.length; i++) {
    			addLast(array[i]);
    		}
    			
    	}
    	
    }
    
    /**
     * Instantiates a new LinkedList by copying another List
     * @param original the LinkedList to copy
     * @postcondition a new List object, which is an identical,
     * but separate, copy of the LinkedList original
     */
    public LinkedList(LinkedList<T> original) {
    	if(original == null) {
    		return;
    	}
    	if(original.length == 0) {
    		length = 0;
    		first = null;
    		last = null;
    		iterator = null;
    	}else {
    		Node temp = original.first;
    		while(temp != null) {
    			addLast(temp.data);
    			temp = temp.next;
    		}
    		iterator = null;
    	}
    
    }
    

    /**** ACCESSORS ****/

    /**
     * Returns the value stored in the first node
     * @precondition if the list is empty it will throw an exception
     * @return the value stored at node first
     * @throws NoSuchElementException indicates as that there are no elements in the list.
     */
    public T getFirst() throws NoSuchElementException {							//Runtime Best: O(1), Avg: O(1), Worst:O(1)
        if(length == 0) {
        	throw new NoSuchElementException("getFirst(): The list is Empty!!!!);");
        }
        else {
        	return first.data;
        }
    }

    /**
     * Returns the value stored in the last node
     * @precondition If the list is empty we throw an exception.
     * @return the value stored in the node last
     * @throws NoSuchElementException indicates if the linked list is empty.
     */
    public T getLast() throws NoSuchElementException {
        if(length == 0) {
        	throw new NoSuchElementException("getLast(): There's no elements in the list!!");		//Runtime Best: O(1), Avg: O(1), Worst:O(1)
        }
        return last.data;
    }

    /**
     * Returns the data stored in the iterator node
     * @precondition iterator is offend so we don't have any data to access
     * @throw NullPointerException when iterator is null
     */
    public T getIterator() throws NullPointerException {
    	if(iterator == null) {
    		throw new NullPointerException("getIterator(): iterator is null!");			//Runtime Best: O(1), Avg: O(1), Worst:O(1)
    	}
        return iterator.data; //general case
        
    }

    /**
     * Returns the current length of the LinkedList
     * @return the length of the LinkedList from 0 to n
     */
    public int getLength() {
        return length;			//Runtime Best: O(1), Avg: O(1), Worst:O(1)
    }

    /**
     * Returns whether the LinkedList is currently empty
     * @return whether the LinkedList is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }					//Runtime Best: O(1), Avg: O(1), Worst:O(1)

 

   /**
     * Returns whether the iterator is offEnd, i.e. null
     * @return whether the iterator is null
     */
    public boolean offEnd() {
        return iterator == null;
    }				//Runtime Best: O(1), Avg: O(1), Worst:O(1)

 

    /**** MUTATORS ****/

    /**
     * Creates a new first element
     * @param data the data to insert at the front of the LinkedList
     * @postcondition A new first node will be created on the linked list.
     */
    public void addFirst(T data) {
        if(length == 0) {
        	first = last = new Node(data);
        }															//Runtime Best: O(1), Avg: O(1), Worst:O(1)
        else {
        	Node newFirstNode = new Node(data);
        	newFirstNode.next = first;
        	first.prev = newFirstNode;
        	first = newFirstNode;
        }
        length++;
    }

    /**
     * Creates a new last element
     * @param data the data to insert at the end of the LinkedList
     * @postcondition <fill in here>
     */
    public void addLast(T data) {
        if(length == 0) {
        	first = last = new Node(data);
        }
        else {
        	Node newLastNode = new Node(data);					//Runtime Best: O(1), Avg: O(1), Worst:O(1)
        	newLastNode.prev = last;
        	last.next = newLastNode;
        	last = newLastNode;
        }
        length++;
    }
    
    /**
     * Inserts a new element after the iterator
     * @param data the data to insert
     * @precondition iterator is null, offend
     * @throws NullPointerException when the iterator is offend
     */
    public void addIterator(T data) throws NullPointerException{
        if(iterator == null) {
        	throw new NullPointerException("addIterator: iterator is offend. Cannot add");
        } else if(iterator == last) { 
        	addLast(data);
        }else {
        	Node n = new Node(data);
        	n.next = iterator.next;
        	n.prev = iterator;
        	iterator.next.prev = n;									//Runtime Best: O(1), Avg: O(n), Worst:O(n)
        	iterator.next = n;
        	length++;
        }
    }

    /**
     * removes the element at the front of the LinkedList
     * @precondition list.length == 0 || list.length == 1;
     * @postcondition <fill in here>
     * @throws NoSuchElementException indicates that in the list there are no elements.
     */
    public void removeFirst() throws NoSuchElementException {
        if(length == 0) {
        	throw new NoSuchElementException("removeLast(): You can't remove from an empty list!!");
        }
        else if(length == 1) {
        	first = last = iterator = null;
        }
        else if(iterator == first) {
        	iterator  = null;
        	first = first.next;
        	first.prev = null;
        }
        else {
        	first = first.next;
        	first.prev = null;
        }
        length--;
       
    }

    /**
     * removes the element at the end of the LinkedList
     * @precondition list.length == 0
     * @postcondition We have removed the last element of the linked list.
     * @throws NoSuchElementException indicates that in the linked list there are no elements.
     */
    public void removeLast() throws NoSuchElementException {
    	if(length == 0) {
    		throw new NoSuchElementException("removeLast(): You can't remove elements in a empty linked list!!");
    	}
    	else if (length == 1) {
    		first = last = iterator = null;
    	}
    	else if(iterator == last) {
    		iterator = null;
    		last = last.prev;
    		last.next = null;
    	}
    	else {
    		last = last.prev;
    		last.next = null;
    	}
    	length--;
    }
    
    /**
     * removes the element referenced by the iterator
     * @precondition The iterator is offEnds
     * @postcondition The iterator node will be removed
     * @throws NullPointerException when the iterator is not in the list
     */
    public void removeIterator() throws NullPointerException {
        if (offEnd()) { //Precondition Test
        	throw new NullPointerException("removeIterator(): The iterator is out of the list!!.");
        } 
        else if (iterator == first) {//edge case 1
        	removeFirst();
        }
        else if (iterator == last){//edge case 2
        	removeLast();
        }
        else {//general case
        	iterator.prev.next = iterator.next;
        	iterator.next.prev = iterator.prev;
        	iterator = null;
        	length--;
        	
        }
    }
    
    /**
     * places the iterator at the first node
     * @postcondition The iterator will be the first node
     */
    public void positionIterator(){
    	iterator = first;
    }
    
    /**
     * Moves the iterator one node towards the last
     * @precondition iterator is null
     * @postcondition the iterator will advance one by one block
     * @throws NullPointerException when the iterator is offend.
     */
    public void advanceIterator() throws NullPointerException {
    	if(offEnd()) { // Precondition
    		throw new NullPointerException("advanceIterator():" + "iterator is null. You can't advance.");
    	}
        iterator = iterator.next;//general case
    }
    
    /**
     * Moves the iterator one node towards the first
     * @precondition Iterator is null
     * @postcondition the iterator will move by one block towards the first node
     * @throws NullPointerException when the iterator is offend
     */
    public void reverseIterator() throws NullPointerException {
    	if(offEnd()) {
    		throw new NullPointerException("reverseIterator: The iterator is null. You can't advance.");
    	}
    	iterator = iterator.prev;
    }

    /**** ADDITIONAL OPERATIONS ****/
    
    /**
     * Determines at which index the iterator is located
     * Indexed from 0 to length - 1
     * @return the index number of the iterator
     * @precondition !offEnd()
     * @throws NullPointerException when precondition is violated
     */
    public int getIteratorIndex() throws NullPointerException {
        if(offEnd()) {
        	throw new NullPointerException("getIteratorIndex(): The iterator is offEnd!!");
        }
        Node p = first;
        int count = 0;
        while(p != iterator) {
        	p = p.next;								
        	count++;
        }
        return count;
    }
    
    /**
     * Searches the LinkedList for a given
     * element's index
     * @param data the data whose index to locate
     * @return the index of the data or -1 if
     * the data is not contained in the LinkedList
     */
    public int findIndex(T data) {
        Node p = first;
        int count = 0;
        while (p!= null) {
        	if(p.data.equals(data)) {
        		return count;
        	}
        	p = p.next;
        	count++;
        }
        return -1;
    }
    
    /**
     * Advances the iterator to location within
     * the LinkedList specified by the given index
     * @param index the index at which to place the
     * iterator.
     * @precondition !offEnd()
     * @throws NullPointerException when the precondition is
     * violated
     */
    public void advanceIteratorToIndex(int index) throws NullPointerException {
       if(offEnd()) {
    	   throw new NullPointerException("advanceIteratorToIndex(): The iterator is offEnd");
       }
       for(int i = 0; i<= index -1; i++) {
    	  advanceIterator();
       }
       
    }

    /**
     * Converts the LinkedList to a String, with each value separated by a blank
     * line At the end of the String, place a new line character
     * @return the LinkedList as a String
     */
    @Override
    public String toString() {
       StringBuilder result = new StringBuilder();
       Node temp = first;
       while(temp != null) {
    	   result.append(temp.data + "\n");   
    	   temp = temp.next;
       }
       return result.toString();
    	}
    
    /**
     * Determines whether the given Object is
     * another LinkedList, containing
     * the same data in the same order
     * @param o another Object
     * @return whether there is equality
     */
	//good practice to remove warning here
    @Override public boolean equals(Object o) {
        if(o == this) {
        	return true;
        }
        else if(!(o instanceof LinkedList)) {
        	return false;
        }
        else {
			LinkedList<T> L = (LinkedList<T>) o;
        	if(L.length != this.length) {
        		return false;
        	}
        	else {
        		Node temp1 = this.first;
        		Node temp2 = L.first;
        		while(temp1 != null) {
        			if(!temp1.data.equals(temp2.data)) {
        				return false;
        			}
        			temp1 = temp1.next;
        			temp2 = temp2.next;
        		}
        		return true;
        	}
        	
        }
    }
    
    /**CHALLENGE METHODS*/
    
    
    /**
     * Zippers two LinkedLists to create a third List
     * which contains alternating values from this list
     * and the given parameter
     * For example: [1,2,3] and [4,5,6] -> [1,4,2,5,3,6]
     * For example: [1, 2, 3, 4] and [5, 6] -> [1, 5, 2, 6, 3, 4]
     * For example: [1, 2] and [3, 4, 5, 6] -> [1, 3, 2, 4, 5, 6]
     * @param list the second LinkedList in the zipper
     * @return a new LinkedList, which is the result of
     * zippering this and list
     * @postcondition this and list are unchanged
     */
    public LinkedList<T> zipperLists(LinkedList<T> list) {
        LinkedList <T> newList = new LinkedList <>();
        Node temp1 = this.first;
        Node temp2 = list.first;
        while(temp1 != null && temp2 != null) {
        	newList.addLast(temp1.data);
        	temp1 = temp1.next;
        	newList.addLast(temp2.data);
        	temp2 = temp2.next;
        }
        while(temp1 != null) {
        	newList.addLast(temp1.data);
        	temp1 = temp1.next;
        }
        
        while (temp2 != null) {
        	newList.addLast(temp2.data);
        	temp2 = temp2.next;
        }
        return newList;
    }
    
    /**
     * Determines whether a LinkedList is
     * reversible, i.e. the data is the same
     * written both forward and backward
     * e.g. isReversible(1 2 3 2 1) -> true
     * e.g. isReversible(A B B A) -> true
     * @return whether the list is reversible
     */
    public boolean isReversible() {
        LinkedList <T> copy = new LinkedList<> (this);
        Node prev = null;
        Node current = copy.first;
        while(current != null) {
        	Node nextTemp = current.next;
        	current.next = prev;
        	prev = current;
        	current = nextTemp;
        }
        Node temp1 = copy.first;
        Node temp2 = this.first;
        int count = 0;
        while(temp1 != null) {
        	if(temp1.data == temp2.data) {
        		count = count + 1;
        	}
        }
        if(this.length == count) {
        	return true;
        } else {
        	return false;
        }
        	
    }
}