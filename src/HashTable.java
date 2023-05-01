/**
 * HashTable.java
 * @author Ran Bar-Niv 
 * @author Tian Hong Zhu Zhou 
 * CIS 22C, Lab 5
 */
import java.util.ArrayList;

public class HashTable<T> {
    
    private int numElements;
    private ArrayList<LinkedList<T>> Table;

    /**
     * Constructor for the HashTable
     * class. Initializes the Table to
     * be sized according to value passed
     * in as a parameter
     * Inserts size empty Lists into the
     * table. Sets numElements to 0
     * @param size the table size
     */
    public HashTable(int size) {
       numElements = 0;
       Table = new ArrayList<LinkedList<T>>();
       for(int i = 0; i <=size - 1; i++) {
    	   Table.add(new LinkedList<T>());
       }
 
    }
    
    /**
     * Constructor for HashTable class
     * Inserts the contents of the given array 
     * into the Table at the appropriate indices
     * @param array an array of elements to insert
     * @param size the size of the Table
     */
    public HashTable(T[] array, int size) {
    	if(array == null) {
    		numElements = 0;
    	    Table = new ArrayList<LinkedList<T>>();
    	    for(int i = 0; i <=size - 1; i++) {
    	    	   Table.add(new LinkedList<T>());
    	       }
    	}
    	else {
    		numElements = 0;
    		Table = new ArrayList<LinkedList<T>>();
    		for(int i = 0; i <=size -1; i++) {
    			Table.add(new LinkedList<T>());
    		}
    		for(int i = 0; i <= array.length -1; i++) {
    			add(array[i]);
    		}
    	}
    }
       
    /**Accessors*/
    
    /**
     * returns the hash value in the Table
     * for a given Object 
     * @param t the Object
     * @return the index in the Table
     */
    private int hash(T t) {
    	   //call hashCode() method on t below
    	   int code = t.hashCode(); //all Objects have a hashCode() method in Java
    	   return Math.abs(code) % Table.size();
    }
    
    /**
     * Counts the number of elements at this index
     * @param index the index in the Table
     * @precondition index < Table.size()
     * @return the count of elements at this index
     * @throws IndexOutOfBoundsException when
     * the precondition is violated
     */
    public int countBucket(int index) throws IndexOutOfBoundsException{
        if(index > Table.size()) {
        	throw new IndexOutOfBoundsException("countBucket():You must choose an index inside the table!!");
        }
        else {
        	return Table.get(index).getLength();
        }
    }
    
    /**
     * Determines total number of elements in the Table
     * @return total number of elements
     */
    public int getNumElements() {
    	return numElements;
    }
    
    /**
     * Accesses a specified element in the Table
     * @param t the element to locate
     * @return the bucket number where the element
     * is located or -1 if it is not found
     * @precondition t != null
     * @throws NullPointerException when the
     * precondition is violated
     */
    public T find(T t) throws NullPointerException{
        if(t == null) {
        	throw new NullPointerException("find(): The element is null!!");
        }
        else {
        	int bucket = hash(t);
        	Table.get(bucket).positionIterator();
        	while (!(Table.get(bucket).offEnd())) {
        		if (Table.get(bucket).getIterator().equals(t)) {
        			return Table.get(bucket).getIterator();
        		}
        		Table.get(bucket).advanceIterator();
        	}
        	
        }
        return null;
    }
    
    /**
     * Determines whether a specified element is in 
     * the Table
     * @param t the element to locate
     * @return  whether the element is in the Table 
     * @precondition <you fill in here>
     * @throws NullPointerException when the 
     * precondition is violated
     */
    public boolean contains(T t) throws NullPointerException{
        if(t == null) {
        	throw new NullPointerException("contains(): The element is null!!");
        }
        else {
        	if(find(t) != null) {
        		return true;
        	}
        }
        return false;
    }
    
     
    /**Mutators*/
    
    /**
     * Inserts a new element in the Table
     * at the end of the chain of the 
     * correct bucket
     * @param t the element to insert
     * @precondition t!=null
     * @throws NullPointerException when the
     * precondition is violated
     */
    public void add(T t) throws NullPointerException{  
    	if(t == null) {
    		throw new NullPointerException("add():The new element is null!!");
    	}
       int bucket = hash(t);
       Table.get(bucket).addLast(t);
       numElements++;
    }  
    
    /**
     * Removes the given element from the Table         
     * @param t the element to remove
     * @precondition t != null
     * @return whether t exists and was removed from the Table
     * @throws NullPointerException when the precondition
     * is violated
     */
    public boolean delete(T t) throws NullPointerException {
    	if(t == null) {
    		throw new NullPointerException("delete(): The element to delete is null!!");
    	}
    	else {
    		if(contains(t) == false) {
    			return false;
    		}
    		else {
        		int bucket = hash(t);
        		Table.get(bucket).advanceIteratorToIndex(Table.get(bucket).findIndex(t));
        		Table.get(bucket).removeIterator();
        		if(contains(t) == false) {
        			numElements--;
        			return true;
        			
        		}
    		}

    	}
    	return false;
    }
    
    /**
     * Resets the hash table back to the
     * empty state, as if the one argument
     * constructor has just been called.
     */
    public void clear() {
    	int size = Table.size();
    	numElements = 0;
    	Table.clear();
        for(int i = 0; i <=size - 1; i++) {
     	   Table.add(new LinkedList<T>());
        }
    	
    }

    /**Additional Methods*/
    
    /**
     * Computes the load factor
     * @return the load factor
     */
    public double getLoadFactor() {
    	double numElementsd = numElements;
    	double doubleSize = Table.size();
        return numElementsd/doubleSize;
    }

    /**
     * Creates a String of all elements at a given bucket
     * @param bucket the index in the Table
     * @return a String of elements, separated by spaces
     * with a new line character at the end
     */
    public String bucketToString(int bucket) {
        StringBuilder result = new StringBuilder();
        if(Table.get(bucket).isEmpty()) {
        	return "\n";
        }
        Table.get(bucket).positionIterator();
        for(int i = 0; i <= Table.get(bucket).getLength() - 1; i++) {
        	result.append(Table.get(bucket).getIterator() + " ");
        	Table.get(bucket).advanceIterator();
        }
        return result.toString() + "\n";
    }
        
    /**
     * Creates a String of the bucket number
     * followed by a colon followed by the first
     * element at each bucket followed
     * by a new line
     * For empty buckets, add the bucket number
     * followed by a colon followed by empty
     * @return a String of all first elements
     * at each bucket
     */
    public String rowToString(){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i <= Table.size() - 1; i++) {
        	if(Table.get(i).isEmpty()) {
        		result.append("Bucket " + i + ": " + "empty" + "\n");
        	}
        	else {
        		result.append("Bucket " + i +": " + Table.get(i).getFirst() + "\n");
        	}
        }
        return result.toString();
     }
    
    /**
     * Starting at the 0th bucket, and continuing 
     * in order until the last bucket,concatenates all 
     * elements at all buckets into one String, with
     * a new line between buckets and one more new line at the
     * end of the entire String
     */
    @Override
    public String toString() {
    	StringBuilder result = new StringBuilder();
    	for(int i = 0; i <= Table.size()-1; i++) {
    		if(!Table.get(i).isEmpty()) {
    			result.append(Table.get(i).toString());
    		}
    	}
    	return result.toString();
   }
}